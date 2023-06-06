import ch.tutteli.gradle.plugins.dokka.DokkaPluginExtension
import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.*
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.IOException
import java.net.URL

buildscript {
    rootProject.version = "1.1.0-SNAPSHOT"
    rootProject.group = "ch.tutteli.atrium"
    dependencies {
        classpath("org.jetbrains.dokka:dokka-base:1.8.10")
    }
}

// kotlin version is configured in buildSrc/build.gradle.kts
val defaultJdkVersion = 11

// main
val kboxVersion by extra("0.16.0")
val niokVersion by extra("1.4.7")

// test
val jacocoToolVersion by extra("0.8.9")
val junitPlatformVersion by extra("1.9.2")
val jupiterVersion by extra("5.9.2")
// need to use an old version of spek as the newer contains a bug which causes that no tests are discovered and executed
val spekVersion by extra("2.0.12")
//TODO v1.1.0 decide whether we drop kotest again or not
//val kotestVersion by extra("4.6.4")
val spekExtensionsVersion by extra("1.2.1")
val mockkVersion by extra("1.10.0")
val mockitoKotlinVersion by extra("2.2.0")

// project setup
val d8Version by extra("1.6.84")


plugins {
    // kotlin version is defined in buildSrc/build.gradle.kts
    kotlin("multiplatform") apply false
    kotlin("jvm") apply false
    //needs to be applied before tutteli's dokka plugin in order that version takes precedence
    id("org.jetbrains.dokka") version "1.8.20"
    val tutteliGradleVersion = "4.9.0"
    id("ch.tutteli.gradle.plugins.dokka") version tutteliGradleVersion
    id("ch.tutteli.gradle.plugins.project.utils") version tutteliGradleVersion
    id("ch.tutteli.gradle.plugins.publish") version tutteliGradleVersion apply false
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
    // used only in subprojects
    id("ch.tutteli.gradle.plugins.kotlin.module.info") version tutteliGradleVersion apply false

    id("ch.tutteli.gradle.plugins.spek") version tutteliGradleVersion apply false
}

repositories {
    mavenCentral()
}

val multiplatformProjectNames by extra(
    setOf(
        "core",
        "logic", "logic-kotlin_1_3",
        "translations-en_GB", "translations-de_CH",
        "api-fluent", "api-fluent-kotlin_1_3",
        "api-infix", "api-infix-kotlin_1_3",
        "fluent",
        "infix",
        "verbs",
        "verbs-internal",
        "specs"
    )
)
val multiplatformProjects by extra(
    multiplatformProjectNames.asSequence().map { prefixedProject(it) }.toSet()
)
val toolProjects by extra(subprojects.asSequence().filter {
    it.projectDir.path.contains("/misc/tools/") || it.projectDir.path.contains("\\misc\\tools\\")
}.toSet())

val bundleSmokeTests = subprojects.asSequence().filter {
    it.name.contains("-smoke-test")
}.toSet()

val subprojectsWithoutToolProjects = subprojects - toolProjects
val subprojectsWithoutToolAndSmokeTestProjects = subprojectsWithoutToolProjects - bundleSmokeTests

configure<ch.tutteli.gradle.plugins.dokka.DokkaPluginExtension> {
    modeSimple.set(false)
}

// for whatever reason, this is done in the root project and not in the individual subprojects which actually have a
// JS target.
tasks.withType<KotlinNpmInstallTask> {
    doFirst {
        val isOffline = try {
            val url = URL("https://www.google.com")
            val connection = url.openConnection()
            connection.connect()
            false
        } catch (e: IOException) {
            logger.warn("could not connect to www.google.com (got $e) -- going to use --offline to resolve npm dependencies")
            true
        }
        if (isOffline) {
            args.add("--offline")
        }
    }
}

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
    }
}

