package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.fluent.en_GB.exactly
import ch.tutteli.atrium.api.fluent.en_GB.values
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import org.spekframework.spek2.style.specification.Suite

abstract class IterableContainsInAnyOrderNotOrAtMostValuesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsNotOrAtMostTriple: Triple<String, (String, String) -> String, Expect<Iterable<Double>>.(Int, Double, Array<out Double>) -> Expect<Iterable<Double>>>,
    containsNotPair: Pair<String, (Int) -> String>,
    rootBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        containsNotOrAtMostTriple.first to expectLambda { containsNotOrAtMostTriple.third(this, 2, 2.3, arrayOf()) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val assert: (Iterable<Double>) -> Expect<Iterable<Double>> = verbs::check
    val expect = verbs::checkException
    val fluent = assert(oneToSeven)

    val (containsNotOrAtMost, containsNotOrAtMostTest, containsNotOrAtMostFunArr) = containsNotOrAtMostTriple
    fun Expect<Iterable<Double>>.containsNotOrAtMostFun(atLeast: Int, a: Double, vararg aX: Double)
        = containsNotOrAtMostFunArr(atLeast, a, aX.toTypedArray())
    val (containsNot, errorMsgContainsNot) = containsNotPair

    describeFun(containsNotOrAtMost) {

        context("throws an $illegalArgumentException") {
            it("for not at all or at most -1 -- only positive numbers") {
                expect {
                    fluent.containsNotOrAtMostFun(-1, 0.0)
                }.toThrow<IllegalArgumentException> { messageContains("positive number", -1) }
            }
            it("for not at all or at most 0 -- points to $containsNot") {
                expect {
                    fluent.containsNotOrAtMostFun(0, 0.0)
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
        }

        context("iterable $oneToSeven") {
            context("happy case with $containsNotOrAtMost once") {
                it("${containsNotOrAtMostTest("1.0", "once")} does not throw") {
                    fluent.containsNotOrAtMostFun(1, 1.0)
                }
                it("${containsNotOrAtMostTest("1.0 and 2.0 and 3.0", "once")} does not throw") {
                    fluent.containsNotOrAtMostFun(1, 1.0, 2.0, 3.0)
                }
                it("${containsNotOrAtMostTest("3.0 and 1.0 and 2.0", "once")} does not throw") {
                    fluent.containsNotOrAtMostFun(1, 3.0, 1.0, 2.0)
                }
                it("${containsNotOrAtMostTest("21.1 and 34.0 and 11.23", "twice")}  does not throw") {
                    fluent.containsNotOrAtMostFun(2, 21.1, 34.0, 11.23)
                }
            }

            context("failing cases; search string at different positions") {
                it("${containsNotOrAtMostTest("4.0", "once")} throws AssertionError") {
                    expect {
                        fluent.containsNotOrAtMostFun(1, 4.0)
                    }.toThrow<AssertionError> { messageContains("$atMost: 1", "$anEntryWhichIs: 4.0") }
                }
                it("${containsNotOrAtMostTest("1.0, 4.0", "once")} throws AssertionError mentioning only 4.0") {
                    expect {
                        fluent.containsNotOrAtMostFun(1, 1.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 1", "$anEntryWhichIs: 4.0")
                            containsNot("$anEntryWhichIs: 1.0")
                        }
                    }
                }
                it("${containsNotOrAtMostTest("4.0, 1.0", "once")} once throws AssertionError mentioning only 4.0") {
                    expect {
                        fluent.containsNotOrAtMostFun(1, 4.0, 1.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 1", "$anEntryWhichIs: 4.0")
                            containsNot("$anEntryWhichIs: 1.0")
                        }
                    }
                }
                it("${containsNotOrAtMostTest("5.0, 3.1, 3.0, 4.0", "once")} throws AssertionError") {
                    expect {
                        fluent.containsNotOrAtMostFun(1, 5.0, 3.1, 3.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(2).values(
                                "$atMost: 1"
                            )
                            contains.exactly(1).values(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 5.0",
                                "$numberOfOccurrences: 2",
                                "$anEntryWhichIs: 4.0",
                                "$numberOfOccurrences: 3"
                            )
                        }
                    }
                }
            }

            context("multiple occurrences of the search string") {
                it("${containsNotOrAtMostTest("5.0", "once")} throws AssertionError and message contains both, how many times we expected (1) and how many times it actually contained 5.0 (2)") {
                    expect {
                        fluent.containsNotOrAtMostFun(1, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 5.0",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$atMost: 1")
                        }
                    }
                }

                it("${containsNotOrAtMostTest("5.0", "twice")} does not throw") {
                    fluent.containsNotOrAtMostFun(2, 5.0)
                }


                it("${containsNotOrAtMostTest("5.0", "3 times")} does not throw") {
                    fluent.containsNotOrAtMostFun(3, 5.0)
                }
                it("${containsNotOrAtMostTest("5.0 and 4.0", "twice")} throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 4.0 (3)") {
                    expect {
                        fluent.containsNotOrAtMostFun(2, 5.0, 4.0)
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
                it("${containsNotOrAtMostTest("4.0", "3 times")} does not throw") {
                    fluent.containsNotOrAtMostFun(3, 4.0)
                }
                it("${containsNotOrAtMostTest("5.0 and 4.0", "3 times")} does not throw") {
                    fluent.containsNotOrAtMostFun(3, 5.0, 4.0)
                }

            }
        }
    }
})
