package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.returnValueOf
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.domain.builders.utils.subExpect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.ErrorMessages

abstract class IterableContainsInAnyOrderOnlyEntriesAssertionsSpec(
    containsInAnyOrderOnlyEntries: Fun2<Iterable<Double>, Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>>,
    containsInAnyOrderOnlyNullableEntries: Fun2<Iterable<Double?>, (Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    explanatoryBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        containsInAnyOrderOnlyEntries.forSubjectLess({ toBe(2.5) }, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        containsInAnyOrderOnlyNullableEntries.forSubjectLess(null, arrayOf())
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double>>(
        describePrefix, listOf(1.2, 2.0),
        *containsInAnyOrderOnlyEntries.forAssertionCreatorSpec(
            "$toBeDescr: 1.2", "$toBeDescr: 2.0",
            { toBe(1.2) }, arrayOf(subExpect { toBe(2.0) })
        )
    ) {})
    include(object : AssertionCreatorSpec<Iterable<Double?>>(
        "$describePrefix[nullable] ", listOf(1.2, 2.0),
        *containsInAnyOrderOnlyNullableEntries.forAssertionCreatorSpec(
            "$toBeDescr: 1.2", "$toBeDescr: 2.0",
            { toBe(1.2) }, arrayOf(subExpect { toBe(2.0) })
        )
    ) {})

    fun Expect<Iterable<Double?>>.containsInAnyOrderOnlyNullableEntriesFun(
        t: (Expect<Double>.() -> Unit)?,
        vararg tX: (Expect<Double>.() -> Unit)?
    ) = containsInAnyOrderOnlyNullableEntries(this, t, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentListBulletPoint = " ".repeat(listBulletPoint.length)

    //@formatter:off
    val anEntryAfterSuccess = "$anEntryWhich: $separator$indentBulletPoint$indentSuccessfulBulletPoint$indentListBulletPoint$explanatoryBulletPoint"
    val anEntryAfterFailing = "$anEntryWhich: $separator$indentBulletPoint$indentFailingBulletPoint$indentListBulletPoint$explanatoryBulletPoint"
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
                    fluentEmpty.containsEntriesFun({ isLessThan(1.0) })
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            "$rootBulletPoint$containsInAnyOrderOnly:",
                            "$failingBulletPoint$anEntryAfterFailing$isLessThanDescr: 1.0"
                        )
                        containsNot(additionalEntries)
                        containsSize(0, 1)
                    }
                }
            }
            it("$isLessThanFun(1.0) and $isGreaterThanFun(4.0) throws AssertionError") {
                expect {
                    fluentEmpty.containsEntriesFun({ isLessThan(1.0) }, { isGreaterThan(4.0) })
                }.toThrow<AssertionError> {
                    message {
                        contains.exactly(1).values(
                            "$rootBulletPoint$containsInAnyOrderOnly:",
                            "$failingBulletPoint$anEntryAfterFailing$isLessThanDescr: 1.0",
                            "$failingBulletPoint$anEntryAfterFailing$isGreaterThanDescr: 4.0"
                        )
                        containsNot(additionalEntries)
                        containsSize(0, 2)
                    }
                }
            }

            //TODO remove with 1.0.0
            it("$returnValueOfFun(...) states warning that subject is not set") {
                expect {
                    fluentEmpty.containsEntriesFun({
                        @Suppress("DEPRECATION")
                        asAssert().returnValueOf(subject::dec).asExpect().toBe(1.0)
                    })
                }.toThrow<AssertionError> { messageContains(ErrorMessages.SUBJECT_ACCESSED_TOO_EARLY.getDefault()) }
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
                            { toBe(it.first()) },
                            *(it.drop(1).map { val f: Expect<Double>.() -> Unit = { toBe(it) }; f }).toTypedArray()
                        )
                    }
                }

                it("1.0, 2.0, 3.0, 4.0 and $isGreaterThanFun(0.0)") {
                    expect(oneToFour()).containsEntriesFun(
                        { toBe(1.0) },
                        { toBe(2.0) },
                        { toBe(3.0) },
                        { toBe(4.0) },
                        { isGreaterThan(0.0) })
                }
                it(" $isLessThanFun(3.0), 2.0, 3.0, 4.0 and 4.0") {
                    expect(oneToFour()).containsEntriesFun(
                        { isLessThan(3.0) },
                        { toBe(2.0) },
                        { toBe(3.0) },
                        { toBe(4.0) },
                        { toBe(4.0) })
                }
                it("2.0, $isLessThanFun(5.0), 3.0, 4.0 and 4.0") {
                    expect(oneToFour()).containsEntriesFun(
                        { toBe(2.0) },
                        { isLessThan(5.0) },
                        { toBe(3.0) },
                        { toBe(4.0) },
                        { toBe(4.0) })
                }
            }

            context("error cases (throws AssertionError)") {

                context("additional entries") {
                    it("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                        expect {
                            expect(oneToFour()).containsEntriesFun(
                                { toBe(1.0) },
                                { toBe(2.0) },
                                { toBe(3.0) },
                                { toBe(4.0) })
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 1.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 2.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 3.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 4.0",
                                    "$warningBulletPoint$additionalEntries:",
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
                                    "$warningBulletPoint$additionalEntries:",
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
                                { toBe(1.0) },
                                { toBe(2.0) },
                                { toBe(3.0) },
                                { toBe(4.0) })
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
                                containsSize(5, 5)
                            }
                        }
                    }
                }

                context("mismatches and additional entries") {
                    it("1.0, $isGreaterThanFun(3.0), $isGreaterThanFun(4.0) -- $isGreaterThanFun(4.0) is wrong and 2.0, 3.0 and 4.0 are missing") {
                        expect {
                            expect(oneToFour()).containsEntriesFun(
                                { toBe(1.0) },
                                { isGreaterThan(3.0) },
                                { isGreaterThan(4.0) })
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 1.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$isGreaterThanDescr: 3.0",
                                    "$failingBulletPoint$anEntryAfterFailing$isGreaterThanDescr: 4.0",
                                    "$warningBulletPoint$mismatchesAdditionalEntries:",
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
                                { toBe(1.0) },
                                { toBe(2.0) },
                                { toBe(3.0) },
                                { toBe(4.0) },
                                { toBe(4.0) },
                                { toBe(5.0) })
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
                                containsNot(additionalEntries, mismatches, mismatchesAdditionalEntries)
                            }
                        }
                    }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun("${containsInAnyOrderOnlyNullableEntries.name} for nullable") {
            val list = listOf(null, 1.0, null, 3.0).asIterable()
            val fluent = expect(list)

            context("iterable $list") {
                context("happy cases (do not throw)") {
                    it("null, $toBeFun(1.0), null, $toBeFun(3.0)") {
                        fluent.containsInAnyOrderOnlyNullableEntriesFun(null, { toBe(1.0) }, null, { toBe(3.0) })
                    }
                    it("$toBeFun(1.0), null, null, $toBeFun(3.0)") {
                        fluent.containsInAnyOrderOnlyNullableEntriesFun({ toBe(1.0) }, null, null, { toBe(3.0) })
                    }
                    it("$toBeFun(1.0), null, $toBeFun(3.0), null") {
                        fluent.containsInAnyOrderOnlyNullableEntriesFun({ toBe(1.0) }, null, { toBe(3.0) }, null)
                    }
                    it("$toBeFun(1.0), $toBeFun(3.0), null, null") {
                        fluent.containsInAnyOrderOnlyNullableEntriesFun({ toBe(1.0) }, { toBe(3.0) }, null, null)
                    }
                }

                context("failing cases") {
                    it("null, $toBeFun(1.0), $toBeFun(3.0) -- null was missing") {
                        expect {
                            fluent.containsInAnyOrderOnlyNullableEntriesFun(null, { toBe(1.0) }, { toBe(3.0) })
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$isDescr: null",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 1.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 3.0",
                                    "$warningBulletPoint$additionalEntries:",
                                    "${listBulletPoint}null"
                                )
                                containsSize(4, 3)
                            }
                        }
                    }

                    it("first wins: $isLessThanFun(5.0), 1.0, 2.0, 3.0, 4.0") {
                        expect {
                            fluent.containsInAnyOrderOnlyNullableEntriesFun(
                                { isLessThan(4.0) },
                                null,
                                null,
                                { toBe(1.0) })
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
                                containsSize(4, 4)
                            }
                        }
                    }
                }
            }
        }
    }
})
