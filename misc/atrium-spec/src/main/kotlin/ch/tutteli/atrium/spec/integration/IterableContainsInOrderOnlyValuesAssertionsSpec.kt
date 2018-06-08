package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

abstract class IterableContainsInOrderOnlyValuesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsInOrderOnlyValuesPair: Pair<String, Assert<Iterable<Double>>.(Double, Array<out Double>) -> Assert<Iterable<Double>>>,
    containsInOrderOnlyNullableValuesPair: Pair<String, Assert<Iterable<Double?>>.(Double?, Array<out Double?>) -> Assert<Iterable<Double?>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsInOrderOnlyValuesPair.first to mapToCreateAssertion { containsInOrderOnlyValuesPair.second(this, 2.5, arrayOf()) },
        containsInOrderOnlyNullableValuesPair.first to mapToCreateAssertion { containsInOrderOnlyNullableValuesPair.second(this, 2.5, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsInOrderOnlyValuesPair.first, { containsInOrderOnlyValuesPair.second(this, 2.5, arrayOf(1.2)) }, listOf(2.5, 1.2).asIterable(), listOf(2.5, 2.2)),
        checkingTriple(containsInOrderOnlyNullableValuesPair.first, { containsInOrderOnlyNullableValuesPair.second(this, 2.5, arrayOf(1.2)) }, listOf(2.5, 1.2).asIterable(), listOf(2.5, 2.2))
    ) {})

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException

    val (containsInOrderOnlyNullableValues, containsInOrderOnlyNullableValuesFunArr) = containsInOrderOnlyNullableValuesPair
    fun Assert<Iterable<Double?>>.containsInOrderOnlyNullableValuesFun(t: Double?, vararg tX: Double?)
        = containsInOrderOnlyNullableValuesFunArr(t, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)

    val toBeWithFeature = "$indentFeatureArrow$featureBulletPoint${DescriptionAnyAssertion.TO_BE.getDefault()}"
    val toBeAfterSuccess = "$indentBulletPoint$indentSuccessfulBulletPoint$toBeWithFeature"
    val toBeAfterFailing = "$indentBulletPoint$indentFailingBulletPoint$toBeWithFeature"

    fun entry(index: Int)
        = String.format(entryWithIndex, index)

    fun Assert<CharSequence>.entrySuccess(index: Int, expected: String): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${entry(index)}: $expected\\E.*$separator" +
                "$toBeAfterSuccess: $expected")
    }

    fun Assert<CharSequence>.entrySuccess(index: Int, expected: Double)= entrySuccess(index, expected.toString())

    fun Assert<CharSequence>.entryFailing(index: Int, actual: Any, expected: Double): Assert<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${entry(index)}: $actual\\E.*$separator" +
                "$toBeAfterFailing: $expected")
    }

    nonNullableCases(describePrefix,
        containsInOrderOnlyValuesPair,
        containsInOrderOnlyNullableValuesPair
    ) { containsValuesFunArr ->

        fun Assert<Iterable<Double>>.containsFun(t: Double, vararg tX: Double) =
            containsValuesFunArr(t, tX.toTypedArray())

        context("empty collection") {
            val fluentEmpty = assert(setOf())
            test("1.0 throws AssertionError") {
                expect {
                    fluentEmpty.containsFun(1.0)
                }.toThrow<AssertionError> {
                    message {
                        contains("$rootBulletPoint$containsInOrderOnly:")
                        entryFailing(0, sizeExceeded, 1.0)
                        containsNot(additionalEntries)
                        containsSize(0, 1)
                    }
                }
            }
            test("1.0 and 4.0 throws AssertionError") {
                expect {
                    fluentEmpty.containsFun(1.0, 4.0)
                }.toThrow<AssertionError> {
                    message {
                        contains("$rootBulletPoint$containsInOrderOnly:")
                        entryFailing(0, sizeExceeded, 1.0)
                        entryFailing(1, sizeExceeded, 4.0)
                        containsNot(additionalEntries)
                        containsSize(0, 2)
                    }
                }
            }
        }

        context("iterable $oneToFour") {
            val fluent = assert(oneToFour)

            describe("happy case") {
                test("1.0, 2.0, 3.0, 4.0, 4.0") {
                    fluent.containsFun(1.0, 2.0, 3.0, 4.0, 4.0)
                }
            }

            describe("error cases (throws AssertionError)") {

                test("4.0, 1.0, 2.0, 3.0, 4.0 -- wrong order") {
                    expect {
                        fluent.containsFun(4.0, 1.0, 2.0, 3.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                            entryFailing(0, 1.0, 4.0)
                            entryFailing(1, 2.0, 1.0)
                            entryFailing(2, 3.0, 2.0)
                            entryFailing(3, 4.0, 3.0)
                            entrySuccess(4, 4.0)
                            containsSize(5, 5)
                        }
                    }
                }

                test("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                    expect {
                        fluent.containsFun(1.0, 2.0, 3.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                            entrySuccess(0, 1.0)
                            entrySuccess(1, 2.0)
                            entrySuccess(2, 3.0)
                            entrySuccess(3, 4.0)
                            contains(
                                "$warningBulletPoint$additionalEntries:",
                                "$listBulletPoint${entry(4)}: 4.0"
                            )
                            containsSize(5, 4)
                        }
                    }
                }

                test("1.0, 4.0 -- 2.0, 3.0 and 4.0 was missing") {
                    expect {
                        fluent.containsFun(1.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                            entrySuccess(0, 1.0)
                            entryFailing(1, 2.0, 4.0)
                            contains(
                                "$warningBulletPoint$additionalEntries:",
                                "$listBulletPoint${entry(2)}: 3.0",
                                "$listBulletPoint${entry(3)}: 4.0",
                                "$listBulletPoint${entry(4)}: 4.0"
                            )
                            containsSize(5, 2)
                        }
                    }
                }
                test("1.0, 3.0, 5.0 -- 5.0 is wrong and 4.0 and 4.0 are missing") {
                    expect {
                        fluent.containsFun(1.0, 3.0, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                            entrySuccess(0, 1.0)
                            entryFailing(1, 2.0, 3.0)
                            entryFailing(2, 3.0, 5.0)
                            contains(
                                "$warningBulletPoint$additionalEntries:",
                                "$listBulletPoint${entry(3)}: 4.0",
                                "$listBulletPoint${entry(4)}: 4.0"
                            )
                            containsSize(5, 3)
                        }
                    }
                }
                test("1.0, 2.0, 3.0, 4.0, 4.0, 5.0 -- 5.0 too much") {
                    expect {
                        fluent.containsFun(1.0, 2.0, 3.0, 4.0, 4.0, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                            entrySuccess(0, 1.0)
                            entrySuccess(1, 2.0)
                            entrySuccess(2, 3.0)
                            entrySuccess(3, 4.0)
                            entrySuccess(4, 4.0)
                            entryFailing(5, sizeExceeded, 5.0)
                            containsSize(5, 6)
                        }
                    }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun(containsInOrderOnlyNullableValues) {
            val list = listOf(null, 1.0, null, 3.0)
            val fluent = verbs.checkImmediately(list)

            context("iterable $list") {
                context("happy cases (do not throw)") {
                    test("null, 1.0, null, 3.0") {
                        fluent.containsInOrderOnlyNullableValuesFun(null, 1.0, null, 3.0)
                    }
                }

                context("failing cases") {
                    test("null, 1.0, 3.0 -- null was missing") {
                        expect {
                            fluent.containsInOrderOnlyNullableValuesFun(null, 1.0, 3.0)
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                                entrySuccess(0, RawString.NULL.string)
                                entrySuccess(1, 1.0)
                                entryFailing(2, RawString.NULL.string, 3.0)
                                contains(
                                    "$warningBulletPoint$additionalEntries:",
                                    "$listBulletPoint${entry(3)}: 3.0"
                                )
                                containsSize(4, 3)
                            }
                        }
                    }
                }
            }
        }
    }
})