configure(multiplatformProjects) {
    val subproject = this

    apply(plugin = "kotlin-multiplatform")
    apply(plugin = "ch.tutteli.gradle.plugins.spek")

    the<ch.tutteli.gradle.plugins.spek.SpekPluginExtension>().version = spekVersion

    //TODO 1.2.0 remove once we moved away from spec to kotlin-test
    if (name == "atrium-logic" || name == "atrium-verbs" || name == "atrium-verbs-internal") {
        the<ch.tutteli.gradle.plugins.junitjacoco.JunitJacocoPluginExtension>()
            .allowedTestTasksWithoutTests.set(listOf("jsNodeTest"))
    }

    configure<KotlinMultiplatformExtension> {
        jvm {
            // for module-info.java and Java sources in test
            withJava()
        }

        //TODO 1.1.0 switch from LEGACY to IR
        js(LEGACY) { nodejs() }

        sourceSets {

            configureLanguageSettings(subproject)

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
                    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:$jupiterVersion")

                    // TODO 1.3.0 should no longer be necessary with kotlin 1.5, adding kotlin("test") to common should be enough
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
                    // TODO 1.3.0 should no longer be necessary with kotlin 1.5, adding kotlin("test") to common should be enough
                    implementation(kotlin("test-js"))
                }
            }
        }
    }

    // needs to be in afterEvaluate for now because the tutteli-spek-plugin overwrites it by using useJunitPlatform which
    // apparently reconfigures the TestFramework (even if already useJunitPlatform was used, so it's more a setJUnitPlatformAsTestFramework)
    afterEvaluate {
        configure<KotlinMultiplatformExtension> {
            jvm {
                testRuns["test"].executionTask.configure {
                    useJUnitPlatform {
                        //TODO 1.1.0 decide if we really want to migrate to kotest, without support for hierarchical tests
                        // there is barely any value for Atrium to switch to kotest and we might be better of with kotlin-test (especially as there are JS issues)
                        includeEngines("spek2", "junit-jupiter")//, "kotest")
                    }
                }
            }
        }
    }
}

fun NamedDomainObjectContainerScope<KotlinSourceSet>.configureLanguageSettings(project: Project) {
    configureEach {
        // TODO 1.1.0 make this configurable so that we can use a higher version in Tests. This will reveal kotlin
        // regressions sooner which means we could already test beta versions in the hope that less regressions
        // are released in the end, because they also hinder Atrium users in the end using Atrium at its full potential
        val languageVersion = if (name.endsWith("Test")) "1.4" else "1.4"
        val apiVersion = if (name.endsWith("Test")) "1.4" else "1.4"
        languageSettings.apply {
            this.languageVersion = languageVersion
            this.apiVersion = apiVersion
            optIn("kotlin.RequiresOptIn")
        }
    }
}


configure(subprojects.filter {
    val parentName = it.projectDir.parentFile.name
    it.name != "bc-tests" && parentName != "old" && parentName != "bc-tests"
} - multiplatformProjects) {
    val subproject = this

    apply(plugin = "org.jetbrains.kotlin.jvm")

    //TODO 2.0.0 should no longer be necessary with kotlin 1.8.x where stdlib-jdk8 is added automatically
    project.extensions.getByType<KotlinJvmProjectExtension>().apply {
        sourceSets {
            configureLanguageSettings(subproject)

            val main by getting {
                dependencies {
                    implementation(kotlin("stdlib-jdk8"))
                }
            }
        }
    }
}

allprojects {
    project.extensions.findByType<JavaPluginExtension>()?.apply {
        toolchain {
            // reading JAVA_VERSION from env to enable jdk17 build in CI
            val jdkVersion = System.getenv("JAVA_VERSION")?.toIntOrNull() ?: defaultJdkVersion
            languageVersion.set(JavaLanguageVersion.of(jdkVersion))
        }
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = defaultJdkVersion.toString()
    }
    tasks.withType<JavaCompile> {
        sourceCompatibility = defaultJdkVersion.toString()
        targetCompatibility = defaultJdkVersion.toString()
    }
}

//TODO 1.1.0 re-introduce bcTests again? I am currently not sure if it is actually worth it
// did not have the need to be binary compatible for a while
//val apiProjects = subprojects.filter {
//    it.name.startsWith("${rootProject.name}-api")
//}
//configure(apiProjects) {
//    createTestJarTask(apiProject)
//    createTestSourcesJarTask(apiProject)
//}


val kdocDir = rootProject.projectDir.resolve("misc/kdoc")

