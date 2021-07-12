// Example project to show how to use Atrium in combination with JUnit5
// For more information on how to setup Atrium for a JVM project -> https://github.com/robstoll/atrium#jvm

import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    kotlin("jvm") version "1.5.20"
}

group = "org.atriumlib.samples"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    val atrium_version = "0.16.0"
    val junit_version = "5.7.2"
    // junit5 requires jdk8, define dependency to stdlib-jdk at least in test
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // setup for Atrium:
    // for infix-api -> change artifact-id to 'atrium-infix-en_GB'
    testImplementation("ch.tutteli.atrium:atrium-fluent-en_GB:$atrium_version")

    // setup for junit5
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit_version")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit_version")
}

tasks.test {
    useJUnitPlatform {
        includeEngines("junit-jupiter")
    }
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}
