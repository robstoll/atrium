plugins {
    id("build-logic.kotlin-dsl-gradle-plugin")
}

dependencies {
    api(projects.basics)
    api(projects.dev)
    api("ch.tutteli.gradle.plugins.dokka:ch.tutteli.gradle.plugins.dokka.gradle.plugin:4.9.0")
    api("org.jetbrains.dokka:org.jetbrains.dokka.gradle.plugin:1.8.10")
    api("org.jetbrains.dokka:dokka-base:1.8.20")
}
