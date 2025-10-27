package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*

abstract class IterableToContainInOrderOnlyEntriesExpectationsSpec(
    toContainInOrderOnlyEntries: Fun3<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>, InOrderOnlyReportingOptions.() -> Unit>,
    toContainInOrderOnlyNullableEntries: Fun3<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>, InOrderOnlyReportingOptions.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        toContainInOrderOnlyEntries.forSubjectLessTest({ toEqual(2.5) }, arrayOf(), emptyInOrderOnlyReportOptions)
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        "$describePrefix[nullable] ",
        toContainInOrderOnlyNullableEntries.forSubjectLessTest(null, arrayOf(), emptyInOrderOnlyReportOptions)
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, listOf(1.2, 2.1),
        *toContainInOrderOnlyEntries.forExpectationCreatorTest(
            "$toEqualDescr: 1.2", "$toEqualDescr: 2.1",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.1) }), emptyInOrderOnlyReportOptions
        )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable] ", listOf(1.2, 2.1) as Iterable<Double?>,
        *toContainInOrderOnlyNullableEntries.forExpectationCreatorTest(
            "$toEqualDescr: 1.2", "$toEqualDescr: 2.1",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.1) }), emptyInOrderOnlyReportOptions
        )
    ) {})

    fun Expect<String>.elementSuccess(index: Int, actual: Any, expected: String): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q$successfulBulletPoint$featureArrow${elementWithIndex(index)}: $actual\\E.*$separator" +
                "$indentRootBulletPoint$indentSuccessfulBulletPoint$indentFeatureArrow$featureBulletPoint$expected"
        )
    }

    fun Expect<String>.elementFailing(
        index: Int,
        actual: Any,
        expected: String,
        explaining: Boolean = false,
        withBulletPoint: Boolean = true
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q${if (withBulletPoint) failingBulletPoint else ""}$featureArrow${elementWithIndex(index)}: $actual\\E.*$separator" +
                "$indentRootBulletPoint$indentFailingBulletPoint$indentFeatureArrow${if (explaining) "$indentFeatureBulletPoint$explanatoryBulletPoint" else featureBulletPoint}$expected"
        )
    }

    fun Expect<String>.elementNonExisting(
        index: Int,
        expected: String,
        withBulletPoint: Boolean = true
    ): Expect<String> =
        elementFailing(index, sizeExceeded, expected, explaining = true, withBulletPoint = withBulletPoint)

    nonNullableCases(
        describePrefix,
        toContainInOrderOnlyEntries,
        toContainInOrderOnlyNullableEntries
    ) { toContainEntriesFunArr ->

        fun Expect<Iterable<Double>>.toContainEntriesFun(
            t: Expect<Double>.() -> Unit,
            vararg tX: Expect<Double>.() -> Unit,
            report: InOrderOnlyReportingOptions.() -> Unit = emptyInOrderOnlyReportOptions
        ) = toContainEntriesFunArr(t, tX, report)

        context("empty collection") {
            it("$toBeLessThanFun(1.1) throws AssertionError") {
                expect {
                    expect(emptyIterable()).toContainEntriesFun({ toBeLessThan(1.1) })
                }.toThrow<AssertionError> {
                    message {
                        toContain("$rootBulletPoint$toContainInOrderOnly:")
                        elementNonExisting(0, "$toBeLessThanDescr: 1.1")
                        notToContain(additionalElements)
                        toContainSize(0, 1)
                    }
                }
            }
            it("$toBeLessThanFun(1.1) and $toBeGreaterThanFun(4.1) throws AssertionError") {
                expect {
                    expect(emptyIterable()).toContainEntriesFun({ toBeLessThan(1.1) }, { toBeGreaterThan(4.1) })
                }.toThrow<AssertionError> {
                    message {
                        toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                        elementNonExisting(0, "$toBeLessThanDescr: 1.1")
                        elementNonExisting(1, "$toBeGreaterThanDescr: 4.1")
                        notToContain(additionalElements)
                        toContainSize(0, 2)
                    }
                }
            }
        }

        context("iterable ${oneToFour().toList()}") {

            context("happy case") {
                it("1.1, 2.1, 3.1, 4.1, 4.1") {
                    expect(oneToFour()).toContainEntriesFun(
                        { toEqual(1.1) },
                        { toEqual(2.1) },
                        { toEqual(3.1) },
                        { toEqual(4.1) },
                        { toEqual(4.1) })
                }
                it("$toBeLessThanFun(5.1), $toBeLessThanFun(5.1), $toBeLessThanFun(5.1), $toBeLessThanFun(5.1), $toBeLessThanFun(5.1)") {
                    expect(oneToFour()).toContainEntriesFun(
                        { toBeLessThan(5.1) },
                        { toBeLessThan(5.1) },
                        { toBeLessThan(5.1) },
                        { toBeLessThan(5.1) },
                        { toBeLessThan(5.1) })
                }
            }

            context("error cases ... throws AssertionError") {

                it("$toBeLessThanFun(5.1), 1.1, 2.1, $toBeGreaterThanFun(2.1), 4.1 -- wrong order") {
                    expect {
                        expect(oneToFour()).toContainEntriesFun(
                            { toBeLessThan(5.1) },
                            { toEqual(1.1) },
                            { toEqual(2.1) },
                            { toBeGreaterThan(2.1) },
                            { toEqual(4.1) })
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.1, "$toBeLessThanDescr: 5.1")
                            elementFailing(1, 2.1, "$toEqualDescr: 1.1")
                            elementFailing(2, 3.1, "$toEqualDescr: 2.1")
                            elementSuccess(3, 4.1, "$toBeGreaterThanDescr: 2.1")
                            elementSuccess(4, 4.1, "$toEqualDescr: 4.1")
                            notToContain(sizeDescr)
                        }
                    }
                }

                it("1.1, 2.1, 3.1, 4.1 -- 4.1 was missing") {
                    expect {
                        expect(oneToFour()).toContainEntriesFun(
                            { toEqual(1.1) },
                            { toEqual(2.1) },
                            { toEqual(3.1) },
                            { toEqual(4.1) })
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.1, "$toEqualDescr: 1.1")
                            elementSuccess(1, 2.1, "$toEqualDescr: 2.1")
                            elementSuccess(2, 3.1, "$toEqualDescr: 3.1")
                            elementSuccess(3, 4.1, "$toEqualDescr: 4.1")
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
                        expect(oneToFour()).toContainEntriesFun({ toEqual(1.1) }, { toEqual(4.1) })
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.1, "$toEqualDescr: 1.1")
                            elementFailing(1, 2.1, "$toEqualDescr: 4.1")
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
                it("1.1, 3.1, $toBeGreaterThanFun(4.1) -- $toBeGreaterThanFun(4.1) is wrong and 4.1 and 4.1 are missing") {
                    expect {
                        expect(oneToFour()).toContainEntriesFun({ toEqual(1.1) },
                            { toEqual(3.1) }, { toBeGreaterThan(4.1) })
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.1, "$toEqualDescr: 1.1")
                            elementFailing(1, 2.1, "$toEqualDescr: 3.1")
                            elementFailing(2, 3.1, "$toBeGreaterThanDescr: 4.1")
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
                        expect(oneToFour()).toContainEntriesFun(
                            { toEqual(1.1) },
                            { toEqual(2.1) },
                            { toEqual(3.1) },
                            { toEqual(4.1) },
                            { toEqual(4.1) },
                            { toEqual(5.1) })
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.1, "$toEqualDescr: 1.1")
                            elementSuccess(1, 2.1, "$toEqualDescr: 2.1")
                            elementSuccess(2, 3.1, "$toEqualDescr: 3.1")
                            elementSuccess(3, 4.1, "$toEqualDescr: 4.1")
                            elementSuccess(4, 4.1, "$toEqualDescr: 4.1")
                            elementNonExisting(5, "$toEqualDescr: 5.1")
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
                        expect(oneToFour()).toContainEntriesFun(
                            { toEqual(1.1) },
                            { toEqual(2.1) },
                            { toEqual(3.1) },
                            { toEqual(4.1) },
                            { toEqual(4.1) },
                            { toEqual(5.1) },
                            report = { showOnlyFailing() })
                    }.toThrow<AssertionError> {
                        message {
                            notToContainElement(0, 1.1)
                            notToContainElement(1, 2.1)
                            notToContainElement(2, 3.1)
                            notToContainElement(3, 4.1)
                            notToContainElement(4, 4.1)
                            elementNonExisting(5, "$toEqualDescr: 5.1", withBulletPoint = false)
                        }
                    }
                }
                it("shows only failing with report option `showOnlyFailingIfMoreExpectedElementsThan(3)` because there are 5") {
                    expect {
                        expect(oneToFour()).toContainEntriesFun(
                            { toEqual(1.1) },
                            { toEqual(2.1) },
                            { toEqual(3.1) },
                            { toEqual(4.1) },
                            { toEqual(4.1) },
                            { toEqual(5.1) },
                            report = { showOnlyFailingIfMoreExpectedElementsThan(3) })
                    }.toThrow<AssertionError> {
                        message {
                            notToContainElement(0, 1.1)
                            notToContainElement(1, 2.1)
                            notToContainElement(2, 3.1)
                            notToContainElement(3, 4.1)
                            notToContainElement(4, 4.1)
                            elementNonExisting(5, "$toEqualDescr: 5.1", withBulletPoint = false)
                        }
                    }
                }

            }

            context("iterable $oneToEleven") {
                it("shows only failing per default as there are more than 10 expected elements") {
                    expect {
                        expect(oneToEleven).toContainEntriesFun(
                            { toEqual(1.1) },
                            { toEqual(2.1) },
                            { toEqual(3.1) },
                            { toEqual(4.1) },
                            { toEqual(-1.1) },
                            { toEqual(6.1) },
                            { toEqual(7.1) },
                            { toEqual(-2.1) },
                            { toEqual(9.1) },
                            { toEqual(10.1) },
                            { toEqual(11.1) }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            notToContainElement(0, 1.1)
                            notToContainElement(1, 2.1)
                            notToContainElement(2, 3.1)
                            notToContainElement(3, 4.1)
                            elementFailing(4, 5.1, "$toEqualDescr: -1.1", withBulletPoint = false)
                            notToContainElement(5, 6.1)
                            notToContainElement(6, 7.1)
                            elementFailing(7, 8.1, "$toEqualDescr: -2.1", withBulletPoint = false)
                            notToContainElement(8, 9.1)
                            notToContainElement(9, 10.1)
                            notToContainElement(10, 11.1)
                        }
                    }
                }
                it("shows all with report option `showAlwaysSummary`") {
                    expect {
                        expect(oneToEleven).toContainEntriesFun(
                            { toEqual(1.1) },
                            { toEqual(2.1) },
                            { toEqual(3.1) },
                            { toEqual(4.1) },
                            { toEqual(-1.1) },
                            { toEqual(6.1) },
                            { toEqual(7.1) },
                            { toEqual(-2.1) },
                            { toEqual(9.1) },
                            { toEqual(10.1) },
                            { toEqual(11.1) },
                            report = { showAlwaysSummary() }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            elementSuccess(0, 1.1, "$toEqualDescr: 1.1")
                            elementSuccess(1, 2.1, "$toEqualDescr: 2.1")
                            elementSuccess(2, 3.1, "$toEqualDescr: 3.1")
                            elementSuccess(3, 4.1, "$toEqualDescr: 4.1")
                            elementFailing(4, 5.1, "$toEqualDescr: -1.1", withBulletPoint = false)
                            elementSuccess(5, 6.1, "$toEqualDescr: 6.1")
                            elementSuccess(6, 7.1, "$toEqualDescr: 7.1")
                            elementFailing(7, 8.1, "$toEqualDescr: -2.1", withBulletPoint = false)
                            elementSuccess(8, 9.1, "$toEqualDescr: 9.1")
                            elementSuccess(9, 10.1, "$toEqualDescr: 10.1")
                            elementSuccess(10, 11.1, "$toEqualDescr: 11.1")
                        }
                    }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        fun Expect<Iterable<Double?>>.toContainInOrderOnlyNullableEntriesFun(
            t: (Expect<Double>.() -> Unit)?,
            vararg tX: (Expect<Double>.() -> Unit)?,
            report: InOrderOnlyReportingOptions.() -> Unit = emptyInOrderOnlyReportOptions
        ) = toContainInOrderOnlyNullableEntries(this, t, tX, report)

        describeFun(toContainInOrderOnlyNullableEntries) {

            val null1null3 = { sequenceOf(null, 1.1, null, 3.1).constrainOnce().asIterable() }

            context("iterable ${null1null3().toList()}") {

                context("happy case") {
                    it("null, 1.1, null, 3.1") {
                        expect(null1null3()).toContainInOrderOnlyNullableEntriesFun(
                            null,
                            { toEqual(1.1) },
                            null,
                            { toEqual(3.1) })
                    }
                    it("null, $toBeLessThanFun(5.1), null, $toBeLessThanFun(5.1)") {
                        expect(null1null3()).toContainInOrderOnlyNullableEntriesFun(
                            null,
                            { toBeLessThan(5.1) },
                            null,
                            { toBeLessThan(5.1) }
                        )
                    }
                }

                context("error cases (throws AssertionError)") {

                    it("null, null, $toBeLessThanFun(5.1), $toBeGreaterThanFun(2.1) -- wrong order") {
                        expect {
                            expect(null1null3()).toContainInOrderOnlyNullableEntriesFun(
                                null,
                                null,
                                { toBeLessThan(5.1) },
                                { toBeGreaterThan(2.1) }
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                                elementSuccess(0, "null", "$toEqualDescr: null")
                                elementFailing(1, 1.1, "$toEqualDescr: null")
                                elementFailing(2, "null", "$toBeLessThanDescr: 5.1", explaining = true)
                                elementSuccess(3, 3.1, "$toBeGreaterThanDescr: 2.1")
                                notToContain(sizeDescr)
                            }
                        }
                    }

                    it("null, 1.1, null, 3.1, null -- null too much") {
                        expect {
                            expect(null1null3()).toContainInOrderOnlyNullableEntriesFun(
                                null,
                                { toEqual(1.1) },
                                null,
                                { toEqual(3.1) },
                                null
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                                elementSuccess(0, "null", "$toEqualDescr: null")
                                elementSuccess(1, 1.1, "$toEqualDescr: 1.1")
                                elementSuccess(2, "null", "$toEqualDescr: null")
                                elementSuccess(3, 3.1, "$toEqualDescr: 3.1")
                                elementNonExisting(4, "$toEqualDescr: null")
                                toContainSize(4, 5)
                            }
                        }
                    }
                }
            }
        }
    }
})

