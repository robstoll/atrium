description = "Core module of Atrium, containing all contracts/interfaces and default implementations"

val kboxVersion: String by rootProject.extra

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                apiWithExclude("ch.tutteli.kbox:kbox-common:$kboxVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(prefixedProject("api-infix-en_GB-common"))
                implementation(prefixedProject("specs-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                apiWithExclude("ch.tutteli.kbox:kbox:$kboxVersion")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(prefixedProject("api-infix-en_GB-jvm"))
                implementation(prefixedProject("specs-jvm"))
            }
        }
        //TODO 0.19.0 activate again as soon as api-infix has a js module
//        val jsMain by getting {
//            dependencies {
//                apiWithExclude("ch.tutteli.kbox:kbox-js:$kboxVersion")
//            }
//        }
//        val jsTest by getting {
//            dependencies {
//                implementation(prefixedProject("api-infix-en_GB-js"))
//                implementation(prefixedProject("specs-js"))
//            }
//        }
    }
}
