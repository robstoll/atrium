import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

//TODO 0.20.0 can this be moved to a plugin as well?
fun Project.prefixedProject(name: String): Project = project(":${rootProject.name}-$name")
fun KotlinDependencyHandler.apiWithExclude(dep: String) = api(dep) { defaultExclude() }
fun KotlinDependencyHandler.implementationWithExclude(dep: String) = implementation(dep) { defaultExclude() }
fun KotlinDependencyHandler.runtimeOnlyWithExclude(dep: String) = runtimeOnly(dep) { defaultExclude() }


fun ExternalModuleDependency.defaultExclude() {
    exclude(mapOf("group" to "org.jetbrains.kotlin"))
    exclude(mapOf("group" to "ch.tutteli.kbox"))
}
