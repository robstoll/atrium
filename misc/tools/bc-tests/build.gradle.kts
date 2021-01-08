import ch.tutteli.niok.*
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import java.nio.file.Files
import java.nio.file.Paths

plugins {
    kotlin("multiplatform") apply false
}

buildscript {
    repositories {
        mavenCentral()
        jcenter()
    }

    dependencies {
        classpath("ch.tutteli.niok:niok:${rootProject.extra["niokVersion"]}")
    }
}

val spekExtensionsVersion: String by rootProject.extra
val niokVersion: String by rootProject.extra
val jupiterVersion: String by rootProject.extra
val mockkVersion: String by rootProject.extra
val junitPlatformVersion: String by rootProject.extra
val spek2Version: String by rootProject.extra

description =
    "Checks that specs from older versions of Atrium can still be run with the components of the current version."

@Suppress("UNCHECKED_CAST")
val bcConfigs =
    (gradle as ExtensionAware).extra.get("bcConfigs") as List<Triple<String, List<Pair<String, List<String>>>, String>>

repositories {
    mavenCentral()
    jcenter()
}

val bcTests = tasks.register("bcTests") {
    group = "verification"
}

subprojects {
    repositories {
        mavenCentral {
            content {
                excludeVersionByRegex("ch.tutteli.atrium", "atrium-api.*-js", "0.14.0")
            }
        }
        jcenter()
    }
    apply(plugin = "org.jetbrains.kotlin.multiplatform")
}

val fixSrcPropertyName = "fixSrc"
var Project.fixSrc: () -> Unit
    get() = @Suppress("UNCHECKED_CAST") (project.extra[fixSrcPropertyName] as () -> Unit)
    set(f) {
        val g: () -> Unit = if (project.extra.has(fixSrcPropertyName)) {
            val current = fixSrc
            { current(); f() }
        } else {
            f
        }
        project.extra.set(fixSrcPropertyName, g)
    }

