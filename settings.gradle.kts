rootProject.name = "atrium"

buildscript {
    val allTargets = listOf("common", "jvm", "js")
    val commonJvm = listOf("common", "jvm")
    //TODO 0.16.0 change to allTargets and remove commonJvm once we have transitioned everything to the new MPP plugin
    val allApisAllTargets = listOf("fluent-en_GB" to commonJvm, "infix-en_GB" to commonJvm)

    val bcConfigs = listOf(
        Triple(
            "0.14.0",
            allApisAllTargets,
            /* includingBbc= */ false to
                "(ch/tutteli/atrium/api/(fluent|infix)/en_GB/(" +
                "(IterableContainsInOrderOnly.*Spec)|" +
                "(MapAssertionsSpec.*)" +
                ").*)"
        ),
        Triple(
            "0.15.0",
            allApisAllTargets,
            /* includingBbc= */  false to
                "^$"
        )
    )
    (gradle as ExtensionAware).extra.apply {
        apply {
            set("tutteli_plugins_version", "0.32.2")
            set("bcConfigs", bcConfigs)
        }
    }
}

// comment `if` out, if you want to modify stuff via IDE (e.g. see compile errors in Intellij
if (System.getenv("BC") != null) {
    include("bc-tests:test-engine")
    project(":bc-tests").projectDir = file("${rootProject.projectDir}/misc/tools/bc-tests")
    project(":bc-tests:test-engine").projectDir = file("${rootProject.projectDir}/misc/tools/bc-tests/test-engine")

    @Suppress("UNCHECKED_CAST")
    val bcConfigs =
        (gradle as ExtensionAware).extra.get("bcConfigs") as List<Triple<String, List<Pair<String, List<String>>>, Pair<Boolean, String>>>
    bcConfigs.forEach { (oldVersion, apis, _) ->
        includeBc(oldVersion, "specs")
        apis.forEach { (apiName, _) ->
            includeBc(oldVersion, "api-$apiName")
        }
    }
}

includeBundleAndApisWithExtensionsAndSmokeTest("fluent-en_GB", "infix-en_GB")
includeKotlinJvmJs("core/api", "atrium-core-api")
includeKotlinJvmJs("core/robstoll", "atrium-core-robstoll")
includeKotlinJvmJs("core/robstoll-lib", "atrium-core-robstoll-lib")

includeKotlinJvmJsWithExtensions("logic", "atrium-logic")

includeKotlinJvmJs("misc/specs", "atrium-specs")
includeKotlinJvmJs("misc/verbs", "atrium-verbs")
includeKotlinJvmJs("misc/verbs-internal", "atrium-verbs-internal")
include("misc/tools", "readme-examples")

includeKotlinJvmJs("translations/de_CH", "atrium-translations-de_CH")
includeKotlinJvmJs("translations/en_GB", "atrium-translations-en_GB")

//TODO 0.17.0 remove
includeKotlinJvmJs("misc/deprecated/domain/api", "atrium-domain-api")
includeKotlinJvmJs("misc/deprecated/domain/robstoll", "atrium-domain-robstoll")
includeKotlinJvmJs("misc/deprecated/domain/robstoll-lib", "atrium-domain-robstoll-lib")
includeKotlinJvmJs("misc/deprecated/domain/builders", "atrium-domain-builders")

fun Settings_gradle.includeBc(oldVersion: String, module: String) {
    val projectName = "$oldVersion-$module"
    include("bc-tests:$projectName")
    project(":bc-tests:$projectName").projectDir = file("misc/tools/bc-tests/old/$projectName")
}

fun Settings_gradle.includeBundleAndApisWithExtensionsAndSmokeTest(vararg apiNames: String) {
    apiNames.forEach { apiName ->
        includeKotlinJvmJs("bundles/$apiName", "atrium-$apiName")
        if (JavaVersion.current() >= JavaVersion.VERSION_1_9) {
            include("bundles/$apiName/", "atrium-$apiName-smoke-test")
            includeKotlinJvmJs("bundles/$apiName/extensions/kotlin_1_3", "atrium-$apiName-smoke-test-kotlin_1_3")
        }
        includeKotlinJvmJsWithExtensions("apis/$apiName", "atrium-api-$apiName")
    }
}

fun Settings_gradle.includeKotlinJvmJs(subPath: String, module: String) {
    include(subPath, "$module-common")
    include(subPath, "$module-js")
    include(subPath, "$module-jvm")
}

fun Settings_gradle.includeKotlinJvmJsWithExtensions(subPath: String, module: String) {
    includeKotlinJvmJs(subPath, module)
    includeKotlinJvmJs("$subPath/extensions/kotlin_1_3", "$module-kotlin_1_3")
}

fun Settings_gradle.include(subPath: String, projectName: String) {
    include(projectName)
    project(":$projectName").projectDir = file("${rootProject.projectDir}/$subPath/$projectName")
}
