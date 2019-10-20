package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE

abstract class IterableContainsInOrderOnlyValuesAssertionsSpec(
    containsInOrderOnlyValues: Fun2<Iterable<Double>, Double, Array<out Double>>,
    containsInOrderOnlyNullableValues: Fun2<Iterable<Double?>, Double?, Array<out Double?>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    featureArrow: String,
    featureBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        containsInOrderOnlyValues.forSubjectLess(2.5, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        containsInOrderOnlyNullableValues.forSubjectLess(2.5, arrayOf())
    ) {})

    fun Expect<Iterable<Double?>>.containsInOrderOnlyNullableValuesFun(t: Double?, vararg tX: Double?) =
        containsInOrderOnlyNullableValues(this, t, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentFeatureArrow = " ".repeat(featureArrow.length)

    val toBeWithFeature = "$indentFeatureArrow$featureBulletPoint${TO_BE.getDefault()}"
    val toBeAfterSuccess = "$indentBulletPoint$indentSuccessfulBulletPoint$toBeWithFeature"
    val toBeAfterFailing = "$indentBulletPoint$indentFailingBulletPoint$toBeWithFeature"

    fun entry(index: Int) = String.format(entryWithIndex, index)

    fun Expect<String>.entrySuccess(index: Int, expected: String): Expect<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${entry(index)}: $expected\\E.*$separator" +
                "$toBeAfterSuccess: $expected"
        )
    }

    fun Expect<String>.entrySuccess(index: Int, expected: Double) = entrySuccess(index, expected.toString())

    fun Expect<String>.entryFailing(index: Int, actual: Any, expected: Double): Expect<CharSequence> {
        return this.contains.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${entry(index)}: $actual\\E.*$separator" +
                "$toBeAfterFailing: $expected"
        )
    }

    nonNullableCases(
        describePrefix,
        containsInOrderOnlyValues,
        containsInOrderOnlyNullableValues
    ) { containsValuesFunArr ->

        fun Expect<Iterable<Double>>.containsFun(t: Double, vararg tX: Double) =
            containsValuesFunArr(t, tX.toTypedArray())

        context("empty collection") {
            it("1.0 throws AssertionError") {
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
            it("1.0 and 4.0 throws AssertionError") {
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

        context("iterable ${oneToFour().toList()}") {

            context("happy case") {
                it("1.0, 2.0, 3.0, 4.0, 4.0") {
                    expect(oneToFour()).containsFun(1.0, 2.0, 3.0, 4.0, 4.0)
                }
            }

            context("error cases (throws AssertionError)") {

                it("4.0, 1.0, 2.0, 3.0, 4.0 -- wrong order") {
                    expect {
                        expect(oneToFour()).containsFun(4.0, 1.0, 2.0, 3.0, 4.0)
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

                it("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                    expect {
                        expect(oneToFour()).containsFun(1.0, 2.0, 3.0, 4.0)
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

                it("1.0, 4.0 -- 2.0, 3.0 and 4.0 was missing") {
                    expect {
                        expect(oneToFour()).containsFun(1.0, 4.0)
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
                it("1.0, 3.0, 5.0 -- 5.0 is wrong and 4.0 and 4.0 are missing") {
                    expect {
                        expect(oneToFour()).containsFun(1.0, 3.0, 5.0)
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
                it("1.0, 2.0, 3.0, 4.0, 4.0, 5.0 -- 5.0 too much") {
                    expect {
                        expect(oneToFour()).containsFun(1.0, 2.0, 3.0, 4.0, 4.0, 5.0)
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

        describeFun("${containsInOrderOnlyNullableValues.name} for nullable") {
            val list = listOf(null, 1.0, null, 3.0).asIterable()
            val fluent = expect(list)

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
