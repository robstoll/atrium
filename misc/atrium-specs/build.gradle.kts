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
                //TODO 0.20.0 I guess we can use a different dependency with kotest 5.x
                apiWithExclude("io.kotest:kotest-framework-engine:$kotestVersion")
                //TODO 0.20.0 should no longer be necessary to state that explicitly with update to kotest 5.x I guess
                implementationWithExclude("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
            }
        }

        val jvmMain by getting {
            dependencies {
                apiWithExclude("io.mockk:mockk:$mockkVersion")
                apiWithExclude("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
                apiWithExclude("ch.tutteli.niok:niok:$niokVersion")
                apiWithExclude("ch.tutteli.spek:tutteli-spek-extensions:$spekExtensionsVersion")
                apiWithExclude("com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoKotlinVersion")
                //TODO 0.20.0 should no longer be necessary to state explicitly with kotlin 1.5.x I guess (unless still necessary due to junit5)
                apiWithExclude("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
                //TODO 0.20.0 should no longer be necessary to state explicitly with kotlin 1.5.x
                apiWithExclude("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.4.3")
            }
        }

        val jsMain by getting {
            dependencies {
                api("io.mockk:mockk-dsl-js:$mockkVersion")
                api("org.spekframework.spek2:spek-dsl-js:$spekVersion")
                //TODO 0.20.0 should no longer be necessary to state explicitly with kotlin 1.5.x I guess (unless still necessary due to junit5)
                apiWithExclude("io.kotest:kotest-framework-engine-js:$kotestVersion")
                //TODO 0.20.0 should no longer be necessary to state explicitly with kotlin 1.5.x
                apiWithExclude("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.4.3")
            }
        }
    }
}
