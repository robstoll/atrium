plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "An infix API in en_GB with a focus on code completion."

val niokVersion: String by rootProject.extra

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(prefixedProject("logic"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("ch.tutteli.niok:niok:$niokVersion")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project(":${rootProject.name}-specs")) {
                    exclude(module = "${rootProject.name}-translations-en_GB")
                }
                implementation(prefixedProject("translations-de_CH"))
                // in order that we can use the correct import in the samples
                implementation(prefixedProject("verbs"))
            }
        }
        val jvmTest by getting {
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
