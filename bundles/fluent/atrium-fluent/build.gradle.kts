plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "Convenience module which depends on atrium-api-fluent and atrium-verbs"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(prefixedProject("verbs"))
                api(prefixedProject("api-fluent"))
                api(prefixedProject("translations-en_GB"))
            }
        }
        jvmMain {
            dependencies {
                api(prefixedProject("verbs"))
                api(prefixedProject("api-fluent"))
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
