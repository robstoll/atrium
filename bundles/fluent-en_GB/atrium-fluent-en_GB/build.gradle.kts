description = "Represents a convenience module which merely bundles dependencies."

val kotestVersion: String by rootProject.extra

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(prefixedProject("verbs"))
                api(prefixedProject("api-fluent-en_GB"))
                api(prefixedProject("translations-en_GB"))
            }
        }
        val jvmMain by getting{
            dependencies {
                api(prefixedProject("verbs"))
                api(prefixedProject("api-fluent-en_GB"))
                api(prefixedProject("translations-en_GB"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementationWithExclude("io.kotest:kotest-runner-junit5:$kotestVersion") // for kotest framework
            }
        }
    }
}

val jacocoAdditional by extra(
    listOf(
        prefixedProject("verbs"),
        prefixedProject("translations-en_GB"),
        prefixedProject("logic"),
        prefixedProject("core")
    )
)
