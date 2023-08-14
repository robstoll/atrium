plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "Provides specifications of Atrium which can be reused by" +
    "APIs and logic/core implementations of Atrium, to verify that they fulfill the specification."

val mockkVersion: String by rootProject.extra
val spekVersion: String by rootProject.extra
val niokVersion: String by rootProject.extra
val spekExtensionsVersion: String by rootProject.extra
val mockitoKotlinVersion: String by rootProject.extra

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(prefixedProject("core"))
                // exclude this dependency in case you want to use another translation
                api(prefixedProject("translations-en_GB"))
                api(prefixedProject("verbs-internal"))
                apiWithExclude("io.mockk:mockk-common:$mockkVersion")

                implementation(prefixedProject("api-fluent"))

                apiWithExclude("org.spekframework.spek2:spek-dsl-metadata:$spekVersion")
            }
        }

        jvmMain {
            dependencies {
                apiWithExclude("io.mockk:mockk:$mockkVersion")
                apiWithExclude("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
                apiWithExclude("ch.tutteli.niok:niok:$niokVersion")
                apiWithExclude("ch.tutteli.spek:tutteli-spek-extensions:$spekExtensionsVersion")
                apiWithExclude("com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoKotlinVersion")
            }
        }

        jsMain {
            dependencies {
                api("io.mockk:mockk-dsl-js:$mockkVersion")
                api("org.spekframework.spek2:spek-dsl-js:$spekVersion")
            }
        }
    }
}
