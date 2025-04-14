import org.gradle.api.GradleException
import org.gradle.api.InvalidUserDataException

/**
 * Gradle plugin for checking Android dexer compatibility of Atrium modules.
*/

// Create and get the plugin extension
val extension = extensions.create<AtriumDexerExtension>("dexer")

/**
 * Validates that the ATRIUM_ANDROID_JAR environment variable is properly set
 * to a valid android.jar file path.
 *
 * @throws GradleException if the environment variable is missing or points to a non-existent file
 */
fun validateAndroidJarPath() {
    val androidJarPath = System.getenv("ATRIUM_ANDROID_JAR")

    if (androidJarPath.isNullOrBlank()) {
        throw GradleException(
            "ATRIUM_ANDROID_JAR environment variable is not set. " +
                "Point it to the android SDK android.jar path (e.g. /android-sdk-linux/platforms/android-26/android.jar"
        )
    }

    if (!File(androidJarPath).exists()) {
        throw GradleException(
            "ATRIUM_ANDROID_JAR environment variable points to a non-existing file: $androidJarPath"
        )
    }

    logger.lifecycle("Using android.jar from: $androidJarPath")
}

// Configure prerequisite check task
val dexerPreCheckTask = tasks.register("dexerPreCheck") {
    doFirst {
        validateAndroidJarPath()
    }
}

// Resolve the list of projects to check for dexing compatibility
val dexerProjects: List<Project> = extension.subprojects
    .get()
    .map { moduleName -> getPrefixedProjectName(moduleName, rootProject.name) }

// Configure dexer for each project
dexerProjects.forEach { project ->
    configureDexerForProject(project, dexerPreCheckTask)
}

// Register prerequisite verification task
val preCheckDexerProjects = tasks.register("preCheckDexerProjects") {
    dependsOn(dexerPreCheckTask)
    doFirst {
        if (dexerProjects.isEmpty()) {
            throw InvalidUserDataException(
                "No dexerProjects defined. Make sure you have specified env variable CI. " +
                "Use e.g. `CI=true ./gradlew checkDexer`"
            )
        }
    }
}

// Register main task to execute all checkDexer tasks
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
