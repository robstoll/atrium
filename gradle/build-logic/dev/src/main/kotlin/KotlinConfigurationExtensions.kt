import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet


fun NamedDomainObjectContainer<KotlinSourceSet>.configureLanguageSettings() {
    configureEach {
        // TODO 1.2.0 make this configurable so that we can use a higher version in Tests. This will reveal kotlin
        // regressions sooner which means we could already test beta versions in the hope that less regressions
        // are released in the end, because those bugs also hinder Atrium users to use Atrium at its full potential
        // in the end
        val languageVersion = if (name.endsWith("Test")) "1.4" else "1.4"
        val apiVersion = if (name.endsWith("Test")) "1.4" else "1.4"
        languageSettings.apply {
            this.languageVersion = languageVersion
            this.apiVersion = apiVersion
            optIn("kotlin.RequiresOptIn")
        }
    }
}
