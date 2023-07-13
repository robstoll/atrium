plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "Expectation verbs for Atrium"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(prefixedProject("logic"))

                //TODO 1.2.0 report kotlin bug, should not be necessary, already defined this way in logic

                // it is up to the consumer which atrium-translations module is used at runtime
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
