plugins {
    id("build-logic.kotlin-dsl-gradle-plugin")
}

dependencies {
    api(projects.basics)

    api(buildLibs.kotlin)
    api(buildLibs.bundles.dokka)
    api(buildLibs.tutteli.junitjacoco)
    api(buildLibs.tutteli.moduleinfo)
    api(buildLibs.tutteli.spek)
}
