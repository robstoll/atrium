package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*

abstract class IterableContainsInAnyOrderOnlyEntriesExpectationsSpec(
    containsInAnyOrderOnlyEntries: Fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>,
    containsInAnyOrderOnlyNullableEntries: Fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        containsInAnyOrderOnlyEntries.forSubjectLess({ toEqual(2.5) }, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        containsInAnyOrderOnlyNullableEntries.forSubjectLess(null, arrayOf())
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, listOf(1.2, 2.0),
        *containsInAnyOrderOnlyEntries.forAssertionCreatorSpec(
            "$toBeDescr: 1.2", "$toBeDescr: 2.0",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.0) })
        )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable] ", listOf(1.2, 2.0),
        *containsInAnyOrderOnlyNullableEntries.forAssertionCreatorSpec(
            "$toBeDescr: 1.2", "$toBeDescr: 2.0",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.0) })
        )
    ) {})

    fun Expect<Iterable<Double?>>.containsInAnyOrderOnlyNullableEntriesFun(
        t: (Expect<Double>.() -> Unit)?,
        vararg tX: (Expect<Double>.() -> Unit)?
    ) = containsInAnyOrderOnlyNullableEntries(this, t, tX)


    //@formatter:off
    val anEntryAfterSuccess = "$anElementWhich: $separator$indentRootBulletPoint$indentSuccessfulBulletPoint$indentListBulletPoint$explanatoryBulletPoint"
    val anEntryAfterFailing = "$anElementWhich: $separator$indentRootBulletPoint$indentFailingBulletPoint$indentListBulletPoint$explanatoryBulletPoint"
    //@formatter:on

    nonNullableCases(
        describePrefix,
        containsInAnyOrderOnlyEntries,
        containsInAnyOrderOnlyNullableEntries
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
                        contains(
                            "$rootBulletPoint$containsInAnyOrderOnly:",
                            "$failingBulletPoint$anEntryAfterFailing$isLessThanDescr: 1.0"
                        )
                        containsNot(additionalElements)
                        containsSize(0, 1)
                    }
                }
            }
            it("$isLessThanFun(1.0) and $isGreaterThanFun(4.0) throws AssertionError") {
                expect {
                    expect(fluentEmpty()).containsEntriesFun({ isLessThan(1.0) }, { isGreaterThan(4.0) })
                }.toThrow<AssertionError> {
                    message {
                        contains.exactly(1).values(
                            "$rootBulletPoint$containsInAnyOrderOnly:",
                            "$failingBulletPoint$anEntryAfterFailing$isLessThanDescr: 1.0",
                            "$failingBulletPoint$anEntryAfterFailing$isGreaterThanDescr: 4.0"
                        )
                        containsNot(additionalElements)
                        containsSize(0, 2)
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
                    it("${it.joinToString()} with matcher $toBeFun") {
                        expect(oneToFour()).containsEntriesFun(
                            { toEqual(it.first()) },
                            *(it.drop(1).map { val f: Expect<Double>.() -> Unit = { toEqual(it) }; f }).toTypedArray()
                        )
                    }
                }

                it("1.0, 2.0, 3.0, 4.0 and $isGreaterThanFun(0.0)") {
                    expect(oneToFour()).containsEntriesFun(
                        { toEqual(1.0) },
                        { toEqual(2.0) },
                        { toEqual(3.0) },
                        { toEqual(4.0) },
                        { isGreaterThan(0.0) })
                }
                it(" $isLessThanFun(3.0), 2.0, 3.0, 4.0 and 4.0") {
                    expect(oneToFour()).containsEntriesFun(
                        { isLessThan(3.0) },
                        { toEqual(2.0) },
                        { toEqual(3.0) },
                        { toEqual(4.0) },
                        { toEqual(4.0) })
                }
                it("2.0, $isLessThanFun(5.0), 3.0, 4.0 and 4.0") {
                    expect(oneToFour()).containsEntriesFun(
                        { toEqual(2.0) },
                        { isLessThan(5.0) },
                        { toEqual(3.0) },
                        { toEqual(4.0) },
                        { toEqual(4.0) })
                }
            }

            context("error cases (throws AssertionError)") {

                context("additional entries") {
                    it("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                        expect {
                            expect(oneToFour()).containsEntriesFun(
                                { toEqual(1.0) },
                                { toEqual(2.0) },
                                { toEqual(3.0) },
                                { toEqual(4.0) })
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 1.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 2.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 3.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 4.0",
                                    "$warningBulletPoint$additionalElements:",
                                    "${listBulletPoint}4.0"
                                )
                                containsSize(5, 4)
                            }
                        }
                    }

                    it("$isLessThanFun(3.0), isGreaterThan(3.0) -- 2.0, 3.0 and 4.0 was missing") {
                        expect {
                            expect(oneToFour()).containsEntriesFun({ isLessThan(3.0) }, { isGreaterThan(3.0) })
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$isLessThanDescr: 3.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$isGreaterThanDescr: 3.0",
                                    "$warningBulletPoint$additionalElements:",
                                    "${listBulletPoint}2.0",
                                    "${listBulletPoint}3.0",
                                    "${listBulletPoint}4.0"
                                )
                                containsSize(5, 2)
                            }
                        }
                    }
                }

                context("mismatches") {
                    it("first wins: $isLessThanFun(5.0), 1.0, 2.0, 3.0, 4.0") {
                        expect {
                            expect(oneToFour()).containsEntriesFun(
                                { isLessThan(5.0) },
                                { toEqual(1.0) },
                                { toEqual(2.0) },
                                { toEqual(3.0) },
                                { toEqual(4.0) })
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$isLessThanDescr: 5.0",
                                    "$failingBulletPoint$anEntryAfterFailing$toBeDescr: 1.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 2.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 3.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 4.0",
                                    "$warningBulletPoint$mismatches:",
                                    "${listBulletPoint}4.0"
                                )
                                containsNot(sizeDescr)
                            }
                        }
                    }
                }

                context("mismatches and additional entries") {
                    it("1.0, $isGreaterThanFun(3.0), $isGreaterThanFun(4.0) -- $isGreaterThanFun(4.0) is wrong and 2.0, 3.0 and 4.0 are missing") {
                        expect {
                            expect(oneToFour()).containsEntriesFun(
                                { toEqual(1.0) },
                                { isGreaterThan(3.0) },
                                { isGreaterThan(4.0) })
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 1.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$isGreaterThanDescr: 3.0",
                                    "$failingBulletPoint$anEntryAfterFailing$isGreaterThanDescr: 4.0",
                                    "$warningBulletPoint$mismatchesAdditionalElements:",
                                    "${listBulletPoint}2.0",
                                    "${listBulletPoint}3.0",
                                    "${listBulletPoint}4.0"
                                )
                                containsSize(5, 3)
                            }
                        }
                    }
                }

                context("too many matcher") {
                    it("1.0, 2.0, 3.0, 4.0, 4.0, 5.0 -- 5.0 was too much") {
                        expect {
                            expect(oneToFour()).containsEntriesFun(
                                { toEqual(1.0) },
                                { toEqual(2.0) },
                                { toEqual(3.0) },
                                { toEqual(4.0) },
                                { toEqual(4.0) },
                                { toEqual(5.0) })
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 1.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 2.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 3.0",
                                    "$failingBulletPoint$anEntryAfterSuccess$toBeDescr: 5.0"
                                )
                                contains.exactly(2)
                                    .value("$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 4.0")
                                containsSize(5, 6)
                                containsNot(additionalElements, mismatches, mismatchesAdditionalElements)
                            }
                        }
                    }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun(containsInAnyOrderOnlyNullableEntries) {
            val null1null3 = { sequenceOf(null, 1.0, null, 3.0).constrainOnce().asIterable() }

            context("iterable ${null1null3().toList()}") {
                context("happy cases (do not throw)") {
                    it("null, $toBeFun(1.0), null, $toBeFun(3.0)") {
                        expect(null1null3()).containsInAnyOrderOnlyNullableEntriesFun(
                            null, { toEqual(1.0) }, null, { toEqual(3.0) }
                        )
                    }
                    it("$toBeFun(1.0), null, null, $toBeFun(3.0)") {
                        expect(null1null3()).containsInAnyOrderOnlyNullableEntriesFun(
                            { toEqual(1.0) }, null, null, { toEqual(3.0) }
                        )
                    }
                    it("$toBeFun(1.0), null, $toBeFun(3.0), null") {
                        expect(null1null3()).containsInAnyOrderOnlyNullableEntriesFun(
                            { toEqual(1.0) }, null, { toEqual(3.0) }, null
                        )
                    }
                    it("$toBeFun(1.0), $toBeFun(3.0), null, null") {
                        expect(null1null3()).containsInAnyOrderOnlyNullableEntriesFun(
                            { toEqual(1.0) }, { toEqual(3.0) }, null, null
                        )
                    }
                }

                context("failing cases") {
                    it("null, $toBeFun(1.0), $toBeFun(3.0) -- second null was missing") {
                        expect {
                            expect(null1null3()).containsInAnyOrderOnlyNullableEntriesFun(
                                null, { toEqual(1.0) }, { toEqual(3.0) }
                            )
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$isDescr: null",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 1.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 3.0",
                                    "$warningBulletPoint$additionalElements:",
                                    "${listBulletPoint}null"
                                )
                                containsSize(4, 3)
                            }
                        }
                    }

                    it("first wins: $isLessThanFun(4.0), null, null, $toBeDescr(1.0)") {
                        expect {
                            expect(null1null3()).containsInAnyOrderOnlyNullableEntriesFun(
                                { isLessThan(4.0) },
                                null,
                                null,
                                { toEqual(1.0) })
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(2).values(
                                    "$successfulBulletPoint$anEntryAfterSuccess$isDescr: null",
                                    "$successfulBulletPoint$anEntryAfterSuccess$isDescr: null"
                                )
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$isLessThanDescr: 4.0",
                                    "$failingBulletPoint$anEntryAfterFailing$toBeDescr: 1.0",
                                    "$warningBulletPoint$mismatches:",
                                    "${listBulletPoint}3.0"
                                )
                                containsNot(sizeDescr)
                            }
                        }
                    }
                }
            }
        }
    }
})
