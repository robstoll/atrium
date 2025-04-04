rootProject.name = "atrium"

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
        id("org.gradle.toolchains.foojay-resolver-convention") version ("0.9.0")
    }
}


listOf("fluent" to "fluent", "fluent" to "fluent-java", "infix" to "infix").forEach { (folder, apiName) ->
    include("bundles/$folder", "atrium-$apiName")
    include("bundles/$folder/smoke-tests", "atrium-$apiName-smoke-test")
    include("apis/$folder", "atrium-api-$apiName")
}

include("", "atrium-core")
include("logic", "atrium-logic")

listOf("en_GB").forEach { lang ->
    include("translations", "atrium-translations-$lang")
}

include("misc", "atrium-specs")
include("misc", "atrium-test-factory")
include("misc", "atrium-verbs")
include("misc", "atrium-verbs-internal")
include("misc", "js-stubs")
include("misc/tools", "java-api-generator")
include("misc/tools", "readme-examples")

// for the future when we provide a compiler plugin to improve `its`
//include("misc/tools", "compiler-plugin")
//includeBuild("misc/tools/compiler-plugin-gradle-plugin")

fun Settings_gradle.include(subPath: String, projectName: String) {
    val dir = file("${rootProject.projectDir}/$subPath/$projectName")
    if (!dir.exists()) {
        throw GradleException("cannot include project $projectName as its projectDir $dir does not exist")
    }
    include(projectName)
    project(":$projectName").projectDir = dir
}
