description = "Expectation verbs for Atrium"

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(prefixedProject("logic"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(prefixedProject("specs-common"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(prefixedProject("specs-jvm"))
            }
        }
    }
}
