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
val spekVersion: String by rootProject.extra
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
                        api("org.spekframework.spek2:spek-dsl-metadata:$spekVersion")

                        api(project(":atrium-verbs-internal-common"))

                        // required by specs
                        //might be we have to switch to api as we have defined some of the modules as api in atrium-specs
                        implementation(project(":atrium-fluent-en_GB-common"))
                    }
                }
                val jvmMain by getting {
                    dependencies {
                        api("io.mockk:mockk:$mockkVersion")
                        api("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
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
//                        api("org.spekframework.spek2:spek-dsl-js:$spekVersion")
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
                                    implementation(project(":atrium-translations-de_CH"))
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
                                    exclude(module = "${rootProject.name}-translations-en_GB")
                                    exclude(module = "${rootProject.name}-translations-en_GB")
                                }
                            }
                            if (apiName == "infix-en_GB") {
                                implementation(project(":atrium-translations-de_CH"))
                            } else {
                                implementation(project(":atrium-translations-en_GB"))
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
                                implementation(project(":atrium-translations-de_CH"))
                            } else {
                                implementation(project(":atrium-translations-en_GB"))
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
                    project(":atrium-translations-de_CH"),
                    project(":atrium-translations-de_CH")
                )
            }
            else -> throw IllegalStateException("re-adjust jacoco task")
        }
        projects.forEach {
            //TODO 0.19.0 simplify once all project use new MPP plugin
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

fun Project.rewriteFile(filePath: String, f: (String) -> String) {
    val file = file(filePath)
    file.writeText(f(file.readText()))
}
//</editor-fold>

// -----------------------------------------------------------------------------------
// Known source backward compatibility breaks:
// remove sources if you change something here in order that the changes take effect

// TODO 0.19.0 activate again
// TODO 0.20.0 remove once we support js again
//listOf("0.18.0").forEach { version ->
//    listOf("fluent", "infix").forEach { apiShortName ->
//        with(project(":bc-tests:$version-api-$apiShortName-en_GB")) {
//            defineSourceFix("common") {
//                rewriteFile("src/commonTest/kotlin/ch/tutteli/atrium/api/$apiShortName/en_GB/FeatureWorstCaseTest.kt") {
//                    it
//                        .replaceFirst("import kotlin.js.JsName", "")
//                        .replaceFirst("@JsName(\"propFun\")", "")
//                }
//            }
//        }
//    }
//}
