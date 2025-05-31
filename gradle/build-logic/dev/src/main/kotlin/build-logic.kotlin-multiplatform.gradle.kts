plugins {
    id("build-logic.kotlin-multiplatform-conventions")
    id("build-logic.kotlin-jvm-conventions")
    id("ch.tutteli.gradle.plugins.spek")
}

if (name != "atrium-specs") {
    apply(plugin = "ch.tutteli.gradle.plugins.kotlin.module.info")
}


val spekVersion: String by rootProject.extra

spek {
    if (rootProject.name != "gradle-kotlin-dsl-accessors") {
        version = spekVersion
    }
}

//TODO 1.4.0 remove once we moved away from spec to kotlin-test
if (name == "atrium-logic" || name == "atrium-verbs" || name == "atrium-verbs-internal") {
    the<ch.tutteli.gradle.plugins.junitjacoco.JunitJacocoPluginExtension>()
        .allowedTestTasksWithoutTests.set(listOf("jsNodeTest"))
}

kotlin {
    jvm {
        // for module-info.java and Java sources in test
        withJava()
    }

    js(IR) {
        nodejs {
            //TODO 1.5.0 remove Action again once ambiguous deprecated overload is removed in Kotlin
            testTask(Action {
                useMocha {
                    // timeout in milliseconds,
                    // Windows regularly has a timeout with the default which
                    // at the time of writing was 2000
                    timeout = "10000"
                }
            })
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api(kotlin("reflect"))
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}


// the bundles don't rely on atrium-specs and thus don't have the spek2-atrium engine
if (name != "atrium-fluent" && name != "atrium-infix") {
    // need to do it afterEvaluate as the spek plugin adds spek and we don't want it
    // also the reason why we don't addIncludeEngines but set it
    project.afterEvaluate {
        kotlin {
            jvm {
                // for module-info.java and Java sources in test
                testRuns["test"].executionTask.configure {
                    useJUnitPlatform {
                        includeEngines = setOf("spek2-atrium", "junit-jupiter")
                    }
                }
            }
        }
    }
}
