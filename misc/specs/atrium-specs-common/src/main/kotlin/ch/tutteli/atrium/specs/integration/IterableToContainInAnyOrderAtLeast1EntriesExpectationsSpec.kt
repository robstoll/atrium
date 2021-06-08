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
        toContainInAnyOrderEntries.forSubjectLess({ toEqual(2.5) }, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        toContainInAnyOrderNullableEntries.forSubjectLess(null, arrayOf())
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, listOf(1.2, 2.0),
        *toContainInAnyOrderEntries.forAssertionCreatorSpec(
            "$toBeDescr: 1.2", "$toBeDescr: 2.0",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.0) })
        )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable] ", listOf(1.2, 2.0),
        *toContainInAnyOrderNullableEntries.forAssertionCreatorSpec(
            "$toBeDescr: 1.2", "$toBeDescr: 2.0",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.0) })
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
            it("$toBeLessThanFun(1.0) throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainEntriesFun({ toBeLessThan(1.0) })
                }.toThrow<AssertionError> {
                    message {
                        toContain.exactly(1).values(
                            "$rootBulletPoint$toContainInAnyOrder: $separator",
                            "$anElementWhich: $separator",
                            "$toBeLessThanDescr: 1.0",
                            noSuchEntryDescr
                        )
                    }
                }
            }
            it("$toBeLessThanFun(1.0) and $toBeGreaterThanFun(2.0) throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainEntriesFun({ toBeLessThan(1.0) }, { toBeGreaterThan(2.0) })
                }.toThrow<AssertionError> {
                    message {
                        toContain.exactly(2).values(
                            "$anElementWhich: $separator",
                            noSuchEntryDescr
                        )
                        toContain.exactly(1).values(
                            "$rootBulletPoint$toContainInAnyOrder: $separator",
                            "$toBeLessThanDescr: 1.0",
                            "$toBeGreaterThanDescr: 2.0"
                        )
                    }
                }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("search for entry which $toBeGreaterThanFun(1.0) and $toBeLessThanFun(2.0)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        expect(oneToSeven()).toContainEntriesFun({ toBeGreaterThan(1.0); toBeLessThan(2.0) })
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).values(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhich: $separator",
                                "$toBeGreaterThanDescr: 1.0",
                                "$toBeLessThanDescr: 2.0",
                                noSuchEntryDescr
                            )
                        }
                    }
                }
            }

            context("search for entry which $toBeGreaterThanFun(1.0) and $toBeLessThanFun(2.1)") {
                it("does not throw an exception") {
                    expect(oneToSeven()).toContainEntriesFun({ toBeGreaterThan(1.0); toBeLessThan(2.1) })
                }
            }

            context("search for entry which $toBeGreaterThanFun(1.0) and $toBeLessThanFun(2.1) and another entry which is $toBeLessThanFun(2.0)") {
                it("does not throw an exception") {
                    //finds twice the entry 1.0 but that is fine since we do not search for unique entries in this case
                    expect(oneToSeven()).toContainEntriesFun(
                        { toBeGreaterThan(1.0).toBeLessThan(2.1) },
                        { toBeLessThan(2.0) })
                }
            }

        }
    }

    nullableCases(describePrefix) {

        describeFun(toContainInAnyOrderNullableEntries) {

            context("iterable ${oneToSevenNullable().toList()}") {
                context("happy cases (do not throw)") {
                    it("$toEqualFun(1.0)") {
                        expect(oneToSevenNullable()).toContainInAnyOrderNullableEntriesFun({ toEqual(1.0) })
                    }
                    it("null") {
                        expect(oneToSevenNullable()).toContainInAnyOrderNullableEntriesFun(null)
                    }
                    it("$toEqualFun(1.0) and null") {
                        expect(oneToSevenNullable()).toContainInAnyOrderNullableEntriesFun({ toEqual(1.0) }, null)
                    }
                    it("$toEqualFun(4.0), null and $toEqualFun(1.0)") {
                        expect(oneToSevenNullable())
                            .toContainInAnyOrderNullableEntriesFun({ toEqual(4.0) }, null, { toEqual(1.0) })
                    }
                    it("null, null, null") {
                        // finds twice the same entry with null but that is fine
                        // since we do not search for unique entries in this case
                        expect(oneToSevenNullable()).toContainInAnyOrderNullableEntriesFun(null, null, null)
                    }
                }

                context("failing cases") {
                    it("$toEqualFun(2.0)") {
                        expect {
                            expect(oneToSevenNullable()).toContainInAnyOrderNullableEntriesFun({ toEqual(2.0) })
                        }.toThrow<AssertionError> {
                            messageToContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhich: $separator",
                                "$toBeDescr: 2.0",
                                noSuchEntryDescr
                            )
                        }
                    }

                    it("$toBeLessThanFun(1.0) and $toBeGreaterThanDescr(7.0)") {
                        expect {
                            expect(oneToSevenNullable()).toContainInAnyOrderNullableEntriesFun(
                                { toBeLessThan(1.0) },
                                { toBeGreaterThan(7.0) }
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(2).values(
                                    "$anElementWhich: $separator",
                                    noSuchEntryDescr
                                )
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrder: $separator",
                                    "$toBeLessThanDescr: 1.0",
                                    "$toBeGreaterThanDescr: 7.0"
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
                            "$anElementWhich: $separator",
                            "$isDescr: null",
                            noSuchEntryDescr
                        )
                    }
                }
            }
        }
    }
})
