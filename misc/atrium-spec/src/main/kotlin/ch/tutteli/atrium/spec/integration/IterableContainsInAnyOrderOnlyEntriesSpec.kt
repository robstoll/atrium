package ch.tutteli.atrium.spec.integration


import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class IterableContainsInAnyOrderOnlyEntriesSpec(
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

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsInAnyOrderOnlyEntriesPair.first to mapToCreateAssertion { containsInAnyOrderOnlyEntriesPair.second(this, { toBe(2.5) }, arrayOf()) },
        containsInAnyOrderOnlyNullableEntriesPair.first to mapToCreateAssertion { containsInAnyOrderOnlyNullableEntriesPair.second(this, null, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsInAnyOrderOnlyEntriesPair.first, { containsInAnyOrderOnlyEntriesPair.second(this, { toBe(2.5) }, arrayOf()) }, listOf(2.5).asIterable(), listOf()),
        checkingTriple(containsInAnyOrderOnlyNullableEntriesPair.first, { containsInAnyOrderOnlyNullableEntriesPair.second(this, { toBe(1.0) }, arrayOf()) }, listOf(1.0).asIterable(), listOf(1.2))
    ) {})

    fun SpecBody.describeFun(funName: String, body: SpecBody.() -> Unit)
        = group("fun `$funName`", body = body)

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException

    val (containsInAnyOrderOnlyEntries, containsInAnyOrderOnlyEntriesFunArr) = containsInAnyOrderOnlyEntriesPair

    val (containsInAnyOrderOnlyNullableEntries, containsInAnyOrderOnlyNullableEntriesArr) = containsInAnyOrderOnlyNullableEntriesPair
    fun Assert<Iterable<Double?>>.containsInAnyOrderOnlyNullableEntriesFun(t: (Assert<Double>.() -> Unit)?, vararg tX: (Assert<Double>.() -> Unit)?)
        = containsInAnyOrderOnlyNullableEntriesArr(t, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)
    val indentListBulletPoint = " ".repeat(listBulletPoint.length)

    val anEntryAfterSuccess = "$anEntryWhich: $separator$indentBulletPoint$indentSuccessfulBulletPoint$indentListBulletPoint$explanatoryBulletPoint"
    val anEntryAfterFailing = "$anEntryWhich: $separator$indentBulletPoint$indentFailingBulletPoint$indentListBulletPoint$explanatoryBulletPoint"

    group("$describePrefix describe non-nullable cases") {
        mapOf<String, Assert<Iterable<Double>>.(Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>) -> Any>(
            containsInAnyOrderOnlyEntries to { a, aX -> this.containsInAnyOrderOnlyEntriesFunArr(a, aX) },
            containsInAnyOrderOnlyNullableEntries to { a, aX -> this.containsInAnyOrderOnlyNullableEntriesArr(a, aX) }
        ).forEach { (describe, containsEntriesFunArr) ->

            fun Assert<Iterable<Double>>.containsEntriesFun(
                t: Assert<Double>.() -> Unit,
                vararg tX: Assert<Double>.() -> Unit
            ) = containsEntriesFunArr(t, tX)

            describeFun(describe) {
                context("empty collection") {
                    val fluentEmpty = assert(setOf())
                    test("$isLessThanFun(1.0) throws AssertionError") {
                        expect {
                            fluentEmpty.containsEntriesFun({ isLessThan(1.0) })
                        }.toThrow<AssertionError> {
                            message {
                                contains(
                                    "$containsInAnyOrderOnly:",
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
                                contains(
                                    "$containsInAnyOrderOnly:",
                                    "$failingBulletPoint$anEntryAfterFailing$isLessThanDescr: 1.0",
                                    "$failingBulletPoint$anEntryAfterFailing$isGreaterThanDescr: 4.0"
                                )
                                containsNot(additionalEntries)
                                containsSize(0, 2)
                            }
                        }
                    }
                    test("$returnValueOfFun(...) states warning that subject is not set") {
                        expect {
                            fluentEmpty.containsEntriesFun({ returnValueOf(subject::dec).toBe(1.0) })
                        }.toThrow<AssertionError> { message { containsDefaultTranslationOf(DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE) } }
                    }
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
                                    *(it.drop(1).map {
                                        val f: Assert<Double>.() -> Unit = { toBe(it) }; f
                                    }).toTypedArray()
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
                                        contains(
                                            "$containsInAnyOrderOnly:",
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
                                        contains(
                                            "$containsInAnyOrderOnly:",
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
                                        contains(
                                            "$containsInAnyOrderOnly:",
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
                                        contains(
                                            "$containsInAnyOrderOnly:",
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
                                        contains(
                                            "$containsInAnyOrderOnly:",
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

                context("search for entry where the lambda does not specify any assertion") {
                    it("throws an ${IllegalArgumentException::class.simpleName}") {
                        expect {
                            fluent.containsEntriesFun({})
                        }.toThrow<IllegalArgumentException> { messageContains("not any assertion created") }
                    }
                }
            }
        }
    }

    group("$describePrefix describe nullable cases") {

        describeFun(containsInAnyOrderOnlyNullableEntries) {
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
                                contains(
                                    "$containsInAnyOrderOnly:",
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
                                contains(
                                    "$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$isLessThanDescr: 4.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$isDescr: null",
                                    "$successfulBulletPoint$anEntryAfterSuccess$isDescr: null",
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

            context("search for entry where the lambda does not specify any assertion") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    expect {
                        fluent.containsInAnyOrderOnlyNullableEntriesFun({})
                    }.toThrow<IllegalArgumentException> { messageContains("not any assertion created") }
                }
            }
        }
    }
})
