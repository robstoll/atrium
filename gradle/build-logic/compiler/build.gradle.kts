plugins {
    id("build-logic.kotlin-dsl-gradle-plugin")
}

dependencies {
    api(projects.dev)

    api(buildLibs.kotlin)
}
