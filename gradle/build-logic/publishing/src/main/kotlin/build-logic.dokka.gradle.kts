import org.jetbrains.dokka.gradle.AbstractDokkaLeafTask
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("build-logic.gradle-conventions")
    id("ch.tutteli.gradle.plugins.dokka")
}

val kdocDir = rootProject.projectDir.resolve("misc/kdoc")

tasks.configureEach<DokkaTask> {
    dokkaSourceSets.configureEach {
        reportUndocumented.set(true)
    }
}

tasks.configureEach<AbstractDokkaLeafTask> {
    dokkaSourceSets.configureEach {
        jdkVersion.set(buildParameters.defaultJdkVersion)
        perPackageOption {
            matchingRegex.set("io.mockk")
            suppress.set(true)
        }
        includes.from(kdocDir.resolve("packages.md"))
    }
    configurePlugins()
}
