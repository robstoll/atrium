package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*

abstract class IterableToContainInAnyOrderAtLeast1EntriesExpectationsSpec(
    toContainInAnyOrderEntries: Fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>,
    toContainInAnyOrderNullableEntries: Fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        toContainInAnyOrderEntries.forSubjectLessTest({ toEqual(2.5) }, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        toContainInAnyOrderNullableEntries.forSubjectLessTest(null, arrayOf())
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, listOf(1.2, 2.1),
        toContainInAnyOrderEntries.forExpectationCreatorTest(
            "$toEqualDescr: 1.2", "$toEqualDescr: 2.1",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.1) })
        )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable] ", listOf(1.2, 2.1),
        toContainInAnyOrderNullableEntries.forExpectationCreatorTest(
            "$toEqualDescr: 1.2", "$toEqualDescr: 2.1",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.1) })
        )
    ) {})

    fun Expect<Iterable<Double?>>.toContainInAnyOrderNullableEntriesFun(
        t: (Expect<Double>.() -> Unit)?,
        vararg tX: (Expect<Double>.() -> Unit)?
    ) = toContainInAnyOrderNullableEntries(this, t, tX)

    nonNullableCases(
        describePrefix,
        toContainInAnyOrderEntries,
        toContainInAnyOrderNullableEntries
    ) { toContainEntriesFunArr ->

        fun Expect<Iterable<Double>>.toContainEntriesFun(
            t: Expect<Double>.() -> Unit,
            vararg tX: Expect<Double>.() -> Unit
        ) = toContainEntriesFunArr(t, tX)

        context("empty collection") {
            it("$toBeLessThanFun(1.1) throws AssertionError") {
                expect {
                    expect(emptyIterable()).toContainEntriesFun({ toBeLessThan(1.1) })
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
                    expect(emptyIterable()).toContainEntriesFun({ toBeLessThan(1.1) }, { toBeGreaterThan(2.1) })
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

        context("iterable ${oneToSeven().toList()}") {
            context("search for entry which $toBeGreaterThanFun(1.1) and $toBeLessThanFun(2.1)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        expect(oneToSeven()).toContainEntriesFun({ toBeGreaterThan(1.1); toBeLessThan(2.1) })
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

            context("search for entry which $toBeGreaterThanFun(1.1) and $toBeLessThanFun(2.2)") {
                it("does not throw an exception") {
                    expect(oneToSeven()).toContainEntriesFun({ toBeGreaterThan(1.1); toBeLessThan(2.2) })
                }
            }

            context("search for entry which $toBeGreaterThanFun(1.1) and $toBeLessThanFun(2.2) and another entry which is $toBeLessThanFun(2.1)") {
                it("does not throw an exception") {
                    //finds twice the entry 1.1 but that is fine since we do not search for unique entries in this case
                    expect(oneToSeven()).toContainEntriesFun(
                        { toBeGreaterThan(1.1).toBeLessThan(2.2) },
                        { toBeLessThan(2.1) })
                }
            }

        }
    }

    nullableCases(describePrefix) {

        describeFun(toContainInAnyOrderNullableEntries) {

            context("iterable ${oneToSevenNullable().toList()}") {
                context("happy cases (do not throw)") {
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

                context("failing cases") {
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
            }

            context("iterable ${oneToSeven().toList()}") {
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
})
