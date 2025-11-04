package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.anElementWhichNeedsDescr
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.noSuchElementDescr
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeGreaterThanFun
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toBeLessThanFun
import ch.tutteli.atrium.specs.integration.IterableToContainEntriesSpecBase.Companion.toEqualFun
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyIterable
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSeven
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSevenNullable
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.separator
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.toContainInAnyOrder
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory

@Suppress("FunctionName")
abstract class AbstractIterableToContainInAnyOrderAtLeast1EntriesExpectationsTest(
    private val toContainInAnyOrderEntriesSpec: Fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>,
    private val toContainInAnyOrderNullableEntriesSpec: Fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            toContainInAnyOrderEntriesSpec.forSubjectLessTest({ toEqual(2.5) }, arrayOf())
        ),
        SubjectLessTestData(
            toContainInAnyOrderNullableEntriesSpec.forSubjectLessTest(null, arrayOf())
        )
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            listOf(1.2, 2.1),
            toContainInAnyOrderEntriesSpec.forExpectationCreatorTest(
                "$toEqualDescr: 1.2",
                "$toEqualDescr: 2.1",
                { toEqual(1.2) },
                arrayOf(expectLambda { toEqual(2.1) }
                )
            )
        ),
        ExpectationCreatorTestData(
            listOf(1.2, 2.1),
            toContainInAnyOrderNullableEntriesSpec.forExpectationCreatorTest(
                "$toEqualDescr: 1.2",
                "$toEqualDescr: 2.1",
                { toEqual(1.2) },
                arrayOf(expectLambda { toEqual(2.1) })
            )
        )
    )

    fun Expect<Iterable<Double?>>.toContainInAnyOrderNullableEntriesFun(
        t: (Expect<Double>.() -> Unit)?,
        vararg tX: (Expect<Double>.() -> Unit)?
    ) = toContainInAnyOrderNullableEntriesSpec(this, t, tX)

    @TestFactory
    fun empty_collection() = testFactoryNonNullable(
        toContainInAnyOrderEntriesSpec,
        toContainInAnyOrderNullableEntriesSpec
    ) { toContainInAnyOrderEntriesFun ->

        it("$toBeLessThanFun(1.1) throws AssertionError") {
            expect {
                expect(emptyIterable()).toContainInAnyOrderEntriesFun({ toBeLessThan(1.1) }, arrayOf())
            }.toThrow<AssertionError> {
                message {
                    toContain.exactly(1).values(
                        "$rootBulletPoint$toContainInAnyOrder: $separator",
                        "$anElementWhichNeedsDescr: $separator",
                        "$toBeLessThanDescr: 1.1",
                        noSuchElementDescr
                    )
                }
            }
        }
        it("$toBeLessThanFun(1.1) and $toBeGreaterThanFun(2.1) throws AssertionError") {
            expect {
                expect(emptyIterable()).toContainInAnyOrderEntriesFun(
                    { toBeLessThan(1.1) }, arrayOf({ toBeGreaterThan(2.1) })
                )
            }.toThrow<AssertionError> {
                message {
                    toContain.exactly(2).values(
                        "$anElementWhichNeedsDescr: $separator",
                        noSuchElementDescr
                    )
                    toContain.exactly(1).values(
                        "$rootBulletPoint$toContainInAnyOrder: $separator",
                        "$toBeLessThanDescr: 1.1",
                        "$toBeGreaterThanDescr: 2.1"
                    )
                }
            }
        }
    }

    @TestFactory
    fun one_to_seven() = testFactoryNonNullable(
        toContainInAnyOrderEntriesSpec,
        toContainInAnyOrderNullableEntriesSpec
    ) { toContainInAnyOrderEntriesFun ->
        describe("search for entry which $toBeGreaterThanFun(1.1) and $toBeLessThanFun(2.1)") {
            it("throws AssertionError containing both assumptions in one assertion") {
                expect {
                    expect(oneToSeven()).toContainInAnyOrderEntriesFun(
                        { toBeGreaterThan(1.1).toBeLessThan(2.1) }, arrayOf()
                    )
                }.toThrow<AssertionError> {
                    message {
                        toContain.exactly(1).values(
                            "$rootBulletPoint$toContainInAnyOrder: $separator",
                            "$anElementWhichNeedsDescr: $separator",
                            "$toBeGreaterThanDescr: 1.1",
                            "$toBeLessThanDescr: 2.1",
                            noSuchElementDescr
                        )
                    }
                }
            }
        }
        describe("search for entry which $toBeGreaterThanFun(1.1) and $toBeLessThanFun(2.2)") {
            it("does not throw an exception") {
                expect(oneToSeven())
                    .toContainInAnyOrderEntriesFun({ toBeGreaterThan(1.1).toBeLessThan(2.2) }, emptyArray())
            }
        }

        describe("search for entry which $toBeGreaterThanFun(1.1) and $toBeLessThanFun(2.2) and another entry which is $toBeLessThanFun(2.1)") {
            it("does not throw an exception") {
                //finds twice the entry 1.1 but that is fine since we do not search for unique entries in this case
                expect(oneToSeven()).toContainInAnyOrderEntriesFun(
                    {
                        toBeGreaterThan(1.1).toBeLessThan(2.2)
                    }, arrayOf({
                        toBeLessThan(2.1)
                    })
                )
            }
        }
    }

    @TestFactory
    fun nullable_cases() = testFactory(toContainInAnyOrderNullableEntriesSpec) { toContainInAnyOrderEntriesFun ->
        describeIterable(::oneToSevenNullable) {
            describe("happy cases (do not throw)") {
                it("$toEqualFun(1.1)") {
                    expect(oneToSevenNullable()).toContainInAnyOrderNullableEntriesFun({ toEqual(1.1) })
                }
                it("null") {
                    expect(oneToSevenNullable()).toContainInAnyOrderNullableEntriesFun(null)
                }
                it("$toEqualFun(1.1) and null") {
                    expect(oneToSevenNullable()).toContainInAnyOrderNullableEntriesFun({ toEqual(1.1) }, null)
                }
                it("$toEqualFun(4.1), null and $toEqualFun(1.1)") {
                    expect(oneToSevenNullable())
                        .toContainInAnyOrderNullableEntriesFun({ toEqual(4.1) }, null, { toEqual(1.1) })
                }
                it("null, null, null") {
                    // finds twice the same entry with null but that is fine
                    // since we do not search for unique entries in this case
                    expect(oneToSevenNullable()).toContainInAnyOrderNullableEntriesFun(null, null, null)
                }
            }
        }
        describe("failing cases") {
            it("$toEqualFun(2.1)") {
                expect {
                    expect(oneToSevenNullable()).toContainInAnyOrderNullableEntriesFun({ toEqual(2.1) })
                }.toThrow<AssertionError> {
                    messageToContain(
                        "$rootBulletPoint$toContainInAnyOrder: $separator",
                        "$anElementWhichNeedsDescr: $separator",
                        "$toEqualDescr: 2.1",
                        noSuchElementDescr
                    )
                }
            }

            it("$toBeLessThanFun(1.1) and $toBeGreaterThanDescr(7.1)") {
                expect {
                    expect(oneToSevenNullable()).toContainInAnyOrderNullableEntriesFun(
                        { toBeLessThan(1.1) },
                        { toBeGreaterThan(7.1) }
                    )
                }.toThrow<AssertionError> {
                    message {
                        toContain.exactly(2).values(
                            "$anElementWhichNeedsDescr: $separator",
                            noSuchElementDescr
                        )
                        toContain.exactly(1).values(
                            "$rootBulletPoint$toContainInAnyOrder: $separator",
                            "$toBeLessThanDescr: 1.1",
                            "$toBeGreaterThanDescr: 7.1"
                        )
                    }
                }
            }
        }
        describeIterable(::oneToSeven) {
            it("null, throws an AssertionError") {
                expect {
                    expect(oneToSeven() as Iterable<Double?>).toContainInAnyOrderNullableEntriesFun(null)
                }.toThrow<AssertionError> {
                    messageToContain(
                        "$rootBulletPoint$toContainInAnyOrder: $separator",
                        "$anElementWhichNeedsDescr: $separator",
                        "$toEqualDescr: null",
                        noSuchElementDescr
                    )
                }
            }
        }
    }
}
