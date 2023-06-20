plugins {
    id("build-logic.kotlin-jvm")
    id("build-logic.spek-smoke-test-conventions")
}

dependencies {
    testImplementation(prefixedProject("fluent"))
    testImplementation(prefixedProject("api-fluent-kotlin_1_3"))
}
