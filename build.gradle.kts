import ch.tutteli.gradle.plugins.publish.PublishPlugin
import ch.tutteli.gradle.plugins.publish.PublishPluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import java.nio.file.Files
import java.nio.file.Paths
buildscript {
    rootProject.version = "0.19.0-SNAPSHOT"
    rootProject.group = "ch.tutteli.atrium"
}

// main
val kboxVersion by extra("0.16.0")
val niokVersion by extra("1.4.7")

// test
val jacocoToolVersion by extra("0.8.8")
val junitPlatformVersion by extra("1.9.2")
val jupiterVersion by extra("5.9.2")
val spekVersion by extra("2.0.12")
val kotestVersion by extra("4.6.4")
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
    id("org.jetbrains.dokka") version "1.7.20"
    val tutteliGradleVersion = "4.6.0-SNAPSHOT"
//    val tutteliGradleVersion = "4.5.1"
    id("ch.tutteli.gradle.plugins.dokka") version tutteliGradleVersion
    id("ch.tutteli.gradle.plugins.project.utils") version tutteliGradleVersion
    id("ch.tutteli.gradle.plugins.publish") version tutteliGradleVersion apply false
    id("io.github.gradle-nexus.publish-plugin") version "1.2.0"
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
    multiplatformProjectNames.map { prefixedProject(it) }.toSet()
)
val toolProjects by extra(subprojects.filter {
    it.projectDir.path.contains("/misc/tools/") || it.projectDir.path.contains("\\misc\\tools\\")
}.toSet())

val subprojectsWithoutToolProjects = subprojects - toolProjects

//TODO 0.19.0 it would maybe make more sense to have a dokka page per api and one for all types underneath
val docProjects by extra(subprojectsWithoutToolProjects.filter {
    !it.name.startsWith("${rootProject.name}-specs")
}.toSet())

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
    }
}

configure(subprojectsWithoutToolProjects) {
    apply(plugin = "ch.tutteli.gradle.plugins.dokka")

    // we don't specify a module-info.java in specs (so far)
    if (name != "atrium-specs") {
        apply(plugin = "ch.tutteli.gradle.plugins.kotlin.module.info")
    }

    // we want to configure DokkaTask as well as DokkaPartialTask
    tasks.withType<org.jetbrains.dokka.gradle.AbstractDokkaLeafTask>().configureEach {
        dokkaSourceSets.configureEach {
            val sourceSet = this
            if (sourceSet.name.endsWith("Main")) {
                val files =
                    fileTree(project.projectDir.resolve("src/${sourceSet.name.substringBeforeLast("Main")}Test/kotlin")) {
                        include("**/*Samples.kt")
                    }
                //TODO 0.19.0 I think this does not work yet
                samples.from(files)
            }
        }

    }

}

configure(multiplatformProjects) {

    apply(plugin = "kotlin-multiplatform")
//    apply(plugin = "ch.tutteli.gradle.plugins.spek")

//    the<ch.tutteli.gradle.plugins.junitjacoco.JunitJacocoPluginExtension>()
//        .allowedTestTasksWithoutTests.set(listOf("jsNodeTest"))

    with(the<KotlinMultiplatformExtension>()) {

        jvm {
            //for module-info.java and Java sources in test
            withJava()

            testRuns["test"].executionTask.configure {
                useJUnitPlatform {
                    includeEngines("spek2", "junit-jupiter", "kotest")
                }
            }
        }

        //TODO 0.21.0 switch from LEGACY to IR once we output Kotlin > 1.4
        js(LEGACY) { nodejs() }

        sourceSets {

            configureLanguageSettings()

            val commonMain by getting {
                dependencies {
                    // TODO 0.20.0 shouldn't be necessary to add stdlib dependency to kotlin with kotlin 1.5.x (is automatically added)
                    api(kotlin("stdlib-common"))
                    api(kotlin("reflect"))
                }
            }
            val commonTest by getting {
                dependencies {
                    // TODO 0.20.0 switch to kotlin(test) with update to kotlin > 1.4
                    implementation(kotlin("test-common"))
                    implementation(kotlin("test-annotations-common"))
                }
            }
            val jvmMain by getting {
                dependencies {
                    // TODO 0.20.0 shouldn't be necessary to add the dependency to kotlin with kotlin 1.5.x (is automatically added, but check, maybe stdlib is added automatically but not stdlib-jdk8)
                    api(kotlin("stdlib-jdk8"))
                }
            }
            val jvmTest by getting {
                dependencies {
                    // TODO  0.20.0 should no longer be necessary with kotlin 1.5, adding kotlin("test") to common should be enough
                    implementation(kotlin("test-junit5"))
                    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:$jupiterVersion")
                    implementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion") {
                        exclude(group = "org.jetbrains.kotlin")
                    }
                    runtimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion") {
                        exclude(group = "org.jetbrains.kotlin")
                    }
                }
            }
            val jsMain by getting {
                dependencies {
                    // TODO 0.20.0 shouldn't be necessary to add the dependency to kotlin with kotlin 1.5.x
                    api(kotlin("stdlib-js"))
                }
            }
            val jsTest by getting {
                dependencies {
                    // TODO  0.20.0 should no longer be necessary with kotlin 1.5, adding kotlin("test") to common should be enough
                    implementation(kotlin("test-js"))
                }
            }
        }
    }
}

