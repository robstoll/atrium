plugins {
    id("build-logic.kotlin-dsl-gradle-plugin")
}

dependencies {
    api(projects.basics)

    api("org.jetbrains.dokka:org.jetbrains.dokka.gradle.plugin:1.8.20")
    api("org.jetbrains.dokka:dokka-base:1.8.20")
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.22")

    api("ch.tutteli.gradle.plugins.junitjacoco:ch.tutteli.gradle.plugins.junitjacoco.gradle.plugin:4.10.0")
    api("ch.tutteli.gradle.plugins.kotlin.module.info:ch.tutteli.gradle.plugins.kotlin.module.info.gradle.plugin:4.10.0")
    api("ch.tutteli.gradle.plugins.spek:ch.tutteli.gradle.plugins.spek.gradle.plugin:4.10.0")
}
