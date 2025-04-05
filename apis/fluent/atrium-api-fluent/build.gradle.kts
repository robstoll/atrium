plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "A fluent expectation function API with a focus on code completion"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
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
