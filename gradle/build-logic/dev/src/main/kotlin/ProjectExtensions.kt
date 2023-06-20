import org.gradle.api.Project

//TODO 1.1.0 can this be moved to a plugin as well?
fun Project.prefixedProject(name: String): Project = project(":${rootProject.name}-$name")
