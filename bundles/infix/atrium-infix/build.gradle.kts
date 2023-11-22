plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "Convenience module which depends on atrium-api-infix and atrium-verbs"

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
junitjacoco {
    additionalProjectSources.addAll(
        prefixedProject("verbs"),
        prefixedProject("translations-en_GB"),
        prefixedProject("logic"),
        prefixedProject("core")
    )
}
