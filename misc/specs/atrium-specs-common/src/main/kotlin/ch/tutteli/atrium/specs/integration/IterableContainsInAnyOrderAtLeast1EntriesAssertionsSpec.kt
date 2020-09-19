package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.returnValueOf
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.ErrorMessages

abstract class IterableContainsInAnyOrderAtLeast1EntriesAssertionsSpec(
    containsInAnyOrderEntries: Fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>,
    containsInAnyOrderNullableEntries: Fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>,
    rootBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        containsInAnyOrderEntries.forSubjectLess({ toBe(2.5) }, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        containsInAnyOrderNullableEntries.forSubjectLess(null, arrayOf())
    ) {})

    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, listOf(1.2, 2.0),
        *containsInAnyOrderEntries.forAssertionCreatorSpec(
            "$toBeDescr: 1.2", "$toBeDescr: 2.0",
            { toBe(1.2) }, arrayOf(expectLambda { toBe(2.0) })
        )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable] ", listOf(1.2, 2.0),
        *containsInAnyOrderNullableEntries.forAssertionCreatorSpec(
            "$toBeDescr: 1.2", "$toBeDescr: 2.0",
            { toBe(1.2) }, arrayOf(expectLambda { toBe(2.0) })
        )
    ) {})

    fun Expect<Iterable<Double?>>.containsInAnyOrderNullableEntriesFun(
        t: (Expect<Double>.() -> Unit)?,
        vararg tX: (Expect<Double>.() -> Unit)?
    ) = containsInAnyOrderNullableEntries(this, t, tX)

    nonNullableCases(
        describePrefix,
        containsInAnyOrderEntries,
        containsInAnyOrderNullableEntries
    ) { containsEntriesFunArr ->

        fun Expect<Iterable<Double>>.containsEntriesFun(
            t: Expect<Double>.() -> Unit,
            vararg tX: Expect<Double>.() -> Unit
        ) = containsEntriesFunArr(t, tX)

        context("empty collection") {
            it("$isLessThanFun(1.0) throws AssertionError") {
                expect {
                    expect(fluentEmpty()).containsEntriesFun({ isLessThan(1.0) })
                }.toThrow<AssertionError> {
                    message {
                        contains.exactly(1).values(
                            "$rootBulletPoint$containsInAnyOrder: $separator",
                            "$anEntryWhich: $separator",
                            "$isLessThanDescr: 1.0",
                            "$numberOfOccurrences: 0",
                            "$atLeast: 1"
                        )
                    }
                }
            }
            it("$isLessThanFun(1.0) and $isGreaterThanFun(2.0) throws AssertionError") {
                expect {
                    expect(fluentEmpty()).containsEntriesFun({ isLessThan(1.0) }, { isGreaterThan(2.0) })
                }.toThrow<AssertionError> {
                    message {
                        contains.exactly(2).values(
                            "$anEntryWhich: $separator",
                            "$numberOfOccurrences: 0",
                            "$atLeast: 1"
                        )
                        contains.exactly(1).values(
                            "$rootBulletPoint$containsInAnyOrder: $separator",
                            "$isLessThanDescr: 1.0",
                            "$isGreaterThanDescr: 2.0"
                        )
                    }
                }
            }
            //TODO remove with 1.0.0
            it("$returnValueOfFun(...) states warning that subject is not set") {
                expect {
                    expect(fluentEmpty()).containsEntriesFun({
                        @Suppress("DEPRECATION")
                        asAssert().returnValueOf(subject::dec).asExpect().toBe(1.0)
                    })
                }.toThrow<AssertionError> {
                    @Suppress("DEPRECATION")
                    messageContains(ErrorMessages.SUBJECT_ACCESSED_TOO_EARLY.getDefault())
                }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.0)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        expect(oneToSeven()).containsEntriesFun({ isGreaterThan(1.0); isLessThan(2.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).values(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhich: $separator",
                                "$isGreaterThanDescr: 1.0",
                                "$isLessThanDescr: 2.0",
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1"
                            )
                        }
                    }
                }
            }

            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.1)") {
                it("does not throw an exception") {
                    expect(oneToSeven()).containsEntriesFun({ isGreaterThan(1.0); isLessThan(2.1) })
                }
            }

            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.1) and another entry which is $isLessThanFun(2.0)") {
                it("does not throw an exception") {
                    //finds twice the entry 1.0 but that is fine since we do not search for unique entries in this case
                    expect(oneToSeven()).containsEntriesFun({ isGreaterThan(1.0).isLessThan(2.1) }, { isLessThan(2.0) })
                }
            }

        }
    }

    nullableCases(describePrefix) {

        describeFun(containsInAnyOrderNullableEntries) {

            context("iterable ${oneToSevenNullable().toList()}") {
                context("happy cases (do not throw)") {
                    it("$toBeFun(1.0)") {
                        expect(oneToSevenNullable()).containsInAnyOrderNullableEntriesFun({ toBe(1.0) })
                    }
                    it("null") {
                        expect(oneToSevenNullable()).containsInAnyOrderNullableEntriesFun(null)
                    }
                    it("$toBeFun(1.0) and null") {
                        expect(oneToSevenNullable()).containsInAnyOrderNullableEntriesFun({ toBe(1.0) }, null)
                    }
                    it("$toBeFun(4.0), null and $toBeFun(1.0)") {
                        expect(oneToSevenNullable())
                            .containsInAnyOrderNullableEntriesFun({ toBe(4.0) }, null, { toBe(1.0) })
                    }
                    it("null, null, null") {
                        // finds twice the same entry with null but that is fine
                        // since we do not search for unique entries in this case
                        expect(oneToSevenNullable()).containsInAnyOrderNullableEntriesFun(null, null, null)
                    }
                }

                context("failing cases") {
                    it("$toBeFun(2.0)") {
                        expect {
                            expect(oneToSevenNullable()).containsInAnyOrderNullableEntriesFun({ toBe(2.0) })
                        }.toThrow<AssertionError> {
                            messageContains(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhich: $separator",
                                "$toBeDescr: 2.0",
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1"
                            )
                        }
                    }

                    it("$isLessThanFun(1.0) and $isGreaterThanDescr(7.0)") {
                        expect {
                            expect(oneToSevenNullable()).containsInAnyOrderNullableEntriesFun(
                                { isLessThan(1.0) },
                                { isGreaterThan(7.0) }
                            )
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(2).values(
                                    "$anEntryWhich: $separator",
                                    "$numberOfOccurrences: 0",
                                    "$atLeast: 1"
                                )
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrder: $separator",
                                    "$isLessThanDescr: 1.0",
                                    "$isGreaterThanDescr: 7.0"
                                )
                            }
                        }
                    }
                }
            }

            context("iterable ${oneToSeven().toList()}") {
                it("null, throws an AssertionError") {
                    expect {
                        expect(oneToSeven() as Iterable<Double?>).containsInAnyOrderNullableEntriesFun(null)
                    }.toThrow<AssertionError> {
                        messageContains(
                            "$rootBulletPoint$containsInAnyOrder: $separator",
                            "$anEntryWhich: $separator",
                            "$isDescr: null",
                            "$numberOfOccurrences: 0",
                            "$atLeast: 1"
                        )
                    }
                }
            }
        }
    }
})
