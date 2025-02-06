plugins {
    id("build-logic.kotlin-multiplatform")
}

description = "Only here to test that an API based on logic will still work as expected"

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
