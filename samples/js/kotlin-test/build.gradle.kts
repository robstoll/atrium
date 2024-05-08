// Example project to show how to use Atrium in combination with kotlin-test and mocha as runner
// For more information on how to set up Atrium for a JS project -> https://github.com/robstoll/atrium#js

import org.gradle.api.tasks.testing.logging.TestExceptionFormat

// for infix-api -> change to 'atrium-infix-js'
val atriumApi = "atrium-fluent-js"
val atriumVersion = "1.2.0"

repositories {
    mavenCentral()
}

dependencies {
    "implementation"("org.jetbrains.kotlin:kotlin-stdlib-js")

    // setup for Atrium:
    "testImplementation"("ch.tutteli.atrium:$atriumApi:$atriumVersion")

    // setup for mocha:
    "testImplementation"("org.jetbrains.kotlin:kotlin-test-js")
}

plugins {
    kotlin("js") version "1.9.24"
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
}
