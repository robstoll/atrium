plugins {
    kotlin("multiplatform")
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

        // necessary due to https://youtrack.jetbrains.com/issue/KT-65352/KMP-Gradle-impossible-to-set-language-apiVersion-for-common-to-1.4
        all {
            languageSettings {
                languageVersion = buildParameters.kotlin.version
                apiVersion = buildParameters.kotlin.version
            }
        }
    }
}

// this is necessary due to some crazy kotlin plugin voodoo. If we define this in the rootProject itself,
// then it does not work.
rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin> {
    rootProject.configure<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension> {
        lockFileDirectory = rootProject.projectDir.resolve("gradle")
    }
}

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
