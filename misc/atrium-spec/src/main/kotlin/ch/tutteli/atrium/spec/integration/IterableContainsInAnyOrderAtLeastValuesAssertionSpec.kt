@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.AT_LEAST
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class IterableContainsInAnyOrderAtLeastValuesAssertionSpec(
    verbs: AssertionVerbFactory,
    containsAtLeastTriple: Triple<String, (String, String) -> String, Assert<Iterable<Double>>.(Int, Double, Array<out Double>) -> Assert<Iterable<Double>>>,
    containsAtLeastButAtMostTriple: Triple<String, (String, String, String) -> String, Assert<Iterable<Double>>.(Int, Int, Double, Array<out Double>) -> Assert<Iterable<Double>>>,
    containsNotPair: Pair<String, (Int) -> String>,
    exactlyPair: Pair<String, (Int) -> String>,
    errorMsgAtLeastButAtMost: (Int, Int) -> String,
    rootBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsAtLeastTriple.first to mapToCreateAssertion { containsAtLeastTriple.third(this, 1, 2.3, arrayOf()) },
        containsAtLeastButAtMostTriple.first to mapToCreateAssertion { containsAtLeastButAtMostTriple.third(this, 1, 2, 2.3, arrayOf()) }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsAtLeastTriple.first, { containsAtLeastTriple.third(this, 1, 2.3, arrayOf()) }, listOf(2.3, 2.3).asIterable(), listOf()),
        checkingTriple(containsAtLeastButAtMostTriple.first, { containsAtLeastButAtMostTriple.third(this, 1, 2, 2.3, arrayOf()) }, listOf(2.3).asIterable(), listOf())
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(oneToSeven)

    val (containsAtLeast, containsAtLeastTest, containsAtLeastFunArr) = containsAtLeastTriple
    fun Assert<Iterable<Double>>.containsAtLeastFun(atLeast: Int, a: Double, vararg aX: Double)
        = containsAtLeastFunArr(atLeast, a, aX.toTypedArray())

    val (containsAtLeastButAtMost, containsAtLeastButAtMostTest, containsAtLeastButAtMostFunArr) = containsAtLeastButAtMostTriple
    fun Assert<Iterable<Double>>.containsAtLeastButAtMostFun(atLeast: Int, atMost: Int, a: Double, vararg aX: Double)
        = containsAtLeastButAtMostFunArr(atLeast, atMost, a, aX.toTypedArray())

    val (containsNot, errorMsgContainsNot) = containsNotPair
    val (exactly, errorMsgExactly) = exactlyPair

    describeFun(containsAtLeast, containsAtLeastButAtMost) {
        context("throws an $illegalArgumentException") {
            test("for at least -1 -- only positive numbers") {
                expect {
                    fluent.containsAtLeastFun(-1, 9.0)
                }.toThrow<IllegalArgumentException> { messageContains("positive number", -1) }
            }
            test("for at least 0 -- points to $containsNot") {
                expect {
                    fluent.containsAtLeastFun(0, 9.0)
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
            group("using $containsAtLeastButAtMost") {
                test("for at least 1 but at most -1 -- since -1 is smaller than 1") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, -1, 9.0)
                    }.toThrow<IllegalArgumentException> { message { toBe(errorMsgAtLeastButAtMost(1, -1)) } }
                }
                test("for at least 1 but at most 0 -- since 0 is smaller than 1") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, 0, 9.0)
                    }.toThrow<IllegalArgumentException> { message { toBe(errorMsgAtLeastButAtMost(1, 0)) } }
                }
                test("for at least 2 but at most 1 -- since 1 is smaller than 2") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(2, 1, 9.0)
                    }.toThrow<IllegalArgumentException> { message { toBe(errorMsgAtLeastButAtMost(2, 1)) } }
                }
                test("for at least 1 but at most 1 -- points to $exactly") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, 1, 9.0)
                    }.toThrow<IllegalArgumentException> { message { toBe(errorMsgExactly(1)) } }
                }
            }
        }

        context("iterable $oneToSeven") {

            group("happy case with $containsAtLeast once") {
                test("${containsAtLeastTest("1.0", "once")} does not throw") {
                    fluent.containsAtLeastFun(1, 1.0)
                }
                test("${containsAtLeastTest("1.0 and 2.0 and 3.0", "once")} does not throw") {
                    fluent.containsAtLeastFun(1, 1.0, 2.0, 3.0)
                }
                test("${containsAtLeastTest("3.0 and 1.0 and 2.0", "once")} does not throw") {
                    fluent.containsAtLeastFun(1, 3.0, 1.0, 2.0)
                }
            }

            group("failing cases; search wrong number at different positions with $containsAtLeast once") {
                test("${containsAtLeastTest("1.1", "once")} throws AssertionError") {
                    expect {
                        fluent.containsAtLeastFun(1, 1.1)
                    }.toThrow<AssertionError> { messageContains("$atLeast: 1", "$anEntryWhichIs: 1.1") }
                }
                test("${containsAtLeastTest("1.0, 2.3", "once")} throws AssertionError mentioning only 2.3") {
                    expect {
                        fluent.containsAtLeastFun(1, 1.0, 2.3)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atLeast: 1", "$anEntryWhichIs: 2.3")
                            containsNot("$anEntryWhichIs: 1.0")
                        }
                    }
                }
                test("${containsAtLeastTest("2.3, 1.0", "once")} throws AssertionError mentioning only 2.3") {
                    expect {
                        fluent.containsAtLeastFun(1, 2.3, 1.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atLeast: 1", "$anEntryWhichIs: 2.3")
                            containsNot("$anEntryWhichIs: 1.0")
                        }
                    }
                }
                test("${containsAtLeastTest("1.0, 2.3, 3.1 and 6.0", "once")} throws AssertionError") {
                    expect {
                        fluent.containsAtLeastFun(1, 1.0, 2.3, 3.1, 6.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(2).values(
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1"
                            )
                            contains.exactly(1).values(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 2.3",
                                "$anEntryWhichIs: 3.1"
                            )
                        }
                    }
                }
            }

            group("multiple occurrences of the search string") {
                test("${containsAtLeastTest("5.0", "once")} does not throw") {
                    fluent.containsAtLeastFun(1, 5.0)
                }
                test("${containsAtLeastTest("5.0", "twice")} does not throw") {
                    fluent.containsAtLeastFun(2, 5.0)
                }

                test("${containsAtLeastTest("5.0", "3 times")} throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 5.0 (2)") {
                    expect {
                        fluent.containsAtLeastFun(3, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 5.0",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$atLeast: 3")
                        }
                    }
                }

                test("${containsAtLeastTest("5.0 and 4.0", "twice")} does not throw") {
                    fluent.containsAtLeastFun(2, 5.0, 4.0)
                }
                test("${containsAtLeastTest("4.0", "3 times")} does not throw") {
                    fluent.containsAtLeastFun(3, 4.0)
                }

                test("${containsAtLeastTest("5.0 and 4.0", "3 times")} throws AssertionError and message contains both, at least: 3 and how many times it actually contained 5.0 (2)") {
                    expect {
                        fluent.containsAtLeastFun(3, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 5.0",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$atLeast: 3")
                            containsNot("$anEntryWhichIs: 4.0")
                        }
                    }
                }
            }

            group("using $containsAtLeastButAtMost") {
                test("${containsAtLeastButAtMostTest("5.0", "once", "twice")} does not throw") {
                    fluent.containsAtLeastButAtMostFun(1, 2, 5.0)
                }
                test("${containsAtLeastButAtMostTest("5.0 and 4.0", "once", "twice")} throws AssertionError and message contains both, at most: 2 and how many times it actually contained 4.0 (3)") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, 2, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 4.0",
                                "$numberOfOccurrences: 3$separator"
                            )
                            endsWith("$atMost: 2")
                            containsNot(atLeast, "$anEntryWhichIs: 5.0")
                        }
                    }
                }
                test("${containsAtLeastButAtMostTest("5.0 and 4.0", "twice", "3 times")} does not throw") {

                    fluent.containsAtLeastButAtMostFun(2, 3, 5.0, 4.0)
                }

                test("${containsAtLeastButAtMostTest("5.0 and 4.0", "3 times", "4 times")} throws AssertionError and message contains both, at least: 3 and how many times it actually contained 5.0 (2)") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(3, 4, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 5.0",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$atLeast: 3")
                            containsNot(atMost, "$anEntryWhichIs: 4.0")
                        }
                    }
                }
            }
        }
    }
})
