package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.containsNot
import ch.tutteli.atrium.api.fluent.en_GB.containsRegex
import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.style.specification.Suite

abstract class IterableContainsNotValuesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsNotValuesPair: Pair<String, Expect<Iterable<Double>>.(Double, Array<out Double>) -> Expect<Iterable<Double>>>,
    containsNotNullableValuesPair: Pair<String, Expect<Iterable<Double?>>.(Double?, Array<out Double?>) -> Expect<Iterable<Double?>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    listBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        containsNotValuesPair.first to expectLambda { containsNotValuesPair.second(this, 2.3, arrayOf()) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(describePrefix,
        "${containsNotNullableValuesPair.first} for nullable" to expectLambda { containsNotNullableValuesPair.second(this, 2.3, arrayOf()) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException

    val (containsNotNullable, containsNotNullableFunArr) = containsNotNullableValuesPair
    fun Expect<Iterable<Double?>>.containsNotNullableFun(a: Double?, vararg aX: Double?) =
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

        fun Expect<Iterable<Double>>.containsNotFun(a: Double, vararg aX: Double)
            = containsNotFunArr(a, aX.toTypedArray())

        context("empty collection") {
            val fluent = verbs.check(setOf<Double>().asIterable())

            it("4.0 throws AssertionError") {
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
            val fluent = verbs.check(oneToSeven)

            context("happy case") {
                it("1.1 does not throw") {
                    fluent.containsNotFun(1.1)
                }
                it("1.1, 2.2, 3.3 does not throw") {
                    fluent.containsNotFun(1.1, 2.2, 3.3)
                }
                it("3.3, 1.1, 2.2 does not throw") {
                    fluent.containsNotFun(3.3, 1.1, 2.2)
                }
            }

            context("failing cases; search string at different positions") {
                it("4.0 throws AssertionError") {
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
                it("1.0, 4.0 throws AssertionError") {
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
                it("4.0, 1.1 throws AssertionError") {
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
                it("null does not throw") {
                    verbs.check(oneToSeven as Iterable<Double?>).containsNotNullableFun(null)
                }
            }
            context("iterable $oneToSevenNullable"){
                it("null throws AssertionError") {
                    expect {
                        verbs.check(oneToSevenNullable).containsNotNullableFun(null)
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

                it("1.1, null throws AssertionError mentioning only null") {
                    expect {
                        verbs.check(oneToSevenNullable).containsNotNullableFun(1.1, null)
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
