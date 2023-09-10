// Example project to show how to use Atrium in combination with Kotest
// For more information on how to setup Atrium for a JVM project -> https://github.com/robstoll/atrium#jvm

import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val atriumVersion = "1.0.0"
val kotestVersion = "5.7.2"

plugins {
    kotlin("jvm") version "1.9.10"
}

group = "org.atriumlib.samples"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // setup for Atrium:
    // for infix-api -> change artifact-id to 'atrium-infix'
    testImplementation("ch.tutteli.atrium:atrium-fluent:$atriumVersion")

    //setup for kotest
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
}


tasks.test {
    useJUnitPlatform()

    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}
