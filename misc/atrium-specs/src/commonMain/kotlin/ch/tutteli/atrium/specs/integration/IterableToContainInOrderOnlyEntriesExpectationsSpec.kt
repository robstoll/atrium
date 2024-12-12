package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.lineSeparator

abstract class IterableToContainInOrderOnlyEntriesExpectationsSpec(
    toContainInOrderOnlyEntries: Fun3<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>, InOrderOnlyReportingOptions.() -> Unit>,
    toContainInOrderOnlyNullableEntries: Fun3<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>, InOrderOnlyReportingOptions.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        toContainInOrderOnlyEntries.forSubjectLess({ toEqual(2.5) }, arrayOf(), emptyInOrderOnlyReportOptions)
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        "$describePrefix[nullable] ",
        toContainInOrderOnlyNullableEntries.forSubjectLess(null, arrayOf(), emptyInOrderOnlyReportOptions)
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, listOf(1.2, 2.0),
        *toContainInOrderOnlyEntries.forAssertionCreatorSpec(
            "$toEqualDescr\\s+: 1.2", "$toEqualDescr\\s+: 2.0",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.0) }), emptyInOrderOnlyReportOptions
        )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable] ", listOf(1.2, 2.0) as Iterable<Double?>,
        *toContainInOrderOnlyNullableEntries.forAssertionCreatorSpec(
            "$toEqualDescr\\s+: 1.2", "$toEqualDescr\\s+: 2.0",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.0) }), emptyInOrderOnlyReportOptions
        )
    ) {})

    fun Expect<String>.elementSuccess(index: Int, actual: Any, expected: String): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q$s$f${elementWithIndex(index)}: $actual\\E.*$lineSeparator" +
                    "$indentRoot$indentSuccess$indentF$featureBulletPoint$expected"
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
            "\\Q${if (withBulletPoint) x else ""}$f${elementWithIndex(index)}: $actual\\E.*$lineSeparator" +
                    "$indentRoot$indentX$indentF${if (explaining) "$indentFeature$explanatoryBulletPoint" else featureBulletPoint}$expected"
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
            it("$toBeLessThanFun(1.0) throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainEntriesFun({ toBeLessThan(1.0) })
                }.toThrow<AssertionError> {
                    message {
                        toContain("$rootBulletPoint$toContainInOrderOnly:")
                        elementNonExisting(0, "$toBeLessThanDescr: 1.0")
                        notToContain(additionalElements)
                        toContainSize(0, 1)
                    }
                }
            }
            it("$toBeLessThanFun(1.0) and $toBeGreaterThanFun(4.0) throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainEntriesFun({ toBeLessThan(1.0) }, { toBeGreaterThan(4.0) })
                }.toThrow<AssertionError> {
                    message {
                        toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                        elementNonExisting(0, "$toBeLessThanDescr: 1.0")
                        elementNonExisting(1, "$toBeGreaterThanDescr: 4.0")
                        notToContain(additionalElements)
                        toContainSize(0, 2)
                    }
                }
            }
        }

        context("iterable ${oneToFour().toList()}") {

            context("happy case") {
                it("1.0, 2.0, 3.0, 4.0, 4.0") {
                    expect(oneToFour()).toContainEntriesFun(
                        { toEqual(1.0) },
                        { toEqual(2.0) },
                        { toEqual(3.0) },
                        { toEqual(4.0) },
                        { toEqual(4.0) })
                }
                it("$toBeLessThanFun(5.0), $toBeLessThanFun(5.0), $toBeLessThanFun(5.0), $toBeLessThanFun(5.0), $toBeLessThanFun(5.0)") {
                    expect(oneToFour()).toContainEntriesFun(
                        { toBeLessThan(5.0) },
                        { toBeLessThan(5.0) },
                        { toBeLessThan(5.0) },
                        { toBeLessThan(5.0) },
                        { toBeLessThan(5.0) })
                }
            }

            context("error cases ... throws AssertionError") {

                it("$toBeLessThanFun(5.0), 1.0, 2.0, $toBeGreaterThanFun(2.0), 4.0 -- wrong order") {
                    expect {
                        expect(oneToFour()).toContainEntriesFun(
                            { toBeLessThan(5.0) },
                            { toEqual(1.0) },
                            { toEqual(2.0) },
                            { toBeGreaterThan(2.0) },
                            { toEqual(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.0, "$toBeLessThanDescr: 5.0")
                            elementFailing(1, 2.0, "$toEqualDescr: 1.0")
                            elementFailing(2, 3.0, "$toEqualDescr: 2.0")
                            elementSuccess(3, 4.0, "$toBeGreaterThanDescr: 2.0")
                            elementSuccess(4, 4.0, "$toEqualDescr: 4.0")
                            notToContain(sizeDescr)
                        }
                    }
                }

                it("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                    expect {
                        expect(oneToFour()).toContainEntriesFun(
                            { toEqual(1.0) },
                            { toEqual(2.0) },
                            { toEqual(3.0) },
                            { toEqual(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.0, "$toEqualDescr: 1.0")
                            elementSuccess(1, 2.0, "$toEqualDescr: 2.0")
                            elementSuccess(2, 3.0, "$toEqualDescr: 3.0")
                            elementSuccess(3, 4.0, "$toEqualDescr: 4.0")
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
                        expect(oneToFour()).toContainEntriesFun({ toEqual(1.0) }, { toEqual(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.0, "$toEqualDescr: 1.0")
                            elementFailing(1, 2.0, "$toEqualDescr: 4.0")
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
                it("1.0, 3.0, $toBeGreaterThanFun(4.0) -- $toBeGreaterThanFun(4.0) is wrong and 4.0 and 4.0 are missing") {
                    expect {
                        expect(oneToFour()).toContainEntriesFun({ toEqual(1.0) },
                            { toEqual(3.0) }, { toBeGreaterThan(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.0, "$toEqualDescr: 1.0")
                            elementFailing(1, 2.0, "$toEqualDescr: 3.0")
                            elementFailing(2, 3.0, "$toBeGreaterThanDescr: 4.0")
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
                        expect(oneToFour()).toContainEntriesFun(
                            { toEqual(1.0) },
                            { toEqual(2.0) },
                            { toEqual(3.0) },
                            { toEqual(4.0) },
                            { toEqual(4.0) },
                            { toEqual(5.0) })
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                            elementSuccess(0, 1.0, "$toEqualDescr: 1.0")
                            elementSuccess(1, 2.0, "$toEqualDescr: 2.0")
                            elementSuccess(2, 3.0, "$toEqualDescr: 3.0")
                            elementSuccess(3, 4.0, "$toEqualDescr: 4.0")
                            elementSuccess(4, 4.0, "$toEqualDescr: 4.0")
                            elementNonExisting(5, "$toEqualDescr: 5.0")
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
                            { toEqual(1.0) },
                            { toEqual(2.0) },
                            { toEqual(3.0) },
                            { toEqual(4.0) },
                            { toEqual(4.0) },
                            { toEqual(5.0) },
                            report = { showOnlyFailing() })
                    }.toThrow<AssertionError> {
                        message {
                            notToContainElement(0, 1.0)
                            notToContainElement(1, 2.0)
                            notToContainElement(2, 3.0)
                            notToContainElement(3, 4.0)
                            notToContainElement(4, 4.0)
                            elementNonExisting(5, "$toEqualDescr: 5.0", withBulletPoint = false)
                        }
                    }
                }
                it("shows only failing with report option `showOnlyFailingIfMoreExpectedElementsThan(3)` because there are 5") {
                    expect {
                        expect(oneToFour()).toContainEntriesFun(
                            { toEqual(1.0) },
                            { toEqual(2.0) },
                            { toEqual(3.0) },
                            { toEqual(4.0) },
                            { toEqual(4.0) },
                            { toEqual(5.0) },
                            report = { showOnlyFailingIfMoreExpectedElementsThan(3) })
                    }.toThrow<AssertionError> {
                        message {
                            notToContainElement(0, 1.0)
                            notToContainElement(1, 2.0)
                            notToContainElement(2, 3.0)
                            notToContainElement(3, 4.0)
                            notToContainElement(4, 4.0)
                            elementNonExisting(5, "$toEqualDescr: 5.0", withBulletPoint = false)
                        }
                    }
                }

            }

            context("iterable $oneToEleven") {
                it("shows only failing per default as there are more than 10 expected elements") {
                    expect {
                        expect(oneToEleven).toContainEntriesFun(
                            { toEqual(1.0) },
                            { toEqual(2.0) },
                            { toEqual(3.0) },
                            { toEqual(4.0) },
                            { toEqual(-1.0) },
                            { toEqual(6.0) },
                            { toEqual(7.0) },
                            { toEqual(-2.0) },
                            { toEqual(9.0) },
                            { toEqual(10.0) },
                            { toEqual(11.0) }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            notToContainElement(0, 1.0)
                            notToContainElement(1, 2.0)
                            notToContainElement(2, 3.0)
                            notToContainElement(3, 4.0)
                            elementFailing(4, 5.0, "$toEqualDescr: -1.0", withBulletPoint = false)
                            notToContainElement(5, 6.0)
                            notToContainElement(6, 7.0)
                            elementFailing(7, 8.0, "$toEqualDescr: -2.0", withBulletPoint = false)
                            notToContainElement(8, 9.0)
                            notToContainElement(9, 10.0)
                            notToContainElement(10, 11.0)
                        }
                    }
                }
                it("shows all with report option `showAlwaysSummary`") {
                    expect {
                        expect(oneToEleven).toContainEntriesFun(
                            { toEqual(1.0) },
                            { toEqual(2.0) },
                            { toEqual(3.0) },
                            { toEqual(4.0) },
                            { toEqual(-1.0) },
                            { toEqual(6.0) },
                            { toEqual(7.0) },
                            { toEqual(-2.0) },
                            { toEqual(9.0) },
                            { toEqual(10.0) },
                            { toEqual(11.0) },
                            report = { showAlwaysSummary() }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            elementSuccess(0, 1.0, "$toEqualDescr: 1.0")
                            elementSuccess(1, 2.0, "$toEqualDescr: 2.0")
                            elementSuccess(2, 3.0, "$toEqualDescr: 3.0")
                            elementSuccess(3, 4.0, "$toEqualDescr: 4.0")
                            elementFailing(4, 5.0, "$toEqualDescr: -1.0", withBulletPoint = false)
                            elementSuccess(5, 6.0, "$toEqualDescr: 6.0")
                            elementSuccess(6, 7.0, "$toEqualDescr: 7.0")
                            elementFailing(7, 8.0, "$toEqualDescr: -2.0", withBulletPoint = false)
                            elementSuccess(8, 9.0, "$toEqualDescr: 9.0")
                            elementSuccess(9, 10.0, "$toEqualDescr: 10.0")
                            elementSuccess(10, 11.0, "$toEqualDescr: 11.0")
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

            val null1null3 = { sequenceOf(null, 1.0, null, 3.0).constrainOnce().asIterable() }

            context("iterable ${null1null3().toList()}") {

                context("happy case") {
                    it("null, 1.0, null, 3.0") {
                        expect(null1null3()).toContainInOrderOnlyNullableEntriesFun(
                            null,
                            { toEqual(1.0) },
                            null,
                            { toEqual(3.0) })
                    }
                    it("null, $toBeLessThanFun(5.0), null, $toBeLessThanFun(5.0)") {
                        expect(null1null3()).toContainInOrderOnlyNullableEntriesFun(
                            null,
                            { toBeLessThan(5.0) },
                            null,
                            { toBeLessThan(5.0) }
                        )
                    }
                }

                context("error cases (throws AssertionError)") {

                    it("null, null, $toBeLessThanFun(5.0), $toBeGreaterThanFun(2.0) -- wrong order") {
                        expect {
                            expect(null1null3()).toContainInOrderOnlyNullableEntriesFun(
                                null,
                                null,
                                { toBeLessThan(5.0) },
                                { toBeGreaterThan(2.0) }
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                                elementSuccess(0, "null", "$toEqualDescr: null")
                                elementFailing(1, 1.0, "$toEqualDescr: null")
                                elementFailing(2, "null", "$toBeLessThanDescr: 5.0", explaining = true)
                                elementSuccess(3, 3.0, "$toBeGreaterThanDescr: 2.0")
                                notToContain(sizeDescr)
                            }
                        }
                    }

                    it("null, 1.0, null, 3.0, null -- null too much") {
                        expect {
                            expect(null1null3()).toContainInOrderOnlyNullableEntriesFun(
                                null,
                                { toEqual(1.0) },
                                null,
                                { toEqual(3.0) },
                                null
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).value("$rootBulletPoint$toContainInOrderOnly:")
                                elementSuccess(0, "null", "$toEqualDescr: null")
                                elementSuccess(1, 1.0, "$toEqualDescr: 1.0")
                                elementSuccess(2, "null", "$toEqualDescr: null")
                                elementSuccess(3, 3.0, "$toEqualDescr: 3.0")
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

