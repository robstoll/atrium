import org.jetbrains.dokka.gradle.AbstractDokkaLeafTask

plugins {
    id("build-logic.gradle-conventions")
    id("ch.tutteli.gradle.plugins.dokka") apply isPublishing()
}

ifIsPublishing {

    val kdocDir = rootProject.projectDir.resolve("misc/kdoc")

    tasks.configureEach<AbstractDokkaLeafTask> {
        dokkaSourceSets.configureEach {
            reportUndocumented.set(true)
            jdkVersion.set(buildParameters.defaultJdkVersion)
            perPackageOption {
                matchingRegex.set("io.mockk")
                suppress.set(true)
            }
            includes.from(kdocDir.resolve("packages.md"))
        }
        configurePlugins()
    }
}
