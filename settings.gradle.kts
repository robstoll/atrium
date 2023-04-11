//pluginManagement {
//    repositories {
//        mavenLocal()
//        gradlePluginPortal()
//    }
//}

rootProject.name = "atrium"

buildscript {

    fun or(vararg patterns: String) =
        "(" + patterns.joinToString(prefix = "(", separator = ")|(", postfix = ")") + ")"

    val allTargets = listOf("common", "jvm", "js")
    val commonJvm = listOf("common", "jvm")

    val bcConfigs: List<Triple<String, List<Pair<String, List<String>>>, Pair<String, Pair<Boolean, String>>>> = listOf()
    (gradle as ExtensionAware).extra.apply {
        apply {
            set("bcConfigs", bcConfigs)
        }
    }
}

val bcTestsPath = "${rootProject.projectDir}/misc/tools/bc-tests"
val bcTestsOldPath = "$bcTestsPath/old"

// comment `if` out, if you want to modify stuff via IDE (e.g. see compile errors in Intellij, debug etc.)
if (System.getenv("BC") != null) {
    include("bc-tests:test-engine")
    project(":bc-tests").projectDir = file(bcTestsPath)
    project(":bc-tests:test-engine").projectDir = file("$bcTestsPath/test-engine")

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
    bcConfigs.forEach { (oldVersion, apis, pair) ->
        val (_, bbcPair) = pair
        val (withBbc, _) = bbcPair
        includeBc(oldVersion, "specs")
        apis.forEach { (apiName, _) ->
            includeBc(oldVersion, "api-$apiName")
            if (withBbc) {
                includeBc(oldVersion, "api-$apiName-bbc")
                val projectName = "$oldVersion-api-$apiName-bbc"
                file("$bcTestsOldPath/$projectName/").mkdirs()
            }
        }
    }
}

listOf("fluent-en_GB", "infix-en_GB").forEach { apiName ->
    include("bundles/$apiName", "atrium-$apiName")
    include("bundles/$apiName/smoke-tests", "atrium-$apiName-smoke-test")
//    include("bundles/$apiName/smoke-tests", "atrium-$apiName-smoke-test-kotlin_1_3")
    include("apis/$apiName",  "atrium-api-$apiName")
    include("apis/$apiName/extensions", "atrium-api-$apiName-kotlin_1_3")
}

include("", "atrium-core")
include("logic", "atrium-logic")
include("logic/extensions", "atrium-logic-kotlin_1_3")

listOf("en_GB", "de_CH").forEach{ lang ->
    include("translations" ,"atrium-translations-$lang")
}

include("misc", "atrium-verbs")
include("misc", "atrium-verbs-internal")
include("misc", "atrium-specs")
include("misc/tools", "readme-examples")

fun Settings_gradle.includeBc(oldVersion: String, module: String) {
    val projectName = "$oldVersion-$module"
    include("bc-tests:$projectName")
    project(":bc-tests:$projectName").projectDir = file("$bcTestsOldPath/$projectName")
}

fun Settings_gradle.include(subPath: String, projectName: String) {
    val dir = file("${rootProject.projectDir}/$subPath/$projectName")
    if (!dir.exists()) {
        throw GradleException("cannot include project $projectName because its projectDir $dir does not exist")
    }
    include(projectName)
    project(":$projectName").projectDir = dir
}
