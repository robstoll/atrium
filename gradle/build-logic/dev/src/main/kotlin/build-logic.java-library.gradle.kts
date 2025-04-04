plugins {
    id("java-library")
    id("build-logic.java-conventions")
    id("build-logic.junit-jacoco-conventions")
    //TODO 1.5.0 remove once we moved away from spek
    id("ch.tutteli.gradle.plugins.spek")
}
