plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "Core module of Atrium, containing all contracts/interfaces and default implementations"

val kboxVersion: String by rootProject.extra

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                apiWithExclude("ch.tutteli.kbox:kbox:$kboxVersion")
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
