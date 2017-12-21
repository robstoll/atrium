package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

abstract class IterableContainsInAnyOrderOnlyObjectsSpec(
    verbs: AssertionVerbFactory,
    containsPair: Pair<String, AssertionPlant<Iterable<Double>>.(Double, Array<out Double>) -> AssertionPlant<Iterable<Double>>>,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsPair.first to mapToCreateAssertion { containsPair.second(this, 2.5, arrayOf()) }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsPair.first, { containsPair.second(this, 2.5, arrayOf()) }, listOf(2.5) as Iterable<Double>, listOf(2.5, 2.2))
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Iterable<Double>) -> AssertionPlant<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val oneToFour = listOf(1.0, 2.0, 3.0, 4.0, 4.0)
    val fluent = assert(oneToFour)

    val (containsObjects, containsFunArr) = containsPair
    fun AssertionPlant<Iterable<Double>>.containsFun(t: Double, vararg tX: Double)
        = containsFunArr(t, tX.toTypedArray())

    val anEntryWhichIs = DescriptionIterableAssertion.AN_ENTRY_WHICH_IS.getDefault()

    describeFun(containsObjects) {
        context("empty collection") {
            val fluentEmptyString = assert(setOf())
            test("$containsObjects 1.0 throws AssertionError") {
                expect {
                    fluentEmptyString.containsFun(1.0)
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            "$containsInAnyOrderOnly:",
                            "$failingBulletPoint$anEntryWhichIs: 1.0"
                        )
                        containsNot(additionalEntries)
                        containsSize(0, 1)
                    }
                }
            }
            test("$containsObjects 1.0 and 4.0 throws AssertionError") {
                expect {
                    fluentEmptyString.containsFun(1.0, 4.0)
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            "$containsInAnyOrderOnly:",
                            "$failingBulletPoint$anEntryWhichIs: 1.0",
                            "$failingBulletPoint$anEntryWhichIs: 4.0"
                        )
                        containsNot(additionalEntries)
                        containsSize(0, 2)
                    }
                }
            }
        }

        context("iterable '$oneToFour'") {

            describe("happy cases $containsObjects") {
                listOf(
                    arrayOf(1.0, 2.0, 3.0, 4.0, 4.0),
                    arrayOf(1.0, 3.0, 2.0, 4.0, 4.0),
                    arrayOf(3.0, 4.0, 2.0, 1.0, 4.0),
                    arrayOf(2.0, 4.0, 4.0, 1.0, 3.0),
                    arrayOf(2.0, 4.0, 1.0, 4.0, 3.0),
                    arrayOf(4.0, 4.0, 3.0, 2.0, 1.0)
                ).forEach {
                    test(it.joinToString()) {
                        fluent.containsFun(it.first(), *it.drop(1).toDoubleArray())
                    }
                }
            }

            describe("error cases $containsObjects ... throws AssertionError") {

                context("additional entries") {
                    test("1.0, 2.0, 3.0, 4.0 -- 4.0 was missing") {
                        expect {
                            fluent.containsFun(1.0, 2.0, 3.0, 4.0)
                        }.toThrow<AssertionError> {
                            message {
                                contains(
                                    "$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryWhichIs: 1.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 2.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 3.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 4.0",
                                    "$warningBulletPoint$additionalEntries:",
                                    "${listBulletPoint}4.0"
                                )
                                containsSize(5, 4)
                            }
                        }
                    }

                    test("1.0, 4.0 -- 2.0, 3.0 and 4.0 was missing") {
                        expect {
                            fluent.containsFun(1.0, 4.0)
                        }.toThrow<AssertionError> {
                            message {
                                contains(
                                    "$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryWhichIs: 1.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 4.0",
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
                    test("1.0, 2.0, 3.0, 4.0, 5.0") {
                        expect {
                            fluent.containsFun(1.0, 2.0, 3.0, 4.0, 5.0)
                        }.toThrow<AssertionError> {
                            message {
                                contains(
                                    "$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryWhichIs: 1.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 2.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 3.0",
                                    "$failingBulletPoint$anEntryWhichIs: 5.0",
                                    "$warningBulletPoint$mismatches:",
                                    "${listBulletPoint}4.0"
                                )
                                containsSize(5, 5)
                            }
                        }
                    }
                }

                context("mismatches and additional entries") {
                    test("1.0, 3.0, 5.0 -- 5.0 is wrong and 2.0, 4.0 and 4.0 are missing") {
                        expect {
                            fluent.containsFun(1.0, 3.0, 5.0)
                        }.toThrow<AssertionError> {
                            message {
                                contains(
                                    "$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryWhichIs: 1.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 3.0",
                                    "$failingBulletPoint$anEntryWhichIs: 5.0",
                                    "$warningBulletPoint$mismatchesAdditionalEntries:",
                                    "${listBulletPoint}2.0",
                                    "${listBulletPoint}4.0",
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
                            fluent.containsFun(1.0, 2.0, 3.0, 4.0, 4.0, 5.0)
                        }.toThrow<AssertionError> {
                            message {
                                contains(
                                    "$containsInAnyOrderOnly:",
                                    "$successfulBulletPoint$anEntryWhichIs: 1.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 2.0",
                                    "$successfulBulletPoint$anEntryWhichIs: 3.0",
                                    "$failingBulletPoint$anEntryWhichIs: 5.0"
                                )
                                contains.exactly(2).value("$successfulBulletPoint$anEntryWhichIs: 4.0")
                                containsSize(5, 6)
                                containsNot(additionalEntries, mismatches, mismatchesAdditionalEntries)
                            }
                        }
                    }
                }
            }
        }
    }
})
