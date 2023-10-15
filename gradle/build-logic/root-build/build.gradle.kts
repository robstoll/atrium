plugins {
    id("build-logic.kotlin-dsl-gradle-plugin")
}

dependencies {
    api(projects.basics)
    api(projects.dev)

    api(buildLibs.bundles.dokka)
    api(buildLibs.tutteli.dokka)
}
