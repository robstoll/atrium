plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "An infix API in en_GB with a focus on code completion."

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
                implementation(project(":${rootProject.name}-specs")) {
                    exclude(module = "${rootProject.name}-translations-en_GB")
                }
                implementation(prefixedProject("translations-de_CH"))
                // in order that we can use the correct import in the samples
                implementation(prefixedProject("verbs"))
            }
        }
        jvmTest {
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
        prefixedProject("core"),
        prefixedProject("verbs")
    )
)
