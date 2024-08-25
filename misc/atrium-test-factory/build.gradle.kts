plugins {
    id("build-logic.published-kotlin-multiplatform")
}
description = "Provides test factory utility functions."

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(kotlin("test"))
                api(prefixedProject("core"))
                api(prefixedProject("logic"))
            }
        }

        commonTest {
            dependencies {
                implementation(prefixedProject("specs"))
                implementation(prefixedProject("api-fluent"))
            }
        }

        jvmMain {
            dependencies {
                api(libs.junit.jupiter.api)
            }
        }
    }
}
