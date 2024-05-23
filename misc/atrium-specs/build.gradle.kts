import org.jetbrains.dokka.gradle.AbstractDokkaLeafTask

plugins {
    id("build-logic.published-kotlin-multiplatform")
}

description = "Provides specifications of Atrium which can be reused by" +
    "APIs and logic/core implementations of Atrium, to verify that they fulfill the specification."

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(kotlin("test"))

                api(prefixedProject("core"))
                // exclude this dependency in case you want to use another translation
                api(prefixedProject("translations-en_GB"))
                api(prefixedProject("verbs-internal"))
                api(libs.mockk.common)

                implementation(prefixedProject("api-fluent"))

                api(libs.spek.common)
            }
        }

        jvmMain {
            dependencies {
                api(libs.mockk.jvm)
                api(libs.spek.jvm)
                api(libs.niok)
                api(libs.tutteli.spek)
                api(libs.mockitoKotlin)
                api(kotlin("test-junit5"))
                api(libs.junit.platform.commons)
            }
        }

        jsMain {
            dependencies {
                api(project(":js-stubs"))
                // TODO 1.5.0 in case it should be compiled against IR at some point, reactivate again
                // and remove the own mocks in jsMain
//                api(libs.mockk.js)
//                api(libs.spek.js)
            }
        }
    }
}

ifIsPublishing {
    tasks.configureEach<AbstractDokkaLeafTask> {
        dokkaSourceSets.configureEach {
            perPackageOption {
                matchingRegex.set("io.mockk")
                suppress.set(true)
            }
            perPackageOption {
                matchingRegex.set("org.spekframework.spek2")
                suppress.set(true)
            }
        }
    }
}
