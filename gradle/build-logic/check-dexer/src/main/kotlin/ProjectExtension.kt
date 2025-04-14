import org.gradle.api.Project

fun Project.getPrefixedProjectName(name: String, rootProject: String): Project =
    project(":$rootProject-$name").also {
        require(it.name != rootProject) { "Project name cannot be the same as root project name" }
    }

fun String.toPrefixedProjectName(rootProject: String): String =
    if (this == rootProject) this else ":$rootProject-$this"