bcConfigs.forEach { (oldVersion, apis, forgivePattern) ->

    fun atrium(module: String): String {
        val artifactNameWithoutPrefix =
            if (module.endsWith("-jvm")) module.substringBeforeLast("-jvm") else module

        return "$group:atrium-$artifactNameWithoutPrefix:$oldVersion"
    }

    fun Project.createUnzipTask(
        module: String,
        specifier: String,
        sourceSet: String,
        target: String
    ): TaskProvider<*> {
        val confName = "bcConf_${oldVersion}_${module}_${target}"
        configurations {
            create(confName)
        }
        dependencies {
            add(confName, atrium("$module-$target") + ":" + specifier) {
                exclude(group = "*")
            }
        }
        val targetDir = "$projectDir/src/$target$sourceSet/kotlin/"
        return tasks.register("unzip-$name-$target") {
            inputs.files(configurations.named(confName))
            outputs.dir(targetDir)

            doLast {
                configurations.getByName(confName).forEach { jar ->
                    copy {
                        from(zipTree(jar))
                        into(targetDir)
                    }
                }

                // solved like this in order that we don't change the content after the unzip task because otherwise
                // we have to re-run unzip on every execution where it should suffice to do it once
                if (project.extra.has(fixSrcPropertyName)) {
                    fixSrc()
                }
            }
        }
    }


    fun Project.createUnzipTasks(module: String, specifier: String, sourceSet: String, targets: List<String>) {
        fun compileTask(target: String) =
            tasks.named(if (sourceSet == "Main") "compileKotlin${target.capitalize()}" else "compile${sourceSet}Kotlin${target.capitalize()}")

        targets.forEach { target ->
            val unzip = createUnzipTask(module, specifier, sourceSet, target)
            val compileTasks = when (target) {
                "common" -> targets.filter { it != "common" }.map { compileTask(it) }
                else -> listOf(compileTask(target))
            }
            compileTasks.forEach {
                it.configure {
                    dependsOn(unzip)
                }
            }
        }
    }

    configure(listOf(project(":bc-tests:$oldVersion-specs"))) {

        the<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension>().apply {
            jvm()
            // TODO 0.16.0 reactivate once we have transition everything to the new MPP plugin
//            js().nodejs {}
            sourceSets {
                val commonMain by getting {
                    dependencies {
                        api(kotlin("stdlib-common"))
                        api(kotlin("reflect"))
                        api("io.mockk:mockk-common:$mockkVersion")
                        api("org.spekframework.spek2:spek-dsl-metadata:$spek2Version")

                        api(project(":atrium-verbs-internal-common"))

                        // required by specs
                        //might be we have to switch to api as we have defined some of the modules as api in atrium-specs
                        implementation(project(":atrium-fluent-en_GB-common"))
//                        runtimeOnly(kotlin("stdlib-jdk8"))

                    }
                }
                val jvmMain by getting {
                    dependencies {
                        api("io.mockk:mockk:$mockkVersion")
                        api("org.spekframework.spek2:spek-dsl-jvm:$spek2Version")
                        api("ch.tutteli.spek:tutteli-spek-extensions:$spekExtensionsVersion")
                        api("ch.tutteli.niok:niok:$niokVersion")

                        api(project(":atrium-verbs-internal-jvm"))

                        // required by specs
                        //might be we have to switch to api as we have defined some of the modules as api in atrium-specs
                        implementation(project(":atrium-fluent-en_GB-jvm"))
                    }
                }
                // TODO 0.16.0 reactivate once we have transition everything to the new MPP plugin
//                val jsMain by getting {
//                    dependencies {
//                        api("io.mockk:mockk-dsl-js:$mockkVersion")
//                        api("org.spekframework.spek2:spek-dsl-js:$spek2Version")
//
//                        api(project(":atrium-verbs-internal-js"))
//
//                        // required by specs
//                        //might be we have to switch to api as we have defined some of the modules as api in atrium-specs
//                        implementation(project(":atrium-fluent-en_GB-js"))
//
//                        //TODO 1.0.0 should no longer be necessary once updated to kotlin 1.4.x
//                        implementation(kotlin("stdlib-js"))
//                    }
//                }
            }
        }

        createUnzipTasks(
            "specs",
            "sources",
            "Main",
            apis.maxBy { it.second.size }?.second ?: throw GradleException("no apis specified")
        )
    }

    apis.forEach { (apiName, targets) ->
        configure(listOf(project(":bc-tests:$oldVersion-api-$apiName"))) {
            the<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension>().apply {
                // TODO 0.16.0 reactivate once we have transition everything to the new MPP plugin
//                js().nodejs {}
                jvm {
                    compilations.all {
                        kotlinOptions.jvmTarget = "1.6"
                    }
                    val testProvider = testRuns["test"].executionTask
                    testProvider.configure {
                        useJUnitPlatform {
                            includeEngines("junit-jupiter")
                        }
                    }

                    val bcTest = tasks.register<JavaExec>("bc-$oldVersion-$apiName") {
                        group = "verification"
                        description =
                            "Checks if specs from $apiName $oldVersion can be compiled and run against the current version."

                        inputs.files(compilations["test"].output)
                        inputs.property("forgivePattern", forgivePattern)

                        // declaring it has no outputs, only inputs matter
                        outputs.upToDateWhen {
                            !project.properties.containsKey("bcForce")
                        }

                        classpath = compilations["test"].runtimeDependencyFiles
                        main = "org.junit.platform.console.ConsoleLauncher"
                        args = getBcArgs(compilations["test"].output.classesDirs.asPath, forgivePattern)
                    }
                    bcTests.configure {
                        dependsOn(bcTest)
                    }
                    //TODO 0.16.0 not yet sure if it makes more sense to include it into :check as well
//                    tasks.named("check").configure {
//                        dependsOn(bcTest)
//                    }
                }

                sourceSets {
                    val commonTest by getting {
                        dependencies {
                            implementation(project(":atrium-api-$apiName-common"))
                            implementation(project(":bc-tests:$oldVersion-specs"))

                            // for samples
                            implementation(kotlin("test-common"))
                            implementation(kotlin("test-annotations-common"))
                        }
                    }
                    val jvmTest by getting {

                        dependencies {
                            implementation(project(":atrium-api-$apiName-jvm"))

                            // to run forgiving spek tests
                            runtimeOnly(project(":bc-tests:test-engine"))

                            // for Samples
                            implementation(kotlin("test-junit5"))
                            runtimeOnly("org.junit.jupiter:junit-jupiter-engine:$jupiterVersion")

                        }
                    }
                    // TODO 0.16.0 reactivate once we have transition everything to the new MPP plugin
//                    val jsTest by getting {
//                        dependencies {
//                            implementation(project(":atrium-api-$apiName-js"))
//                            implementation(kotlin("test-js"))
//
//
//                            api(project(":atrium-core-robstoll-js"))
//                            api(project(":atrium-domain-robstoll-js"))
//
//                            //TODO 1.0.0 should no longer be necessary once updated to kotlin 1.4.x
//                            implementation(kotlin("stdlib-js"))
//                        }
//                    }
                }
            }

            fun memoizeTestFile(testTask: Test) =
                project.file("${project.buildDir}/test-results/memoize-previous-state-${testTask.name}.txt")

            tasks.withType<Test> {
                testLogging {
                    events(
                        TestLogEvent.FAILED,
                        TestLogEvent.SKIPPED,
                        TestLogEvent.STANDARD_OUT,
                        TestLogEvent.STANDARD_ERROR
                    )
                    exceptionFormat = TestExceptionFormat.FULL
                    showExceptions = true
                    showCauses = true
                    showStackTraces = true
                }
                val testTask = this
                addTestListener(object : TestListener {
                    override fun beforeSuite(suite: TestDescriptor) {}
                    override fun beforeTest(testDescriptor: TestDescriptor) {}
                    override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {}
                    override fun afterSuite(suite: TestDescriptor, result: TestResult) {
                        if (suite.parent == null) {
                            if (result.testCount == 0L) {
                                throw GradleException("No tests executed, most likely the discovery failed.")
                            }
                            println("Result: ${result.resultType} (${result.successfulTestCount} succeeded, ${result.failedTestCount} failed, ${result.skippedTestCount} skipped)")
                            memoizeTestFile(testTask).writeText(result.resultType.toString())
                        }
                    }
                })
            }

            tasks.withType<Test>().forEach { testTask ->
                val failIfTestFailedLastTime =
                    tasks.register("fail-if-${testTask.name}-failed-last-time") {
                        doLast {
                            if (!testTask.didWork) {
                                val memoizeTestFile = memoizeTestFile(testTask)
                                if (memoizeTestFile.exists() && memoizeTestFile.readText() == TestResult.ResultType.FAILURE.toString()) {
                                    val allTests = tasks.getByName("allTests") as TestReport
                                    throw GradleException(
                                        "test failed in last run, execute clean${testTask.name} to force its execution\n" +
                                            "See the following report for more information:\nfile://${allTests.destinationDir}/index.html"
                                    )
                                }
                            }
                        }
                    }
                testTask.finalizedBy(failIfTestFailedLastTime)
            }

            createUnzipTasks("api-$apiName", "testsources", "Test", targets)
        }
    }
}

