// Example project to show how to use Atrium in a multiplatform project
// For more information on how to setup Atrium for a multiplatform project -> https://github.com/robstoll/atrium#common

import org.gradle.api.tasks.testing.logging.TestExceptionFormat

// for infix-api -> change to 'atrium-infix-en_GB'
val atriumApi = "atrium-fluent-en_GB"
val atriumVersion = "0.18.0"
val junitVersion = "5.9.3"

plugins {
    kotlin("multiplatform") version "1.8.10"
}

group = "org.atriumlib.samples"
version = "0.0.1"

repositories {
    mavenCentral()
}

kotlin {
    jvm().compilations.all {
        // Atrium requires at least jdk 11
        kotlinOptions.jvmTarget = "11"
    }
    java.targetCompatibility = JavaVersion.VERSION_11
    // atrium only supports LEGACY for now
    js(LEGACY).nodejs()
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

rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin> {
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension>().apply {
        // fix vulnerable version, use 8.1.0 instead of 7.2.0 which still relies on minimatch 3.0.4
        resolution("glob", "8.1.0")
    }
}
