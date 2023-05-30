// Example project to show how to use Atrium in combination with JUnit5
// For more information on how to setup Atrium for a JVM project -> https://github.com/robstoll/atrium#jvm

import org.gradle.api.tasks.testing.logging.TestExceptionFormat

val atriumVersion = "1.0.0"
val junitVersion = "5.9.3"

plugins {
    kotlin("jvm") version "1.8.21"
}

group = "org.atriumlib.samples"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    // junit5 requires jdk8, define dependency to stdlib-jdk at least in test
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // setup for Atrium:
    // for infix-api -> change artifact-id to 'atrium-infix'
    testImplementation("ch.tutteli.atrium:atrium-fluent:$atriumVersion")

    // setup for junit5
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
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

kotlin {
    target.compilations.all {
        // Atrium requires at least jdk 11
        kotlinOptions.jvmTarget = "11"

    }
}
java.targetCompatibility = JavaVersion.VERSION_11
