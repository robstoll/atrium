description = "Kotlin 1.3 specific expectation functions and builders for atrium-api-fluent-en_GB -- will be merged into fluent-en_GB with 1.0.0 at the latest"

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(prefixedProject("api-fluent-en_GB"))
                api(prefixedProject("logic-kotlin_1_3"))
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

        configureEach {
            languageSettings.apply {
                languageVersion = "1.3"
                apiVersion = "1.3"
            }
        }
    }
}
