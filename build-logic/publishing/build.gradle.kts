plugins {
    id("build-logic.kotlin-dsl-gradle-plugin")
}

dependencies {
    api(projects.basics)
    api(projects.jvm)
    api("ch.tutteli.atrium.build-logic:gradle-plugin")
    api("ch.tutteli.gradle.plugins.dokka:ch.tutteli.gradle.plugins.dokka.gradle.plugin:4.9.0")
    api("org.jetbrains.dokka:dokka-base:1.8.10")
    api("ch.tutteli.gradle.plugins.publish:ch.tutteli.gradle.plugins.publish.gradle.plugin:4.9.0")
}
