plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("build-logic.kotlin-jvm-conventions")
    id("java-gradle-plugin")
    id("maven-publish")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin-api")
}
