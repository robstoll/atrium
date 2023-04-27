plugins {
    id("build-logic.kotlin-jvm")
    id("build-logic.spek-smoke-test-conventions")
}

dependencies {
    testImplementation(prefixedProject("infix"))
    testImplementation(prefixedProject("api-infix-kotlin_1_3"))
}
