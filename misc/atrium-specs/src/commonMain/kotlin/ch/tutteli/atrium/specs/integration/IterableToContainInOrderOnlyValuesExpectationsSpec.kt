package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.specs.*

abstract class IterableToContainInOrderOnlyValuesExpectationsSpec(
    toContainInOrderOnlyValues: Fun3<Iterable<Double>, Double, Array<out Double>, InOrderOnlyReportingOptions.() -> Unit>,
    toContainInOrderOnlyNullableValues: Fun3<Iterable<Double?>, Double?, Array<out Double?>, InOrderOnlyReportingOptions.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : IterableToContainSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        toContainInOrderOnlyValues.forSubjectLessTest(2.5, arrayOf(), emptyInOrderOnlyReportOptions)
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        toContainInOrderOnlyNullableValues.forSubjectLessTest(2.5, arrayOf(), emptyInOrderOnlyReportOptions)
    ) {})

    val toBeWithFeature = "$indentFeatureArrow$featureBulletPoint$toEqualDescr"
    val toBeAfterSuccess = "$indentRootBulletPoint$indentSuccessfulBulletPoint$toBeWithFeature"
    val toBeAfterFailing = "$indentRootBulletPoint$indentFailingBulletPoint$toBeWithFeature"


    fun Expect<String>.elementSuccess(index: Int, expected: String, withBulletPoint: Boolean = true): Expect<String> =
        this.toContain.exactly(1).regex(
            "\\Q${if (withBulletPoint) successfulBulletPoint else ""}$featureArrow${elementWithIndex(index)}: $expected\\E.*$separator" +
                "$toBeAfterSuccess: $expected"
        )

    fun Expect<String>.elementSuccess(index: Int, expected: Double) = elementSuccess(index, expected.toString())

    fun Expect<String>.elementFailing(
        index: Int,
        actual: Any,
        expected: Double,
        withBulletPoint: Boolean = true
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q${if (withBulletPoint) failingBulletPoint else ""}$featureArrow${elementWithIndex(index)}: $actual\\E.*$separator" +
                "$toBeAfterFailing: $expected"
        )
    }

    fun Expect<String>.elementNonExisting(
        index: Int,
        expected: Double,
        withBulletPoint: Boolean = true
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q${if (withBulletPoint) failingBulletPoint else ""}$featureArrow${elementWithIndex(index)}: $sizeExceeded\\E.*$separator" +
                "$indentRootBulletPoint$indentFailingBulletPoint$indentFeatureArrow$indentFeatureBulletPoint$explanatoryBulletPoint$toEqualDescr: $expected"
        )
    }


    nonNullableCases(
        describePrefix,
        toContainInOrderOnlyValues,
        toContainInOrderOnlyNullableValues
    ) { toContainValuesFunArr ->

        fun Expect<Iterable<Double>>.toContainFun(
            t: Double,
            vararg tX: Double,
            report: InOrderOnlyReportingOptions.() -> Unit = emptyInOrderOnlyReportOptions
        ) = toContainValuesFunArr(t, tX.toTypedArray(), report)

        context("empty collection") {
            it("1.1 throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainFun(1.1)
                }.toThrow<AssertionError> {
                    message {
                        toContain("$rootBulletPoint$toContainInOrderOnly:")
                        elementNonExisting(0, 1.1)
                        notToContain(additionalElements)
                        toContainSize(0, 1)
                    }
                }
            }
            it("1.1 and 4.1 throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainFun(1.1, 4.1)
                }.toThrow<AssertionError> {
                    message {
                        toContain("$rootBulletPoint$toContainInOrderOnly:")
                        elementNonExisting(0, 1.1)
                        elementNonExisting(1, 4.1)
                        notToContain(additionalElements)
                        toContainSize(0, 2)
                    }
                }
            }
        }

        context("iterable ${oneToFour().toList()}") {

            context("happy case") {
                it("1.1, 2.1, 3.1, 4.1, 4.1") {
                    expect(oneToFour()).toContainFun(1.1, 2.1, 3.1, 4.1, 4.1)
                }
            }

            context("error cases (throws AssertionError)") {

                it("4.1, 1.1, 2.1, 3.1, 4.1 -- wrong order") {
                    expect {
                        expect(oneToFour()).toContainFun(4.1, 1.1, 2.1, 3.1, 4.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementFailing(0, 1.1, 4.1)
                            elementFailing(1, 2.1, 1.1)
                            elementFailing(2, 3.1, 2.1)
                            elementFailing(3, 4.1, 3.1)
                            elementSuccess(4, 4.1)
                            notToContain(sizeDescr)
                        }
                    }
                }

                it("1.1, 2.1, 3.1, 4.1 -- 4.1 was missing") {
                    expect {
                        expect(oneToFour()).toContainFun(1.1, 2.1, 3.1, 4.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.1)
                            elementSuccess(1, 2.1)
                            elementSuccess(2, 3.1)
                            elementSuccess(3, 4.1)
                            toContain(
                                "$warningBulletPoint$additionalElements:",
                                "$listBulletPoint${elementWithIndex(4)}: 4.1"
                            )
                            toContainSize(5, 4)
                        }
                    }
                }

                it("1.1, 4.1 -- 2.1, 3.1 and 4.1 was missing") {
                    expect {
                        expect(oneToFour()).toContainFun(1.1, 4.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.1)
                            elementFailing(1, 2.1, 4.1)
                            toContain(
                                "$warningBulletPoint$additionalElements:",
                                "$listBulletPoint${elementWithIndex(2)}: 3.1",
                                "$listBulletPoint${elementWithIndex(3)}: 4.1",
                                "$listBulletPoint${elementWithIndex(4)}: 4.1"
                            )
                            toContainSize(5, 2)
                        }
                    }
                }
                it("1.1, 3.1, 5.1 -- 5.1 is wrong and 4.1 and 4.1 are missing") {
                    expect {
                        expect(oneToFour()).toContainFun(1.1, 3.1, 5.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.1)
                            elementFailing(1, 2.1, 3.1)
                            elementFailing(2, 3.1, 5.1)
                            toContain(
                                "$warningBulletPoint$additionalElements:",
                                "$listBulletPoint${elementWithIndex(3)}: 4.1",
                                "$listBulletPoint${elementWithIndex(4)}: 4.1"
                            )
                            toContainSize(5, 3)
                        }
                    }
                }
                it("1.1, 2.1, 3.1, 4.1, 4.1, 5.1 -- 5.1 too much") {
                    expect {
                        expect(oneToFour()).toContainFun(1.1, 2.1, 3.1, 4.1, 4.1, 5.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.1)
                            elementSuccess(1, 2.1)
                            elementSuccess(2, 3.1)
                            elementSuccess(3, 4.1)
                            elementSuccess(4, 4.1)
                            elementNonExisting(5, 5.1)
                            toContainSize(5, 6)
                        }
                    }
                }
            }
        }

        context("report options") {
            context("iterable ${oneToFour().toList()}") {
                it("shows only failing with report option `showOnlyFailing`") {
                    expect {
                        expect(oneToFour()).toContainFun(1.1, 2.1, 3.1, 4.1, 4.1, 5.1, report = { showOnlyFailing() })
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 6)
                            notToContainElement(0, 1.1)
                            notToContainElement(1, 2.1)
                            notToContainElement(2, 3.1)
                            notToContainElement(3, 4.1)
                            notToContainElement(4, 4.1)
                            elementNonExisting(5, 5.1, withBulletPoint = false)

                        }
                    }
                }
                it("shows only failing with report option `showOnlyFailingIfMoreExpectedElementsThan(5)` because there are 6") {
                    expect {
                        expect(oneToFour()).toContainFun(
                            1.1,
                            2.1,
                            3.1,
                            4.1,
                            4.1,
                            5.1,
                            report = { showOnlyFailingIfMoreExpectedElementsThan(5) })
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 6)
                            notToContainElement(0, 1.1)
                            notToContainElement(1, 2.1)
                            notToContainElement(2, 3.1)
                            notToContainElement(3, 4.1)
                            notToContainElement(4, 4.1)
                            elementNonExisting(5, 5.1, withBulletPoint = false)
                        }
                    }
                }
                it("shows summary with report option `showOnlyFailingIfMoreExpectedElementsThan(2)` because there are 2") {
                    expect {
                        expect(oneToFour()).toContainFun(
                            1.1,
                            2.1,
                            report = { showOnlyFailingIfMoreExpectedElementsThan(2) }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 2)
                            elementSuccess(0, 1.1)
                            elementSuccess(1, 2.1)
                            toContain(
                                "$warningBulletPoint$additionalElements:",
                                "$listBulletPoint${elementWithIndex(2)}: 3.1",
                                "$listBulletPoint${elementWithIndex(3)}: 4.1",
                                "$listBulletPoint${elementWithIndex(4)}: 4.1"
                            )

                        }
                    }
                }

                it("shows summary without report option if there are 10 expected elements because default for showOnlyFailingIfMoreExpectedElementsThan is 10") {
                    expect {
                        expect(oneToFour()).toContainFun(1.1, 2.1, 3.1, 4.1, 5.1, 6.1, 7.1, 8.1, 9.1, 10.1)
                    }.toThrow<AssertionError> {
                        message {
                            elementSuccess(0, 1.1)
                            elementSuccess(1, 2.1)
                            elementSuccess(2, 3.1)
                            elementSuccess(3, 4.1)
                            elementFailing(4, 4.1, 5.1)
                            elementNonExisting(5, 6.1)
                            elementNonExisting(6, 7.1)
                            elementNonExisting(7, 8.1)
                            elementNonExisting(8, 9.1)
                            elementNonExisting(9, 10.1)
                            toContainSize(5, 10)
                        }
                    }
                }
                it("shows only failing without report option if there are 11 expected elements because default for showOnlyFailingIfMoreExpectedElementsThan is 10") {
                    expect {
                        expect(oneToFour()).toContainFun(1.1, 2.1, 3.1, 4.1, 5.1, 6.1, 7.1, 8.1, 9.1, 10.1, 11.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 11)
                            elementFailing(4, 4.1, 5.1, withBulletPoint = false)
                            elementNonExisting(5, 6.1, withBulletPoint = false)
                            elementNonExisting(6, 7.1, withBulletPoint = false)
                            elementNonExisting(7, 8.1, withBulletPoint = false)
                            elementNonExisting(8, 9.1, withBulletPoint = false)
                            elementNonExisting(9, 10.1, withBulletPoint = false)
                            elementNonExisting(10, 11.1, withBulletPoint = false)
                        }
                    }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        fun Expect<Iterable<Double?>>.toContainFun(
            t: Double?,
            vararg tX: Double?,
            report: InOrderOnlyReportingOptions.() -> Unit = emptyInOrderOnlyReportOptions
        ) = toContainInOrderOnlyNullableValues(this, t, tX, report)

        describeFun(toContainInOrderOnlyNullableValues) {
            val null1null3 = { sequenceOf(null, 1.1, null, 3.1).constrainOnce().asIterable() }

            context("iterable ${null1null3().toList()}") {
                context("happy cases (do not throw)") {
                    it("null, 1.1, null, 3.1") {
                        expect(null1null3()).toContainFun(null, 1.1, null, 3.1)
                    }
                }

                context("failing cases") {
                    it("null, 1.1, 3.1 -- null was missing") {
                        expect {
                            expect(null1null3()).toContainFun(null, 1.1, 3.1)
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                                elementSuccess(0, Text.NULL.string)
                                elementSuccess(1, 1.1)
                                elementFailing(2, Text.NULL.string, 3.1)
                                toContain(
                                    "$warningBulletPoint$additionalElements:",
                                    "$listBulletPoint${elementWithIndex(3)}: 3.1"
                                )
                                toContainSize(4, 3)
                            }
                        }
                    }
                }
            }
        }
    }
})
