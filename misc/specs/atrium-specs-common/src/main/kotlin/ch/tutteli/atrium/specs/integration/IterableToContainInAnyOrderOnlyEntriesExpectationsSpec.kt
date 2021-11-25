package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.specs.*

abstract class IterableToContainInAnyOrderOnlyEntriesExpectationsSpec(
    toContainInAnyOrderOnlyEntries: Fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>,
    toContainInAnyOrderOnlyNullableEntries: Fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>,
    describePrefix: String = "[Atrium] "
) : IterableToContainEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        toContainInAnyOrderOnlyEntries.forSubjectLess({ toEqual(2.5) }, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        toContainInAnyOrderOnlyNullableEntries.forSubjectLess(null, arrayOf())
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, listOf(1.2, 2.0),
        *toContainInAnyOrderOnlyEntries.forAssertionCreatorSpec(
            "$toEqualDescr: 1.2", "$toEqualDescr: 2.0",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.0) })
        )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable] ", listOf(1.2, 2.0),
        *toContainInAnyOrderOnlyNullableEntries.forAssertionCreatorSpec(
            "$toEqualDescr: 1.2", "$toEqualDescr: 2.0",
            { toEqual(1.2) }, arrayOf(expectLambda { toEqual(2.0) })
        )
    ) {})

    fun Expect<Iterable<Double?>>.toContainInAnyOrderOnlyNullableEntriesFun(
        t: (Expect<Double>.() -> Unit)?,
        vararg tX: (Expect<Double>.() -> Unit)?
    ) = toContainInAnyOrderOnlyNullableEntries(this, t, tX)


    //@formatter:off
    val anEntryAfterSuccess = "$anElementWhich: $separator$indentRootBulletPoint$indentSuccessfulBulletPoint$indentListBulletPoint$explanatoryBulletPoint"
    val anEntryAfterFailing = "$anElementWhich: $separator$indentRootBulletPoint$indentFailingBulletPoint$indentListBulletPoint$explanatoryBulletPoint"
    //@formatter:on

    nonNullableCases(
        describePrefix,
        toContainInAnyOrderOnlyEntries,
        toContainInAnyOrderOnlyNullableEntries
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
                        toContain(
                            "$rootBulletPoint$toContainInAnyOrderOnly:",
                            "$failingBulletPoint$anEntryAfterFailing$toBeLessThanDescr: 1.0"
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
                            "$failingBulletPoint$anEntryAfterFailing$toBeLessThanDescr: 1.0",
                            "$failingBulletPoint$anEntryAfterFailing$toBeGreaterThanDescr: 4.0"
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
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 1.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 2.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 3.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 4.0",
                                    "$warningBulletPoint$additionalElements:",
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
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeLessThanDescr: 3.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeGreaterThanDescr: 3.0",
                                    "$warningBulletPoint$additionalElements:",
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
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeLessThanDescr: 5.0",
                                    "$failingBulletPoint$anEntryAfterFailing$toEqualDescr: 1.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 2.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 3.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 4.0",
                                    "$warningBulletPoint$mismatches:",
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
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 1.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeGreaterThanDescr: 3.0",
                                    "$failingBulletPoint$anEntryAfterFailing$toBeGreaterThanDescr: 4.0",
                                    "$warningBulletPoint$mismatchesAdditionalElements:",
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
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 1.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 2.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 3.0",
                                    "$failingBulletPoint$anEntryAfterSuccess$toEqualDescr: 5.0"
                                )
                                toContain.exactly(2)
                                    .value("$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 4.0")
                                toContainSize(5, 6)
                                notToContain(additionalElements, mismatches, mismatchesAdditionalElements)
                            }
                        }
                    }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun(toContainInAnyOrderOnlyNullableEntries) {
            val null1null3 = { sequenceOf(null, 1.0, null, 3.0).constrainOnce().asIterable() }

            context("iterable ${null1null3().toList()}") {
                context("happy cases (do not throw)") {
                    it("null, $toEqualFun(1.0), null, $toEqualFun(3.0)") {
                        expect(null1null3()).toContainInAnyOrderOnlyNullableEntriesFun(
                            null, { toEqual(1.0) }, null, { toEqual(3.0) }
                        )
                    }
                    it("$toEqualFun(1.0), null, null, $toEqualFun(3.0)") {
                        expect(null1null3()).toContainInAnyOrderOnlyNullableEntriesFun(
                            { toEqual(1.0) }, null, null, { toEqual(3.0) }
                        )
                    }
                    it("$toEqualFun(1.0), null, $toEqualFun(3.0), null") {
                        expect(null1null3()).toContainInAnyOrderOnlyNullableEntriesFun(
                            { toEqual(1.0) }, null, { toEqual(3.0) }, null
                        )
                    }
                    it("$toEqualFun(1.0), $toEqualFun(3.0), null, null") {
                        expect(null1null3()).toContainInAnyOrderOnlyNullableEntriesFun(
                            { toEqual(1.0) }, { toEqual(3.0) }, null, null
                        )
                    }
                }

                context("failing cases") {
                    it("null, $toEqualFun(1.0), $toEqualFun(3.0) -- second null was missing") {
                        expect {
                            expect(null1null3()).toContainInAnyOrderOnlyNullableEntriesFun(
                                null, { toEqual(1.0) }, { toEqual(3.0) }
                            )
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: null",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 1.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: 3.0",
                                    "$warningBulletPoint$additionalElements:",
                                    "${listBulletPoint}null"
                                )
                                toContainSize(4, 3)
                            }
                        }
                    }

                    it("first wins: $toBeLessThanFun(4.0), null, null, $toEqualDescr(1.0)") {
                        expect {
                            expect(null1null3()).toContainInAnyOrderOnlyNullableEntriesFun(
                                { toBeLessThan(4.0) },
                                null,
                                null,
                                { toEqual(1.0) })
                        }.toThrow<AssertionError> {
                            message {
                                toContain.exactly(2).values(
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: null",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toEqualDescr: null"
                                )
                                toContain.exactly(1).values(
                                    "$rootBulletPoint$toContainInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeLessThanDescr: 4.0",
                                    "$failingBulletPoint$anEntryAfterFailing$toEqualDescr: 1.0",
                                    "$warningBulletPoint$mismatches:",
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
