plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "Contains translations for Atrium in de_CH"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(prefixedProject("core"))
            }
        }
    }
}
