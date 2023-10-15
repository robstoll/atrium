plugins {
    id("build-logic.kotlin-dsl-gradle-plugin")
}

dependencies {
    api(projects.basics)
    api(projects.dev)

    api(buildLibs.bundles.dokka)
    api(buildLibs.tutteli.dokka)
}

rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin> {
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension>().lockFileDirectory =
        rootProject.projectDir.resolve("gradle")
}
