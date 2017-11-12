package ch.tutteli.atrium.spec.assertions


import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class IterableContainsInAnyOrderOnlyEntriesSpec(
    verbs: IAssertionVerbFactory,
    containsEntriesPair: Pair<String, IAssertionPlant<Iterable<Double>>.(IAssertionPlant<Double>.() -> Unit, Array<out IAssertionPlant<Double>.() -> Unit>) -> IAssertionPlant<Iterable<Double>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String
) : IterableContainsEntriesSpecBase(verbs, {

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<Iterable<Double>>(
        containsEntriesPair.first to mapToCreateAssertion { containsEntriesPair.second(this, { toBe(2.5) }, arrayOf()) }
    ) {})

    val assert: (Iterable<Double>) -> IAssertionPlant<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val oneToFour = listOf(1.0, 2.0, 3.0, 4.0, 4.0)
    val fluent = assert(oneToFour)

    val (containsEntries, containsEntriesFunArr) = containsEntriesPair
    fun IAssertionPlant<Iterable<Double>>.containsEntriesFun(t: IAssertionPlant<Double>.() -> Unit, vararg tX: IAssertionPlant<Double>.() -> Unit)
        = containsEntriesFunArr(t, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)

    val additionalEntries = DescriptionIterableAssertion.WARNING_ADDITIONAL_ENTRIES.getDefault()
    val mismatches = DescriptionIterableAssertion.WARNING_MISMATCHES.getDefault()
    val mismatchesAdditionalEntries = DescriptionIterableAssertion.WARNING_MISMATCHES_ADDITIONAL_ENTRIES.getDefault()
    val anEntryAfterSuccess = "$anEntryWhich: $separator$indentBulletPoint$indentSuccessfulBulletPoint$listBulletPoint"
    val anEntryAfterFailing = "$anEntryWhich: $separator$indentBulletPoint$indentFailingBulletPoint$listBulletPoint"

    describe("fun $containsEntries")
    {
        context("empty collection $containsEntries ...") {
            val fluentEmptyString = assert(setOf())
            test("$isLessThanFun(1.0) throws AssertionError") {
                expect {
                    fluentEmptyString.containsEntriesFun({ isLessThan(1.0) })
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            "$containsInAnyOrderOnly:",
                            "$failingBulletPoint$anEntryAfterFailing$isLessThanDescr: 1.0"
                        ).containsNotDefaultTranslationOf(DescriptionIterableAssertion.WARNING_ADDITIONAL_ENTRIES)
                    }
                }
            }
            test("$isLessThanFun(1.0) and $isGreaterThanFun(4.0) throws AssertionError") {
                expect {
                    fluentEmptyString.containsEntriesFun({ isLessThan(1.0) }, { isGreaterThan(4.0) })
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            "$containsInAnyOrderOnly:",
                            "$failingBulletPoint$anEntryAfterFailing$isLessThanDescr: 1.0",
                            "$failingBulletPoint$anEntryAfterFailing$isGreaterThanDescr: 4.0"
                        ).containsNotDefaultTranslationOf(DescriptionIterableAssertion.WARNING_ADDITIONAL_ENTRIES)
                    }
                }
            }
            test("$returnValueOfFun(...) states warning that subject is not set") {
                expect {
                    fluentEmptyString.containsEntriesFun({ returnValueOf(subject::dec).toBe(1.0) })
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(DescriptionIterableAssertion.WARNING_SUBJECT_NOT_SET) } }
            }
        }

        context("iterable '$oneToFour'") {

            describe("happy cases $containsEntries ...") {

                listOf(
                    arrayOf(1.0, 2.0, 3.0, 4.0, 4.0),
                    arrayOf(1.0, 3.0, 2.0, 4.0, 4.0),
                    arrayOf(3.0, 4.0, 2.0, 1.0, 4.0),
                    arrayOf(2.0, 4.0, 4.0, 1.0, 3.0),
                    arrayOf(2.0, 4.0, 1.0, 4.0, 3.0),
                    arrayOf(4.0, 4.0, 3.0, 2.0, 1.0)
                ).forEach {
                    test("${it.joinToString()} with matcher $toBeFun") {
                        fluent.containsEntriesFun({ toBe(it.first()) }, *(it.drop(1).map { val f: IAssertionPlant<Double>.() -> Unit = { toBe(it) }; f }).toTypedArray())
                    }
                }

                test("1.0, 2.0, 3.0, 4.0 and $isGreaterThanFun(0.0)") {
                    fluent.containsEntriesFun({ toBe(1.0) }, { toBe(2.0) }, { toBe(3.0) }, { toBe(4.0) }, { isGreaterThan(0.0) })
                }
                test(" $isLessThanFun(3.0), 2.0, 3.0, 4.0 and 4.0") {
                    fluent.containsEntriesFun({ isLessThan(3.0) }, { toBe(2.0) }, { toBe(3.0) }, { toBe(4.0) }, { toBe(4.0) })
                }
                test("2.0, $isLessThanFun(5.0), 3.0, 4.0 and 4.0") {
                    fluent.containsEntriesFun({ toBe(2.0) }, { isLessThan(5.0) }, { toBe(3.0) }, { toBe(4.0) }, { toBe(4.0) })
                }
            }

            describe("error cases $containsEntries ... throws AssertionError") {

                context("additional entries") {
                    test("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                        expect {
                            fluent.containsEntriesFun({ toBe(1.0) }, { toBe(2.0) }, { toBe(3.0) }, { toBe(4.0) })
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
                            fluent.containsEntriesFun({ isLessThan(5.0) }, { toBe(1.0) }, { toBe(2.0) }, { toBe(3.0) }, { toBe(4.0) })
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
                            fluent.containsEntriesFun({ toBe(1.0) }, { isGreaterThan(3.0) }, { isGreaterThan(4.0) })
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
                            fluent.containsEntriesFun({ toBe(1.0) }, { toBe(2.0) }, { toBe(3.0) }, { toBe(4.0) }, { toBe(4.0) }, { toBe(5.0) })
                        }.toThrow<AssertionError> {
                            message {
                                contains(
                                    "$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 1.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 2.0",
                                    "$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 3.0",
                                    "$failingBulletPoint$anEntryAfterSuccess$toBeDescr: 5.0"
                                )
                                contains.exactly(2).value("$successfulBulletPoint$anEntryAfterSuccess$toBeDescr: 4.0")
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
                }.toThrow<IllegalArgumentException> { message { contains("not any assertion created") } }
            }
        }
    }
})
