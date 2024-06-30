plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "A fluent expectation function API in en_GB with a focus on code completion"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(prefixedProject("domain"))
                compileOnly(prefixedProject("translations-en_GB"))

                //TODO remove with 2.0.0 at the latest
                api(prefixedProject("logic"))
            }
        }

        jvmMain {
            dependencies {
                implementation(libs.niok)
            }
        }

        commonTest {
            dependencies {
                implementation(prefixedProject("specs"))
                // in order that we can use the correct import in the samples
                implementation(prefixedProject("verbs"))
            }
        }
    }
}

junitjacoco {
    additionalProjectSources.addAll(
        prefixedProject("translations-en_GB"),
        prefixedProject("logic"),
        prefixedProject("core"),
        prefixedProject("verbs"),
    )
}
