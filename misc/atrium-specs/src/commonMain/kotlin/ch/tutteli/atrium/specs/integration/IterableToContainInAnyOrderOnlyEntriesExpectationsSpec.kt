package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.lineSeparator

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
        describePrefix, listOf(1.2, 2.0),
        *toContainInAnyOrderOnlyEntries.forExpectationCreatorTest(
            "$toEqualDescr\\s+: 1.2", "$toEqualDescr\\s+: 2.0",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.0) }), emptyInAnyOrderOnlyReportOptions
        )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable] ", listOf(1.2, 2.0),
        *toContainInAnyOrderOnlyNullableEntries.forExpectationCreatorTest(
            "$toEqualDescr\\s+: 1.2", "$toEqualDescr\\s+: 2.0",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.0) }), emptyInAnyOrderOnlyReportOptions
        )
    ) {})


    //@formatter:off
    val anEntryAfterSuccess = "$anElementWhichNeedsDescr: $lineSeparator$indentRoot$indentSuccess$indentList$explanatoryBulletPoint"
    val anEntryAfterFailing = "$anElementWhichNeedsDescr: $lineSeparator$indentRoot$indentX$indentList$explanatoryBulletPoint"
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
            it("$toBeLessThanFun(1.0) throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainEntriesFun({ toBeLessThan(1.0) })
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            "$rootBulletPoint$toContainInAnyOrderOnly:",
                            "$x$anEntryAfterFailing$toBeLessThanDescr: 1.0"
                        )
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
                        toContain.exactly(1).values(
                            "$rootBulletPoint$toContainInAnyOrderOnly:",
                            "$x$anEntryAfterFailing$toBeLessThanDescr: 1.0",
                            "$x$anEntryAfterFailing$toBeGreaterThanDescr: 4.0"
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
                    arrayOf(1.0, 2.0, 3.0, 4.0, 4.0),
                    arrayOf(1.0, 3.0, 2.0, 4.0, 4.0),
                    arrayOf(3.0, 4.0, 2.0, 1.0, 4.0),
                    arrayOf(2.0, 4.0, 4.0, 1.0, 3.0),
                    arrayOf(2.0, 4.0, 1.0, 4.0, 3.0),
                    arrayOf(4.0, 4.0, 3.0, 2.0, 1.0)
                ).forEach {
                    it("${it.joinToString()} with matcher $toEqualFun") {
                        expect(oneToFour()).toContainEntriesFun(
                            { toEqual(it.first()) },
                            *(it.drop(1).map { val f: Expect<Double>.() -> Unit = { toEqual(it) }; f }).toTypedArray()
                        )
                    }
                }

                it("1.0, 2.0, 3.0, 4.0 and $toBeGreaterThanFun(0.0)") {
                    expect(oneToFour()).toContainEntriesFun(
                        { toEqual(1.0) },
                        { toEqual(2.0) },
                        { toEqual(3.0) },
                        { toEqual(4.0) },
                        { toBeGreaterThan(0.0) })
                }
                it(" $toBeLessThanFun(3.0), 2.0, 3.0, 4.0 and 4.0") {
                    expect(oneToFour()).toContainEntriesFun(
                        { toBeLessThan(3.0) },
                        { toEqual(2.0) },
                        { toEqual(3.0) },
                        { toEqual(4.0) },
                        { toEqual(4.0) })
                }
                it("2.0, $toBeLessThanFun(5.0), 3.0, 4.0 and 4.0") {
                    expect(oneToFour()).toContainEntriesFun(
                        { toEqual(2.0) },
                        { toBeLessThan(5.0) },
                        { toEqual(3.0) },
                        { toEqual(4.0) },
                        { toEqual(4.0) })
                }
            }

            context("error cases (throws AssertionError)") {

                context("additional entries") {
                    it("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                        expect {
                            expect(oneToFour()).toContainEntriesFun(
                                { toEqual(1.0) },
                                { toEqual(2.0) },
                                { toEqual(3.0) },
                                { toEqual(4.0) })
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$s$anEntryAfterSuccess$toEqualDescr: 1.0",
                                    "$s$anEntryAfterSuccess$toEqualDescr: 2.0",
                                    "$s$anEntryAfterSuccess$toEqualDescr: 3.0",
                                    "$s$anEntryAfterSuccess$toEqualDescr: 4.0",
                                    "$bb$additionalElements:",
                                    "${listBulletPoint}4.0"
                                )
                                toContainSize(5, 4)
                            }
                        }
                    }

                    it("$toBeLessThanFun(3.0), isGreaterThan(3.0) -- 2.0, 3.0 and 4.0 was missing") {
                        expect {
                            expect(oneToFour()).toContainEntriesFun({ toBeLessThan(3.0) }, { toBeGreaterThan(3.0) })
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$s$anEntryAfterSuccess$toBeLessThanDescr: 3.0",
                                    "$s$anEntryAfterSuccess$toBeGreaterThanDescr: 3.0",
                                    "$bb$additionalElements:",
                                    "${listBulletPoint}2.0",
                                    "${listBulletPoint}3.0",
                                    "${listBulletPoint}4.0"
                                )
                                toContainSize(5, 2)
                            }
                        }
                    }
                }

                context("mismatches") {
                    it("first wins: $toBeLessThanFun(5.0), 1.0, 2.0, 3.0, 4.0") {
                        expect {
                            expect(oneToFour()).toContainEntriesFun(
                                { toBeLessThan(5.0) },
                                { toEqual(1.0) },
                                { toEqual(2.0) },
                                { toEqual(3.0) },
                                { toEqual(4.0) })
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$s$anEntryAfterSuccess$toBeLessThanDescr: 5.0",
                                    "$x$anEntryAfterFailing$toEqualDescr: 1.0",
                                    "$s$anEntryAfterSuccess$toEqualDescr: 2.0",
                                    "$s$anEntryAfterSuccess$toEqualDescr: 3.0",
                                    "$s$anEntryAfterSuccess$toEqualDescr: 4.0",
                                    "$bb$mismatches:",
                                    "${listBulletPoint}4.0"
                                )
                                notToContain(sizeDescr)
                            }
                        }
                    }
                }

                context("mismatches and additional entries") {
                    it("1.0, $toBeGreaterThanFun(3.0), $toBeGreaterThanFun(4.0) -- $toBeGreaterThanFun(4.0) is wrong and 2.0, 3.0 and 4.0 are missing") {
                        expect {
                            expect(oneToFour()).toContainEntriesFun(
                                { toEqual(1.0) },
                                { toBeGreaterThan(3.0) },
                                { toBeGreaterThan(4.0) })
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$s$anEntryAfterSuccess$toEqualDescr: 1.0",
                                    "$s$anEntryAfterSuccess$toBeGreaterThanDescr: 3.0",
                                    "$x$anEntryAfterFailing$toBeGreaterThanDescr: 4.0",
                                    "$bb$mismatchesAdditionalElements:",
                                    "${listBulletPoint}2.0",
                                    "${listBulletPoint}3.0",
                                    "${listBulletPoint}4.0"
                                )
                                toContainSize(5, 3)
                            }
                        }
                    }
                }

                context("too many matcher") {
                    it("1.0, 2.0, 3.0, 4.0, 4.0, 5.0 -- 5.0 was too much") {
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
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$s$anEntryAfterSuccess$toEqualDescr: 1.0",
                                    "$s$anEntryAfterSuccess$toEqualDescr: 2.0",
                                    "$s$anEntryAfterSuccess$toEqualDescr: 3.0",
                                    "$x$anEntryAfterSuccess$toEqualDescr: 5.0"
                                )
                                toContain.exactly(2)
                                    .value("$s$anEntryAfterSuccess$toEqualDescr: 4.0")
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
                        expect(oneToFour()).toContainEntriesFun({ toEqual(2.0) }, report = { showOnlyFailing() })
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(5, 1)
                            toContain.exactly(1).values(
                                "$rootBulletPoint$toContainInAnyOrderOnly:",
                                "$bb$additionalElements:",
                                "${listBulletPoint}1.0",
                                "${listBulletPoint}3.0"
                            )
                            toContain.exactly(2).value("${listBulletPoint}4.0")
                            notToContain("$toEqualDescr: 2.0")

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
                            toContainSize(5, 6)
                            toContain.exactly(1).values("$listBulletPoint$anEntryAfterSuccess$toEqualDescr: 5.0")
                            notToContain(
                                "$toEqualDescr: 1.0",
                                "$toEqualDescr: 2.0",
                                "$toEqualDescr: 3.0",
                                "$toEqualDescr: 4.0"
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
                            toContain.exactly(1).values(
                                "$listBulletPoint$anEntryAfterSuccess$toEqualDescr: -1.0",
                                "$listBulletPoint$anEntryAfterSuccess$toEqualDescr: -2.0",
                                "$bb$mismatches:",
                                "${listBulletPoint}5.0",
                                "${listBulletPoint}8.0"
                            )
                            notToContain(
                                "$toEqualDescr: 1.0",
                                "$toEqualDescr: 2.0",
                                "$toEqualDescr: 3.0",
                                "$toEqualDescr: 4.0",
                                "$toEqualDescr: 6.0",
                                "$toEqualDescr: 7.0",
                                "$toEqualDescr: 9.0",
                                "$toEqualDescr: 10.0",
                                "$toEqualDescr: 11.0",

                                additionalElements, mismatchesAdditionalElements
                            )
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
                            toContain.exactly(1).values(
                                "$s$anEntryAfterSuccess$toEqualDescr: 1.0",
                                "$s$anEntryAfterSuccess$toEqualDescr: 2.0",
                                "$s$anEntryAfterSuccess$toEqualDescr: 3.0",
                                "$s$anEntryAfterSuccess$toEqualDescr: 4.0",
                                "$x$anEntryAfterFailing$toEqualDescr: -1.0",
                                "$s$anEntryAfterSuccess$toEqualDescr: 6.0",
                                "$s$anEntryAfterSuccess$toEqualDescr: 7.0",
                                "$x$anEntryAfterFailing$toEqualDescr: -2.0",
                                "$s$anEntryAfterSuccess$toEqualDescr: 9.0",
                                "$s$anEntryAfterSuccess$toEqualDescr: 10.0",
                                "$s$anEntryAfterSuccess$toEqualDescr: 11.0",
                                "$bb$mismatches:",
                                "${listBulletPoint}5.0",
                                "${listBulletPoint}8.0"
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
            val null1null3 = { sequenceOf(null, 1.0, null, 3.0).constrainOnce().asIterable() }

            context("iterable ${null1null3().toList()}") {
                context("happy cases (do not throw)") {
                    it("null, $toEqualFun(1.0), null, $toEqualFun(3.0)") {
                        expect(null1null3()).toContainFun(
                            null, { toEqual(1.0) }, null, { toEqual(3.0) }
                        )
                    }
                    it("$toEqualFun(1.0), null, null, $toEqualFun(3.0)") {
                        expect(null1null3()).toContainFun(
                            { toEqual(1.0) }, null, null, { toEqual(3.0) }
                        )
                    }
                    it("$toEqualFun(1.0), null, $toEqualFun(3.0), null") {
                        expect(null1null3()).toContainFun(
                            { toEqual(1.0) }, null, { toEqual(3.0) }, null
                        )
                    }
                    it("$toEqualFun(1.0), $toEqualFun(3.0), null, null") {
                        expect(null1null3()).toContainFun(
                            { toEqual(1.0) }, { toEqual(3.0) }, null, null
                        )
                    }
                }

                context("failing cases") {
                    it("null, $toEqualFun(1.0), $toEqualFun(3.0) -- second null was missing") {
                        expect {
                            expect(null1null3()).toContainFun(
                                null, { toEqual(1.0) }, { toEqual(3.0) }
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$s$anEntryAfterSuccess$toEqualDescr: null",
                                    "$s$anEntryAfterSuccess$toEqualDescr: 1.0",
                                    "$s$anEntryAfterSuccess$toEqualDescr: 3.0",
                                    "$bb$additionalElements:",
                                    "${listBulletPoint}null"
                                )
                                toContainSize(4, 3)
                            }
                        }
                    }

                    it("first wins: $toBeLessThanFun(4.0), null, null, $toEqualDescr(1.0)") {
                        expect {
                            expect(null1null3()).toContainFun(
                                { toBeLessThan(4.0) },
                                null,
                                null,
                                { toEqual(1.0) })
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(2).values(
                                    "$s$anEntryAfterSuccess$toEqualDescr: null",
                                    "$s$anEntryAfterSuccess$toEqualDescr: null"
                                )
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$s$anEntryAfterSuccess$toBeLessThanDescr: 4.0",
                                    "$x$anEntryAfterFailing$toEqualDescr: 1.0",
                                    "$bb$mismatches:",
                                    "${listBulletPoint}3.0"
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
