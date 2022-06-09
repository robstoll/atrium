// Example project to show how to use Atrium in combination with mocha
// For more information on how to setup Atrium for a JS project -> https://github.com/robstoll/atrium#js

import org.gradle.api.tasks.testing.logging.TestExceptionFormat

// for infix-api -> change to 'atrium-infix-en_GB-js'
val atriumApi = "atrium-fluent-en_GB-js"
val atriumVersion = "0.18.0"

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
    kotlin("js") version "1.7.0"
}

group = "org.atriumlib.samples"
version = "0.0.1"

kotlin {
    js {
        nodejs {
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
