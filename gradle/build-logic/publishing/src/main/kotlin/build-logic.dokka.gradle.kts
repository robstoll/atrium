import org.jetbrains.dokka.gradle.AbstractDokkaLeafTask

plugins {
    id("build-logic.gradle-conventions")
}

ifIsPublishing {
    apply(plugin="ch.tutteli.gradle.plugins.dokka")

    val kdocDir = rootProject.projectDir.resolve("misc/kdoc")

    tasks.configureEach<AbstractDokkaLeafTask> {
        dokkaSourceSets.configureEach {
            reportUndocumented.set(true)
            jdkVersion.set(buildParameters.defaultJdkVersion)
            includes.from(kdocDir.resolve("packages.md"))
        }
        configurePlugins()
    }
}
