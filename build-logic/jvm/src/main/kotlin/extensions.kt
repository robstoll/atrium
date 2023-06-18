import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.kotlin.dsl.exclude
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

//TODO 1.1.0 can this be moved to a plugin as well?
fun Project.prefixedProject(name: String): Project = project(":${rootProject.name}-$name")

fun NamedDomainObjectContainer<KotlinSourceSet>.configureLanguageSettings(project: Project) {
    configureEach {
        // TODO 1.1.0 make this configurable so that we can use a higher version in Tests. This will reveal kotlin
        // regressions sooner which means we could already test beta versions in the hope that less regressions
        // are released in the end, because they also hinder Atrium users in the end using Atrium at its full potential
        val languageVersion = if (name.endsWith("Test")) "1.4" else "1.4"
        val apiVersion = if (name.endsWith("Test")) "1.4" else "1.4"
        languageSettings.apply {
            this.languageVersion = languageVersion
            this.apiVersion = apiVersion
            optIn("kotlin.RequiresOptIn")
        }
    }
}
