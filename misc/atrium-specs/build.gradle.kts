description = "Provides specifications of Atrium which can be reused by" +
    "APIs and logic/core implementations of Atrium, to verify that they fulfill the specification."

val mockkVersion: String by rootProject.extra
val spekVersion: String by rootProject.extra
val niokVersion: String by rootProject.extra
val spekExtensionsVersion: String by rootProject.extra
val mockitoKotlinVersion: String by rootProject.extra

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(prefixedProject("core"))
                // exclude this dependency in case you want to use another translation
                api(prefixedProject("translations-en_GB"))
                api(prefixedProject("verbs-internal"))
                api("io.mockk:mockk-common:$mockkVersion")

                implementation(prefixedProject("api-fluent-en_GB"))

                api("org.spekframework.spek2:spek-dsl-metadata:$spekVersion")
            }
        }

        val jvmMain by getting {
            dependencies {
                api("io.mockk:mockk:$mockkVersion")
                api("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
                api("ch.tutteli.niok:niok:$niokVersion")
                api("ch.tutteli.spek:tutteli-spek-extensions:$spekExtensionsVersion")
                api("com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoKotlinVersion")
            }
        }
        //TODO 0.19.0 activate once all are migrated to MPP
//        val jsTest by getting {
//            api("io.mockk:mockk-dsl-js:$mockkVersion")
//
//            api("org.spekframework.spek2:spek-dsl-js:$spekVersion")
//        }

        configureEach {
            languageSettings.apply {
                languageVersion = "1.3"
                apiVersion = "1.3"
            }
        }
    }
}
