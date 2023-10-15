pluginManagement {
    repositories {
//        mavenLocal()
        gradlePluginPortal()
    }
    includeBuild("gradle/build-logic")
    includeBuild("gradle/build-logic-conventions")
}

dependencyResolutionManagement {
    // kotlinNodeJsSetup configures ivy repository 'Node Distributions at https://nodejs.org/dist'
    // repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        google()
    }
}

plugins {
    if (System.getenv("CI").toBoolean()) {
        id("org.gradle.toolchains.foojay-resolver-convention") version ("0.4.0")
    }
}

rootProject.name = "atrium"

listOf("fluent", "infix").forEach { apiName ->
    include("bundles/$apiName", "atrium-$apiName")
    include("bundles/$apiName/smoke-tests", "atrium-$apiName-smoke-test")
    include("bundles/$apiName/smoke-tests", "atrium-$apiName-smoke-test-kotlin_1_3")
    include("apis/$apiName", "atrium-api-$apiName")
    include("apis/$apiName/extensions", "atrium-api-$apiName-kotlin_1_3")
}

include("", "atrium-core")
include("logic", "atrium-logic")
include("logic/extensions", "atrium-logic-kotlin_1_3")

listOf("en_GB", "de_CH").forEach { lang ->
    include("translations", "atrium-translations-$lang")
}

include("misc", "atrium-verbs")
include("misc", "atrium-verbs-internal")
include("misc", "atrium-specs")
include("misc/tools", "readme-examples")

fun Settings_gradle.include(subPath: String, projectName: String) {
    val dir = file("${rootProject.projectDir}/$subPath/$projectName")
    if (!dir.exists()) {
        throw GradleException("cannot include project $projectName as its projectDir $dir does not exist")
    }
    include(projectName)
    project(":$projectName").projectDir = dir
}
