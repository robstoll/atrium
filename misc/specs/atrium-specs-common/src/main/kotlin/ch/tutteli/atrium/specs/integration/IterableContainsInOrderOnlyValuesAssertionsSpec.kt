package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.exactly
import ch.tutteli.atrium.api.fluent.en_GB.regex
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionAnyAssertion

abstract class IterableContainsInOrderOnlyValuesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsInOrderOnlyValuesPair: Pair<String, Expect<Iterable<Double>>.(Double, Array<out Double>) -> Expect<Iterable<Double>>>,
    containsInOrderOnlyNullableValuesPair: Pair<String, Expect<Iterable<Double?>>.(Double?, Array<out Double?>) -> Expect<Iterable<Double?>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        containsInOrderOnlyValuesPair.first to expectLambda { containsInOrderOnlyValuesPair.second(this, 2.5, arrayOf()) }
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(describePrefix,
        "${containsInOrderOnlyNullableValuesPair.first} for nullable" to expectLambda { containsInOrderOnlyNullableValuesPair.second(this, 2.5, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsInOrderOnlyValuesPair.first, { containsInOrderOnlyValuesPair.second(this, 2.5, arrayOf(1.2)) }, listOf(2.5, 1.2).asIterable(), listOf(2.5, 2.2))
    ) {})
    include(object : CheckingAssertionSpec<Iterable<Double?>>(verbs, describePrefix,
        checkingTriple("${containsInOrderOnlyNullableValuesPair.first} for nullable", { containsInOrderOnlyNullableValuesPair.second(this, 2.5, arrayOf(1.2)) }, listOf(2.5 as Double?, 1.2).asIterable(), listOf(2.5, 2.2))
    ) {})

    val assert: (Iterable<Double>) -> Expect<Iterable<Double>> = verbs::check
    val expect = verbs::checkException

    val (containsInOrderOnlyNullableValues, containsInOrderOnlyNullableValuesFunArr) = containsInOrderOnlyNullableValuesPair
    fun Expect<Iterable<Double?>>.containsInOrderOnlyNullableValuesFun(t: Double?, vararg tX: Double?)
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

    fun Expect<String>.entrySuccess(index: Int, expected: String): Expect<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${entry(index)}: $expected\\E.*$separator" +
                "$toBeAfterSuccess: $expected")
    }

    fun Expect<String>.entrySuccess(index: Int, expected: Double)= entrySuccess(index, expected.toString())

    fun Expect<String>.entryFailing(index: Int, actual: Any, expected: Double): Expect<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${entry(index)}: $actual\\E.*$separator" +
                "$toBeAfterFailing: $expected")
    }

    nonNullableCases(describePrefix,
        containsInOrderOnlyValuesPair,
        containsInOrderOnlyNullableValuesPair
    ) { containsValuesFunArr ->

        fun Expect<Iterable<Double>>.containsFun(t: Double, vararg tX: Double) =
            containsValuesFunArr(t, tX.toTypedArray())

        context("empty collection") {
            val fluentEmpty = assert(setOf())
            it("1.0 throws AssertionError") {
                expect {
                    fluentEmpty.containsFun(1.0)
                }.toThrow<AssertionError> {
                    message {
                        contains("$rootBulletPoint$containsInOrderOnly:")
                        asExpect().entryFailing(0, sizeExceeded, 1.0)
                        containsNot(additionalEntries)
                        asExpect().containsSize(0, 1)
                    }
                }
            }
            it("1.0 and 4.0 throws AssertionError") {
                expect {
                    fluentEmpty.containsFun(1.0, 4.0)
                }.toThrow<AssertionError> {
                    message {
                        contains("$rootBulletPoint$containsInOrderOnly:")
                        asExpect().entryFailing(0, sizeExceeded, 1.0)
                        asExpect().entryFailing(1, sizeExceeded, 4.0)
                        containsNot(additionalEntries)
                        asExpect().containsSize(0, 2)
                    }
                }
            }
        }

        context("iterable $oneToFour") {
            val fluent = assert(oneToFour)

            context("happy case") {
                it("1.0, 2.0, 3.0, 4.0, 4.0") {
                    fluent.containsFun(1.0, 2.0, 3.0, 4.0, 4.0)
                }
            }

            context("error cases (throws AssertionError)") {

                it("4.0, 1.0, 2.0, 3.0, 4.0 -- wrong order") {
                    expect {
                        fluent.containsFun(4.0, 1.0, 2.0, 3.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                            asExpect().entryFailing(0, 1.0, 4.0)
                            asExpect().entryFailing(1, 2.0, 1.0)
                            asExpect().entryFailing(2, 3.0, 2.0)
                            asExpect().entryFailing(3, 4.0, 3.0)
                            asExpect().entrySuccess(4, 4.0)
                            asExpect().containsSize(5, 5)
                        }
                    }
                }

                it("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                    expect {
                        fluent.containsFun(1.0, 2.0, 3.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                            asExpect().entrySuccess(0, 1.0)
                            asExpect().entrySuccess(1, 2.0)
                            asExpect().entrySuccess(2, 3.0)
                            asExpect().entrySuccess(3, 4.0)
                            contains(
                                "$warningBulletPoint$additionalEntries:",
                                "$listBulletPoint${entry(4)}: 4.0"
                            )
                            asExpect().containsSize(5, 4)
                        }
                    }
                }

                it("1.0, 4.0 -- 2.0, 3.0 and 4.0 was missing") {
                    expect {
                        fluent.containsFun(1.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                            asExpect().entrySuccess(0, 1.0)
                            asExpect().entryFailing(1, 2.0, 4.0)
                            contains(
                                "$warningBulletPoint$additionalEntries:",
                                "$listBulletPoint${entry(2)}: 3.0",
                                "$listBulletPoint${entry(3)}: 4.0",
                                "$listBulletPoint${entry(4)}: 4.0"
                            )
                            asExpect().containsSize(5, 2)
                        }
                    }
                }
                it("1.0, 3.0, 5.0 -- 5.0 is wrong and 4.0 and 4.0 are missing") {
                    expect {
                        fluent.containsFun(1.0, 3.0, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                            asExpect().entrySuccess(0, 1.0)
                            asExpect().entryFailing(1, 2.0, 3.0)
                            asExpect().entryFailing(2, 3.0, 5.0)
                            contains(
                                "$warningBulletPoint$additionalEntries:",
                                "$listBulletPoint${entry(3)}: 4.0",
                                "$listBulletPoint${entry(4)}: 4.0"
                            )
                            asExpect().containsSize(5, 3)
                        }
                    }
                }
                it("1.0, 2.0, 3.0, 4.0, 4.0, 5.0 -- 5.0 too much") {
                    expect {
                        fluent.containsFun(1.0, 2.0, 3.0, 4.0, 4.0, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                            asExpect().entrySuccess(0, 1.0)
                            asExpect().entrySuccess(1, 2.0)
                            asExpect().entrySuccess(2, 3.0)
                            asExpect().entrySuccess(3, 4.0)
                            asExpect().entrySuccess(4, 4.0)
                            asExpect().entryFailing(5, sizeExceeded, 5.0)
                            asExpect().containsSize(5, 6)
                        }
                    }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun("$containsInOrderOnlyNullableValues for nullable") {
            val list = listOf(null, 1.0, null, 3.0).asIterable()
            val fluent = verbs.check(list)

            context("iterable $list") {
                context("happy cases (do not throw)") {
                    it("null, 1.0, null, 3.0") {
                        fluent.containsInOrderOnlyNullableValuesFun(null, 1.0, null, 3.0)
                    }
                }

                context("failing cases") {
                    it("null, 1.0, 3.0 -- null was missing") {
                        expect {
                            fluent.containsInOrderOnlyNullableValuesFun(null, 1.0, 3.0)
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                                asExpect().entrySuccess(0, RawString.NULL.string)
                                asExpect().entrySuccess(1, 1.0)
                                asExpect().entryFailing(2, RawString.NULL.string, 3.0)
                                contains(
                                    "$warningBulletPoint$additionalEntries:",
                                    "$listBulletPoint${entry(3)}: 3.0"
                                )
                                asExpect().containsSize(4, 3)
                            }
                        }
                    }
                }
            }
        }
    }
})
