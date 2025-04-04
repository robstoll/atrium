plugins {
    id("build-logic.kotlin-compiler-plugin-conventions")
}

description = "Kotlin compiler plugin which generates the java-api based on the kotlin api"

dependencies {
    testImplementation(prefixedProject("specs"))
    testImplementation(prefixedProject("api-fluent"))
    implementation(prefixedProject("logic"))
}
