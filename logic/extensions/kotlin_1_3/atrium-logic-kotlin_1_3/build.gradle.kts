description = "The domain logic of the Kotlin 1.3 extension for Atrium"

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(prefixedProject("logic"))
                // it is up to the consumer which atrium-translations module is used at runtime
                compileOnly(prefixedProject("translations-en_GB"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(prefixedProject("api-fluent-en_GB"))
                implementation(prefixedProject("specs"))
            }
        }
        val jvmTest by getting {
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
