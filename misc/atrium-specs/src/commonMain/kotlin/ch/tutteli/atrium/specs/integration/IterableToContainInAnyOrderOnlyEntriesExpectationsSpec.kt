package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*

abstract class IterableToContainInAnyOrderOnlyEntriesExpectationsSpec(
    toContainInAnyOrderOnlyEntries: Fun3<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>, InAnyOrderOnlyReportingOptions.() -> Unit>,
    toContainInAnyOrderOnlyNullableEntries: Fun3<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>, InAnyOrderOnlyReportingOptions.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        toContainInAnyOrderOnlyEntries.forSubjectLessTest({ toEqual(2.5) }, arrayOf(), emptyInAnyOrderOnlyReportOptions)
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        toContainInAnyOrderOnlyNullableEntries.forSubjectLessTest(null, arrayOf(), emptyInAnyOrderOnlyReportOptions)
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, listOf(1.2, 2.1),
        *toContainInAnyOrderOnlyEntries.forExpectationCreatorTest(
            "$toEqualDescr: 1.2", "$toEqualDescr: 2.1",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.1) }), emptyInAnyOrderOnlyReportOptions
        )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable] ", listOf(1.2, 2.1),
        *toContainInAnyOrderOnlyNullableEntries.forExpectationCreatorTest(
            "$toEqualDescr: 1.2", "$toEqualDescr: 2.1",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.1) }), emptyInAnyOrderOnlyReportOptions
        )
    ) {})


    //@formatter:off
    val anEntryAfterSuccess = "$anElementWhichNeedsDescr: $separator$indentRootBulletPoint$indentSuccessfulBulletPoint$indentListBulletPoint$explanatoryBulletPoint"
    val anEntryAfterFailing = "$anElementWhichNeedsDescr: $separator$indentRootBulletPoint$indentFailingBulletPoint$indentListBulletPoint$explanatoryBulletPoint"
    //@formatter:on

    nonNullableCases(
        describePrefix,
        toContainInAnyOrderOnlyEntries,
        toContainInAnyOrderOnlyNullableEntries
    ) { toContainEntriesFunArr ->
        fun Expect<Iterable<Double>>.toContainEntriesFun(
            t: Expect<Double>.() -> Unit,
            vararg tX: Expect<Double>.() -> Unit,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = {}
        ) = toContainEntriesFunArr(t, tX, report)

        context("empty collection") {
            it("$toBeLessThanFun(1.1) throws AssertionError") {
                expect {
                    expect(emptyIterable()).toContainEntriesFun({ toBeLessThan(1.1) })
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            "$rootBulletPoint$toContainInAnyOrderOnly:",
                            "$failingBulletPoint$anEntryAfterFailing$toBeLessThanDescr: 1.1"
                        )
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
                        toContain.exactly(1).values(
                            "$rootBulletPoint$toContainInAnyOrderOnly:",
                            "$failingBulletPoint$anEntryAfterFailing$toBeLessThanDescr: 1.1",
                            "$failingBulletPoint$anEntryAfterFailing$toBeGreaterThanDescr: 4.1"
                        )
                        notToContain(additionalElements)
                        toContainSize(0, 2)
                    }
                }
            }
        }

        context("iterable ${oneToFour().toList()}") {

            context("happy cases") {

                listOf(
                    arrayOf(1.1, 2.1, 3.1, 4.1, 4.1),
                    arrayOf(1.1, 3.1, 2.1, 4.1, 4.1),
                    arrayOf(3.1, 4.1, 2.1, 1.1, 4.1),
                    arrayOf(2.1, 4.1, 4.1, 1.1, 3.1),
                    arrayOf(2.1, 4.1, 1.1, 4.1, 3.1),
                    arrayOf(4.1, 4.1, 3.1, 2.1, 1.1)
                ).forEach {
                    it("${it.joinToString()} with matcher $toEqualFun") {
                        expect(oneToFour()).toContainEntriesFun(
                            { toEqual(it.first()) },
                            *(it.drop(1).map { val f: Expect<Double>.() -> Unit = { toEqual(it) }; f }).toTypedArray()
                        )
                    }
                }

                it("1.1, 2.1, 3.1, 4.1 and $toBeGreaterThanFun(0.1)") {
                    expect(oneToFour()).toContainEntriesFun(
                        { toEqual(1.1) },
                        { toEqual(2.1) },
                        { toEqual(3.1) },
                        { toEqual(4.1) },
                        { toBeGreaterThan(0.1) })
                }
                it(" $toBeLessThanFun(3.1), 2.1, 3.1, 4.1 and 4.1") {
                    expect(oneToFour()).toContainEntriesFun(
                        { toBeLessThan(3.1) },
                        { toEqual(2.1) },
                        { toEqual(3.1) },
                        { toEqual(4.1) },
                        { toEqual(4.1) })
                }
                it("2.1, $toBeLessThanFun(5.1), 3.1, 4.1 and 4.1") {
                    expect(oneToFour()).toContainEntriesFun(
                        { toEqual(2.1) },
                        { toBeLessThan(5.1) },
                        { toEqual(3.1) },
                        { toEqual(4.1) },
                        { toEqual(4.1) })
                }
            }

            context("error cases (throws AssertionError)") {

                context("additional entries") {
                    it("1.1, 2.1, 3.1, 4.1 -- 4.1 was missing") {
                        expect {
                            expect(oneToFour()).toContainEntriesFun(
                                { toEqual(1.1) },
                                { toEqual(2.1) },
                                { toEqual(3.1) },
                                { toEqual(4.1) })
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 1.1",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 2.1",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 3.1",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 4.1",
                                    "$warningBulletPoint$additionalElements:",
                                    "${listBulletPoint}4.1"
                                )
                                toContainSize(5, 4)
                            }
                        }
                    }

                    it("$toBeLessThanFun(3.1), isGreaterThan(3.1) -- 2.1, 3.1 and 4.1 was missing") {
                        expect {
                            expect(oneToFour()).toContainEntriesFun({ toBeLessThan(3.1) }, { toBeGreaterThan(3.1) })
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeLessThanDescr: 3.1",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeGreaterThanDescr: 3.1",
                                    "$warningBulletPoint$additionalElements:",
                                    "${listBulletPoint}2.1",
                                    "${listBulletPoint}3.1",
                                    "${listBulletPoint}4.1"
                                )
                                toContainSize(5, 2)
                            }
                        }
                    }
                }

                context("mismatches") {
                    it("first wins: $toBeLessThanFun(5.1), 1.1, 2.1, 3.1, 4.1") {
                        expect {
                            expect(oneToFour()).toContainEntriesFun(
                                { toBeLessThan(5.1) },
                                { toEqual(1.1) },
                                { toEqual(2.1) },
                                { toEqual(3.1) },
                                { toEqual(4.1) })
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeLessThanDescr: 5.1",
                                    "$failingBulletPoint$anEntryAfterFailing$toEqualDescr: 1.1",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 2.1",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 3.1",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 4.1",
                                    "$warningBulletPoint$mismatches:",
                                    "${listBulletPoint}4.1"
                                )
                                notToContain(sizeDescr)
                            }
                        }
                    }
                }

                context("mismatches and additional entries") {
                    it("1.1, $toBeGreaterThanFun(3.1), $toBeGreaterThanFun(4.1) -- $toBeGreaterThanFun(4.1) is wrong and 2.1, 3.1 and 4.1 are missing") {
                        expect {
                            expect(oneToFour()).toContainEntriesFun(
                                { toEqual(1.1) },
                                { toBeGreaterThan(3.1) },
                                { toBeGreaterThan(4.1) })
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 1.1",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeGreaterThanDescr: 3.1",
                                    "$failingBulletPoint$anEntryAfterFailing$toBeGreaterThanDescr: 4.1",
                                    "$warningBulletPoint$mismatchesAdditionalElements:",
                                    "${listBulletPoint}2.1",
                                    "${listBulletPoint}3.1",
                                    "${listBulletPoint}4.1"
                                )
                                toContainSize(5, 3)
                            }
                        }
                    }
                }

                context("too many matcher") {
                    it("1.1, 2.1, 3.1, 4.1, 4.1, 5.1 -- 5.1 was too much") {
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
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 1.1",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 2.1",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 3.1",
                                    "$failingBulletPoint$anEntryAfterSuccess$toEqualDescr: 5.1"
                                )
                                toContain.exactly(2)
                                    .value("$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 4.1")
                                toContainSize(5, 6)
                                notToContain(additionalElements, mismatches, mismatchesAdditionalElements)
                            }
                        }
                    }
                }
            }
        }

        context("report options") {
            context("iterable ${oneToFour().toList()}") {
                it("shows only failing with report option `showOnlyFailing`") {
                    expect {
                        expect(oneToFour()).toContainEntriesFun({ toEqual(2.1) }, report = { showOnlyFailing() })
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 1)
                            toContain.exactly(1).values(
                                "$rootBulletPoint$toContainInAnyOrderOnly:",
                                "$warningBulletPoint$additionalElements:",
                                "${listBulletPoint}1.1",
                                "${listBulletPoint}3.1"
                            )
                            toContain.exactly(2).value("${listBulletPoint}4.1")
                            notToContain("$toEqualDescr: 2.1")

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
                            toContainSize(5, 6)
                            toContain.exactly(1).values("$listBulletPoint$anEntryAfterSuccess$toEqualDescr: 5.1")
                            notToContain(
                                "$toEqualDescr: 1.1",
                                "$toEqualDescr: 2.1",
                                "$toEqualDescr: 3.1",
                                "$toEqualDescr: 4.1"
                            )
                            notToContain(additionalElements, mismatches, mismatchesAdditionalElements)
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
                            toContain.exactly(1).values(
                                "$listBulletPoint$anEntryAfterSuccess$toEqualDescr: -1.1",
                                "$listBulletPoint$anEntryAfterSuccess$toEqualDescr: -2.1",
                                "$warningBulletPoint$mismatches:",
                                "${listBulletPoint}5.1",
                                "${listBulletPoint}8.1"
                            )
                            notToContain(
                                "$toEqualDescr: 1.1",
                                "$toEqualDescr: 2.1",
                                "$toEqualDescr: 3.1",
                                "$toEqualDescr: 4.1",
                                "$toEqualDescr: 6.1",
                                "$toEqualDescr: 7.1",
                                "$toEqualDescr: 9.1",
                                "$toEqualDescr: 10.1",
                                "$toEqualDescr: 11.1",

                                additionalElements, mismatchesAdditionalElements
                            )
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
                            toContain.exactly(1).values(
                                "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 1.1",
                                "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 2.1",
                                "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 3.1",
                                "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 4.1",
                                "$failingBulletPoint$anEntryAfterFailing$toEqualDescr: -1.1",
                                "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 6.1",
                                "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 7.1",
                                "$failingBulletPoint$anEntryAfterFailing$toEqualDescr: -2.1",
                                "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 9.1",
                                "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 10.1",
                                "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 11.1",
                                "$warningBulletPoint$mismatches:",
                                "${listBulletPoint}5.1",
                                "${listBulletPoint}8.1"
                            )
                            notToContain(additionalElements, mismatchesAdditionalElements)
                        }
                    }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        fun Expect<Iterable<Double?>>.toContainFun(
            t: (Expect<Double>.() -> Unit)?,
            vararg tX: (Expect<Double>.() -> Unit)?,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = emptyInAnyOrderOnlyReportOptions
        ) = toContainInAnyOrderOnlyNullableEntries(this, t, tX, report)

        describeFun(toContainInAnyOrderOnlyNullableEntries) {
            val null1null3 = { sequenceOf(null, 1.1, null, 3.1).constrainOnce().asIterable() }

            context("iterable ${null1null3().toList()}") {
                context("happy cases (do not throw)") {
                    it("null, $toEqualFun(1.1), null, $toEqualFun(3.1)") {
                        expect(null1null3()).toContainFun(
                            null, { toEqual(1.1) }, null, { toEqual(3.1) }
                        )
                    }
                    it("$toEqualFun(1.1), null, null, $toEqualFun(3.1)") {
                        expect(null1null3()).toContainFun(
                            { toEqual(1.1) }, null, null, { toEqual(3.1) }
                        )
                    }
                    it("$toEqualFun(1.1), null, $toEqualFun(3.1), null") {
                        expect(null1null3()).toContainFun(
                            { toEqual(1.1) }, null, { toEqual(3.1) }, null
                        )
                    }
                    it("$toEqualFun(1.1), $toEqualFun(3.1), null, null") {
                        expect(null1null3()).toContainFun(
                            { toEqual(1.1) }, { toEqual(3.1) }, null, null
                        )
                    }
                }

                context("failing cases") {
                    it("null, $toEqualFun(1.1), $toEqualFun(3.1) -- second null was missing") {
                        expect {
                            expect(null1null3()).toContainFun(
                                null, { toEqual(1.1) }, { toEqual(3.1) }
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: null",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 1.1",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 3.1",
                                    "$warningBulletPoint$additionalElements:",
                                    "${listBulletPoint}null"
                                )
                                toContainSize(4, 3)
                            }
                        }
                    }

                    it("first wins: $toBeLessThanFun(4.1), null, null, $toEqualDescr(1.1)") {
                        expect {
                            expect(null1null3()).toContainFun(
                                { toBeLessThan(4.1) },
                                null,
                                null,
                                { toEqual(1.1) })
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(2).values(
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: null",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: null"
                                )
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeLessThanDescr: 4.1",
                                    "$failingBulletPoint$anEntryAfterFailing$toEqualDescr: 1.1",
                                    "$warningBulletPoint$mismatches:",
                                    "${listBulletPoint}3.1"
                                )
                                notToContain(sizeDescr)
                            }
                        }
                    }
                }
            }
        }
    }
})
