plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "Expectation verbs used internally of the Atrium project"

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(prefixedProject("logic"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(prefixedProject("specs"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(prefixedProject("specs"))
            }
        }
    }
}
