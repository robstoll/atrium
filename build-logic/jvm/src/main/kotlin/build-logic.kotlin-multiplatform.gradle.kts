import kotlinmpp.configureLanguageSettings

plugins {
    kotlin("multiplatform")
    id("build-logic.kotlin-conventions")
    id("ch.tutteli.gradle.plugins.spek")
}

val spekVersion: String by rootProject.extra

spek {
    if (rootProject.name != "gradle-kotlin-dsl-accessors") {
        version = spekVersion
    }
}

if (name != "atrium-specs") {
    apply(plugin = "ch.tutteli.gradle.plugins.kotlin.module.info")
}

//TODO 1.2.0 remove once we moved away from spec to kotlin-test
if (name == "atrium-logic" || name == "atrium-verbs" || name == "atrium-verbs-internal") {
    the<ch.tutteli.gradle.plugins.junitjacoco.JunitJacocoPluginExtension>()
        .allowedTestTasksWithoutTests.set(listOf("jsNodeTest"))
}

val jupiterVersion: String by rootProject.extra

kotlin {
    jvm {
        // for module-info.java and Java sources in test
        withJava()
    }

    //TODO 1.1.0 switch from LEGACY to IR
    js(LEGACY) { nodejs() }

    sourceSets {

        configureLanguageSettings(project)

        val commonMain by getting {
            dependencies {
                api(kotlin("reflect"))

                // TODO 1.3.0 shouldn't be necessary to add stdlib dependency to kotlin with kotlin 1.5.x (is automatically added)
                api(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                // TODO 1.3.0 switch to kotlin(test) with update to kotlin > 1.4, dependency to test-annotations-common should then no longer be necessary
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                // TODO 1.3.0 shouldn't be necessary to add the dependency to kotlin with kotlin 1.5.x (is automatically added, but check, maybe stdlib is added automatically but not stdlib-jdk8)
                api(kotlin("stdlib-jdk8"))
            }
        }
        val jvmTest by getting {
            dependencies {
                if (rootProject.name != "gradle-kotlin-dsl-accessors") {
                    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:$jupiterVersion")
                }

                // TODO  1.3.0 should no longer be necessary with kotlin 1.5, adding kotlin("test") to common should be enough
                implementation(kotlin("test-junit5"))
            }
        }
        val jsMain by getting {
            dependencies {
                // TODO 1.3.0 shouldn't be necessary to add the dependency to kotlin with kotlin 1.5.x
                api(kotlin("stdlib-js"))
            }
        }
        val jsTest by getting {
            dependencies {
                // TODO  1.3.0 should no longer be necessary with kotlin 1.5, adding kotlin("test") to common should be enough
                implementation(kotlin("test-js"))
            }
        }
    }
}

// needs to be in afterEvaluate for now because the tutteli-spek-plugin overwrites it by using useJunitPlatform which
// apparently reconfigures the TestFramework (even if already useJunitPlatform was used, so it's more a setJUnitPlatformAsTestFramework)
afterEvaluate {
    kotlin {
        jvm {
            testRuns["test"].executionTask.configure {
                useJUnitPlatform {
                    // TODO 1.1.0 decide if we really want to migrate to kotest, without support for hierarchical tests
                    // there is barely any value for Atrium to switch to kotest and we might be better of with kotlin-test (especially as there are JS issues)
                    includeEngines("spek2", "junit-jupiter")//, "kotest")
                }
            }
        }
    }
}
