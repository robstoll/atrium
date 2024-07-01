plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "An infix API in en_GB with a focus on code completion."

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                compileOnly(prefixedProject("translations-en_GB"))

                //TODO remove with 1.4.0
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
                implementation(project(":${rootProject.name}-specs"))
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
        prefixedProject("verbs")
    )
}
