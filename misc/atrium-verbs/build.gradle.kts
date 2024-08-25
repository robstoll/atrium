plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "Expectation verbs for Atrium"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(prefixedProject("logic"))
                api(prefixedProject("test-factory"))
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
