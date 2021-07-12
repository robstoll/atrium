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
        toContainInOrderOnlyEntries.forSubjectLess({ toEqual(2.5) }, arrayOf(), emptyInOrderOnlyReportOptions)
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        "$describePrefix[nullable] ",
        toContainInOrderOnlyNullableEntries.forSubjectLess(null, arrayOf(), emptyInOrderOnlyReportOptions)
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, listOf(1.2, 2.0),
        *toContainInOrderOnlyEntries.forAssertionCreatorSpec(
            "$toBeDescr: 1.2", "$toBeDescr: 2.0",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.0) }), emptyInOrderOnlyReportOptions
        )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable] ", listOf(1.2, 2.0) as Iterable<Double?>,
        *toContainInOrderOnlyNullableEntries.forAssertionCreatorSpec(
            "$toBeDescr: 1.2", "$toBeDescr: 2.0",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.0) }), emptyInOrderOnlyReportOptions
        )
    ) {})

    fun Expect<Iterable<Double?>>.toContainInOrderOnlyNullableEntriesFun(
        t: (Expect<Double>.() -> Unit)?,
        vararg tX: (Expect<Double>.() -> Unit)?,
        report : InOrderOnlyReportingOptions.() -> Unit = emptyInOrderOnlyReportOptions
    ) = toContainInOrderOnlyNullableEntries(this, t, tX, report)

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
        explaining: Boolean = false
    ): Expect<String> {
        return this.toContain.exactly(1).regex(
            "\\Q$failingBulletPoint$featureArrow${elementWithIndex(index)}: $actual\\E.*$separator" +
                "$indentRootBulletPoint$indentFailingBulletPoint$indentFeatureArrow${if (explaining) "$indentFeatureBulletPoint$explanatoryBulletPoint" else featureBulletPoint}$expected"
        )
    }

    fun Expect<String>.elementNonExisting(index: Int, expected: String): Expect<String> =
        elementFailing(index, sizeExceeded, expected, explaining = true)

    nonNullableCases(
        describePrefix,
        toContainInOrderOnlyEntries,
        toContainInOrderOnlyNullableEntries
    ) { toContainEntriesFunArr ->

        fun Expect<Iterable<Double>>.toContainEntriesFun(
            t: Expect<Double>.() -> Unit,
            vararg tX: Expect<Double>.() -> Unit,
            report : InOrderOnlyReportingOptions.() -> Unit = emptyInOrderOnlyReportOptions
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
                            elementFailing(1, 2.0, "$toBeDescr: 1.0")
                            elementFailing(2, 3.0, "$toBeDescr: 2.0")
                            elementSuccess(3, 4.0, "$toBeGreaterThanDescr: 2.0")
                            elementSuccess(4, 4.0, "$toBeDescr: 4.0")
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
                            elementSuccess(0, 1.0, "$toBeDescr: 1.0")
                            elementSuccess(1, 2.0, "$toBeDescr: 2.0")
                            elementSuccess(2, 3.0, "$toBeDescr: 3.0")
                            elementSuccess(3, 4.0, "$toBeDescr: 4.0")
                            toContain(
                                "$warningBulletPoint$additionalElements:",
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
                            elementSuccess(0, 1.0, "$toBeDescr: 1.0")
                            elementFailing(1, 2.0, "$toBeDescr: 4.0")
                            toContain(
                                "$warningBulletPoint$additionalElements:",
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
                            elementSuccess(0, 1.0, "$toBeDescr: 1.0")
                            elementFailing(1, 2.0, "$toBeDescr: 3.0")
                            elementFailing(2, 3.0, "$toBeGreaterThanDescr: 4.0")
                            toContain(
                                "$warningBulletPoint$additionalElements:",
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
                            elementSuccess(0, 1.0, "$toBeDescr: 1.0")
                            elementSuccess(1, 2.0, "$toBeDescr: 2.0")
                            elementSuccess(2, 3.0, "$toBeDescr: 3.0")
                            elementSuccess(3, 4.0, "$toBeDescr: 4.0")
                            elementSuccess(4, 4.0, "$toBeDescr: 4.0")
                            elementNonExisting(5, "$toBeDescr: 5.0")
                            toContainSize(5, 6)
                        }
                    }
                }
            }
        }
    }

    nullableCases(describePrefix) {

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
                                elementSuccess(0, "null", "$toBeDescr: null")
                                elementFailing(1, 1.0, "$toBeDescr: null")
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
                                elementSuccess(0, "null", "$toBeDescr: null")
                                elementSuccess(1, 1.0, "$toBeDescr: 1.0")
                                elementSuccess(2, "null", "$toBeDescr: null")
                                elementSuccess(3, 3.0, "$toBeDescr: 3.0")
                                elementNonExisting(4, "$toBeDescr: null")
                                toContainSize(4, 5)
                            }
                        }
                    }
                }
            }
        }
    }
})

