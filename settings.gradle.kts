rootProject.name = "atrium"

buildscript {

    fun or(vararg patterns: String) =
        "(" + patterns.joinToString(prefix = "(", separator = ")|(", postfix = ")") + ")"

    val allTargets = listOf("common", "jvm", "js")
    val commonJvm = listOf("common", "jvm")
    //TODO 0.19.0 or 0.20.0 change to allTargets and remove commonJvm once we have transitioned everything to the new MPP plugin
    val allApisAllTargets = listOf("fluent-en_GB" to commonJvm, "infix-en_GB" to commonJvm)

    val bcConfigs: List<Triple<String, List<Pair<String, List<String>>>, Pair<String, Pair<Boolean, String>>>> = listOf()
    (gradle as ExtensionAware).extra.apply {
        apply {
            set("tutteli_plugins_version", "0.32.2")
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

includeBundleAndApisWithExtensionsAndSmokeTest("fluent-en_GB", "infix-en_GB")
val newMultiplatformPluginProjects = listOf("core")
newMultiplatformPluginProjects.forEach { name ->
    include("", "atrium-$name")
}
listOf("en_GB", "de_CH").forEach{ lang ->
    include("translations" ,"atrium-translations-$lang")
}

includeKotlinJvmJsWithExtensions("logic", "atrium-logic")

includeKotlinJvmJs("misc/specs", "atrium-specs")
includeKotlinJvmJs("misc/verbs", "atrium-verbs")
includeKotlinJvmJs("misc/verbs-internal", "atrium-verbs-internal")
include("misc/tools", "readme-examples")

fun Settings_gradle.includeBc(oldVersion: String, module: String) {
    val projectName = "$oldVersion-$module"
    include("bc-tests:$projectName")
    project(":bc-tests:$projectName").projectDir = file("$bcTestsOldPath/$projectName")
}

fun Settings_gradle.includeBundleAndApisWithExtensionsAndSmokeTest(vararg apiNames: String) {
    apiNames.forEach { apiName ->
        includeKotlinJvmJs("bundles/$apiName", "atrium-$apiName")
        if (JavaVersion.current() >= JavaVersion.VERSION_1_9) {
            include("bundles/$apiName/", "atrium-$apiName-smoke-test")
            include("bundles/$apiName/extensions", "atrium-$apiName-smoke-test-kotlin_1_3")
        }
        includeKotlinJvmJsWithExtensions("apis/$apiName", "atrium-api-$apiName")
    }
}

fun Settings_gradle.includeKotlinJvmJs(subPath: String, module: String) {
    include(subPath, "$module-common")
    include(subPath, "$module-jvm")
    //TODO 0.19.0 commented out because js makes trouble in migrating to new MPP
    // in the end, when all modules use the new MPP we should no longer need this extension function
    // js starts to be annoying on local development. Let's carry this only out on CI
    // if (System.getenv("CI") == "true") {
    //    include(subPath, "$module-js")
    // }
}

fun Settings_gradle.includeKotlinJvmJsWithExtensions(subPath: String, module: String) {
    includeKotlinJvmJs(subPath, module)
    includeKotlinJvmJs("$subPath/extensions/kotlin_1_3", "$module-kotlin_1_3")
}

fun Settings_gradle.include(subPath: String, projectName: String) {
    val dir = file("${rootProject.projectDir}/$subPath/$projectName")
    if (!dir.exists()) {
        throw GradleException("cannot include project $projectName as its projectDir $dir does not exist")
    }
    include(projectName)
    project(":$projectName").projectDir = dir
}
