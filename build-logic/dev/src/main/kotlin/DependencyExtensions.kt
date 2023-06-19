import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.kotlin.dsl.exclude
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

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
