// Example project to show how to use Atrium in a multiplatform project
// For more information on how to set up Atrium for a multiplatform project -> https://github.com/robstoll/atrium#common

import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

// for infix-api -> change to 'atrium-infix'
val atriumApi = "atrium-fluent"
val atriumVersion = "1.2.0"
val junitVersion = "5.9.3"

plugins {
    kotlin("multiplatform") version "1.9.24"
}

group = "org.atriumlib.samples"
version = "0.0.1"

repositories {
    mavenCentral()
}

kotlin {
    jvm()

    // atrium only supports LEGACY for now
    js(IR).nodejs()

    sourceSets {
        val commonTest by getting {
            dependencies {
                // setup for Atrium:
                implementation("ch.tutteli.atrium:$atriumApi:$atriumVersion")

                // setup for kotlin-test:
                implementation(kotlin("test"))
            }
        }
    }
}

// optional: not related to Atrium, might be handy for you as well :)
project.tasks.withType(AbstractTestTask::class.java) {
    testLogging {
        events(
            TestLogEvent.FAILED,
            TestLogEvent.SKIPPED,
            TestLogEvent.STANDARD_OUT,
            TestLogEvent.STANDARD_ERROR
        )
        exceptionFormat = TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}

rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin>().configureEach {
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension>().apply {
        // fix vulnerable version, use 8.1.0 instead of 7.2.0 which still relies on minimatch 3.0.4
        resolution("glob", "8.1.0")
    }
}
