plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "Contains translations for Atrium in en_GB"

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(prefixedProject("core"))
            }
        }
    }
}
