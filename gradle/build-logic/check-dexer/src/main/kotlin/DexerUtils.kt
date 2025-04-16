import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.attributes.Attribute
import org.gradle.api.attributes.Usage
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.JavaExec
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.register
import org.gradle.process.CommandLineArgumentProvider
import java.io.File
import java.io.OutputStream

fun configureDexerForProject(project: Project, dexerPreCheckTask: Provider<Task>) {

    project.repositories.apply {
        google()
        mavenCentral()
    }

    configureR8(project)

    configureD8(project)

    registerCheckDexerTask(project, dexerPreCheckTask)
}

private fun configureR8(project: Project) {
    if (project.configurations.findByName("r8") == null) {
        project.configurations.create("r8")
        project.dependencies.add("r8", "com.android.tools:r8:8.7.18")
    }
}


private fun configureD8(project: Project) {
    if (project.configurations.findByName("d8") == null) {
        val d8Configuration = project.configurations.create("d8") {
            attributes {
                attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage.JAVA_RUNTIME))
                attribute(
                    Attribute.of("org.jetbrains.kotlin.platform.type", String::class.java),
                    "jvm"
                )
            }
        }
        project.dependencies.add(d8Configuration.name, project)
    }
}


private fun getAndroidJarArgsForD8(): List<String> {
    val androidJarPath = System.getenv(AtriumDexerExtension.ATRIUM_ANDROID_JAR)
    return androidJarPath?.let {
        listOf("--lib", File(it).absolutePath)
    } ?: emptyList()
}


/**
 * Registers and configures the checkDexer task that compiles JVM bytecode
 * into DEX bytecode using the D8 compiler.
 */
private fun registerCheckDexerTask(project: Project, dexerPreCheckTask: Provider<Task>) {
    project.tasks.register<JavaExec>("checkDexer") {
        description = "Compiles android class files into dex bytecode"
        inputs.property(AtriumDexerExtension.ATRIUM_ANDROID_JAR, System.getenv(AtriumDexerExtension.ATRIUM_ANDROID_JAR))

        val outputPath = project.layout.buildDirectory.dir("d8")
        outputs.dir(outputPath)

        val jvmJar = project.tasks.named<org.gradle.jvm.tasks.Jar>("jvmJar").get()
        dependsOn(jvmJar)
        dependsOn(dexerPreCheckTask)
        jvmJar.mustRunAfter(dexerPreCheckTask)

        classpath(project.configurations.named("r8").get().asPath)
        mainClass.set("com.android.tools.r8.D8")

        // Build the D8 command line arguments
        argumentProviders.add(CommandLineArgumentProvider {
            val d8Classpath = getD8ClasspathArgs(project)
            val androidJarArgs = getAndroidJarArgsForD8()

            d8Classpath + androidJarArgs + listOf(
                "--output", outputPath.get().asFile.absolutePath,
                jvmJar.archiveFile.get().asFile.absolutePath
            )
        })

        configureErrorOutput(this)
    }
}


private fun getD8ClasspathArgs(project: Project): List<String> {
    return project.configurations.named("d8").get().files.flatMap {
        listOf("--classpath", it.absolutePath)
    }
}

private fun configureErrorOutput(task: JavaExec) {
    val oldStream = System.err
    var outputWritten = false

    task.errorOutput = object : OutputStream() {
        override fun write(b: Int) {
            outputWritten = true
            oldStream?.write(b) ?: System.out.write(b)
        }
    }

    task.doLast {
        if (outputWritten) {
            throw GradleException("checkDexer failed due to output, see above.")
        }
    }
}
