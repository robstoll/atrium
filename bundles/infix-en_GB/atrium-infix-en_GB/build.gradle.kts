description = "Convenience module which depends on atrim-api-infix and atrium-verbs"

val niokVersion: String by rootProject.extra

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(prefixedProject("verbs"))
                api(prefixedProject("api-infix-en_GB"))
                api(prefixedProject("translations-en_GB"))
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
