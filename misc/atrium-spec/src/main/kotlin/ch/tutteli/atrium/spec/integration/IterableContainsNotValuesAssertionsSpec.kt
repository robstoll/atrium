@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.containsNot
import ch.tutteli.atrium.api.cc.en_GB.containsRegex
import ch.tutteli.atrium.api.cc.en_GB.message
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class IterableContainsNotValuesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsNotValuesPair: Pair<String, Assert<Iterable<Double>>.(Double, Array<out Double>) -> Assert<Iterable<Double>>>,
    containsNotNullableValuesPair: Pair<String, Assert<Iterable<Double?>>.(Double?, Array<out Double?>) -> Assert<Iterable<Double?>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    listBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsNotValuesPair.first to mapToCreateAssertion { containsNotValuesPair.second(this, 2.3, arrayOf()) },
        "${containsNotNullableValuesPair.first} for nullable" to mapToCreateAssertion { containsNotNullableValuesPair.second(this, 2.3, arrayOf()) }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsNotValuesPair.first, { containsNotValuesPair.second(this, 2.3, arrayOf()) }, listOf(2.1).asIterable(), listOf(2.1, 2.3)),
        checkingTriple("${containsNotNullableValuesPair.first} for nullable", { containsNotNullableValuesPair.second(this, 2.3, arrayOf()) }, listOf(2.1).asIterable(), listOf(2.1, 2.3))
    ) {})

    fun SpecBody.describeFun(funName: String, body: SpecBody.() -> Unit)
        = group("fun `$funName`", body = body)

    val expect = verbs::checkException

    val (containsNotNullable, containsNotNullableFunArr) = containsNotNullableValuesPair
    fun Assert<Iterable<Double?>>.containsNotNullableFun(a: Double?, vararg aX: Double?) =
        containsNotNullableFunArr(a, aX)

    val containsNotDescr = DescriptionIterableAssertion.CONTAINS_NOT.getDefault()
    val hasElement = DescriptionIterableAssertion.HAS_ELEMENT.getDefault()

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentListBulletPoint = " ".repeat(listBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)

    val featureSuccess = "$indentBulletPoint$indentListBulletPoint\\Q$successfulBulletPoint$featureArrow\\E"
    val featureFailing = "$indentBulletPoint$indentListBulletPoint\\Q$failingBulletPoint$featureArrow\\E"
    val isAfterFailing = "$indentBulletPoint$indentListBulletPoint$indentFailingBulletPoint$indentFeatureArrow\\Q$featureBulletPoint\\E$isDescr"
    val isAfterSuccess = "$indentBulletPoint$indentListBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow\\Q$featureBulletPoint\\E$isDescr"

    val anEntryWhichIsWithIndent = "$indentBulletPoint$listBulletPoint$anEntryWhichIs"

    nonNullableCases(
        describePrefix,
        containsNotValuesPair,
        containsNotNullableValuesPair
    ) {containsNotFunArr ->

        fun Assert<Iterable<Double>>.containsNotFun(a: Double, vararg aX: Double)
            = containsNotFunArr(a, aX.toTypedArray())

        context("empty collection") {
            val fluent = verbs.checkImmediately(setOf<Double>())

            test("4.0 throws AssertionError") {
                expect {
                    fluent.containsNotFun(4.0)
                }.toThrow<AssertionError> {
                    message {
                        containsRegex(
                            "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                "$anEntryWhichIsWithIndent: 4.0.*$separator" +
                                    "$featureSuccess$numberOfOccurrences: 0$separator"+
                                    "$isAfterSuccess: 0.*$separator"+
                                    "$featureFailing$hasElement: false$separator" +
                                    "$isAfterFailing: true"
                        )
                    }
                }
            }
        }

        context("iterable $oneToSeven") {
            val fluent = verbs.checkImmediately(oneToSeven)

            group("happy case") {
                test("1.1 does not throw") {
                    fluent.containsNotFun(1.1)
                }
                test("1.1, 2.2, 3.3 does not throw") {
                    fluent.containsNotFun(1.1, 2.2, 3.3)
                }
                test("3.3, 1.1, 2.2 does not throw") {
                    fluent.containsNotFun(3.3, 1.1, 2.2)
                }
            }

            group("failing cases; search string at different positions") {
                test("4.0 throws AssertionError") {
                    expect {
                        fluent.containsNotFun(4.0)
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                     "$anEntryWhichIsWithIndent: 4.0.*$separator" +
                                        "$featureFailing$numberOfOccurrences: 3$separator"+
                                        "$isAfterFailing: 0.*$separator"+
                                        "$featureSuccess$hasElement: true$separator" +
                                        "$isAfterSuccess: true"
                            )
                        }
                    }
                }
                test("1.0, 4.0 throws AssertionError") {
                    expect {
                        fluent.containsNotFun(1.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$anEntryWhichIsWithIndent: 1.0.*$separator" +
                                        "$featureFailing$numberOfOccurrences: 1$separator"+
                                        "$isAfterFailing: 0.*$separator"+
                                        "$featureSuccess$hasElement: true$separator" +
                                        "$isAfterSuccess: true$separator"+
                                    "$anEntryWhichIsWithIndent: 4.0.*$separator" +
                                        "$featureFailing$numberOfOccurrences: 3$separator"+
                                        "$isAfterFailing: 0.*$separator"+
                                        "$featureSuccess$hasElement: true$separator" +
                                        "$isAfterSuccess: true"
                            )
                        }
                    }
                }
                test("4.0, 1.1 throws AssertionError") {
                    expect {
                        fluent.containsNotFun(4.0, 1.0)
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                    "$anEntryWhichIsWithIndent: 4.0.*$separator" +
                                        "$featureFailing$numberOfOccurrences: 3$separator" +
                                        "$isAfterFailing: 0.*$separator" +
                                        "$featureSuccess$hasElement: true$separator" +
                                        "$isAfterSuccess: true$separator" +
                                    "$anEntryWhichIsWithIndent: 1.0.*$separator" +
                                        "$featureFailing$numberOfOccurrences: 1$separator" +
                                        "$isAfterFailing: 0.*$separator" +
                                        "$featureSuccess$hasElement: true$separator" +
                                        "$isAfterSuccess: true"
                            )
                        }
                    }
                }
            }

        }
    }

    nullableCases(describePrefix) {
        describeFun("$containsNotNullable for nullable") {
            context("iterable $oneToSeven") {
                test("null does not throw") {
                    verbs.checkImmediately(oneToSeven).containsNotNullableFun(null)
                }
            }
            context("iterable $oneToSevenNullable"){
                test("null throws AssertionError") {
                    expect {
                        verbs.checkImmediately(oneToSevenNullable).containsNotNullableFun(null)
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                "$anEntryWhichIsWithIndent: null$separator" +
                                    "$featureFailing$numberOfOccurrences: 2$separator" +
                                    "$isAfterFailing: 0.*$separator" +
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true"
                            )
                        }
                    }
                }

                test("1.1, null throws AssertionError mentioning only null") {
                    expect {
                        verbs.checkImmediately(oneToSevenNullable).containsNotNullableFun(1.1, null)
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "\\Q$rootBulletPoint\\E$containsNotDescr: $separator" +
                                "$anEntryWhichIsWithIndent: null$separator" +
                                    "$featureFailing$numberOfOccurrences: 2$separator" +
                                    "$isAfterFailing: 0.*$separator" +
                                    "$featureSuccess$hasElement: true$separator" +
                                    "$isAfterSuccess: true"
                            )
                            containsNot("$containsNotDescr: 1.1")
                        }
                    }
                }
            }
        }
    }
})
