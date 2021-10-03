rootProject.name = "atrium"

buildscript {

    fun or(vararg patterns: String) =
        "(" + patterns.joinToString(prefix = "(", separator = ")|(", postfix = ")") + ")"

    val allTargets = listOf("common", "jvm", "js")
    val commonJvm = listOf("common", "jvm")
    //TODO 0.18.0 or 0.18.0 change to allTargets and remove commonJvm once we have transitioned everything to the new MPP plugin
    val allApisAllTargets = listOf("fluent-en_GB" to commonJvm, "infix-en_GB" to commonJvm)

    val bcConfigs = listOf(
        Triple(
            "0.14.0",
            allApisAllTargets,
            // forgive for bc and bbc
            or(
                "(ch/tutteli/atrium/api/(fluent|infix)/en_GB/" +
                    or(
                        // improved reporting
                        "IterableContainsInOrderOnly.*Spec",
                        "IterableContainsInAnyOrder.*error cases",
                        "IterableContainsInAnyOrderOnlyEntriesAssertionsSpec.*nullable cases",
                        // implementation and spec was wrong
                        "IterableAssertionsSpec/.*`" + or(
                            "containsNoDuplicates",
                            "contains noDuplicates"
                        ) + "`/list with duplicates",
                        // changed reporting as most of it is no longer based on IterableLike.contains
                        "MapAssertionsSpec",
                        "BigDecimalAssertionsSpec.*overload throws PleaseUseReplacementException",
                        // we renamed containsNot to notToContain with 0.17.0
                        "CharSequenceContains.*Spec.*points to containsNot",
                        "IterableContains.*Spec.*points to containsNot",
                        // we improved reporting for notToContain with 0.17.0
                        "IterableContainsNot(Entries|Values)AssertionsSpec.*`containsNot( nullable)?`.*throws AssertionError",
                        "IterableNoneAssertionsSpec.*`(none|containsNot)( nullable)?`.*throws AssertionError",
                        // changed reporting for contains.atLeast(1) with 0.17.0
                        or(
                            "(CharSequence|Iterable)Contains.*Spec",
                            "IterableAnyAssertionsSpec"
                        ) + ".*`.*(any|contains).*`.*(throws.*AssertionError|failing cases)",
                        // changed reporting for Iterable.all empty collection cases with 0.17.0
                        "IterableAllAssertionsSpec.*" + "empty collection.*" + "throws AssertionError"
                    ) + ".*)",
                // we don't use asci bullet points in reporting since 0.17.0
                // but have own tests to assure that changing bullet points work
                "(ch/tutteli/atrium/api/infix/en_GB/.*Spec.*" + or(
                    "throws",
                    "is thrown",
                    "error message contains",
                    "shows (all )?suppressed",
                    "null was missing", "failing cases"
                ) + ".*)"
            ).let { commonPatterns ->
                Pair(
                    // bc
                    commonPatterns,
                    // bbc
                    true to
                        or(
                            commonPatterns,
                            "(ch/tutteli/atrium/api/(fluent|infix)/en_GB/" +
                                or(
                                    // removed overload which expects kClass
                                    "AnyAssertionsSpec.*toBeNullIfNullGivenElse.*",
                                    "AnyAssertionSamples.toBeNullIfNullGivenElse",
                                    // returnValueOf was part of Assert, no longer in there
                                    or(
                                        "IterableAnyAssertionsSpec",
                                        "IterableContainsInAnyOrderAtLeast1EntriesAssertionsSpec",
                                        "IterableContainsInAnyOrderOnlyEntriesAssertionsSpec",
                                        "IterableContainsInOrderOnlyEntriesAssertionsSpec"
                                    ) + ".*/returnValueOf",
                                    // we moved MetaFeature and MetaFeatureOptions
                                    "FeatureAssertions.*(Manual|Reference).*Spec",
                                    // we removed ExpectBuilder and SubjectLessSpec uses it
                                    ".*assertion function can be used in an AssertionGroup with an ExplanatoryAssertionGroupType and reportBuilder without failure.*"
                                ) + ".*)",
                            // removed overload which expects kClass
                            "ch.tutteli.atrium.api.fluent.en_GB.samples.AnyAssertionSamples#toBeNullIfNullGivenElse"
                        )
                )
            }
        ),
        Triple(
            "0.15.0",
            allApisAllTargets,
            // forgive for bc and bbc
            or(
                "(ch/tutteli/atrium/api/(fluent|infix)/en_GB/" +
                    or(
                        "IterableContainsInOrderOnly.*error cases",
                        "IterableContainsInAnyOrder.*error cases",
                        "IterableContainsInAnyOrderOnlyEntriesAssertionsSpec.*nullable cases",
                        // implementation and spec was wrong
                        "IterableAssertionsSpec/.*`" + or(
                            "containsNoDuplicates",
                            "contains noDuplicates"
                        ) + "`/list with duplicates",
                        "BigDecimalAssertionsSpec.*overload throws PleaseUseReplacementException.*",
                        // we renamed containsNot to notToContain with 0.17.0
                        "CharSequenceContains.*Spec.*points to containsNot",
                        "IterableContains.*Spec.*points to containsNot",
                        // we improved reporting for notToContain with 0.17.0
                        "IterableContainsNot(Entries|Values)AssertionsSpec.*`containsNot.*`.*throws AssertionError",
                        "IterableNoneAssertionsSpec.*`(none|containsNot).*`.*throws AssertionError",
                        // changed reporting for contains.atLeast(1) with 0.17.0
                        or(
                            "(CharSequence|Iterable)Contains.*Spec",
                            "IterableAnyAssertionsSpec"
                        ) + ".*`.*(any|contains).*`.*(throws.*AssertionError|failing cases)",
                        // changed reporting for Iterable.all empty collection cases with 0.17.0
                        "IterableAllAssertionsSpec.*" + "empty collection.*" + "throws AssertionError"
                    ) + ".*)",
                // we don't use asci bullet points in reporting since 0.17.0
                // but have own tests to assure that changing bullet points work
                "(ch/tutteli/atrium/api/infix/en_GB/.*Spec.*" + or(
                    "throws",
                    "is thrown",
                    "error message contains",
                    "shows (all )?suppressed",
                    "null was missing", "failing cases",
                    "provoke the failing"
                ) + ".*)"
            ).let { commonPatterns ->
                Pair(
                    // bc
                    or(
                        commonPatterns,
                        "(ch/tutteli/atrium/api/infix/en_GB/" +
                            or(
                                // looks like we were unlucky in infix and kotlin actually has chosen the deprecated overload
                                // in bytecode instead of the new one in those particular specs (not in others). No idea why...
                                // we have removed the deprecated `contains o` function in 0.16.0
                                "IterableContainsInOrderOnlyGroupedEntriesAssertionsSpec.*",
                                "IterableContainsInOrderOnlyGroupedValuesAssertionsSpec.*"
                            ) + ".*)"
                    ),
                    // bbc
                    true to or(
                        commonPatterns,
                        "(ch/tutteli/atrium/api/(fluent|infix)/en_GB/" +
                            or(
                                // removed overload which expects kClass
                                "AnyAssertionsSpec.*toBeNullIfNullGivenElse.*",
                                "AnyAssertionSamples.toBeNullIfNullGivenElse",
                                // API uses now Group from logic
                                "IterableContainsInOrderOnlyGrouped.*Spec",
                                // returnValueOf was part of Assert, no longer in there
                                or(
                                    "IterableAnyAssertionsSpec",
                                    "IterableContainsInAnyOrderAtLeast1EntriesAssertionsSpec",
                                    "IterableContainsInAnyOrderOnlyEntriesAssertionsSpec",
                                    "IterableContainsInOrderOnlyEntriesAssertionsSpec"
                                ) + ".*/returnValueOf",
                                // we moved MetaFeature and MetaFeatureOptions
                                "FeatureAssertions.*(Manual|Reference).*Spec",
                                // we removed ExpectBuilder and SubjectLessSpec uses it
                                ".*assertion function can be used in an AssertionGroup with an ExplanatoryAssertionGroupType and report without failure.*"
                            ) + ".*)"
                    )
                )
            }
        ),
        Triple(
            "0.16.0",
            allApisAllTargets,
            // forgive for bc and bbc
            ("(ch/tutteli/atrium/api/(fluent|infix)/en_GB/" + or(
                // we renamed containsNot to notToContain with 0.17.0
                "CharSequenceContains.*Spec.*points to containsNot",
                "IterableContains.*Spec.*points to containsNot",
                // we improved reporting for containsNoDuplicates
                "IterableExpectationsSpec.*`(containsNoDuplicates|contains noDuplicates)`",
                // we improved reporting for notToContain with 0.17.0
                "IterableContainsNot(Entries|Values)ExpectationsSpec.*`containsNot.*`.*throws AssertionError",
                "IterableNoneExpectationsSpec.*`(none|containsNot).*`.*throws AssertionError",
                // changed reporting for contains.atLeast(1) with 0.17.0
                or(
                    "(CharSequence|Iterable)Contains.*Spec",
                    "IterableAnyExpectationsSpec"
                ) + ".*`.*(any|contains).*`.*(throws.*AssertionError|failing cases)",
                // changed reporting for Iterable.all empty collection cases with 0.17.0
                "IterableAllExpectationsSpec.*" + "empty collection.*" + "throws AssertionError"
            ) + ".*)").let { commonPatterns ->
                Pair(
                    // bc
                    commonPatterns,
                    // bbc
                    true to commonPatterns
                )
            }
        )
    )
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
includeKotlinJvmJs("core", "atrium-core")

includeKotlinJvmJsWithExtensions("logic", "atrium-logic")

includeKotlinJvmJs("misc/specs", "atrium-specs")
includeKotlinJvmJs("misc/verbs", "atrium-verbs")
includeKotlinJvmJs("misc/verbs-internal", "atrium-verbs-internal")
include("misc/tools", "readme-examples")

includeKotlinJvmJs("translations/de_CH", "atrium-translations-de_CH")
includeKotlinJvmJs("translations/en_GB", "atrium-translations-en_GB")

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
    include(subPath, "$module-js")
    include(subPath, "$module-jvm")
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
