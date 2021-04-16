package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.specs.*

abstract class IterableContainsInOrderOnlyValuesExpectationsSpec(
    containsInOrderOnlyValues: Fun2<Iterable<Double>, Double, Array<out Double>>,
    containsInOrderOnlyNullableValues: Fun2<Iterable<Double?>, Double?, Array<out Double?>>,
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

    val toBeWithFeature = "$indentFeatureArrow$featureBulletPoint$toBeDescr"
    val toBeAfterSuccess = "$indentRootBulletPoint$indentSuccessfulBulletPoint$toBeWithFeature"
    val toBeAfterFailing = "$indentRootBulletPoint$indentFailingBulletPoint$toBeWithFeature"

    fun Expect<String>.elementSuccess(index: Int, expected: String): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${elementWithIndex(index)}: $expected\\E.*$separator" +
                "$toBeAfterSuccess: $expected"
        )
    }

    fun Expect<String>.elementSuccess(index: Int, expected: Double) = elementSuccess(index, expected.toString())

    fun Expect<String>.elementFailing(index: Int, actual: Any, expected: Double): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${elementWithIndex(index)}: $actual\\E.*$separator" +
                "$toBeAfterFailing: $expected"
        )
    }

    fun Expect<String>.elementNonExisting(index: Int, expected: Double): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${elementWithIndex(index)}: $sizeExceeded\\E.*$separator" +
                "$indentRootBulletPoint$indentFailingBulletPoint$indentFeatureArrow$indentFeatureBulletPoint$explanatoryBulletPoint$toBeDescr: $expected"
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
                    expect(fluentEmpty()).containsFun(1.0)
                }.toThrow<AssertionError> {
                    message {
                        toContain("$rootBulletPoint$containsInOrderOnly:")
                        elementNonExisting(0, 1.0)
                        notToContain(additionalElements)
                        containsSize(0, 1)
                    }
                }
            }
            it("1.0 and 4.0 throws AssertionError") {
                expect {
                    expect(fluentEmpty()).containsFun(1.0, 4.0)
                }.toThrow<AssertionError> {
                    message {
                        toContain("$rootBulletPoint$containsInOrderOnly:")
                        elementNonExisting(0, 1.0)
                        elementNonExisting(1, 4.0)
                        notToContain(additionalElements)
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
                            toContain.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                            elementFailing(0, 1.0, 4.0)
                            elementFailing(1, 2.0, 1.0)
                            elementFailing(2, 3.0, 2.0)
                            elementFailing(3, 4.0, 3.0)
                            elementSuccess(4, 4.0)
                            notToContain(sizeDescr)
                        }
                    }
                }

                it("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                    expect {
                        expect(oneToFour()).containsFun(1.0, 2.0, 3.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                            elementSuccess(0, 1.0)
                            elementSuccess(1, 2.0)
                            elementSuccess(2, 3.0)
                            elementSuccess(3, 4.0)
                            toContain(
                                "$warningBulletPoint$additionalElements:",
                                "$listBulletPoint${elementWithIndex(4)}: 4.0"
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
                            toContain.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                            elementSuccess(0, 1.0)
                            elementFailing(1, 2.0, 4.0)
                            toContain(
                                "$warningBulletPoint$additionalElements:",
                                "$listBulletPoint${elementWithIndex(2)}: 3.0",
                                "$listBulletPoint${elementWithIndex(3)}: 4.0",
                                "$listBulletPoint${elementWithIndex(4)}: 4.0"
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
                            toContain.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                            elementSuccess(0, 1.0)
                            elementFailing(1, 2.0, 3.0)
                            elementFailing(2, 3.0, 5.0)
                            toContain(
                                "$warningBulletPoint$additionalElements:",
                                "$listBulletPoint${elementWithIndex(3)}: 4.0",
                                "$listBulletPoint${elementWithIndex(4)}: 4.0"
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
                            toContain.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                            elementSuccess(0, 1.0)
                            elementSuccess(1, 2.0)
                            elementSuccess(2, 3.0)
                            elementSuccess(3, 4.0)
                            elementSuccess(4, 4.0)
                            elementNonExisting(5, 5.0)
                            containsSize(5, 6)
                        }
                    }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun(containsInOrderOnlyNullableValues) {
            val null1null3 = { sequenceOf(null, 1.0, null, 3.0).constrainOnce().asIterable() }

            context("iterable ${null1null3().toList()}") {
                context("happy cases (do not throw)") {
                    it("null, 1.0, null, 3.0") {
                        expect(null1null3()).containsInOrderOnlyNullableValuesFun(null, 1.0, null, 3.0)
                    }
                }

                context("failing cases") {
                    it("null, 1.0, 3.0 -- null was missing") {
                        expect {
                            expect(null1null3()).containsInOrderOnlyNullableValuesFun(null, 1.0, 3.0)
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).value("$rootBulletPoint$containsInOrderOnly:")
                                elementSuccess(0, Text.NULL.string)
                                elementSuccess(1, 1.0)
                                elementFailing(2, Text.NULL.string, 3.0)
                                toContain(
                                    "$warningBulletPoint$additionalElements:",
                                    "$listBulletPoint${elementWithIndex(3)}: 3.0"
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
