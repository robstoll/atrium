import org.gradle.api.Project

/**
 * Returns a project with the given name prefixed with the root project name.
 * For example, for module name "core" and root project "atrium", returns project ":atrium-core"
 */
fun Project.getPrefixedProjectName(name: String, rootProject: String): Project =
    project(":$rootProject-$name").also {
        require(it.name != rootProject) {
            "Project name '$name' cannot be the same as root project name '$rootProject'"
        }
    }
