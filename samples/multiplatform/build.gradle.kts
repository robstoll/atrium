// Example project to show how to use Atrium in a multiplatform project
// For more information on how to setup Atrium for a multiplatform project -> https://github.com/robstoll/atrium#common

import org.gradle.api.tasks.testing.logging.TestExceptionFormat

// for infix-api -> change to 'atrium-infix-en_GB'
val atriumApi = "atrium-fluent-en_GB"
val atriumVersion = "0.16.0"
val junitVersion = "5.8.0"

plugins {
    kotlin("multiplatform") version "1.5.30"
}

group = "org.atriumlib.samples"
version = "0.0.1"

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    js().nodejs()
    sourceSets {
        val commonTest by getting {
            dependencies {
                // setup for Atrium:
                implementation("ch.tutteli.atrium:$atriumApi-common:$atriumVersion")

                // setup for common tests:
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmTest by getting {
            dependencies {
                // setup for Atrium:
                implementation("ch.tutteli.atrium:$atriumApi:$atriumVersion")

                // setup for Junit5:
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
            }

        }
        val jsTest by getting {
            dependencies {
                // setup for Atrium:
                implementation("ch.tutteli.atrium:$atriumApi-js:$atriumVersion")

                // setup for mocha:
                implementation(kotlin("test-js"))
            }
        }
    }
}

tasks {
    val jvmTest by getting(Test::class) {
        testLogging {
            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
        }
    }
}
