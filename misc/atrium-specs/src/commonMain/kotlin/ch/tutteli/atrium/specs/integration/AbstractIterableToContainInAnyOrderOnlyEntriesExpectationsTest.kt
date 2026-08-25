package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.anElementWhichNeedsDescr
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeGreaterThanFun
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeLessThanFun
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toEqualFun

import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.additionalElements
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyInAnyOrderOnlyReportOptions
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyIterable
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.mismatches
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.mismatchesAdditionalElements
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToEleven
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToFour
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.separator
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.sizeDescr
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.toContainInAnyOrderOnly
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.toContainSize

import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeGreaterThanDescr
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeLessThanDescr
import ch.tutteli.atrium.specs.*

@Suppress("FunctionName")
abstract class AbstractIterableToContainInAnyOrderOnlyEntriesExpectationsTest(
    private val toContainInAnyOrderOnlyEntriesSpec: Fun3<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>, InAnyOrderOnlyReportingOptions.() -> Unit>,
    private val toContainInAnyOrderOnlyNullableEntriesSpec: Fun3<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>, InAnyOrderOnlyReportingOptions.() -> Unit>
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            toContainInAnyOrderOnlyEntriesSpec.forSubjectLessTest(
                { toEqual(2.5) },
                arrayOf(),
                emptyInAnyOrderOnlyReportOptions
            )
        ),
        SubjectLessTestData(
            toContainInAnyOrderOnlyNullableEntriesSpec.forSubjectLessTest(
                null,
                arrayOf(),
                emptyInAnyOrderOnlyReportOptions
            )
        )
    )
    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            listOf(1.2, 2.1),
            toContainInAnyOrderOnlyEntriesSpec.forExpectationCreatorTest(
                "$toEqualDescr: 1.2",
                "$toEqualDescr: 2.1",
                { toEqual(1.2) },
                arrayOf(expectLambda { toEqual(2.1) }),
                emptyInAnyOrderOnlyReportOptions
            )
        ),
        ExpectationCreatorTestData(
            listOf(1.2, 2.1),
            toContainInAnyOrderOnlyNullableEntriesSpec.forExpectationCreatorTest(
                "$toEqualDescr: 1.2",
                "$toEqualDescr: 2.1",
                { toEqual(1.2) },
                arrayOf(expectLambda { toEqual(2.1) }),
                emptyInAnyOrderOnlyReportOptions
            )
        )
    )


    //@formatter:off
    val anEntryAfterSuccess = "$anElementWhichNeedsDescr: $separator$indentRootBulletPoint$indentSuccessfulBulletPoint$indentListBulletPoint$explanatoryBulletPoint"
    val anEntryAfterFailing = "$anElementWhichNeedsDescr: $separator$indentRootBulletPoint$indentFailingBulletPoint$indentListBulletPoint$explanatoryBulletPoint"
    //@formatter:on

    @TestFactory
    fun empty_collection() = testFactoryNonNullable(
        toContainInAnyOrderOnlyEntriesSpec,
        toContainInAnyOrderOnlyNullableEntriesSpec
    ) { toContainEntriesFunArr ->
        fun Expect<Iterable<Double>>.toContainEntriesFun(
            t: Expect<Double>.() -> Unit,
            vararg tX: Expect<Double>.() -> Unit,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = {}
        ) = toContainEntriesFunArr(t, tX, report)

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
                expect(emptyIterable()).toContainEntriesFun(
                    { toBeLessThan(1.1) },
                    { toBeGreaterThan(4.1) }
                )
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

    @TestFactory
    fun happy_cases() = testFactoryNonNullable(
        toContainInAnyOrderOnlyEntriesSpec,
        toContainInAnyOrderOnlyNullableEntriesSpec
    ) { toContainEntriesFunArr ->
        fun Expect<Iterable<Double>>.toContainEntriesFun(
            t: Expect<Double>.() -> Unit,
            vararg tX: Expect<Double>.() -> Unit,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = {}
        ) = toContainEntriesFunArr(t, tX, report)

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
                    *(it.drop(1).map {
                        val f: Expect<Double>.() -> Unit = { toEqual(it) }
                        f
                    }).toTypedArray()
                )
            }
        }

        it("1.1, 2.1, 3.1, 4.1 and $toBeGreaterThanFun(0.1)") {
            expect(oneToFour()).toContainEntriesFun(
                { toEqual(1.1) },
                { toEqual(2.1) },
                { toEqual(3.1) },
                { toEqual(4.1) },
                { toBeGreaterThan(0.1) }
            )
        }

        it(" $toBeLessThanFun(3.1), 2.1, 3.1, 4.1 and 4.1") {
            expect(oneToFour()).toContainEntriesFun(
                { toBeLessThan(3.1) },
                { toEqual(2.1) },
                { toEqual(3.1) },
                { toEqual(4.1) },
                { toEqual(4.1) }
            )
        }

        it("2.1, $toBeLessThanFun(5.1), 3.1, 4.1 and 4.1") {
            expect(oneToFour()).toContainEntriesFun(
                { toEqual(2.1) },
                { toBeLessThan(5.1) },
                { toEqual(3.1) },
                { toEqual(4.1) },
                { toEqual(4.1) }
            )
        }
    }


    @TestFactory
    fun error_case_additional_entries() = testFactoryNonNullable(
        toContainInAnyOrderOnlyEntriesSpec,
        toContainInAnyOrderOnlyNullableEntriesSpec
    ) { toContainEntriesFunArr ->
        fun Expect<Iterable<Double>>.toContainEntriesFun(
            t: Expect<Double>.() -> Unit,
            vararg tX: Expect<Double>.() -> Unit,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = {}
        ) = toContainEntriesFunArr(t, tX, report)

        it("1.1, 2.1, 3.1, 4.1 -- 4.1 was missing") {
            expect {
                expect(oneToFour()).toContainEntriesFun(
                    { toEqual(1.1) },
                    { toEqual(2.1) },
                    { toEqual(3.1) },
                    { toEqual(4.1) }
                )
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
                expect(oneToFour()).toContainEntriesFun(
                    { toBeLessThan(3.1) },
                    { toBeGreaterThan(3.1) }
                )
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

    @TestFactory
    fun error_case_mismatches() = testFactoryNonNullable(
        toContainInAnyOrderOnlyEntriesSpec,
        toContainInAnyOrderOnlyNullableEntriesSpec
    ) { toContainEntriesFunArr ->
        fun Expect<Iterable<Double>>.toContainEntriesFun(
            t: Expect<Double>.() -> Unit,
            vararg tX: Expect<Double>.() -> Unit,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = {}
        ) = toContainEntriesFunArr(t, tX, report)

        it("first wins: $toBeLessThanFun(5.1), 1.1, 2.1, 3.1, 4.1") {
            expect {
                expect(oneToFour()).toContainEntriesFun(
                    { toBeLessThan(5.1) },
                    { toEqual(1.1) },
                    { toEqual(2.1) },
                    { toEqual(3.1) },
                    { toEqual(4.1) }
                )
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

    @TestFactory
    fun error_case_mismatches_and_additional_entries() = testFactoryNonNullable(
        toContainInAnyOrderOnlyEntriesSpec,
        toContainInAnyOrderOnlyNullableEntriesSpec
    ) { toContainEntriesFunArr ->
        fun Expect<Iterable<Double>>.toContainEntriesFun(
            t: Expect<Double>.() -> Unit,
            vararg tX: Expect<Double>.() -> Unit,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = {}
        ) = toContainEntriesFunArr(t, tX, report)

        it("1.1, $toBeGreaterThanFun(3.1), $toBeGreaterThanFun(4.1) -- $toBeGreaterThanFun(4.1) is wrong and 2.1, 3.1 and 4.1 are missing") {
            expect {
                expect(oneToFour()).toContainEntriesFun(
                    { toEqual(1.1) },
                    { toBeGreaterThan(3.1) },
                    { toBeGreaterThan(4.1) }
                )
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

    @TestFactory
    fun error_case_too_many_matchers() = testFactoryNonNullable(
        toContainInAnyOrderOnlyEntriesSpec,
        toContainInAnyOrderOnlyNullableEntriesSpec
    ) { toContainEntriesFunArr ->
        fun Expect<Iterable<Double>>.toContainEntriesFun(
            t: Expect<Double>.() -> Unit,
            vararg tX: Expect<Double>.() -> Unit,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = {}
        ) = toContainEntriesFunArr(t, tX, report)

        it("1.1, 2.1, 3.1, 4.1, 4.1, 5.1 -- 5.1 was too much") {
            expect {
                expect(oneToFour()).toContainEntriesFun(
                    { toEqual(1.1) },
                    { toEqual(2.1) },
                    { toEqual(3.1) },
                    { toEqual(4.1) },
                    { toEqual(4.1) },
                    { toEqual(5.1) }
                )
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
                    notToContain(
                        additionalElements,
                        mismatches,
                        mismatchesAdditionalElements
                    )
                }
            }
        }
    }


    @TestFactory
    fun report_options() = testFactoryNonNullable(
        toContainInAnyOrderOnlyEntriesSpec,
        toContainInAnyOrderOnlyNullableEntriesSpec
    ) { toContainEntriesFunArr ->
        fun Expect<Iterable<Double>>.toContainEntriesFun(
            t: Expect<Double>.() -> Unit,
            vararg tX: Expect<Double>.() -> Unit,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = {}
        ) = toContainEntriesFunArr(t, tX, report)

        it("shows only failing with report option `showOnlyFailing`") {
            expect {
                expect(oneToFour()).toContainEntriesFun(
                    { toEqual(2.1) },
                    report = { showOnlyFailing() }
                )
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
                    report = { showOnlyFailingIfMoreExpectedElementsThan(3) }
                )
            }.toThrow<AssertionError> {
                message {
                    toContainSize(5, 6)
                    toContain.exactly(1).values(
                        "$listBulletPoint$anEntryAfterSuccess$toEqualDescr: 5.1"
                    )
                    notToContain(
                        "$toEqualDescr: 1.1",
                        "$toEqualDescr: 2.1",
                        "$toEqualDescr: 3.1",
                        "$toEqualDescr: 4.1"
                    )
                    notToContain(
                        additionalElements,
                        mismatches,
                        mismatchesAdditionalElements
                    )
                }
            }
        }

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
                        additionalElements,
                        mismatchesAdditionalElements
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
                    notToContain(
                        additionalElements,
                        mismatchesAdditionalElements
                    )
                }
            }
        }
    }
    private val null1null3 = { sequenceOf(null, 1.1, null, 3.1).constrainOnce().asIterable() }

    @TestFactory
    fun nullable_happy_cases() = testFactory(
        toContainInAnyOrderOnlyNullableEntriesSpec
    ) { toContainInAnyOrderOnlyNullableEntriesFunArr ->

        fun Expect<Iterable<Double?>>.toContainFun(
            t: (Expect<Double>.() -> Unit)?,
            vararg tX: (Expect<Double>.() -> Unit)?,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = emptyInAnyOrderOnlyReportOptions
        ) = toContainInAnyOrderOnlyNullableEntriesFunArr(this, t, tX, report)

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

    @TestFactory
    fun nullable_error_case_additional_entries() = testFactory(
        toContainInAnyOrderOnlyNullableEntriesSpec
    ) { toContainInAnyOrderOnlyNullableEntriesFunArr ->

        fun Expect<Iterable<Double?>>.toContainFun(
            t: (Expect<Double>.() -> Unit)?,
            vararg tX: (Expect<Double>.() -> Unit)?,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = emptyInAnyOrderOnlyReportOptions
        ) = toContainInAnyOrderOnlyNullableEntriesFunArr(this, t, tX, report)

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
    }

    @TestFactory
    fun nullable_error_case_mismatches() = testFactory(
        toContainInAnyOrderOnlyNullableEntriesSpec
    ) { toContainInAnyOrderOnlyNullableEntriesFunArr ->

        fun Expect<Iterable<Double?>>.toContainFun(
            t: (Expect<Double>.() -> Unit)?,
            vararg tX: (Expect<Double>.() -> Unit)?,
            report: InAnyOrderOnlyReportingOptions.() -> Unit = emptyInAnyOrderOnlyReportOptions
        ) = toContainInAnyOrderOnlyNullableEntriesFunArr(this, t, tX, report)

        it("first wins: $toBeLessThanFun(4.1), null, null, $toEqualDescr(1.1)") {
            expect {
                expect(null1null3()).toContainFun(
                    { toBeLessThan(4.1) },
                    null,
                    null,
                    { toEqual(1.1) }
                )
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
