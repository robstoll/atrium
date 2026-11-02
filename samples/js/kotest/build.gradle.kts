import org.gradle.api.tasks.testing.logging.TestExceptionFormat

// Example project to show how to use Atrium in combination with Kotest JS

plugins {
    kotlin("multiplatform") version "2.4.21"
    id("com.google.devtools.ksp").version("2.3.13")
    id("io.kotest").version("6.2.6")
}

repositories {
    mavenCentral()
}

group = "org.atriumlib.samples"
version = "0.0.1"

kotlin {
    js {
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
                implementation("ch.tutteli.atrium:atrium-fluent-js:1.3.0") {
                    exclude(group = "ch.tutteli.atrium", module = "atrium-test-factory")
                }
                implementation("io.kotest:kotest-framework-engine:6.2.3")
            }
        }
    }
}
