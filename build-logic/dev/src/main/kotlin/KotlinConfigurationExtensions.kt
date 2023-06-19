import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

fun KotlinProjectExtension.configureKotlinJvm(){
    // Require explicit access modifiers and require explicit types for public APIs.
    // See https://kotlinlang.org/docs/whatsnew14.html#explicit-api-mode-for-library-authors
//    explicitApi()
}

fun NamedDomainObjectContainer<KotlinSourceSet>.configureLanguageSettings() {
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
