plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description =
    "Kotlin 1.3 specific expectation functions and builders for atrium-api-fluent -- will be merged into fluent with 1.0.0 at the latest"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(prefixedProject("api-infix"))
                api(prefixedProject("logic-kotlin_1_3"))
            }
        }

        commonTest {
            dependencies {
                implementation(prefixedProject("specs"))
                // in order that we can use the correct import in the samples
                implementation(prefixedProject("verbs"))
            }
        }
        jvmTest {
            dependencies {
                implementation(prefixedProject("specs"))
            }
        }
    }
}
