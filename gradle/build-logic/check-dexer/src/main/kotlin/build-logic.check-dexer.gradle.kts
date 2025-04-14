import java.io.OutputStream

val extension = extensions.create<AtriumDexerExtension>("dexer")

val dexerPreCheckTask = tasks.register("dexerPreCheck") {
    val androidJarPath = System.getenv("ATRIUM_ANDROID_JAR")

    if (androidJarPath.isNullOrBlank()) {
        throw GradleException("ATRIUM_ANDROID_JAR environment variable is not set. " +
            "Point it to the android SDK android.jar path (e.g. /android-sdk-linux/platforms/android-26/android.jar")
    }

    if (!File(androidJarPath).exists()) {
        throw GradleException("ATRIUM_ANDROID_JAR environment variable points to a non-existing file: $androidJarPath")
    }

    logger.lifecycle("Using android.jar from: $androidJarPath")
}


val dexerProjects: List<Project> = extension.subprojects
        .get()
        .map { moduleName -> getPrefixedProjectName(moduleName, rootProject.name) }


dexerProjects.forEach { project ->

    repositories {
        google()
        mavenCentral()
    }

    if (project.configurations.findByName("r8") == null) {
        project.configurations.create("r8")
        project.dependencies.add("r8", "com.android.tools:r8:8.7.18")
    }

    if (project.configurations.findByName("d8") == null) {
        val d8Configuration = project.configurations.create("d8").apply {
            attributes.apply {
                attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
                attribute(Attribute.of("org.jetbrains.kotlin.platform.type", String::class.java), "jvm")
            }
        }
        project.dependencies.add(d8Configuration.name, project)
    }


    project.tasks.register<JavaExec>("checkDexer") {
        description = "compiles android class files into dex bytecode"
        inputs.property("ATRIUM_ANDROID_JAR", System.getenv("ATRIUM_ANDROID_JAR"))

        val outputPath = "${project.layout.buildDirectory}/d8"
        outputs.dir(outputPath)

        val jvmJar = project.tasks.named<Jar>("jvmJar").get()
        dependsOn(jvmJar)
        dependsOn(dexerPreCheckTask)
        jvmJar.mustRunAfter(dexerPreCheckTask)


        classpath(project.configurations.named("r8").get().asPath)
        mainClass.set("com.android.tools.r8.D8")

        val d8Classpath = project.configurations.named("d8").get().files.flatMap {
            listOf("--classpath", it.absolutePath)
        }

        val androidJarPath = System.getenv("ATRIUM_ANDROID_JAR")

        // ATRIUM_ANDROID_JAR has to point to something like /android-sdk-linux/platforms/android-XY/android.jar
        // where Xy is the version we want to support
        val androidJar = androidJarPath?.let { listOf("--lib", file(it).absolutePath) } ?: emptyList()

        args = d8Classpath + androidJar + listOf("--output", outputPath, jvmJar.archiveFile.get().asFile.absolutePath)

        val oldStream = System.err
        var outputWritten = false
        errorOutput = object : OutputStream() {
            override fun write(b: Int) {
                outputWritten = true
                oldStream?.write(b) ?: System.out.write(b)
            }
        }
        doLast {
            if (outputWritten) {
                throw GradleException("checkDexer failed due to output, see above.")
            }
        }
    }
}

val preCheckDexerProjects = tasks.register("preCheckDexerProjects") {
    dependsOn(dexerPreCheckTask)
    doFirst {
        if (dexerProjects.isEmpty()) {
            throw InvalidUserDataException("No dexerProjects defined. Make sure you have specified env variable CI. Use e.g. `CI=true ./gradlew checkDexer`")
        }
    }
}

tasks.register("checkDexer") {
    val self = this
    description = "executes all checkDexer tasks"
    dependsOn("preCheckDexerProjects")

    dexerProjects.forEach { subproject ->
        val subprojectCheckDexer = subproject.tasks.named("checkDexer")
        self.dependsOn(subprojectCheckDexer)
        subprojectCheckDexer.configure {
            mustRunAfter(preCheckDexerProjects)
        }
    }

}

