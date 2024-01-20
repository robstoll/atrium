plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "Expectation verbs used internally of the Atrium project"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(prefixedProject("logic"))
                compileOnly(prefixedProject("translations-en_GB"))
            }
        }

        commonTest {
            dependencies {
                implementation(prefixedProject("specs"))
            }
        }
        jvmTest {
            dependencies {
                implementation(prefixedProject("specs"))
            }
        }
    }
}