fun NamedDomainObjectContainerScope<KotlinSourceSet>.configureLanguageSettings() {
    configureEach {
        //TODO 0.20.0 remove -kotlin_1_3 (or once we drop support for kotlin 1.2)
        val languageVersion = if (name.endsWith("Test") || name.endsWith("-kotlin_1_3")) "1.3" else "1.2"
        val apiVersion = if (name.endsWith("Test")) "1.3" else "1.2"
        with(languageSettings) {
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
    apply(plugin = "kotlin-jvm")

    //TODO 0.23.0 should no longer be necessary with kotlin 1.8.x where stdlib-jdk8 is added automatically
    with(the<KotlinJvmProjectExtension>()) {
        sourceSets {
            configureLanguageSettings()

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

val apiProjects = subprojects.filter {
    it.name.startsWith("${rootProject.name}-api")
}

//TODO 0.20.0 re-introduce bcTests again
configure(apiProjects) {
//    createTestJarTask(apiProject)
//    createTestSourcesJarTask(apiProject)
}

val bundleSmokeTests = subprojects.filter {
    it.name.contains("-smoke-test")
}.toSet()

//TODO 0.19.0 check artifacts before publishing
configure(subprojects - bundleSmokeTests - toolProjects) {
    apply(plugin = "ch.tutteli.gradle.plugins.publish")

//    with(the<PublishPluginExtension>()) {
//        resetLicenses("EUPL-1.2")
//    }  with(the<PublishPluginExtension>()) {
//        resetLicenses("EUPL-1.2")
//    }
}


//        // jacoco-multi-project.gradle
//        jacocoMulti = [
//            sourceProjects:
//                (subprojects - toolProjectsFun).findAll {
//                    !it.name.endsWith("-js") &&
//                        // would have two classes with the same name if we add it as project as well,
//                        // (clashes with "${rootProject.name}-translations-en_GB"
//                        it.name != "${rootProject.name}-translations-de_CH" &&
//                        // does not make sense to listen specs in coverage
//                        !it.name.startsWith("${rootProject.name}-specs") &&
//                        !it.name.contains("smoke-test")
//
//                },
//            jacocoProjects:
//                (subprojects - toolProjectsFun).findAll {
//                    !(it.projectDir.path.contains("/translations/") || it.projectDir.path.contains("\\translations\\")) &&
//                        !it.name.endsWith("-common") &&
//                        !it.name.endsWith("-js") &&
//                        !it.name.startsWith("${rootProject.name}-specs")
//                }
//        ]
//        prefixedProject = { String name ->
//            ExtensionsKt.prefixedProject(project, name)
//        }
//    }
//

//
//
////TODO 0.19.0 I guess this is still a problem when realising. Check new modules
////configure(jsProjects) { subProject ->
////    compileKotlin2Js {
////        kotlinOptions {
////            if (subProject.name.startsWith("atrium-translations")) {
////                //necessary as the module name is then also called atrium-translations-js and can be shared (the name) by the other translation modules
////                outputFile = "$buildDir/classes/kotlin/main/atrium-translations-js.js"
////            }
////            languageVersion = "1.2"
////        }
////    }
////
////    compileTestKotlin2Js {
////        //TODO activate as soon as https://youtrack.jetbrains.com/issue/KT-21348 is fixed
////        kotlinOptions.allWarningsAsErrors = false
////    }
////}
//
//
//


//
////TODO 0.19.0 spek is used in another way, see above, but we have not yet applied the jacoco plugin
//configure(jacocoMulti.jacocoProjects - multiplatformProjects) {
//    apply plugin: "ch.tutteli.gradle.plugins.spek"
//    spek.version = spekVersion
//
//    //TODO 0.19.0 remove once we update to new tutteli-gradle-plugins
//    // we don't want to have a dependency on dl.bintray anymore (the current version of ch.tutteli.spek (which is old)
//    // still adds it for convenience reasons.
//    repositories.removeAll {
//        it instanceof MavenArtifactRepository && (it as MavenArtifactRepository).url.toString().startsWith("https://dl.bintray.com")
//    }
////
////    dependencies {
////        testImplementation "com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoKotlinVersion", excludeKotlin
////    }
//
//    afterEvaluate {
//        junitjacoco {
//
//            jacoco {
//                toolVersion = jacocoToolVersion
//            }
//
//            jacocoTestReport {
//                if (project.hasProperty("jacocoAdditional")) {
//                    project.jacocoAdditional.each { otherProject ->
//                        sourceSets otherProject.sourceSets.main
//                    }
//                }
//                reports {
//                    html.enabled = true
//                }
//            }
//        }
//    }
//}
//
//
//// this is already configured for multiplatformProjects further above, thus the substraction
//configure(subprojects - toolProjects - multiplatformProjects) {
//    sourceSets.configureEach {
//        languageSettings {
//            useExperimentalAnnotation("kotlin.Experimental")
//        }
//    }
//}
//
////TODO 0.21.0 remove if we use kotlin 1.7.x should no longer be necessary
//tasks.withType(KotlinCompile).configureEach {
//    kotlinOptions {
//        freeCompilerArgs = ["-Xopt-in=kotlin.RequiresOptIn"]
//    }
//}
//
//// takes some time to configure since gradle 6.9 so only if CI
//if (Boolean.parseBoolean(System.getenv("CI"))) {
//    apply from: "gradle/scripts/check-dexer.gradle"
//}
////apply from: "gradle/scripts/gh-pages.gradle"
//apply from: "gradle/scripts/jacoco-multi-project.gradle"
//
//configure(bundleSmokeTests) {
//
//    def suffix = "-smoke-test"
//    def isABundleAndNotExtensionSmokeTest = it.name.endsWith(suffix)
//    if (isABundleAndNotExtensionSmokeTest) {
//        def bundleUnderTest = it.name.substring(0, it.name.indexOf(suffix))
//        Project bundle = project(":$bundleUnderTest")
//
//        description = "Represents a JDK >= 9 smoke test for $bundleUnderTest"
//
//        sourceCompatibility = JavaVersion.current()
//        targetCompatibility = JavaVersion.current()
//
//        ext.jacocoAdditional = [bundle]
//
//        sourceSets {
//            // we are reusing the source from the bundle, so that we do not have to re-invent the spec
//            test { kotlin { srcDirs += ["${bundle.projectDir}/src/test/kotlin"] } }
//        }
//
//        dependencies {
//            //I don't see how to set up compileTestKotlin with --patch-module, so we have put the module-info.java directly in src/test/kotlin instead
//            testImplementation bundle
//        }
//    }
//}
//
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
//
//apply plugin: "io.github.gradle-nexus.publish-plugin"
//nexusPublishing {
//    repositories {
//        sonatype()
//    }
//}
//
///*
//Release & deploy a commit
//--------------------------------
//
//1. update main:
//
//Either use the following commands or the manual steps below
//
//export ATRIUM_PREVIOUS_VERSION=0.18.0
//export ATRIUM_VERSION=0.19.0
//find ./ -name "*.md" | xargs perl -0777 -i \
//   -pe "s@$ATRIUM_PREVIOUS_VERSION@$ATRIUM_VERSION@g;" \
//   -pe "s@tree/main@tree/v$ATRIUM_VERSION@g;" \
//   -pe "s@latest#/doc@$ATRIUM_VERSION/doc@g;"
//perl -0777 -i \
//  -pe "s@$ATRIUM_PREVIOUS_VERSION@$ATRIUM_VERSION@g;" \
//  -pe "s@dokka_sourceMapping = \"tree/main\"@dokka_sourceMapping = \"tree/v$ATRIUM_VERSION\"@;" \
//  -pe "s/rootProject.version = '$ATRIUM_VERSION-SNAPSHOT'/rootProject.version = '$ATRIUM_VERSION'/;" \
//  ./build.gradle
//perl -0777 -i \
//  -pe 's/(<!-- for main -->\n)\n([\S\s]*?)(\n<!-- for a specific release -->\n)<!--\n([\S\s]*?)-->\n(\n# <img)/$1<!--\n$2-->$3\n$4\n$5/;' \
//  -pe 's/(---\n❗ You are taking[^-]*?---)/<!$1>/;' \
//  ./README.md
//git commit -a -m "v$ATRIUM_VERSION"
//
//check changes
//git push
//
//alternatively the manual steps:
//
//    a) change rootProject.version in build.gradle to X.Y.Z
//    b) search for old version in README.md and replace with new
//    c) search for `tree/main` in all .md files and replace it with `tree/vX.Y.Z`
//    d) adjust dokka_sourceMapping from `tree/main` to tree/vX.Y.Z
//    e) search for `latest#/doc` in all .md files and replace with `X.Y.Z/doc`
//    f) use the release badges in README (comment out the ones for main and uncomment the ones for the release)
//    g) comment out the warning in README.md about taking a sneak peak
//    h) commit & push (modified CONTRIBUTING.md, differences.md, build.gradle and README.md)
//
//2. prepare release on github
//    a) git tag "v$ATRIUM_VERSION"
//    b) git push origin "v$ATRIUM_VERSION"
//    c) Log in to github and create draft for the release
//
//The tag is required for dokka in order that the externalLinkDocumentation and source-mapping works
//
//3. update github pages:
//Assumes you have a atrium-gh-pages folder on the same level as atrium where the gh-pages branch is checked out
//
//Either use the following commands or the manual steps below (assuming ATRIUM_VERSION is already set from commands above)
//
//gr ghPages
//cd ../atrium-gh-pages
//perl -0777 -i \
//  -pe "s@$ATRIUM_PREVIOUS_VERSION@$ATRIUM_VERSION@g;" \
//  ./latest/index.html
//perl -0777 -i \
//  -pe "s@(- \[$ATRIUM_PREVIOUS_VERSION\]\($ATRIUM_PREVIOUS_VERSION\))@- \[$ATRIUM_VERSION\]\($ATRIUM_VERSION\)\n\$1@;" \
//  ./README.md
//git add . && git commit -m "v$ATRIUM_VERSION"
//
//check changes
//git push
//
//cd ../atrium
//
//alternatively the manual steps:
//    a) gr ghPages
//    b) change version number in atrium-gh-pages/latest/index.html
//    c) add new version to atrium-gh-pages/README.md
//    d) commit & push changes
//
//3. deploy to maven central:
//(assumes you have an alias named gr pointing to ./gradlew)
//    a) java -version 2>&1 | grep "version \"11" && CI=true gr clean publishToSonatype
//    b) Log into https://oss.sonatype.org/#stagingRepositories
//    c) check if staging repo is ok
//    d) close repo
//    e) release repo
//
//4. publish release on github
//    1) Log in to github and publish draft
//
//Prepare next dev cycle
//-----------------------
//    1. update main:
//
//Either use the following commands or the manual steps below
//
//export ATRIUM_VERSION=0.18.0
//export ATRIUM_NEXT_VERSION=0.19.0
//find ./ -name "*.md" | xargs perl -0777 -i \
//   -pe "s@tree/v$ATRIUM_VERSION@tree/main@g;" \
//   -pe "s@$ATRIUM_VERSION/doc@latest#/doc@g;" \
//   -pe "s/add \\\`\@since $ATRIUM_VERSION\\\` \(adapt to current/add \\\`\@since $ATRIUM_NEXT_VERSION\\\` \(adapt to current/g;"
//perl -0777 -i \
//  -pe "s@dokka_sourceMapping = \"tree/v$ATRIUM_VERSION\"@dokka_sourceMapping = \"tree/main\"@;" \
//  -pe "s/rootProject.version = '$ATRIUM_VERSION'/rootProject.version = '$ATRIUM_NEXT_VERSION-SNAPSHOT'/;" \
//  -pe "s/ATRIUM_VERSION=$ATRIUM_VERSION/ATRIUM_VERSION=$ATRIUM_NEXT_VERSION/;" \
//  ./build.gradle
//perl -0777 -i \
//  -pe 's/(<!-- for main -->\n)<!--\n([\S\s]*?)-->(\n<!-- for a specific release -->)\n([\S\s]*?)\n(\n# <img)/$1\n$2$3\n<!--$4-->\n$5/;' \
//  -pe 's/<!(---\n❗ You are taking[^-]*?---)>/$1/;' \
//  -pe "s@(For instance, the \[README of v$ATRIUM_VERSION\].*tree/)main/@\$1v$ATRIUM_VERSION/@;" \
//  ./README.md
//git commit -a -m "prepare dev cycle of $ATRIUM_NEXT_VERSION"
//
//check changes
//git push
//
//alternatively the manual steps:
//
//    a) search for `tree/vX.Y.Z` in all .md and build.gradle files and replace it with `tree/v0.12.0`
//b) search for `X.Y.Z/doc` in all .md files and replace with `latest#/doc`
//   c) use the main badges in README (uncomment them in README and comment out release badges)
//   d) uncomment the warning about taking a sneak peek in README and revert `tree/v0.12.0` still point to the tag
//e) change rootProject.version in build.gradle to X.Y.Z-SNAPSHOT
//f) commit & push changes
//
//2. establish backward compatibility tests for the previous version
//a) append new version to bcConfigs in settings.gradle.kts
//b) git commit -a -m "establish backward compatibility tests for v$ATRIUM_VERSION"
//c) commit & push changes
//
//3. update samples (optional, since dependabot will create pull requests)
//a) use newly released version in samples (search again for the old-version and replace with new)
//b) commit & push changes
//
//*/
