// Example project to show how to use Atrium in combination with Spek
// For more information on how to setup Atrium for a JVM project -> https://github.com/robstoll/atrium#jvm

import org.gradle.api.tasks.testing.logging.TestExceptionFormat

val atriumVersion = "1.0.0"
val spekVersion = "2.0.19"

plugins {
    kotlin("jvm") version "1.8.21"
}

group = "org.atriumlib.samples"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    //spek requires jdk8, define dependency to stdlib-jdk at least in test
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // setup for Atrium:
    // for infix-api -> change artifact-id to 'atrium-infix'
    testImplementation("ch.tutteli.atrium:atrium-fluent:$atriumVersion")

    // setup for 'spek', for some kotlin-version
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
}

tasks.test {
    useJUnitPlatform {
        includeEngines("spek2")
    }
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}

// Atrium requires at least jdk 11
kotlin {
    target.compilations.all {
        kotlinOptions.jvmTarget = "11"
    }
}
java.targetCompatibility = JavaVersion.VERSION_11

