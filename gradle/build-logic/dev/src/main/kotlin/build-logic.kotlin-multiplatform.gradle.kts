import org.jetbrains.kotlin.gradle.dsl.JsSourceMapEmbedMode
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.testing.KotlinJsTest

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
        compilerOptions {
            // embed the original kotlin sources into the sourcemaps so that c8 (jsCoverageReport)
            // can remap the collected V8 coverage back to the kotlin sources; by default kotlin
            // only writes null entries into sourcesContent which leaves the coverage per source
            // file with a single line
            sourceMapEmbedSources.set(JsSourceMapEmbedMode.SOURCE_MAP_SOURCE_CONTENT_ALWAYS)
        }

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

        jsTest {
            dependencies {
                // used by jsCoverageReport to turn the collected V8 coverage into istanbul-style reports
                implementation(npm("c8", "10.1.3"))
            }
        }
    }
}


// the bundles don't rely on atrium-specs and thus don't have the spek2-atrium engine
if (name != "atrium-fluent") {
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


// -- code coverage for js ----------------------------------------------------
// Neither jacoco nor Kover can measure js (Kover only supports jvm/android, see
// https://github.com/Kotlin/kotlinx-kover), hence we use the coverage built into V8 instead:
// jsNodeTest runs mocha on node with NODE_V8_COVERAGE set, which makes node write one
// coverage json per process into <module>/build/coverage/js-v8. jsCoverageReport then feeds
// these dumps to c8 which produces istanbul-style reports (lcov for codecov, html for humans)
// under <module>/build/reports/coverage/js, remapping the measured js back to the original
// kotlin sources via the sourcemaps the js compiler produces.
val jsV8CoverageDir = layout.buildDirectory.dir("coverage/js-v8")
val jsCoverageReportDir = layout.buildDirectory.dir("reports/coverage/js")

tasks.withType<KotlinJsTest>().matching { it.name == "jsNodeTest" }.configureEach {
    environment("NODE_V8_COVERAGE", jsV8CoverageDir.get().asFile.absolutePath)
    // node appends one dump per process, drop the dumps of previous runs before the tests start
    doFirst {
        val dir = jsV8CoverageDir.get().asFile
        dir.deleteRecursively()
        dir.mkdirs()
    }
    finalizedBy("jsCoverageReport")
}

tasks.register<Exec>("jsCoverageReport") {
    group = "verification"
    description = "Turns the V8 coverage collected during jsNodeTest into istanbul-style " +
        "coverage reports (lcov, html) via c8"
    // module has no js tests (or jsNodeTest has not run yet) -> no coverage dumps -> nothing to report
    onlyIf { jsV8CoverageDir.get().asFile.listFiles().orEmpty().any { it.extension == "json" } }
    inputs.dir(jsV8CoverageDir)
    outputs.dir(jsCoverageReportDir)
    // run c8 from the root project so that the include pattern matches; the compiled js of all
    // modules lives in the rootProject's build dir and c8 only matches files within its cwd
    workingDir(rootProject.projectDir)
    doFirst {
        val nodeExecutable = rootProject.extensions
            .getByType(NodeJsRootExtension::class.java)
            .requireConfigured()
            .executable
        val c8 = rootProject.layout.buildDirectory
            .file("js/node_modules/c8/bin/c8.js").get().asFile

        executable = nodeExecutable
        args(listOf(
            c8.absolutePath,
            "report",
            "--temp-directory=${jsV8CoverageDir.get().asFile.absolutePath}",
            // only the js compiled from atrium modules; stdlib, mocha & co live next to them
            // in the same kotlin dir or under node_modules and are of no interest to us
            "--include=**/kotlin/atrium-*.js",
            // apply include/exclude before remapping via sourcemaps, i.e. on the compiled js
            "--exclude-after-remap=false",
            "--reporter=lcov",
            "--reporter=html",
            "--reporter=text-summary",
            "--report-dir=${jsCoverageReportDir.get().asFile.absolutePath}"
        ))
    }
}
