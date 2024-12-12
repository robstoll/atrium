package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.lineSeparator

abstract class IterableToContainInOrderOnlyValuesExpectationsSpec(
    toContainInOrderOnlyValues: Fun3<Iterable<Double>, Double, Array<out Double>, InOrderOnlyReportingOptions.() -> Unit>,
    toContainInOrderOnlyNullableValues: Fun3<Iterable<Double?>, Double?, Array<out Double?>, InOrderOnlyReportingOptions.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : IterableToContainSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        toContainInOrderOnlyValues.forSubjectLess(2.5, arrayOf(), emptyInOrderOnlyReportOptions)
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        toContainInOrderOnlyNullableValues.forSubjectLess(2.5, arrayOf(), emptyInOrderOnlyReportOptions)
    ) {})

    val toBeWithFeature = "$indentF$featureBulletPoint$toEqualDescr"
    val toBeAfterSuccess = "$indentRoot$indentSuccess$toBeWithFeature"
    val toBeAfterFailing = "$indentRoot$indentX$toBeWithFeature"


    fun Expect<String>.elementSuccess(index: Int, expected: String, withBulletPoint: Boolean = true): Expect<String> =
        this.toContain.exactly(1).regex(
            "\\Q${if (withBulletPoint) s else ""}$f${elementWithIndex(index)}: $expected\\E.*$lineSeparator" +
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
            "\\Q${if (withBulletPoint) x else ""}$f${elementWithIndex(index)}: $actual\\E.*$lineSeparator" +
                    "$toBeAfterFailing: $expected"
        )
    }

    fun Expect<String>.elementNonExisting(
        index: Int,
        expected: Double,
        withBulletPoint: Boolean = true
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q${if (withBulletPoint) x else ""}$f${elementWithIndex(index)}: $sizeExceeded\\E.*$lineSeparator" +
                    "$indentRoot$indentX$indentF$indentFeature$explanatoryBulletPoint$toEqualDescr: $expected"
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
            it("1.0 throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainFun(1.0)
                }.toThrow<AssertionError> {
                    message {
                        toContain("$rootBulletPoint$toContainInOrderOnly:")
                        elementNonExisting(0, 1.0)
                        notToContain(additionalElements)
                        toContainSize(0, 1)
                    }
                }
            }
            it("1.0 and 4.0 throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainFun(1.0, 4.0)
                }.toThrow<AssertionError> {
                    message {
                        toContain("$rootBulletPoint$toContainInOrderOnly:")
                        elementNonExisting(0, 1.0)
                        elementNonExisting(1, 4.0)
                        notToContain(additionalElements)
                        toContainSize(0, 2)
                    }
                }
            }
        }

        context("iterable ${oneToFour().toList()}") {

            context("happy case") {
                it("1.0, 2.0, 3.0, 4.0, 4.0") {
                    expect(oneToFour()).toContainFun(1.0, 2.0, 3.0, 4.0, 4.0)
                }
            }

            context("error cases (throws AssertionError)") {

                it("4.0, 1.0, 2.0, 3.0, 4.0 -- wrong order") {
                    expect {
                        expect(oneToFour()).toContainFun(4.0, 1.0, 2.0, 3.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
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
                        expect(oneToFour()).toContainFun(1.0, 2.0, 3.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.0)
                            elementSuccess(1, 2.0)
                            elementSuccess(2, 3.0)
                            elementSuccess(3, 4.0)
                            toContain(
                                "$bb$additionalElements:",
                                "$listBulletPoint${elementWithIndex(4)}: 4.0"
                            )
                            toContainSize(5, 4)
                        }
                    }
                }

                it("1.0, 4.0 -- 2.0, 3.0 and 4.0 was missing") {
                    expect {
                        expect(oneToFour()).toContainFun(1.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.0)
                            elementFailing(1, 2.0, 4.0)
                            toContain(
                                "$bb$additionalElements:",
                                "$listBulletPoint${elementWithIndex(2)}: 3.0",
                                "$listBulletPoint${elementWithIndex(3)}: 4.0",
                                "$listBulletPoint${elementWithIndex(4)}: 4.0"
                            )
                            toContainSize(5, 2)
                        }
                    }
                }
                it("1.0, 3.0, 5.0 -- 5.0 is wrong and 4.0 and 4.0 are missing") {
                    expect {
                        expect(oneToFour()).toContainFun(1.0, 3.0, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.0)
                            elementFailing(1, 2.0, 3.0)
                            elementFailing(2, 3.0, 5.0)
                            toContain(
                                "$bb$additionalElements:",
                                "$listBulletPoint${elementWithIndex(3)}: 4.0",
                                "$listBulletPoint${elementWithIndex(4)}: 4.0"
                            )
                            toContainSize(5, 3)
                        }
                    }
                }
                it("1.0, 2.0, 3.0, 4.0, 4.0, 5.0 -- 5.0 too much") {
                    expect {
                        expect(oneToFour()).toContainFun(1.0, 2.0, 3.0, 4.0, 4.0, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.0)
                            elementSuccess(1, 2.0)
                            elementSuccess(2, 3.0)
                            elementSuccess(3, 4.0)
                            elementSuccess(4, 4.0)
                            elementNonExisting(5, 5.0)
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
                        expect(oneToFour()).toContainFun(1.0, 2.0, 3.0, 4.0, 4.0, 5.0, report = { showOnlyFailing() })
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 6)
                            notToContainElement(0, 1.0)
                            notToContainElement(1, 2.0)
                            notToContainElement(2, 3.0)
                            notToContainElement(3, 4.0)
                            notToContainElement(4, 4.0)
                            elementNonExisting(5, 5.0, withBulletPoint = false)

                        }
                    }
                }
                it("shows only failing with report option `showOnlyFailingIfMoreExpectedElementsThan(5)` because there are 6") {
                    expect {
                        expect(oneToFour()).toContainFun(
                            1.0,
                            2.0,
                            3.0,
                            4.0,
                            4.0,
                            5.0,
                            report = { showOnlyFailingIfMoreExpectedElementsThan(5) })
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 6)
                            notToContainElement(0, 1.0)
                            notToContainElement(1, 2.0)
                            notToContainElement(2, 3.0)
                            notToContainElement(3, 4.0)
                            notToContainElement(4, 4.0)
                            elementNonExisting(5, 5.0, withBulletPoint = false)
                        }
                    }
                }
                it("shows summary with report option `showOnlyFailingIfMoreExpectedElementsThan(2)` because there are 2") {
                    expect {
                        expect(oneToFour()).toContainFun(
                            1.0,
                            2.0,
                            report = { showOnlyFailingIfMoreExpectedElementsThan(2) }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 2)
                            elementSuccess(0, 1.0)
                            elementSuccess(1, 2.0)
                            toContain(
                                "$bb$additionalElements:",
                                "$listBulletPoint${elementWithIndex(2)}: 3.0",
                                "$listBulletPoint${elementWithIndex(3)}: 4.0",
                                "$listBulletPoint${elementWithIndex(4)}: 4.0"
                            )

                        }
                    }
                }

                it("shows summary without report option if there are 10 expected elements because default for showOnlyFailingIfMoreExpectedElementsThan is 10") {
                    expect {
                        expect(oneToFour()).toContainFun(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
                    }.toThrow<AssertionError> {
                        message {
                            elementSuccess(0, 1.0)
                            elementSuccess(1, 2.0)
                            elementSuccess(2, 3.0)
                            elementSuccess(3, 4.0)
                            elementFailing(4, 4.0, 5.0)
                            elementNonExisting(5, 6.0)
                            elementNonExisting(6, 7.0)
                            elementNonExisting(7, 8.0)
                            elementNonExisting(8, 9.0)
                            elementNonExisting(9, 10.0)
                            toContainSize(5, 10)
                        }
                    }
                }
                it("shows only failing without report option if there are 11 expected elements because default for showOnlyFailingIfMoreExpectedElementsThan is 10") {
                    expect {
                        expect(oneToFour()).toContainFun(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 11)
                            elementFailing(4, 4.0, 5.0, withBulletPoint = false)
                            elementNonExisting(5, 6.0, withBulletPoint = false)
                            elementNonExisting(6, 7.0, withBulletPoint = false)
                            elementNonExisting(7, 8.0, withBulletPoint = false)
                            elementNonExisting(8, 9.0, withBulletPoint = false)
                            elementNonExisting(9, 10.0, withBulletPoint = false)
                            elementNonExisting(10, 11.0, withBulletPoint = false)
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
            val null1null3 = { sequenceOf(null, 1.0, null, 3.0).constrainOnce().asIterable() }

            context("iterable ${null1null3().toList()}") {
                context("happy cases (do not throw)") {
                    it("null, 1.0, null, 3.0") {
                        expect(null1null3()).toContainFun(null, 1.0, null, 3.0)
                    }
                }

                context("failing cases") {
                    it("null, 1.0, 3.0 -- null was missing") {
                        expect {
                            expect(null1null3()).toContainFun(null, 1.0, 3.0)
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                                elementSuccess(0, Text.NULL.string)
                                elementSuccess(1, 1.0)
                                elementFailing(2, Text.NULL.string, 3.0)
                                toContain(
                                    "$bb$additionalElements:",
                                    "$listBulletPoint${elementWithIndex(3)}: 3.0"
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
