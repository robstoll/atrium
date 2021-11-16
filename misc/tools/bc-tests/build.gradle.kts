// Note that this project is not part of the project per default.
// you need to specify the environment variable BC in order that this project (as well as the subprojects)
// are included -> alternatively, you can remove the `if` in settings.gradle.kts (search for System.getenv("BC"))

import ch.tutteli.niok.*
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import java.nio.file.Files
import java.nio.file.Paths

//<editor-fold desc="project setup">

plugins {
    kotlin("multiplatform") apply false
}

buildscript {
    repositories {
        mavenCentral()
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
val jacocoToolVersion: String by rootProject.extra

description =
    "Checks that specs from older versions of Atrium can still be run with the components of the current version."

@Suppress("UNCHECKED_CAST")
val bcConfigs =
    (gradle as ExtensionAware).extra.get("bcConfigs") as List<Triple<
        // version
        String,
        // api with targets
        List<Pair<String, List<String>>>,
        // forgivePatternBc, withBbc to forgivePatternBbc
        Pair<String, Pair<Boolean, String>>
        >>

repositories {
    mavenCentral()
}

val bcTests = tasks.register("bcTests") {
    group = "verification"
    description = "source backward compatibility tests"
}

val bbcTests = tasks.register("bbcTests") {
    group = "verification"
    description = "binary backward compatibility tests"
}

subprojects {
    repositories {
        mavenCentral {
            content {
                excludeVersionByRegex("ch.tutteli.atrium", "atrium-api.*-js", "0.14.0")
            }
        }
    }
    apply(plugin = "org.jetbrains.kotlin.multiplatform")
}

val fixSrcPropertyName = "fixSrc"
fun Project.defineSourceFix(target: String, fix: () -> Unit) {
    val map = if (project.extra.has(fixSrcPropertyName)) {
        getSrcFixes()
    } else {
        val map = mutableMapOf<String, () -> Unit>()
        project.extra.set(fixSrcPropertyName, map)
        map
    }
    val current = map[target]
    if (current != null) {
        map[target] = { current(); fix() }
    } else {
        map[target] = fix
    }
}

fun Project.getSrcFixes() =
    @Suppress("UNCHECKED_CAST") (project.extra[fixSrcPropertyName] as MutableMap<String, () -> Unit>)

fun Project.applySourceFix(target: String) {
    val map = getSrcFixes()
    val sourceFix = map[target]
    if (sourceFix != null) sourceFix()
}

val testEngineProjectName = ":bc-tests:test-engine"
bcConfigs.forEach { (oldVersion, apis, pair) ->
    val (forgivePatternBc, bbcPair) = pair
    val (withBbc, forgivePatternBbc) = bbcPair
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
                    applySourceFix(target)
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

                    doFirst {
                        if (this is AbstractCompile) {
                            // we don't want to see all the deprecation errors during compilation
                            this.logging.level = LogLevel.QUIET
                        }
                    }
                }
            }
        }
    }

    configure(listOf(project(":bc-tests:$oldVersion-specs"))) {
        the<KotlinMultiplatformExtension>().apply {
            jvm()
            // TODO 0.19.0 reactivate once we have transitioned everything to the new MPP plugin
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
                // TODO 0.19.0 reactivate once we have transitioned everything to the new MPP plugin
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

        fun org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget.createBcLikeTaskWithCoverage(
            project: Project,
            kind: String,
            description: String,
            forgivePattern: String,
            scanClassPath: String
        ): TaskProvider<JavaExec> = project.tasks.register<JavaExec>(kind) {
            group = "verification"
            this.description = description

            inputs.files(compilations["test"].output)
            inputs.property("forgivePattern", forgivePattern)

            // declaring it has no outputs, only inputs matter but allow to force run via -PbcForce=1
            outputs.upToDateWhen {
                !project.properties.containsKey("bcForce")
            }

            classpath = compilations["test"].runtimeDependencyFiles

            main = "org.junit.platform.console.ConsoleLauncher"
            args = listOf(
                "--scan-class-path", scanClassPath,
                "--disable-banner",
                "--fail-if-no-tests",
                "--include-engine", "spek2-forgiving",
                "--include-classname", ".*(Spec|Samples)",
                "--config", "forgive=$forgivePattern",
                "--details", "summary"
            ) +
                if (kind == "bbc") {
                    listOf(
                        "--include-engine", "junit-jupiter",
                        "--config", "junit.jupiter.extensions.autodetection.enabled=true"
                    )
                } else {
                    listOf()
                }
        }

        if (withBbc) {
            configure(listOf(project(":bc-tests:$oldVersion-api-$apiName-bbc"))) {
                apply(plugin = "jacoco")

                the<KotlinMultiplatformExtension>().apply {
                    val confName = "confBbc"

                    jvm {
                        configureTestSetupAndJdkVersion()

                        configurations {
                            create(confName)
                        }
                        dependencies {
                            // test jar with compiled tests
                            add(confName, atrium("api-$apiName") + ":tests") {
                                exclude(group = "*")
                            }
                        }

                        val bbcTest = createBcLikeTaskWithCoverage(
                            project,
                            "bbc",
                            "Checks if specs from $apiName $oldVersion can be run against the current version without recompilation",
                            forgivePatternBbc,
                            configurations[confName].asPath
                        )
                        createJacocoReportTask(apiName, bbcTest)
                        bbcTests.configure {
                            dependsOn(bbcTest)
                        }
                    }

                    sourceSets {
                        all {
                            // we don't have src nor resources for bbc
                            kotlin.setSrcDirs(listOf<File>())
                            resources.setSrcDirs(listOf<File>())
                        }
                        val jvmTest by getting {

                            dependencies {
                                implementation(project(":atrium-api-$apiName-jvm"))
                                if (apiName == "infix-en_GB") {
                                    implementation(project(":atrium-translations-de_CH-jvm"))
                                }
                                configurations[confName].dependencies.forEach {
                                    implementation(it)
                                }

                                // compiled specs
                                implementation(atrium("specs")) {
                                    exclude(group = "ch.tutteli.atrium")
                                }

                                // required by specs
                                implementation(project(":atrium-fluent-en_GB-jvm"))
                                implementation(project(":atrium-verbs-internal-jvm"))

                                // to run forgiving spek tests
                                runtimeOnly(project(testEngineProjectName))

                                // to run samples
                                implementation(kotlin("test-junit5"))
                                runtimeOnly("org.junit.jupiter:junit-jupiter-engine:$jupiterVersion")
                            }
                        }
                    }
                }
            }
        }

        configure(listOf(project(":bc-tests:$oldVersion-api-$apiName"))) {

            apply(plugin = "jacoco")

            the<KotlinMultiplatformExtension>().apply {

                // TODO 0.19.0 reactivate once we have transitioned everything to the new MPP plugin
//                js().nodejs {}

                jvm {
                    configureTestSetupAndJdkVersion()
                    val bcTest = createBcLikeTaskWithCoverage(
                        project,
                        "bc",
                        "Checks if specs from $apiName $oldVersion can be compiled and run against the current version.",
                        forgivePatternBc,
                        //spek ignores this setting and searches on the classpath,
                        // we don't execute junit-jupiter here (is done via build) so we can pass whatever we want
                        ""
                    )
                    createJacocoReportTask(apiName, bcTest)
                    bcTests.configure {
                        dependsOn(bcTest)
                        // we want to run the samples as well
                        dependsOn(tasks.named("build"))
                    }
                    //TODO 0.19.0 not yet sure if it makes more sense to include it into :check as well
//                    tasks.named("check").configure {
//                        dependsOn(bcTest)
//                    }
                }

                sourceSets {
                    val commonTest by getting {
                        dependencies {
                            implementation(project(":atrium-api-$apiName-common"))
                            implementation(project(":bc-tests:$oldVersion-specs")) {
                                if (apiName == "infix-en_GB") {
                                    exclude(module = "${rootProject.name}-translations-en_GB-common")
                                    exclude(module = "${rootProject.name}-translations-en_GB-jvm")
                                }
                            }
                            if (apiName == "infix-en_GB") {
                                implementation(project(":atrium-translations-de_CH-common"))
                            } else {
                                implementation(project(":atrium-translations-en_GB-common"))
                            }

                            // for samples
                            implementation(kotlin("test-common"))
                            implementation(kotlin("test-annotations-common"))
                        }
                    }
                    val jvmTest by getting {

                        dependencies {
                            implementation(project(":atrium-api-$apiName-jvm"))
                            if (apiName == "infix-en_GB") {
                                implementation(project(":atrium-translations-de_CH-jvm"))
                            } else {
                                implementation(project(":atrium-translations-en_GB-jvm"))
                            }

                            // to run forgiving spek tests
                            runtimeOnly(project(testEngineProjectName))

                            // for Samples
                            implementation(kotlin("test-junit5"))
                            runtimeOnly("org.junit.jupiter:junit-jupiter-engine:$jupiterVersion")

                        }
                    }
                    // TODO 0.19.0 reactivate once we have transitioned everything to the new MPP plugin
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
            configureTestTasks()

            createUnzipTasks("api-$apiName", "testsources", "Test", targets)
        }

    }
}

fun org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget.configureTestSetupAndJdkVersion() {
    compilations.all {
        kotlinOptions.jvmTarget = "1.8"
    }
    val testProvider = testRuns["test"].executionTask
    testProvider.configure {
        useJUnitPlatform {
            includeEngines("junit-jupiter")
        }
    }
}

fun Project.createJacocoReportTask(
    apiName: String,
    runTaskProvider: TaskProvider<JavaExec>
): TaskProvider<JacocoReport> {
    val runTask = runTaskProvider.get()
    val jacocoReport = tasks.register<JacocoReport>("jacoco-${runTask.name}") {
        group = "report"

        dependsOn(runTaskProvider)
        executionData(runTask)

        val jacocoMulti: Map<String, Iterable<Project>> by rootProject.extra
        val sourceProjects = jacocoMulti["sourceProjects"]!!
        val projects = when (apiName) {
            "fluent-en_GB" -> sourceProjects.filter { !it.name.contains("infix-en_GB") }
            "infix-en_GB" -> {
                sourceProjects.filter {
                    !it.name.contains("translations-en_GB") &&
                        !it.name.contains("fluent-en_GB")
                } + listOf(
                    project(":atrium-translations-de_CH-jvm"),
                    project(":atrium-translations-de_CH-common")
                )
            }
            else -> throw IllegalStateException("re-adjust jacoco task")
        }
        projects.forEach {
            //TODO 0.16.0 simplify once all project use new MPP plugin
            val sourceSetContainer = it.extensions.findByType<SourceSetContainer>()
            if (sourceSetContainer != null) {
                sourceSets(sourceSetContainer["main"])
            } else {
                it.the<KotlinMultiplatformExtension>().sourceSets.forEach { kotlinSourceSet ->
                    sourceDirectories.from(kotlinSourceSet.kotlin.srcDirs)
                }
            }

        }
        // DEBUG sourceDirectories
//                            println("list $oldVersion $apiName: ${sourceDirectories.map { it.absolutePath }.joinToString("\n")}\n")

        reports {
            csv.isEnabled = false
            xml.isEnabled = true
            xml.destination = file("${buildDir}/reports/jacoco/$name/report.xml")
            html.isEnabled = true
            html.destination = file("${buildDir}/reports/jacoco/$name/html/")
        }
    }

    the<JacocoPluginExtension>().apply {
        toolVersion = jacocoToolVersion
        this.applyTo<JavaExec>(runTask)
    }
    runTaskProvider.configure {
        finalizedBy(jacocoReport)
    }
    return jacocoReport
}


fun Project.configureTestTasks() {
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
}

fun Project.rewriteFile(filePath: String, f: (String) -> String) {
    val file = file(filePath)
    file.writeText(f(file.readText()))
}
//</editor-fold>

// -----------------------------------------------------------------------------------
// Known source backward compatibility breaks:
// remove sources if you change something here in order that the changes take effect

listOf("0.14.0", "0.15.0").forEach { version ->
    with(project(":bc-tests:$version-specs")) {
        defineSourceFix("common") {
            listOf(
                "IterableAnyAssertionsSpec",
                "IterableContainsInAnyOrderAtLeast1EntriesAssertionsSpec",
                "IterableContainsInAnyOrderOnlyEntriesAssertionsSpec",
                "IterableContainsInOrderOnlyEntriesAssertionsSpec"
            ).forEach { spec ->
                rewriteFile("src/commonMain/kotlin/main/kotlin/ch/tutteli/atrium/specs/integration/$spec.kt") {
                    it
                        .replaceFirst("import ch.tutteli.atrium.api.cc.en_GB.returnValueOf", "")
                        .replaceFirst(
                            "import ch.tutteli.atrium.domain.builders.migration.asAssert\n" +
                                "import ch.tutteli.atrium.domain.builders.migration.asExpect", ""
                        )
                        .replaceFirst(
                            Regex("//TODO remove with 1.0.0\n.*it\\(\"\\\$returnValueOfFun\\(...\\) states warning that subject is not set\"\\)([^\\}]+\\}){4}"),
                            ""
                        )
                }
            }

            // fix specs, was a wrong implementation and the specs tested the wrong thing
            rewriteFile("src/commonMain/kotlin/main/kotlin/ch/tutteli/atrium/specs/integration/IterableAssertionsSpec.kt") {
                it.replaceFirst(
                    "contains(\"\$hasDescriptionBasic: \$duplicateElements\")",
                    "contains(\"\$hasNotDescriptionBasic: \$duplicateElements\")"
                )
            }

            listOf(
                "IterableContainsInOrderOnlyGroupedEntriesAssertionsSpec",
                "IterableContainsInOrderOnlyGroupedValuesAssertionsSpec"
            ).forEach { spec ->
                rewriteFile("src/commonMain/kotlin/main/kotlin/ch/tutteli/atrium/specs/integration/$spec.kt") {
                    it.replaceFirst(
                        "import ch.tutteli.atrium.domain.builders.utils.Group",
                        "import ch.tutteli.atrium.logic.utils.Group"
                    )
                }
            }


            // deleted AssertionPlant and co. in 0.16.0, hence specs don't make sense any more (it's a bc on core level not API)
            file("src/commonMain/kotlin/main/kotlin/ch/tutteli/atrium/specs/checking/").deleteRecursively()
            file("src/commonMain/kotlin/main/kotlin/ch/tutteli/atrium/specs/creating/").deleteRecursively()
            file("src/commonMain/kotlin/main/kotlin/ch/tutteli/atrium/specs/reporting/TextIndentAssertionGroupFormatterSpec.kt").delete()

            // we removed ExpectBuilder in 0.17.0
            rewriteFile("src/commonMain/kotlin/main/kotlin/ch/tutteli/atrium/specs/SubjectLessSpec.kt") {
                it
                    .replaceFirst("import ch.tutteli.atrium.core.coreFactory", "")
                    .replaceFirst(
                        "import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder\n" +
                            "import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions",
                        "import ch.tutteli.atrium.logic.creating.RootExpectBuilder\n" +
                            "import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer\n" +
                            "import ch.tutteli.atrium.core.ExperimentalNewExpectTypes\n" +
                            "import ch.tutteli.atrium.reporting.AtriumErrorAdjuster\n" +
                            "import ch.tutteli.atrium.reporting.erroradjusters.NoOpAtriumErrorAdjuster\n"
                    )
                    .replaceFirst(
                        "val container = ExpectBuilder.forSubject(1.0)",
                        "@Suppress(\"DEPRECATION\" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)\n" +
                            "                @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)\n" +
                            "                val container = RootExpectBuilder.forSubject(1.0)"
                    )
                    .replaceFirst(
                        ".withOptions(\n" +
                            "                        ExpectOptions(\n" +
                            "                            reporter = coreFactory.newOnlyFailureReporter(\n" +
                            "                                coreFactory.newAssertionFormatterFacade(coreFactory.newAssertionFormatterController()),\n" +
                            "                                coreFactory.newNoOpAtriumErrorAdjuster()\n" +
                            "                            )\n" +
                            "                        )\n" +
                            "                    )", ".withOptions {\n" +
                            "                        withComponent(AtriumErrorAdjuster::class) { _ -> NoOpAtriumErrorAdjuster }\n" +
                            "                    }"
                    )

            }

            // ReporterFactory removed, needs to be done via ComponentFactoryContainer
            file("src/commonMain/kotlin/main/kotlin/ch/tutteli/atrium/specs/testutils/AsciiBulletPointReporterFactory.kt").toPath()
                .deleteIfExists()
            //just don't bother to rewrite it and this one is not that important
            file("src/commonMain/kotlin/main/kotlin/ch/tutteli/atrium/specs/verbs/VerbSpec.kt").toPath()
                .deleteIfExists()
        }

        defineSourceFix("jvm") {
            rewriteFile("src/jvmMain/kotlin/main/kotlin/ch/tutteli/atrium/specs/integration/BigDecimalAssertionsSpec.kt") {
                it.replaceFirst(
                    "import ch.tutteli.atrium.domain.builders.creating.PleaseUseReplacementException",
                    "import ch.tutteli.atrium.creating.PleaseUseReplacementException"
                )
            }
            rewriteFile("src/jvmMain/kotlin/main/kotlin/ch/tutteli/atrium/specs/reporting/translating/TranslatorIntSpec.kt") {
                it
                    .replaceFirst(
                        "import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder\n" +
                            "import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions",
                        "import ch.tutteli.atrium.logic.creating.RootExpectBuilder\n" +
                            "import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer\n" +
                            "import ch.tutteli.atrium.core.ExperimentalNewExpectTypes\n"
                    )
                    .replaceFirst(
                        "abstract class TranslatorIntSpec(",
                        "@Suppress(\"DEPRECATION\" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)\n" +
                            "@UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)\n" +
                            "abstract class TranslatorIntSpec("
                    )
                    .replace(Regex("ExpectBuilder\\.forSubject\\(([^\\)]+)\\)"), "RootExpectBuilder.forSubject\\($1\\)")
                    .replace(
                        Regex("\\.withOptions\\(ExpectOptions\\(reporter = ([^\\)]+)\\)\\)"),
                        ".withOptions\\{\n" +
                            "                withComponent\\(Reporter::class\\) \\{ _ -> $1\\}\n" +
                            "            \\}"
                    )
            }
        }
    }

    listOf("fluent", "infix").forEach { apiShortName ->
        with(project(":bc-tests:$version-api-$apiShortName-en_GB")) {
            defineSourceFix("common") {
                // not really a source compatibility break but we don't have access here to an internal function
                rewriteFile("src/commonTest/kotlin/ch/tutteli/atrium/api/$apiShortName/en_GB/CharSequenceContainsSpecBase.kt") {
                    it
                        .replaceFirst(
                            "import ch.tutteli.atrium.api.$apiShortName.en_GB.creating.charsequence.contains.impl.StaticName",
                            ""
                        )
                        .replace(Regex(" StaticName\\.([a-zA-Z]+)"), "\"$1\"")
                }

                rewriteFile("src/commonTest/kotlin/ch/tutteli/atrium/api/$apiShortName/en_GB/IterableAnyAssertionsSpec.kt") {
                    it.replaceFirst("import ch.tutteli.atrium.domain.builders.ExpectImpl", "")
                }


                listOf(
                    "IterableContainsInOrderOnlyGroupedEntriesAssertionsSpec",
                    "IterableContainsInOrderOnlyGroupedValuesAssertionsSpec",
                    "IterableContainsSpecBase"
                ).forEach { spec ->
                    rewriteFile("src/commonTest/kotlin/ch/tutteli/atrium/api/$apiShortName/en_GB/$spec.kt") {
                        it.replaceFirst(
                            "import ch.tutteli.atrium.domain.builders.utils.Group",
                            "import ch.tutteli.atrium.logic.utils.Group"
                        )
                    }
                }
            }
        }
    }

    // testsources jar currently includes resources files in the root (as it would be in a jar)
    with(project(":bc-tests:$version-api-infix-en_GB")) {
        defineSourceFix("common") {
            // we removed WithAsciiReporter in 0.17.0
            listOf(
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/AnyAssertionsSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/ArrayAsListAssertionsSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/CharSequenceAssertionsSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/CharSequenceContainsSpecBase.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/CollectionAssertionsSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/ComparableAssertionsSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/FeatureAssertionsBoundedReferenceAlternativeSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/FeatureAssertionsBoundedReferenceSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/FeatureAssertionsClassReferenceSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/FeatureAssertionsManualSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/Fun0AssertionsSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/IterableAllAssertionsSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/IterableAssertionsSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/IterableContainsSpecBase.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/IteratorAssertionSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/ListAssertionsSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/MapAsEntriesAssertionsSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/MapAssertionsSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/MapEntryAssertionsSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/PairAssertionsSpec.kt",
                "src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/ThrowableAssertionsSpec.kt"
            ).forEach { file ->
                removeWithAsciiReporter(file)
            }
        }
        defineSourceFix("jvm") {
            val source = Paths.get("${project.projectDir}/src/jvmTest/kotlin/META-INF")
            if (source.exists) {
                val targetDir = Paths.get("${project.projectDir}/src/jvmTest/resources")
                targetDir.reCreateDirectory()

                Files.move(
                    source,
                    targetDir.resolve("META-INF")
                )
            }
            listOf(
                "src/jvmTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/BigDecimalAssertionsSpec.kt",
                "src/jvmTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/ChronoLocalDateAssertionsSpec.kt",
                "src/jvmTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/ChronoLocalDateTimeAssertionSpec.kt",
                "src/jvmTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/ChronoZonedDateTimeAssertionSpec.kt",
                "src/jvmTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/FileAsPathAssertionsSpec.kt",
                "src/jvmTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/Fun0AssertionsJvmSpec.kt",
                "src/jvmTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/LocalDateAssertionsSpec.kt",
                "src/jvmTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/LocalDateTimeAssertionsSpec.kt",
                "src/jvmTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/OptionalAssertionsSpec.kt",
                "src/jvmTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/PathAssertionsSpec.kt",
                "src/jvmTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/PathFeatureAssertionsSpec.kt",
                "src/jvmTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/ZonedDateTimeAssertionsSpec.kt"
            ).forEach { file ->
                removeWithAsciiReporter(file)
            }
        }
    }
}

fun Project.removeWithAsciiReporter(file: String) {
    rewriteFile(file) {
        it
            .replaceFirst("import ch.tutteli.atrium.specs.testutils.WithAsciiReporter", "")
            .replaceFirst(": WithAsciiReporter()", "")
    }
}

with(project(":bc-tests:0.15.0-api-infix-en_GB")) {
    defineSourceFix("common") {
        rewriteFile("src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/MapContainsInAnyOrderKeyValueAssertionsSpec.kt") {
            it
                .replace("import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyWithValueCreator", "")
                .replace("KeyWithValueCreator", "keyValue")
        }

        removeWithAsciiReporter("src/commonTest/kotlin/ch/tutteli/atrium/api/infix/en_GB/MapContainsSpecBase.kt")
    }
}


// we removed RawString and co in 0.17.0
listOf("0.14.0", "0.15.0", "0.16.0").forEach { version ->
    with(project(":bc-tests:$version-specs")) {
        defineSourceFix("common") {
            rewriteFile("src/commonMain/kotlin/main/kotlin/ch/tutteli/atrium/specs/reporting/ObjectFormatterSpec.kt") {
                it.replaceFirst(
                    "//TODO remove with 1.0.0\n" +
                        "        @Suppress(\"DEPRECATION\")\n" +
                        "        context(\"a \${ch.tutteli.atrium.reporting.StringBasedRawString::class.simpleName}\") {\n" +
                        "            val result = testee.format(Text(\"hello\"))\n" +
                        "            it(\"returns the containing string\") {\n" +
                        "                expect(result).toBe(\"hello\")\n" +
                        "            }\n" +
                        "        }\n" +
                        "\n" +
                        "        //TODO remove with 1.0.0\n" +
                        "        @Suppress(\"DEPRECATION\")\n" +
                        "        context(\"a \${ch.tutteli.atrium.reporting.translating.TranslatableBasedRawString::class.simpleName}\") {\n" +
                        "            val result = testee.format(ch.tutteli.atrium.api.verbs.internal.AssertionVerb.EXPECT)\n" +
                        "            it(\"returns the translated string\") {\n" +
                        "                expect(result).isSameAs(translatedText)\n" +
                        "            }\n" +
                        "        }", ""
                )
            }
        }
    }
}

fun Project.rewriteIterableToContainSpecBase(file: String) {
    rewriteFile(file) {
        it
            .replaceFirst(
                "import kotlin.reflect.KFunction4",
                "import kotlin.reflect.KFunction5\nimport ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions"
            )
            .replaceFirst(
                "private val withinInAnyOrderFun: KFunction4<IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>, Group<Int>, Group<Int>, Array<out Group<Int>>, Expect<Iterable<Int>>> =\n" +
                    "        IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>::inAnyOrder",
                "private val withinInAnyOrderFun: KFunction5<IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>, Group<Int>, Group<Int>, Array<out Group<Int>>, InOrderOnlyReportingOptions.() -> Unit, Expect<Iterable<Int>>> =\n" +
                    "        IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour, >::inAnyOrder"
            )
    }
}

listOf("0.14.0", "0.15.0", "0.16.0").forEach { version ->
    listOf("fluent", "infix").forEach { apiShortName ->
        with(project(":bc-tests:$version-api-$apiShortName-en_GB")) {

            the<KotlinMultiplatformExtension>().apply {
                sourceSets {
                    val main = file("src/main/kotlin/")
                    val jvmTest by getting {
                        kotlin.srcDir(main)
                    }
                    main.mkdirs()

                    // InOrderOnlyReportingOptions was introduced in 0.17.0
                    main.resolve("InOrderOnlyReportingOptions.kt").writeText(
                        """
                            package ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting

                            import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

                            interface InOrderOnlyReportingOptions {

                            /**
                             * Always shows only failing expectations, same as [InOrderOnlyReportingOptions.showOnlyFailingIfMoreElementsThan]`(0)`
                             */
                            fun showOnlyFailing() = showOnlyFailingIfMoreElementsThan(0)

                            /**
                             * Always shows a summary where both failing and successful expectations are shown, same as
                             * [InOrderOnlyReportingOptions.showOnlyFailingIfMoreElementsThan]`(Int.MAX_VALUE)`.
                             */
                            fun showAlwaysSummary() = showOnlyFailingIfMoreElementsThan(Int.MAX_VALUE)

                            /**
                             * Show only failing expectations, i.e. elements which do not match, instead of a summary which
                             * lists also successful expectations/elements.
                             *
                             * Default shows up to 10 elements in a summary ans only failing afterwards,
                             * i.e. default is [showOnlyFailingIfMoreElementsThan]`(10)`
                             */
                            fun showOnlyFailingIfMoreElementsThan(number: Int)

                            /**
                             * Indicates until how many elements the summary view shall be used. If there are more elements in the
                             * [IterableLike], then only failing expectations shall be shown.
                             */
                            val numberOfElementsInSummary: Int
                            }

                        """.trimIndent()
                    )
                }
            }

            defineSourceFix("common") {
                rewriteIterableToContainSpecBase("src/commonTest/kotlin/ch/tutteli/atrium/api/$apiShortName/en_GB/IterableContainsSpecBase.kt")
            }
        }
    }
}

listOf("0.17.0").forEach { version ->
    listOf("fluent", "infix").forEach { apiShortName ->
        with(project(":bc-tests:$version-api-$apiShortName-en_GB")) {
            defineSourceFix("common") {
                rewriteIterableToContainSpecBase("src/commonTest/kotlin/ch/tutteli/atrium/api/$apiShortName/en_GB/IterableToContainSpecBase.kt")
            }
        }
    }
}

listOf("0.14.0", "0.15.0").forEach { version ->
    listOf("fluent", "infix").forEach { apiShortName ->
        with(project(":bc-tests:$version-api-$apiShortName-en_GB")) {
            defineSourceFix("jvm") {
                // we removed FloatingPointWithErrorToleranceJvmAssertions with 0.18.0, no need to test this anymore
                file("src/jvmTest/kotlin/ch/tutteli/atrium/api/$apiShortName/en_GB/FloatingPointWithErrorToleranceAssertionsJvmSpec.kt").delete()
            }
        }
    }
}
listOf("0.16.0", "0.17.0").forEach { version ->
    listOf("fluent", "infix").forEach { apiShortName ->
        with(project(":bc-tests:$version-api-$apiShortName-en_GB")) {
            defineSourceFix("jvm") {
                // we removed FloatingPointWithErrorToleranceJvmAssertions with 0.18.0, no need to test this anymore
                file("src/jvmTest/kotlin/ch/tutteli/atrium/api/$apiShortName/en_GB/FloatingPointWithErrorToleranceExpectationsJvmSpec.kt").delete()
            }
        }
    }
}


// TODO 0.19.0 or 0.20.0 remove once we support js again
listOf("0.14.0", "0.15.0", "0.16.0", "0.17.0").forEach { version ->
    listOf("fluent", "infix").forEach { apiShortName ->
        with(project(":bc-tests:$version-api-$apiShortName-en_GB")) {
            defineSourceFix("common") {
                rewriteFile("src/commonTest/kotlin/ch/tutteli/atrium/api/$apiShortName/en_GB/FeatureWorstCaseTest.kt") {
                    it
                        .replaceFirst("import kotlin.js.JsName", "")
                        .replaceFirst("@JsName(\"propFun\")", "")
                }
            }
        }
    }
}
