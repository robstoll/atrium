plugins {
    id("build-logic.kotlin-jvm")
    id("build-logic.spek-smoke-test-conventions")
}

dependencies {
    testImplementation(prefixedProject("fluent-java"))
}