fun getBcArgs(scanClassPath: String, forgivePattern: String) = listOf(
    "--scan-class-path", scanClassPath,
    "--disable-banner",
    "--fail-if-no-tests",
    "--include-engine", "spek2-deprecation",
    "--include-classname", ".*Spec",
    "--config", "forgive=$forgivePattern",
    "--details", "summary"
)

fun Project.rewriteFile(filePath: String, f: (String) -> String) {
    val file = file(filePath)
    file.writeText(f(file.readText()))
}

// -----------------------------------------------------------------------------------
// Known source backward compatibility breaks:
// remove sources if you change something here in order that the changes take effect

with(project(":bc-tests:0.14.0-specs")) {
    fixSrc = {
        listOf(
            "IterableAnyAssertionsSpec",
            "IterableContainsInAnyOrderAtLeast1EntriesAssertionsSpec",
            "IterableContainsInAnyOrderOnlyEntriesAssertionsSpec",
            "IterableContainsInOrderOnlyEntriesAssertionsSpec"
        ).forEach { spec ->
            rewriteFile("src/commonMain/kotlin/main/kotlin/ch/tutteli/atrium/specs/integration/$spec.kt") {
                it
                    .replaceFirst(Regex("import ch.tutteli.atrium.api.cc.en_GB.returnValueOf"), "")
                    .replaceFirst(
                        Regex(
                            "import ch.tutteli.atrium.domain.builders.migration.asAssert\n" +
                                "import ch.tutteli.atrium.domain.builders.migration.asExpect"
                        ), ""
                    )
                    .replaceFirst(
                        Regex("//TODO remove with 1.0.0\n.*it\\(\"\\\$returnValueOfFun\\(...\\) states warning that subject is not set\"\\)([^\\}]+\\}){4}"),
                        ""
                    )
            }
        }
    }
}

// not really a source compatibility break but we don't have access here to an internal function
listOf("fluent", "infix").forEach { apiShortName ->
    with(project(":bc-tests:0.14.0-api-$apiShortName-en_GB")) {
        fixSrc = {
            rewriteFile("src/commonTest/kotlin/ch/tutteli/atrium/api/$apiShortName/en_GB/CharSequenceContainsSpecBase.kt") {
                it
                    .replaceFirst(
                        Regex("import ch.tutteli.atrium.api.$apiShortName.en_GB.creating.charsequence.contains.impl.StaticName"),
                        ""
                    )
                    .replace(Regex(" StaticName\\.([a-zA-Z]+)"), "\"$1\"")
            }

            // TODO 0.16.0 remove once we support js again
            rewriteFile("src/commonTest/kotlin/ch/tutteli/atrium/api/$apiShortName/en_GB/FeatureWorstCaseTest.kt") {
                it
                    .replaceFirst(Regex("import kotlin.js.JsName"), "")
                    .replaceFirst(Regex("@JsName\\(\"propFun\"\\)"), "")
            }
        }
    }
}

// testsources jar currently includes resources files in the root (as it would be in a jar)
with(project(":bc-tests:0.14.0-api-infix-en_GB")) {
    fixSrc = {
        val source = Paths.get("${project.projectDir}/src/jvmTest/kotlin/META-INF")
        if (source.exists) {
            val targetDir = Paths.get("${project.projectDir}/src/jvmTest/resources")
            // TODO use reCreateDirectory from niok once 1.4.0 is out
            targetDir.reCreateDirectory()

            Files.move(
                source,
                targetDir.resolve("META-INF")
            )
        }
    }
}