fun AbstractDokkaTask.configurePlugins() {
    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        footerMessage = "Atrium &copy; Copyright Robert Stoll &lt;rstoll@tutteli.ch&gt;"
    }
}

configure(subprojectsWithoutToolAndSmokeTestProjects) {
    val subproject = this
    apply(plugin = "ch.tutteli.gradle.plugins.dokka")
    apply(plugin = "ch.tutteli.gradle.plugins.publish")

    tasks.withType<DokkaTask> {
        dokkaSourceSets.configureEach {
            reportUndocumented.set(true)
        }
    }
    tasks.withType<AbstractDokkaLeafTask> {
        dokkaSourceSets.configureEach {
            jdkVersion.set(defaultJdkVersion)
            perPackageOption {
                matchingRegex.set("io.mockk")
                suppress.set(true)
            }
            includes.from(kdocDir.resolve("packages.md"))

            //TODO 1.1.0 remove with update to tutteli-gradle 4.10.0
            externalDocumentationLink {
                val extension = rootProject.the<DokkaPluginExtension>()
                url.set(extension.githubUser.flatMap { githubUser ->
                    extension.modeSimple.map { usesSimpleDocs ->
                        if (usesSimpleDocs) {
                            URL("https://$githubUser.github.io/${rootProject.name}/kdoc/${rootProject.name}/")
                        } else {
                            URL("https://$githubUser.github.io/${rootProject.name}/${rootProject.version}/kdoc/")
                        }
                    }
                })
            }
        }
        configurePlugins()
    }

    configure<ch.tutteli.gradle.plugins.publish.PublishPluginExtension> {
        resetLicenses("EUPL-1.2")
    }

    //TODO 1.1.0 remove relocation publications again
    val projectsWhichNoLongerContainLanguage = mapOf(
        "atrium-api-fluent" to "atrium-api-fluent-en_GB",
        "atrium-api-fluent-kotlin_1_3" to "atrium-api-fluent-en_GB-kotlin_1_3",
        "atrium-api-infix" to "atrium-api-infix-en_GB",
        "atrium-api-infix-kotlin_1_3" to "atrium-api-infix-en_GB-kotlin_1_3"
    )
    configure<PublishingExtension> {
        publications.withType<MavenPublication>()
            .matching { !it.name.endsWith("-relocation") }
            .all {
                val pub = this
                // we did not have a -metadata jar before, so no need for a relocation publication
                if (!artifactId.endsWith("metadata")) {
                    val oldProjectName = projectsWhichNoLongerContainLanguage[subproject.name] ?: subproject.name
                    val oldArtifactId = if (artifactId == subproject.name) {
                        // before the common platform had a suffix
                        "$oldProjectName-common"
                    } else if (artifactId.endsWith("-jvm")) {
                        // before the jvm platform was the artifact without suffix
                        oldProjectName
                    } else {
                        artifactId.replace(subproject.name, oldProjectName)
                    }
                    if (artifactId != oldArtifactId) {
                        publications.register<MavenPublication>("${pub.name}-relocation") {
                            pom {
                                // Old artifact coordinates
                                groupId = pub.groupId
                                artifactId = oldArtifactId
                                version = pub.version

                                distributionManagement {
                                    relocation {
                                        // New artifact coordinates
                                        groupId.set(pub.groupId)
                                        artifactId.set(pub.artifactId)
                                        version.set(pub.version)
                                        message.set(
                                            "artifactId has changed: " +
                                                if (projectsWhichNoLongerContainLanguage.contains(subproject.name)) " removed -en_GB as we drop support for a translated report /" else "" +
                                                    if (pub.artifactId == subproject.name || pub.artifactId.endsWith("-jvm")) " jvm has now the suffix -jvm and common no longer has the suffix -common" else ""
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
    }
}

tasks.withType<DokkaMultiModuleTask> {
    moduleName.set("Atrium")
    configurePlugins()

    // we want to be sure that we don't include spec in dokkaMultiModule
    dependsOn(prefixedProject("specs").tasks.getByName("cleanDokkaHtmlPartial"))
    dependsOn(prefixedProject("verbs-internal").tasks.getByName("cleanDokkaHtmlPartial"))
}
gradle.taskGraph.whenReady {
    if (hasTask(":dokkaHtmlMultiModule")) {
        listOf("specs", "verbs-internal").forEach { projectName ->
            prefixedProject(projectName).tasks.withType<DokkaTaskPartial> {
                enabled = false
            }
        }
    }
}


// TODO remove with 1.1.0 again
// we will publish a relocation for all jars we renamed in the past so that maintainers of projects using it will get
// that there is a newer version
listOf(
    "atrium-fluent-en_GB-android" to "atrium-fluent",
    "atrium-cc-infix-en_GB-robstoll-android" to "atrium-infix",
    "atrium-verbs-android" to "atrium-verbs",
    "atrium-api-cc-de_CH-android" to "atrium-api-fluent",
    "atrium-core-robstoll-android" to "atrium-core",
    "atrium-api-cc-infix-en_GB-android" to "atrium-api-infix",
    "atrium-core-robstoll-lib-android" to "atrium-core",
    "atrium-domain-api-android" to "atrium-logic",
    "atrium-domain-robstoll-lib-android" to "atrium-logic",
    "atrium-domain-robstoll-android" to "atrium-logic",
    "atrium-domain-api-kotlin_1_3-android" to "atrium-logic-kotlin_1_3",
    "atrium-api-fluent-en_GB-en_GB-kotlin_1_3-android" to "atrium-api-fluent-kotlin_1_3",
    "atrium-domain-builders-android" to "atrium-logic",
    "atrium-domain-robstoll-kotlin_1_3-android" to "atrium-logic",
    "atrium-domain-robstoll-lib-kotlin_1_3-android" to "atrium-logic",
    "atrium-cc-en_GB-robstoll-android" to "atrium-fluent",
    "atrium-cc-de_CH-robstoll-android" to "atrium-fluent",
    "atrium-api-cc-en_GB-android" to "atrium-api-fluent",
    "atrium-api-fluent-en_GB-android" to "atrium-api-fluent",
    "atrium-domain-builders-kotlin_1_3-android" to "atrium-logic",
    "atrium-verbs-internal-android" to "atrium-verbs-internal",
    "atrium-specs-android" to "atrium-specs",
    "atrium-api-fluent-en_GB-jdk8-android" to "atrium-api-fluent",
    "atrium-api-infix-en_GB-jdk8-common" to "atrium-api-infix",
    "atrium-api-infix-en_GB-jdk8" to "atrium-api-infix",
    "atrium-domain-builders-kotlin_1_3-common" to "atrium-logic",
    "atrium-cc-infix-en_UK-robstoll" to "atrium-infix",
    "atrium-api-fluent-en_GB-jdk8-common" to "atrium-api-fluent",
    "atrium-api-cc-infix-en_GB" to "atrium-api-infix",
    "atrium-cc-en_GB-robstoll" to "atrium-fluent",
    "atrium-cc-de_CH-robstoll" to "atrium-fluent",
    "atrium-api-cc-infix-en_GB-common" to "atrium-api-infix",
    "atrium-domain-api-kotlin_1_3-js" to "atrium-logic-kotlin_1_3",
    "atrium-domain-builders-kotlin_1_3-js" to "atrium-logic-kotlin_1_3",
    "atrium-api-fluent-en_GB-jdk8" to "atrium-api-fluent",
    "atrium-spec" to "atrium-specs",
    "atrium-api-cc-en_GB-common" to "atrium-api-fluent",
    "atrium-api-cc-infix-en_UK" to "atrium-api-infix",
    "atrium-domain-builders-kotlin_1_3" to "atrium-logic-kotlin_1_3",
    "atrium-domain-robstoll-lib-kotlin_1_3-js" to "atrium-logic-kotlin_1_3",
    "atrium-cc-infix-en_GB-robstoll-js" to "atrium-infix",
    "atrium-cc-en_GB-robstoll-common" to "atrium-fluent",
    "atrium-cc-en_GB-robstoll-js" to "atrium-fluent",
    "atrium-api-cc-de_CH" to "atrium-api-fluent",
    "atrium-api-cc-de_CH-common" to "atrium-api-fluent",
    "atrium-api-cc-infix-en_GB-js" to "atrium-api-infix",
    "atrium-domain-robstoll-kotlin_1_3" to "atrium-logic-kotlin_1_3",
    "atrium-api-cc-de_CH-js" to "atrium-api-fluent",
    "atrium-domain-api-kotlin_1_3-common" to "atrium-logic-kotlin_1_3",
    "atrium-domain-robstoll-kotlin_1_3-common" to "atrium-logic-kotlin_1_3",
    "atrium-assertions" to "atrium-logic",
    "atrium-cc-en_UK-robstoll" to "atrium-fluent",
    "atrium-cc-infix-en_GB-robstoll-common" to "atrium-infix",
    "atrium-cc-de_CH-robstoll-js" to "atrium-fluent",
    "atrium-api-cc-en_GB" to "atrium-api-fluent",
    "atrium-domain-robstoll-lib-kotlin_1_3-common" to "atrium-logic-kotlin_1_3",
    "atrium-cc-de_CH-robstoll-common" to "atrium-fluent",
    "atrium-domain-robstoll-lib-kotlin_1_3" to "atrium-logic",
    "atrium-api-cc-en_UK" to "atrium-fluent",
    "atrium-cc-infix-en_GB-robstoll" to "atrium-infix",
    "atrium-domain-api-kotlin_1_3" to "atrium-logic-kotlin_1_3",
    "atrium-domain-robstoll-kotlin_1_3-js" to "atrium-logic-kotlin_1_3",
    "atrium-api-cc-en_GB-js" to "atrium-fluent",
    "atrium-core-robstoll-lib" to "atrium-core",
    "atrium-core-robstoll-lib-js" to "atrium-core",
    "atrium-domain-robstoll-common" to "atrium-logic",
    "atrium-domain-api-js" to "atrium-logic",
    "atrium-domain-robstoll-lib-common" to "atrium-logic",
    "atrium-domain-builders-common" to "atrium-logic",
    "atrium-domain-api" to "atrium-logic",
    "atrium-core-robstoll-common" to "atrium-core",
    "atrium-core-api-js" to "atrium-core",
    "atrium-domain-builders" to "atrium-logic",
    "atrium-domain-robstoll" to "atrium-logic",
    "atrium-domain-api-common" to "atrium-logic",
    "atrium-core-api-common" to "atrium-core",
    "atrium-domain-robstoll-js" to "atrium-logic",
    "atrium-core-robstoll-js" to "atrium-core",
    "atrium-core-robstoll" to "atrium-core",
    "atrium-core-robstoll-lib-common" to "atrium-core",
    "atrium-core-api" to "atrium-core",
    "atrium-domain-robstoll-lib" to "atrium-logic"
).forEach { (oldArtifactId, projectName) ->
    val newPubName = if (oldArtifactId.endsWith("-common")) "kotlinMultiplatform"
    else if (oldArtifactId.endsWith("-js")) "js"
    else if (oldArtifactId.endsWith("-android")) "jvm"
    else "jvm"

    configure(listOf(project(":$projectName"))) {
        configure<PublishingExtension> {
            val pub = publications.getByName(newPubName) as MavenPublication
            publications.register<MavenPublication>("${oldArtifactId}-to-${pub.artifactId}-relocation") {
                pom {
                    // Old artifact coordinates
                    groupId = pub.groupId
                    artifactId = oldArtifactId
                    version = pub.version

                    distributionManagement {
                        relocation {
                            // New artifact coordinates
                            groupId.set(pub.groupId)
                            artifactId.set(pub.artifactId)
                            version.set(pub.version)
                            message.set("artifactId has changed")
                        }
                    }
                }
            }
        }
    }
}



configure(subprojectsWithoutToolAndSmokeTestProjects) {
// we don't specify a module-info.java in specs (so far)
    if (name != "atrium-specs") {
        apply(plugin = "ch.tutteli.gradle.plugins.kotlin.module.info")
    }
}

val jacocoAdditionalExtraName = "jacocoAdditional"
subprojects {
    val subproject = this
    extensions.findByType<JacocoPluginExtension>()?.apply {
        toolVersion = jacocoToolVersion
    }

    tasks.findByName("jacocoTestReport")?.let { task ->
        (task as JacocoReport).apply {

            if (subproject.extra.has(jacocoAdditionalExtraName)) {

                val additional = subproject.extra.get(jacocoAdditionalExtraName) as List<*>
                additional.forEach { p ->
                    val otherProject = p as Project
                    val kotlin = otherProject.extensions
                        .findByType<org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension>()
                    kotlin?.sourceSets?.filterNot { it.name.contains("Test") }?.forEach {
                        additionalSourceDirs(it.kotlin.sourceDirectories)
                    }
                }
            }

            reports {
                html.required.set(true)
            }
        }
    }
}

//TODO 2.0.0 remove if we use kotlin 1.7.x should no longer be necessary
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}

// takes some time to configure since gradle 6.9 so only if CI
if (java.lang.Boolean.parseBoolean(System.getenv("CI"))) {
    apply(from = "gradle/scripts/check-dexer.gradle")
}

configure(bundleSmokeTests) {
    apply(plugin = "ch.tutteli.gradle.plugins.spek")

    tasks.named<JavaCompile>("compileTestJava") {
        doFirst {
            options.compilerArgs.addAll(listOf("--module-path", classpath.asPath))
            classpath = files()
        }
    }
}

// TODO 1.5.0 reactivate and transform to Kotlin as soon as we tackle the scala API again
//def getSubprojectTasks(String name) {
//    return subprojects.collect { it.tasks.findByName(name) }.findAll { it != null }
//}
//
//task publishForScala(description: "fast publish to maven local for scala projects") {
//    dependsOn getSubprojectTasks("publishToMavenLocal")
//}
//
//gradle.taskGraph.whenReady { graph ->
//    if (graph.hasTask(":publishForScala")) {
//        ["test", "dokka", "signTutteliPublication", "validateBeforePublish", "javadocJar", "sourcesJar"].forEach {
//            getSubprojectTasks(it).forEach { it.enabled = false }
//        }
//    }
//}

nexusPublishing {
    repositories {
        sonatype()
    }
}

/*
Release & deploy a commit
--------------------------------

1. update main:

Either use the following commands or the manual steps below

export ATRIUM_PREVIOUS_VERSION=1.0.0
export ATRIUM_VERSION=1.1.0
find ./ -name "*.md" | xargs perl -0777 -i \
   -pe "s@$ATRIUM_PREVIOUS_VERSION@$ATRIUM_VERSION@g;" \
   -pe "s@tree/main@tree/v$ATRIUM_VERSION@g;" \
   -pe "s@latest#/doc@$ATRIUM_VERSION/doc@g;"
perl -0777 -i \
  -pe "s@$ATRIUM_PREVIOUS_VERSION@$ATRIUM_VERSION@g;" \
  -pe "s/rootProject.version = \"${ATRIUM_VERSION}-SNAPSHOT\"/rootProject.version = \"$ATRIUM_VERSION\"/;" \
  ./build.gradle.kts
perl -0777 -i \
  -pe 's/(<!-- for main -->\n)\n([\S\s]*?)(\n<!-- for a specific release -->\n)<!--\n([\S\s]*?)-->\n(\n# <img)/$1<!--\n$2-->$3\n$4\n$5/;' \
  -pe 's/(---\n❗ You are taking[^-]*?---)/<!$1>/;' \
  ./README.md
git commit -a -m "v$ATRIUM_VERSION"

check changes (CONTRIBUTING.md, difference.md, build.gradle.kts, README.md)
git push

alternatively the manual steps:

    a) change rootProject.version in build.gradle.kts to X.Y.Z
    b) search for old version in README.md and replace with new
    c) search for `tree/main` in all .md files and replace it with `tree/vX.Y.Z`
    d) search for `latest#/doc` in all .md files and replace with `X.Y.Z/doc`
    e) use the release badges in README (comment out the ones for main and uncomment the ones for the release)
    f) comment out the warning in README.md about taking a sneak peak
    g) commit & push (modified CONTRIBUTING.md, differences.md, build.gradle and README.md)

2. prepare release on github
    a) git tag "v$ATRIUM_VERSION"
    b) git push origin "v$ATRIUM_VERSION"
    c) Log in to github and create draft for the release

The tag is required for dokka in order that the externalLinkDocumentation and source-mapping works

3. update github pages:
Assumes you have a atrium-gh-pages folder on the same level as atrium where the gh-pages branch is checked out

Either use the following commands or the manual steps below (assuming ATRIUM_VERSION is already set from commands above)

export ATRIUM_GH_PAGES_LOGO_CSS_VERSION="1.2"
export ATRIUM_GH_PAGES_ALERT_CSS_VERSION="1.1"
export ATRIUM_GH_PAGES_VERSIONS_JS_VERSION="1.1.3"
export ATRIUM_GH_PAGES_VERSIONS_JS_VERSION_NEXT="1.2.0"

gr dokkaHtmlMultiModule
git add . && git commit -m "dokka generation for v$ATRIUM_VERSION"

cd ../atrium-gh-pages
perl -0777 -i \
  -pe "s@$ATRIUM_PREVIOUS_VERSION@$ATRIUM_VERSION@g;" \
  ./index.html
perl -0777 -i \
  -pe "s@$ATRIUM_PREVIOUS_VERSION@$ATRIUM_VERSION@g;" \
  ./latest/index.html
perl -0777 -i \
  -pe "s/(\s+)\"$ATRIUM_PREVIOUS_VERSION\",/\$1\"$ATRIUM_VERSION\",\$1\"$ATRIUM_PREVIOUS_VERSION\",/;" \
  ./scripts/versions.js
perl -0777 -i \
  -pe "s@(<div class=\"sideMenu\">)@\${1}\n <div class=\"sideMenuPart\" pageid=\"Atrium::.ext/allModules///PointingToDeclaration//0\"><div class=\"overview\"><a href=\"./\">All modules</a></div></div>@g;" \
  "./$ATRIUM_VERSION/kdoc/navigation.html"

find "./$ATRIUM_VERSION" -name "*.html" | xargs perl -0777 -i \
    -pe "s@\"((?:\.\./+)*)styles/logo-styles.css\" rel=\"Stylesheet\">@\"../../\${1}styles/logo-styles.css?v=$ATRIUM_GH_PAGES_LOGO_CSS_VERSION\" rel=\"Stylesheet\">\n<link href=\"../../\${1}styles/alert.css?v=$ATRIUM_GH_PAGES_ALERT_CSS_VERSION\" rel=\"Stylesheet\">\n<script id=\"versions-script\" type=\"text/javascript\" src=\"\../../\${1}scripts/versions.js?v=$ATRIUM_GH_PAGES_VERSIONS_JS_VERSION\" data-version=\"$ATRIUM_VERSION\" async=\"async\"></script>@g;" \
    -pe "s@((?:\.\./+)*)images/logo-icon.svg\"([^>]+)>@../../\${1}logo-icon.svg\"\$2>\n<meta name=\"og:image\" content=\"\${1}logo_social.png\"/>@g;" \
    -pe "s@(<div class=\"library-name\">[\s\n\r]+<a href=\"(?:\.\./+)*)index.html\">@\$1../../index.html\">@g;" \
    -pe "s@<html>@<html lang=\"en\">@g;" \
    -pe "s@<head>@<meta name=\"keywords\" content=\"Atrium, Kotlin, Expectation-library, Assertion-Library, Test, Testing, Multiplatform, better error reports, Code Documentation\">\n<meta name=\"author\" content=\"Robert Stoll\">\n<meta name=\"copyright\" content=\"Robert Stoll\">@g;" \
    -pe "s@<title>([^<]+)</title>@<title>\$1 - Atrium $ATRIUM_VERSION</title>\n<meta name=\"description\" content=\"Code documentation of Atrium $ATRIUM_VERSION: \$1\">@g;" \
    -pe "s@(<div class=\"library-name\">\n\s*<a href=\"[^\"]+)index.html\"@\${1}\" title=\"Back to Overview Code Documentation of Atrium\"@g;"

find "./" -name "*.html" | xargs perl -0777 -i \
    -pe "s@(scripts/versions\.js\?v\=)$ATRIUM_GH_PAGES_VERSIONS_JS_VERSION@\${1}$ATRIUM_GH_PAGES_VERSIONS_JS_VERSION_NEXT@g;"

cp "./$ATRIUM_PREVIOUS_VERSION/index.html" "./$ATRIUM_VERSION/index.html"
perl -0777 -i \
  -pe "s/$ATRIUM_PREVIOUS_VERSION/$ATRIUM_VERSION/g;" \
  -pe "s@Released .*</p>@Released $(date '+%b %d, %Y')</p>@;" \
  "./$ATRIUM_VERSION/index.html"
git add . && git commit -m "v$ATRIUM_VERSION"

check changes
git push

cd ../atrium

alternatively the manual steps:
    a) gr gr dokkaHtmlMultiModule
    b) change version number in atrium-gh-pages/index.html and atrium-gh-pages/latest/index.html
    c) add new version to atrium-gh-pages/scripts/versions.js
    d) replace logo-styles.css with own in root
    d) search and replace to add version drop down into the header
    e) commit & push changes

3. deploy to maven central:
(assumes you have an alias named gr pointing to ./gradlew)
    a) java -version 2>&1 | grep "version \"11" && CI=true gr clean publishToSonatype
    b) Log into https://oss.sonatype.org/#stagingRepositories
    c) check if staging repo is ok
    d) close repo
    e) release repo

4. publish release on github
    1) Log in to github and publish draft

Prepare next dev cycle
-----------------------
    1. update main:

Either use the following commands or the manual steps below

export ATRIUM_VERSION=1.0.0
export ATRIUM_NEXT_VERSION=1.1.0
find ./ -name "*.md" | xargs perl -0777 -i \
   -pe "s@tree/v$ATRIUM_VERSION@tree/main@g;" \
   -pe "s@$ATRIUM_VERSION/doc@latest#/doc@g;" \
   -pe "s/add \\\`\@since $ATRIUM_VERSION\\\` \(adapt to current/add \\\`\@since $ATRIUM_NEXT_VERSION\\\` \(adapt to current/g;"
perl -0777 -i \
  -pe "s/rootProject.version = \"$ATRIUM_VERSION\"/rootProject.version = \"${ATRIUM_NEXT_VERSION}-SNAPSHOT\"/;" \
  -pe "s/ATRIUM_VERSION=$ATRIUM_VERSION/ATRIUM_VERSION=$ATRIUM_NEXT_VERSION/;" \
  ./build.gradle.kts
perl -0777 -i \
  -pe 's/(<!-- for main -->\n)<!--\n([\S\s]*?)-->(\n<!-- for a specific release -->)\n([\S\s]*?)\n(\n# <img)/$1\n$2$3\n<!--$4-->\n$5/;' \
  -pe 's/<!(---\n❗ You are taking[^-]*?---)>/$1/;' \
  -pe "s@(latest version: \[README of v$ATRIUM_VERSION\].*tree/)main/@\$1v$ATRIUM_VERSION/@;" \
  ./README.md
git commit -a -m "prepare dev cycle of $ATRIUM_NEXT_VERSION"

check changes
git push

alternatively the manual steps:

    a) search for `tree/vX.Y.Z` in all .md and build.gradle files and replace it with `tree/v0.12.0`
b) search for `X.Y.Z/doc` in all .md files and replace with `latest#/doc`
   c) use the main badges in README (uncomment them in README and comment out release badges)
   d) uncomment the warning about taking a sneak peek in README and revert `tree/v0.12.0` still point to the tag
e) change rootProject.version in build.gradle to X.Y.Z-SNAPSHOT
f) commit & push changes

2. establish backward compatibility tests for the previous version
a) append new version to bcConfigs in settings.gradle.kts
b) git commit -a -m "establish backward compatibility tests for v$ATRIUM_VERSION"
c) commit & push changes

3. update samples (optional, since dependabot will create pull requests)
a) use newly released version in samples (search again for the old-version and replace with new)
b) commit & push changes

*/
