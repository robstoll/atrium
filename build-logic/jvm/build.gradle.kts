plugins {
    id("build-logic.kotlin-dsl-gradle-plugin")
}

dependencies {
    api(projects.buildParameters)
    api("ch.tutteli.gradle.plugins.junitjacoco:ch.tutteli.gradle.plugins.junitjacoco.gradle.plugin:4.9.0")
    api("ch.tutteli.gradle.plugins.kotlin.module.info:ch.tutteli.gradle.plugins.kotlin.module.info.gradle.plugin:4.9.0")
    api("ch.tutteli.gradle.plugins.spek:ch.tutteli.gradle.plugins.spek.gradle.plugin:4.9.0")
    api("ch.tutteli.niok:niok:1.4.7")
    api("com.github.vlsi.crlf:com.github.vlsi.crlf.gradle.plugin:1.88")
    api("com.github.vlsi.gradle-extensions:com.github.vlsi.gradle-extensions.gradle.plugin:1.88")
    api("org.jetbrains.dokka:org.jetbrains.dokka.gradle.plugin:1.8.10")
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
}
