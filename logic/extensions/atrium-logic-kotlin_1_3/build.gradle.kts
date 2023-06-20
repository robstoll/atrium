plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "The domain logic of the Kotlin 1.3 extension for Atrium"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(prefixedProject("logic"))
                // it is up to the consumer which atrium-translations module is used at runtime
                compileOnly(prefixedProject("translations-en_GB"))
            }
        }

        commonTest {
            dependencies {
                implementation(prefixedProject("api-fluent"))
                implementation(prefixedProject("specs"))
            }
        }
        jvmTest {
            dependencies {
                implementation(prefixedProject("specs"))
            }
        }

        configureEach {
            languageSettings.apply {
                languageVersion = "1.3"
                apiVersion = "1.3"
            }
        }
    }
}

createGenerateLogicTask(
    includingTarget("common"),
    suffix = "kotlin_1_3"
)
