import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.IOException
import java.net.URL

buildscript {
    rootProject.version = "1.0.0-RC1"
    rootProject.group = "ch.tutteli.atrium"
}

// kotlin version is configured in buildSrc/build.gradle.kts
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
    id("org.jetbrains.dokka") version "1.8.10"
    val tutteliGradleVersion = "4.7.2"
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
        "api-fluent-en_GB", "api-fluent-en_GB-kotlin_1_3",
        "api-infix-en_GB", "api-infix-en_GB-kotlin_1_3",
        "fluent-en_GB",
        "infix-en_GB",
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

        //TODO 1.3.0 switch from LEGACY to IR once we output Kotlin > 1.4
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
        //TODO 1.1.0 remove -kotlin_1_3 (once we drop support for kotlin 1.2) and use 1.4 in tests
        val languageVersion = if (name.endsWith("Test") || project.name.endsWith("-kotlin_1_3")) "1.3" else "1.2"
        val apiVersion = if (name.endsWith("Test") || project.name.endsWith("-kotlin_1_3")) "1.3" else "1.2"
        languageSettings.apply {
            this.languageVersion = languageVersion
            this.apiVersion = apiVersion
            useExperimentalAnnotation("kotlin.Experimental")
        }
    }
}

configure(subprojects.filter {
    val parentName = it.projectDir.parentFile.name
    it.name != "bc-tests" && parentName != "old" && parentName != "bc-tests"
} - multiplatformProjects) {
    val subproject = this

    apply(plugin = "org.jetbrains.kotlin.jvm")

    //TODO 0.23.0 should no longer be necessary with kotlin 1.8.x where stdlib-jdk8 is added automatically
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

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
    sourceCompatibility = "11"
    targetCompatibility = "11"
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


configure(subprojectsWithoutToolAndSmokeTestProjects) {
    apply(plugin = "ch.tutteli.gradle.plugins.dokka")
    apply(plugin = "ch.tutteli.gradle.plugins.publish")

    configure<ch.tutteli.gradle.plugins.publish.PublishPluginExtension> {
        resetLicenses("EUPL-1.2")
    }

    val languageSuffixes = setOf("-en_GB", "-de_CH")
    if (languageSuffixes.any { name.contains(it) }) {
        configure<PublishingExtension> {
            publications.withType<MavenPublication>()
                .matching { !it.name.endsWith("-relocation") }
                .all {
                    val artifactIdBeforeLanguageRemoval = artifactId
                    val artifactIdWithoutLanguage = languageSuffixes.fold(artifactId) { id, suffix ->
                        id.replace(suffix, "")
                    }

                    // TODO 1.1.0 consider to remove en_GB from the project name
                    artifactId = artifactIdWithoutLanguage

                    //TODO 1.1.0 remove again, consider to rename the project-name and directory
                    // we did not have a -metadata jar before, so no need for a relocation publication
                    if (!artifactId.endsWith("metadata")) {
                        val oldArtifactId = if (artifactIdBeforeLanguageRemoval == project.name) {
                            "${project.name}-common"
                        } else if (artifactId.endsWith("-jvm")) {
                            project.name
                        } else {
                            artifactIdBeforeLanguageRemoval
                        }

                        val pub = this
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
                                        artifactId.set(artifactIdWithoutLanguage)
                                        version.set(pub.version)
                                        message.set("artifactId has changed, removed -en_GB as we drop support for a translated report and jvm has now the suffix -jvm and common no longer has the suffix -common")
                                    }
                                }
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

// TODO 0.22.0 reactivate and transform to Kotlin as soon as we tackle the scala API again
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

export ATRIUM_PREVIOUS_VERSION=1.0.0-RC1
export ATRIUM_VERSION=1.0.0-RC1
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

    a) change rootProject.version in build.gradle to X.Y.Z
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

gr dokkaHtmlMultiModule
cd ../atrium-gh-pages
perl -0777 -i \
  -pe "s@$ATRIUM_PREVIOUS_VERSION@$ATRIUM_VERSION@g;" \
  ./latest/index.html
perl -0777 -i \
  -pe "s@(- \[$ATRIUM_PREVIOUS_VERSION\]\($ATRIUM_PREVIOUS_VERSION\))@- \[$ATRIUM_VERSION\]\($ATRIUM_VERSION\)\n\$1@;" \
  ./README.md
git add . && git commit -m "v$ATRIUM_VERSION"

check changes
git push

cd ../atrium

alternatively the manual steps:
    a) gr ghPages
    b) change version number in atrium-gh-pages/latest/index.html
    c) add new version to atrium-gh-pages/README.md
    d) commit & push changes

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

export ATRIUM_VERSION=1.0.0-RC1
export ATRIUM_NEXT_VERSION=1.0.0
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
