import ch.tutteli.gradle.plugins.publish.PublishPluginExtension

ifIsPublishing {
    apply(plugin = "ch.tutteli.gradle.plugins.publish")

    the<PublishPluginExtension>().run {
        resetLicenses("EUPL-1.2")
    }
}
