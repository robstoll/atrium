plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "Contains translations for Atrium in en_GB"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(prefixedProject("core"))
            }
        }
    }
}
