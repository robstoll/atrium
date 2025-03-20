import org.gradle.kotlin.dsl.kotlin

plugins {
    kotlin("multiplatform")
    id("build-logic.kotlin-conventions")
}

kotlin {
    sourceSets {
        // necessary due to https://youtrack.jetbrains.com/issue/KT-65352/KMP-Gradle-impossible-to-set-language-apiVersion-for-common-to-1.4
        all {
            languageSettings {
                languageVersion = buildParameters.kotlin.version
                apiVersion = buildParameters.kotlin.version
            }
        }
    }
}

// this is necessary due to some crazy kotlin plugin voodoo. If we define this in the rootProject itself,
// then it does not work.
rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin> {
    rootProject.configure<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension> {
        lockFileDirectory = rootProject.projectDir.resolve("gradle")
    }
}
