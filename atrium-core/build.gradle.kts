plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "Core module of Atrium, containing all contracts/interfaces and default implementations"

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.kbox)
            }
        }
        commonTest {
            dependencies {
                implementation(prefixedProject("api-infix"))
                implementation(prefixedProject("specs"))
            }
        }
    }
}
