description = "Contains translations for Atrium in de_CH"

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(prefixedProject("core"))
            }
        }
    }
}
