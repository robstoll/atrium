import com.github.vlsi.gradle.properties.dsl.props

plugins {
    kotlin("jvm")
    id("build-logic.kotlin-conventions")
    id("build-logic.build-params")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
}

kotlin {
    // Require explicit access modifiers and require explicit types for public APIs.
    // See https://kotlinlang.org/docs/whatsnew14.html#explicit-api-mode-for-library-authors
    if (props.bool("kotlin.explicitApi", default = true)) {
        explicitApi()
    }
    sourceSets {
        configureLanguageSettings(project)
    }
}
