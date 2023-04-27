plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "Kotlin 1.3 specific expectation functions and builders for atrium-api-fluent -- will be merged into fluent with 1.0.0 at the latest"

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(prefixedProject("api-fluent"))
                api(prefixedProject("logic-kotlin_1_3"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(prefixedProject("specs"))
                // in order that we can use the correct import in the samples
                implementation(prefixedProject("verbs"))
            }
        }
    }
}
