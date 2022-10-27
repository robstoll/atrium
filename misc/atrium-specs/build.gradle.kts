description = "Provides specifications of Atrium which can be reused by" +
    "APIs and logic/core implementations of Atrium, to verify that they fulfill the specification."

val mockkVersion: String by rootProject.extra
val spekVersion: String by rootProject.extra
val niokVersion: String by rootProject.extra
val spekExtensionsVersion: String by rootProject.extra
val mockitoKotlinVersion: String by rootProject.extra
val kotestVersion: String by rootProject.extra

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(prefixedProject("core"))
                // exclude this dependency in case you want to use another translation
                api(prefixedProject("translations-en_GB"))
                api(prefixedProject("verbs-internal"))
                apiWithExclude("io.mockk:mockk-common:$mockkVersion")

                implementation(prefixedProject("api-fluent-en_GB"))

                apiWithExclude("org.spekframework.spek2:spek-dsl-metadata:$spekVersion")
                apiWithExclude("io.kotest:kotest-runner-junit5:$kotestVersion")
                // necessary in order that intellij sees the io.kotest symbols (runner-junit5 actually already depends on it)
                apiWithExclude("io.kotest:kotest-framework-api:$kotestVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.3.9")
            }
        }

        val jvmMain by getting {
            dependencies {
                apiWithExclude("io.mockk:mockk:$mockkVersion")
                apiWithExclude("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
                apiWithExclude("ch.tutteli.niok:niok:$niokVersion")
                apiWithExclude("ch.tutteli.spek:tutteli-spek-extensions:$spekExtensionsVersion")
                apiWithExclude("com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoKotlinVersion")
                apiWithExclude("io.kotest:kotest-runner-junit5:$kotestVersion")
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
