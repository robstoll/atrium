import ch.tutteli.gradle.plugins.publish.PublishPluginExtension

plugins {
    id("build-logic.kotlin-multiplatform")
    id("build-logic.publish-to-tmp-maven-repo")
    id("build-logic.dokka")
}

ifIsPublishing {
    apply(plugin = "ch.tutteli.gradle.plugins.publish")

    the<PublishPluginExtension>().run {
        resetLicenses("EUPL-1.2")
    }
}
