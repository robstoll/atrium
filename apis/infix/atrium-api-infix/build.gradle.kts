plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "An infix API in en_GB with a focus on code completion."

val niokVersion: String by rootProject.extra

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

        jvmMain {
            dependencies {
                implementation("ch.tutteli.niok:niok:$niokVersion")
            }
        }

        commonTest {
            dependencies {
                implementation(project(":${rootProject.name}-specs")) {
                    exclude(module = "${rootProject.name}-translations-en_GB")
                }
                implementation(prefixedProject("translations-de_CH"))
                // in order that we can use the correct import in the samples
                implementation(prefixedProject("verbs"))
            }
        }
        jvmTest{
            dependencies {
                implementation(project(":${rootProject.name}-specs")) {
                    exclude(module = "${rootProject.name}-translations-en_GB")
                }
            }
        }
    }
}

val jacocoAdditional: List<Project> by extra(
    listOf(
        prefixedProject("translations-en_GB"),
        prefixedProject("logic"),
        prefixedProject("core")
    )
)
