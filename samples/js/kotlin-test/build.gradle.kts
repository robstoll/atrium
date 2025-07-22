// Example project to show how to use Atrium in combination with kotlin-test and mocha as runner
// For more information on how to set up Atrium for a JS project -> https://github.com/robstoll/atrium#js

import org.gradle.api.tasks.testing.logging.TestExceptionFormat

// for infix-api -> change to 'atrium-infix-js'
val atriumApi = "atrium-fluent-js"
val atriumVersion = "1.2.0"

repositories {
    mavenCentral()
}

plugins {
    kotlin("multiplatform") version "2.2.0"
}

group = "org.atriumlib.samples"
version = "0.0.1"

kotlin {
    // atrium only supports IR
    js(IR) {
        nodejs {
            // optional: not related to Atrium, might be handy for you as well :)
            testTask {
                testLogging {
                    exceptionFormat = TestExceptionFormat.FULL // Show full exception when an assertion fails
                    showExceptions = true                      // defaults to true
                    showCauses = true                          // defaults to true
                    showStackTraces = true                     // defaults to true
                }
            }
        }
    }

    sourceSets {
        val jsTest by getting {
            dependencies {
                // setup for Atrium:
                implementation("ch.tutteli.atrium:$atriumApi:$atriumVersion")

                // setup for mocha:
                implementation(kotlin("test"))
            }
        }
    }
}
