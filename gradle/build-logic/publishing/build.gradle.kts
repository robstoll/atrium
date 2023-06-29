plugins {
    id("build-logic.kotlin-dsl-gradle-plugin")
}

dependencies {
    api(projects.basics)
    api(projects.dev)

    api("org.jetbrains.dokka:org.jetbrains.dokka.gradle.plugin:1.8.20")
    api("org.jetbrains.dokka:dokka-base:1.8.20")

    api("ch.tutteli.gradle.plugins.dokka:ch.tutteli.gradle.plugins.dokka.gradle.plugin:4.10.0")
    api("ch.tutteli.gradle.plugins.publish:ch.tutteli.gradle.plugins.publish.gradle.plugin:4.10.0")
}
