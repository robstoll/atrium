import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.kotlin.dsl.exclude
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

//TODO 0.20.0 can this be moved to a plugin as well?
fun Project.prefixedProject(name: String): Project = project(":${rootProject.name}-$name")
fun KotlinDependencyHandler.apiWithExclude(
    dep: String,
    additionalConfiguration: (ExternalModuleDependency.() -> Unit)? = null
) = api(dep) {
    defaultExclude()
    additionalConfiguration?.also { this.it() }
}

fun KotlinDependencyHandler.implementationWithExclude(
    dep: String,
    additionalConfiguration: (ExternalModuleDependency.() -> Unit)? = null
) = implementation(dep) {
    defaultExclude()
    additionalConfiguration?.also { this.it() }
}

fun KotlinDependencyHandler.runtimeOnlyWithExclude(
    dep: String,
    additionalConfiguration: (ExternalModuleDependency.() -> Unit)? = null
) = runtimeOnly(dep) {
    defaultExclude()
    additionalConfiguration?.also { this.it() }
}


fun ExternalModuleDependency.defaultExclude() {
    exclude(group = "org.jetbrains.kotlin")
    exclude(group = "ch.tutteli.kbox")
}
