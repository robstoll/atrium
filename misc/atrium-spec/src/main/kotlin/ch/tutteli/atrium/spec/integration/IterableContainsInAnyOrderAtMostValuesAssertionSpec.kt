package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.AT_MOST
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

abstract class IterableContainsInAnyOrderAtMostValuesAssertionSpec(
    verbs: AssertionVerbFactory,
    containsAtMostTriple: Triple<String, (String, String) -> String, Assert<Iterable<Double>>.(Int, Double, Array<out Double>) -> Assert<Iterable<Double>>>,
    containsNotPair: Pair<String, (Int) -> String>,
    exactlyPair: Pair<String, (Int) -> String>,
    rootBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        containsAtMostTriple.first to mapToCreateAssertion { containsAtMostTriple.third(this, 2, 2.3, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(containsAtMostTriple.first, { containsAtMostTriple.third(this, 2, 2.3, arrayOf()) }, listOf(2.3, 2.3).asIterable(), listOf(2.3, 2.3, 2.3))
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(oneToSeven)

    val (containsAtMost, containsAtMostTest, containsAtMostFunArr) = containsAtMostTriple
    fun Assert<Iterable<Double>>.containsAtMostFun(atLeast: Int, a: Double, vararg aX: Double)
        = containsAtMostFunArr(atLeast, a, aX.toTypedArray())

    val (containsNot, errorMsgContainsNot) = containsNotPair
    val (exactly, errorMsgExactly) = exactlyPair

    val anEntryWhichIs = DescriptionIterableAssertion.AN_ENTRY_WHICH_IS.getDefault()

    describeFun(containsAtMost) {

        context("throws an $illegalArgumentException") {
            test("for at most -1 -- only positive numbers") {
                expect {
                    fluent.containsAtMostFun(-1, 0.0)
                }.toThrow<IllegalArgumentException> { messageContains("positive number", -1) }
            }
            test("for at most 0 -- points to $containsNot") {
                expect {
                    fluent.containsAtMostFun(0, 0.0)
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
            test("for at most 1 -- points to $exactly") {
                expect {
                    fluent.containsAtMostFun(1, 0.0)
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgExactly(1)) } }
            }
        }

        context("iterable $oneToSeven") {
            group("happy case with $containsAtMost twice") {
                test("${containsAtMostTest("1.0", "once")} does not throw") {
                    fluent.containsAtMostFun(2, 1.0)
                }
                test("${containsAtMostTest("1.0 and 2.0 and 3.0", "once")} does not throw") {
                    fluent.containsAtMostFun(2, 1.0, 2.0, 3.0)
                }
                test("${containsAtMostTest("3.0 and 1.0 and 2.0", "once")} does not throw") {
                    fluent.containsAtMostFun(2, 3.0, 1.0, 2.0)
                }
            }

            group("failing assertions; search string at different positions") {
                test("${containsAtMostTest("4.0", "twice")} throws AssertionError") {
                    expect {
                        fluent.containsAtMostFun(2, 4.0)
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(AT_MOST) } }
                }
                test("${containsAtMostTest("1.0, 4.0", "twice")} throws AssertionError") {
                    expect {
                        fluent.containsAtMostFun(2, 1.0, 4.0)
                    }.toThrow<AssertionError> { messageContains(atMost, 4.0) }
                }
                test("${containsAtMostTest("4.0, 1.0", "twice")} once throws AssertionError") {
                    expect {
                        fluent.containsAtMostFun(2, 4.0, 1.0)
                    }.toThrow<AssertionError> { messageContains(atMost, 4.0) }
                }
                test("${containsAtMostTest("5.0, 3.1, 3.0, 4.0", "twice")} throws AssertionError") {
                    expect {
                        fluent.containsAtMostFun(2, 5.0, 3.1, 3.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).values(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 3.1",
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1",
                                "$anEntryWhichIs: 4.0",
                                "$numberOfOccurrences: 3",
                                "$atMost: 2"
                            )
                        }
                    }
                }
                test("${containsAtMostTest("21.1 and 34.0 and 11.23", "twice")} throws AssertionError") {
                    expect {
                        fluent.containsAtMostFun(2, 21.1, 34.0, 11.23)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(3).values(
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1"
                            )
                            contains.exactly(1).values(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 21.1",
                                "$anEntryWhichIs: 34.0",
                                "$anEntryWhichIs: 11.23"
                            )
                        }
                    }
                }
            }

            group("multiple occurrences of the search string") {

                test("${containsAtMostTest("5.0", "twice")} does not throw") {
                    fluent.containsAtMostFun(2, 5.0)
                }

                test("${containsAtMostTest("5.0", "3 times")} does not throw") {
                    fluent.containsAtMostFun(3, 5.0)
                }
                test("${containsAtMostTest("5.0 and 4.0", "twice")} throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 4.0 (3)") {
                    expect {
                        fluent.containsAtMostFun(2, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 4.0",
                                "$numberOfOccurrences: 3$separator"
                            )
                            endsWith("$atMost: 2")
                            containsNot("$anEntryWhichIs: 5.0")
                        }
                    }
                }
                test("${containsAtMostTest("4.0", "3 times")} does not throw") {
                    fluent.containsAtMostFun(3, 4.0)
                }
                test("${containsAtMostTest("5.0 and 4.0", "3 times")} does not throw") {
                    fluent.containsAtMostFun(3, 5.0, 4.0)
                }

            }
        }
    }
})
