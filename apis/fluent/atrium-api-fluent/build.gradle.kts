plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "A fluent expectation function API in en_GB with a focus on code completion"

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

val jacocoAdditional: List<Project> by extra(
    listOf(
        prefixedProject("translations-en_GB"),
        prefixedProject("logic"),
        prefixedProject("core"),
        prefixedProject("verbs"),
    )
)
