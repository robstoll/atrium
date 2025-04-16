import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.attributes.Attribute
import org.gradle.api.attributes.Usage
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.repositories
import java.io.File
import java.io.OutputStream

fun Project.configureDexerForProject(dexerPreCheckTask: Provider<Task>): TaskProvider<JavaExec> {

    repositories {
        google()
        mavenCentral()
    }

    configureR8()
    configureD8()

    return registerCheckDexerTask(dexerPreCheckTask)
}

private fun Project.configureR8() {
    if (configurations.findByName("r8") == null) {
        configurations.create("r8")
        dependencies.add("r8", "com.android.tools:r8:8.7.18")
    }
}


private fun Project.configureD8() {
    if (configurations.findByName("d8") == null) {
        val d8Configuration = configurations.create("d8") {
            attributes {
                attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
                attribute(Attribute.of("org.jetbrains.kotlin.platform.type", String::class.java), "jvm")
            }
        }
        dependencies.add(d8Configuration.name, this)
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
private fun Project.registerCheckDexerTask(dexerPreCheckTask: Provider<Task>) =
    tasks.register<JavaExec>(AtriumDexerExtension.TASK_NAME) {
        description = "Compiles android class files into dex bytecode"
        inputs.property(AtriumDexerExtension.ATRIUM_ANDROID_JAR, System.getenv(AtriumDexerExtension.ATRIUM_ANDROID_JAR))

        val outputPath = project.layout.buildDirectory.dir("d8")
        outputs.dir(outputPath)

        val jvmJar = tasks.named<org.gradle.jvm.tasks.Jar>("jvmJar").get()
        dependsOn(jvmJar)
        dependsOn(dexerPreCheckTask)
        jvmJar.mustRunAfter(dexerPreCheckTask)

        classpath(project.configurations.named("r8"))
        mainClass.set("com.android.tools.r8.D8")

        configureErrorOutput()

        // retrieve Providers after configuration to keep it lazily, hence inside doFirst block
        doFirst {
            args(project.getD8ClasspathArgs())
            args(getAndroidJarArgsForD8())
            args(listOf("--output", outputPath.get().asFile.absolutePath))
            args(jvmJar.archiveFile.get().asFile.absolutePath)
        }
    }

private fun Project.getD8ClasspathArgs(): List<String> =
    project.configurations.named("d8").get().files.flatMap {
        listOf("--classpath", it.absolutePath)
    }

private fun JavaExec.configureErrorOutput() {
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
            throw GradleException("${AtriumDexerExtension.TASK_NAME} failed due to output, see above.")
        }
    }
}
