import AtriumDexerExtension.Companion.ATRIUM_ANDROID_JAR
import org.gradle.api.GradleException
import org.gradle.api.InvalidUserDataException

/**
 * Gradle plugin for checking Android dexer compatibility of Atrium modules.
 */

val extension = extensions.create<AtriumDexerExtension>("dexer")

val dexerPreCheckTask = tasks.register("dexerPreCheck") {
    doFirst {
        validateAndroidJarPath()
    }
}

/**
 * Validates that the ATRIUM_ANDROID_JAR environment variable is properly set
 * to a valid android.jar file path.
 *
 * @throws GradleException if the environment variable is missing or points to a non-existent file
 */
fun validateAndroidJarPath() {
    val androidJarPath = System.getenv(ATRIUM_ANDROID_JAR)

    if (androidJarPath.isNullOrBlank()) {
        throw GradleException(
            "$ATRIUM_ANDROID_JAR environment variable is not set. " +
                    "Point it to the android SDK android.jar path (e.g. /android-sdk-linux/platforms/android-26/android.jar"
        )
    }

    if (File(androidJarPath).exists().not()) {
        throw GradleException(
            "$ATRIUM_ANDROID_JAR environment variable points to a non-existing file: $androidJarPath"
        )
    }

    logger.lifecycle("Using android.jar from: $androidJarPath")
}


val dexerProjects: Provider<List<Project>> = extension.subprojects.map { projects ->
    projects.map { prefixedProject(it) }
}
val checkDexerInSubprojects = dexerProjects.map { projects ->
    projects.map { it.configureDexerForProject(dexerPreCheckTask) }
}

val preCheckDexerProjects = tasks.register("preCheckDexerProjects") {
    dependsOn(dexerPreCheckTask)
    doFirst {
        if (dexerProjects.get().isEmpty()) {
            throw InvalidUserDataException(
                "No dexerProjects defined. Make sure you have specified env variable CI. " +
                        "Use e.g. `CI=true ./gradlew ${AtriumDexerExtension.TASK_NAME}`"
            )
        }
    }
}

tasks.register(AtriumDexerExtension.TASK_NAME) {
    val self = this
    description = "executes all ${AtriumDexerExtension.TASK_NAME} tasks of the configured dexer.subprojects"
    dependsOn(preCheckDexerProjects)

    checkDexerInSubprojects.get().forEach { subprojectCheckDexer ->
        self.dependsOn(subprojectCheckDexer)
        subprojectCheckDexer.configure {
            mustRunAfter(preCheckDexerProjects)
        }
    }
}
