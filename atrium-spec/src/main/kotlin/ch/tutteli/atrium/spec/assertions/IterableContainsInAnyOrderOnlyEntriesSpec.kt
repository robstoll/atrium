package ch.tutteli.atrium.spec.assertions


import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class IterableContainsInAnyOrderOnlyEntriesSpec(
    verbs: AssertionVerbFactory,
    containsEntriesPair: Pair<String, AssertionPlant<Iterable<Double>>.(AssertionPlant<Double>.() -> Unit, Array<out AssertionPlant<Double>.() -> Unit>) -> AssertionPlant<Iterable<Double>>>,
    rootBulletPoint: String,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsEntriesPair.first to mapToCreateAssertion { containsEntriesPair.second(this, { toBe(2.5) }, arrayOf()) }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsEntriesPair.first, { containsEntriesPair.second(this, { toBe(2.5) }, arrayOf()) }, listOf(2.5) as Iterable<Double>, listOf())
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Iterable<Double>) -> AssertionPlant<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val oneToFour = listOf(1.0, 2.0, 3.0, 4.0, 4.0)
    val fluent = assert(oneToFour)

    val (containsEntries, containsEntriesFunArr) = containsEntriesPair
    fun AssertionPlant<Iterable<Double>>.containsEntriesFun(t: AssertionPlant<Double>.() -> Unit, vararg tX: AssertionPlant<Double>.() -> Unit)
        = containsEntriesFunArr(t, tX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val indentSuccessfulBulletPoint = " ".repeat(successfulBulletPoint.length)
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length)

    val anEntryAfterSuccess = "$anEntryWhich: $separator$indentBulletPoint$indentSuccessfulBulletPoint$listBulletPoint"
    val anEntryAfterFailing = "$anEntryWhich: $separator$indentBulletPoint$indentFailingBulletPoint$listBulletPoint"

    describeFun(containsEntries) {
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
                        )
                        containsNot(additionalEntries)
                        containsSize(0, 1)
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
                        )
                        containsNot(additionalEntries)
                        containsSize(0, 2)
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
                        fluent.containsEntriesFun({ toBe(it.first()) }, *(it.drop(1).map { val f: AssertionPlant<Double>.() -> Unit = { toBe(it) }; f }).toTypedArray())
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
