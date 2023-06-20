plugins {
    kotlin("jvm")
    id("build-logic.kotlin-conventions")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
}

kotlin {
    sourceSets {
        configureLanguageSettings()
    }
}
