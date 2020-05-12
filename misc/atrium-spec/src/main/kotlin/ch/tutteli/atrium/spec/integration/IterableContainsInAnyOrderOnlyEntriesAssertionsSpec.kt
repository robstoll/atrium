// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.spec.integration


import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class IterableContainsInAnyOrderOnlyEntriesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsInAnyOrderOnlyEntriesPair: Pair<String, Assert<Iterable<Double>>.(Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>) -> Assert<Iterable<Double>>>,
    containsInAnyOrderOnlyNullableEntriesPair: Pair<String, Assert<Iterable<Double?>>.((Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>) -> Assert<Iterable<Double?>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    explanatoryBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsInAnyOrderOnlyEntriesPair.first to mapToCreateAssertion { containsInAnyOrderOnlyEntriesPair.second(this, { toBe(2.5) }, arrayOf()) },
        "${containsInAnyOrderOnlyNullableEntriesPair.first} for nullable" to mapToCreateAssertion { containsInAnyOrderOnlyNullableEntriesPair.second(this, null, arrayOf()) }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsInAnyOrderOnlyEntriesPair.first, { containsInAnyOrderOnlyEntriesPair.second(this, { toBe(2.5) }, arrayOf()) }, listOf(2.5).asIterable(), listOf()),
        checkingTriple("${containsInAnyOrderOnlyNullableEntriesPair.first} for nullable", { containsInAnyOrderOnlyNullableEntriesPair.second(this, { toBe(1.0) }, arrayOf()) }, listOf(1.0).asIterable(), listOf(1.2))
    ) {})

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException

    val (containsInAnyOrderOnlyNullableEntries, containsInAnyOrderOnlyNullableEntriesArr) = containsInAnyOrderOnlyNullableEntriesPair
    fun Assert<Iterable<Double?>>.containsInAnyOrderOnlyNullableEntriesFun(t: (Assert<Double>.() -> Unit)?, vararg tX: (Assert<Double>.() -> Unit)?)
        = containsInAnyOrderOnlyNullableEntriesArr(t, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentListBulletPoint = " ".repeat(listBulletPoint.length)

    val anEntryAfterSuccess = "$anEntryWhich: $separator$indentBulletPoint$indentSuccessfulBulletPoint$indentListBulletPoint$explanatoryBulletPoint"
    val anEntryAfterFailing = "$anEntryWhich: $separator$indentBulletPoint$indentFailingBulletPoint$indentListBulletPoint$explanatoryBulletPoint"

    nonNullableCases(describePrefix,
        containsInAnyOrderOnlyEntriesPair,
        containsInAnyOrderOnlyNullableEntriesPair
    ) { containsEntriesFunArr ->
        fun Assert<Iterable<Double>>.containsEntriesFun(t: Assert<Double>.() -> Unit, vararg tX: Assert<Double>.() -> Unit)
            = containsEntriesFunArr(t, tX)

        context("empty collection") {
            val fluentEmpty = assert(setOf())
            test("$isLessThanFun(1.0) throws AssertionError") {
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
            test("$isLessThanFun(1.0) and $isGreaterThanFun(4.0) throws AssertionError") {
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
            // message changed in new infix-API, not going to test it any more (is covered by bc-tests)
//            test("$returnValueOfFun(...) states warning that subject is not set") {
//                expect {
//                    fluentEmpty.containsEntriesFun({
//                        @Suppress("DEPRECATION")
//                        returnValueOf(subject::dec).toBe(1.0)
//                    })
//                }.toThrow<AssertionError> { messageContains(DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE.getDefault()) }
//            }
        }

        val fluent = assert(oneToFour)
        context("iterable $oneToFour") {

            describe("happy cases") {

                listOf(
                    arrayOf(1.0, 2.0, 3.0, 4.0, 4.0),
                    arrayOf(1.0, 3.0, 2.0, 4.0, 4.0),
                    arrayOf(3.0, 4.0, 2.0, 1.0, 4.0),
                    arrayOf(2.0, 4.0, 4.0, 1.0, 3.0),
                    arrayOf(2.0, 4.0, 1.0, 4.0, 3.0),
                    arrayOf(4.0, 4.0, 3.0, 2.0, 1.0)
                ).forEach {
                    test("${it.joinToString()} with matcher $toBeFun") {
                        fluent.containsEntriesFun(
                            { toBe(it.first()) },
                            *(it.drop(1).map { val f: Assert<Double>.() -> Unit = { toBe(it) }; f }).toTypedArray()
                        )
                    }
                }

                test("1.0, 2.0, 3.0, 4.0 and $isGreaterThanFun(0.0)") {
                    fluent.containsEntriesFun(
                        { toBe(1.0) },
                        { toBe(2.0) },
                        { toBe(3.0) },
                        { toBe(4.0) },
                        { isGreaterThan(0.0) })
                }
                test(" $isLessThanFun(3.0), 2.0, 3.0, 4.0 and 4.0") {
                    fluent.containsEntriesFun(
                        { isLessThan(3.0) },
                        { toBe(2.0) },
                        { toBe(3.0) },
                        { toBe(4.0) },
                        { toBe(4.0) })
                }
                test("2.0, $isLessThanFun(5.0), 3.0, 4.0 and 4.0") {
                    fluent.containsEntriesFun(
                        { toBe(2.0) },
                        { isLessThan(5.0) },
                        { toBe(3.0) },
                        { toBe(4.0) },
                        { toBe(4.0) })
                }
            }

            describe("error cases (throws AssertionError)") {

                context("additional entries") {
                    test("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                        expect {
                            fluent.containsEntriesFun(
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

                    test("$isLessThanFun(3.0), isGreaterThan(3.0) -- 2.0, 3.0 and 4.0 was missing") {
                        expect {
                            fluent.containsEntriesFun({ isLessThan(3.0) }, { isGreaterThan(3.0) })
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
                    test("first wins: $isLessThanFun(5.0), 1.0, 2.0, 3.0, 4.0") {
                        expect {
                            fluent.containsEntriesFun(
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
                    test("1.0, $isGreaterThanFun(3.0), $isGreaterThanFun(4.0) -- $isGreaterThanFun(4.0) is wrong and 2.0, 3.0 and 4.0 are missing") {
                        expect {
                            fluent.containsEntriesFun(
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
                    test("1.0, 2.0, 3.0, 4.0, 4.0, 5.0 -- 5.0 was too much") {
                        expect {
                            fluent.containsEntriesFun(
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

        // the new infix throws now an AssertionError instead - not going to test it any more (is covered by bc-tests)
//        context("search for entry where the lambda does not specify any assertion") {
//            it("throws an ${IllegalArgumentException::class.simpleName}") {
//                expect {
//                    fluent.containsEntriesFun({})
//                }.toThrow<IllegalArgumentException> { messageContains("not any assertion created") }
//            }
//        }
    }

    nullableCases(describePrefix) {

        describeFun("$containsInAnyOrderOnlyNullableEntries for nullable") {
            val list = listOf(null, 1.0, null, 3.0)
            val fluent = verbs.checkImmediately(list)
            absentSubjectTests(verbs, Assert<Iterable<Double?>>::containsInAnyOrderOnlyNullableEntriesFun)

            context("iterable $list") {
                context("happy cases (do not throw)") {
                    test("null, $toBeFun(1.0), null, $toBeFun(3.0)") {
                        fluent.containsInAnyOrderOnlyNullableEntriesFun(null, { toBe(1.0) }, null, { toBe(3.0) })
                    }
                    test("$toBeFun(1.0), null, null, $toBeFun(3.0)") {
                        fluent.containsInAnyOrderOnlyNullableEntriesFun({ toBe(1.0) }, null, null, { toBe(3.0) })
                    }
                    test("$toBeFun(1.0), null, $toBeFun(3.0), null") {
                        fluent.containsInAnyOrderOnlyNullableEntriesFun({ toBe(1.0) }, null, { toBe(3.0) }, null)
                    }
                    test("$toBeFun(1.0), $toBeFun(3.0), null, null") {
                        fluent.containsInAnyOrderOnlyNullableEntriesFun({ toBe(1.0) }, { toBe(3.0) }, null, null)
                    }
                }

                context("failing cases") {
                    test("null, $toBeFun(1.0), $toBeFun(3.0) -- null was missing") {
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

                    test("first wins: $isLessThanFun(5.0), 1.0, 2.0, 3.0, 4.0") {
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

            // the new infix throws now an AssertionError instead - not going to test it any more (is covered by bc-tests)
//            context("search for entry where the lambda does not specify any assertion") {
//                it("throws an ${IllegalArgumentException::class.simpleName}") {
//                    expect {
//                        fluent.containsInAnyOrderOnlyNullableEntriesFun({})
//                    }.toThrow<IllegalArgumentException> { messageContains("not any assertion created") }
//                }
//            }
        }
    }
})
