plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "Convenience module which depends on atrium-api-infix and atrium-verbs"

val niokVersion: String by rootProject.extra

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(prefixedProject("verbs"))
                api(prefixedProject("api-infix"))
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
