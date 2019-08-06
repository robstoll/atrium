package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.fluent.en_GB.exactly
import ch.tutteli.atrium.api.fluent.en_GB.values
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.EXACTLY
import org.spekframework.spek2.style.specification.Suite

abstract class IterableContainsInAnyOrderExactlyValuesAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsExactlyTriple: Triple<String, (String, String) -> String, Expect<Iterable<Double>>.(Int, Double, Array<out Double>) -> Expect<Iterable<Double>>>,
    containsNotPair: Pair<String, (Int) -> String>,
    rootBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(describePrefix,
        containsExactlyTriple.first to expectLambda { containsExactlyTriple.third(this, 2, 2.3, arrayOf()) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val assert: (Iterable<Double>) -> Expect<Iterable<Double>> = verbs::check
    val expect = verbs::checkException
    val fluent = assert(oneToSeven)

    val (containsExactly, containsExactlyTest, containsExactlyFunArr) = containsExactlyTriple
    fun Expect<Iterable<Double>>.containsExactlyFun(atLeast: Int, a: Double, vararg aX: Double)
        = containsExactlyFunArr(atLeast, a, aX.toTypedArray())

    val (containsNot, errorMsgContainsNot) = containsNotPair

    val exactly = EXACTLY.getDefault()

    describeFun(containsExactly) {
        context("throws an $illegalArgumentException") {
            it("for exactly -1 -- only positive numbers") {
                expect {
                    fluent.containsExactlyFun(-1, 0.0)
                }.toThrow<IllegalArgumentException> { messageContains("positive number", -1) }
            }
            it("for exactly 0 -- points to $containsNot") {
                expect {
                    fluent.containsExactlyFun(0, 0.0)
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
        }

        context("iterable $oneToSeven") {

            context("happy case with $containsExactly once") {
                it("${containsExactlyTest("1.0", "once")} does not throw") {
                    fluent.containsExactlyFun(1, 1.0)
                }
                it("${containsExactlyTest("1.0 and 2.0 and 3.0", "once")} does not throw") {
                    fluent.containsExactlyFun(1, 1.0, 2.0, 3.0)
                }
                it("${containsExactlyTest("3.0 and 1.0 and 2.0", "once")} does not throw") {
                    fluent.containsExactlyFun(1, 3.0, 1.0, 2.0)
                }
            }

            context("failing cases; search string at different positions with $containsExactly once") {
                it("${containsExactlyTest("4.0", "once")} throws AssertionError") {
                    expect {
                        fluent.containsExactlyFun(1, 4.0)
                    }.toThrow<AssertionError> { messageContains("$exactly: 1", "$anEntryWhichIs: 4.0") }
                }

                it("${containsExactlyTest("1.0, 2.3", "once")} throws AssertionError mentioning only 2.3") {
                    expect {
                        fluent.containsExactlyFun(1, 1.0, 2.3)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$exactly: 1", "$anEntryWhichIs: 2.3")
                            containsNot("$anEntryWhichIs: 1.0")
                        }
                    }
                }

                it("${containsExactlyTest("2.3, 1.0", "once")} throws AssertionError mentioning only 2.3") {
                    expect {
                        fluent.containsExactlyFun(1, 2.3, 1.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$exactly: 1", "$anEntryWhichIs: 2.3")
                            containsNot("$anEntryWhichIs: 1.0")
                        }
                    }
                }

                it("${containsExactlyTest("1.0 and 2.3 and 3.1", "once")} throws AssertionError") {
                    expect {
                        fluent.containsExactlyFun(1, 1.0, 2.3, 3.1)
                    }.toThrow<AssertionError> {
                        message {
                            contains(exactly, 2.3, 3.1)
                            contains.exactly(2).values(
                                "$numberOfOccurrences: 0",
                                "$exactly: 1"
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

            context("multiple occurrences of the search string") {
                it("${containsExactlyTest("5.0", "once")} throws AssertionError") {
                    expect {
                        fluent.containsExactlyFun(1, 5.0)
                    }.toThrow<AssertionError> { messageContains(EXACTLY.getDefault()) }
                }
                it("${containsExactlyTest("5.0", "twice")} does not throw") {
                    fluent.containsExactlyFun(2, 5.0)
                }

                it("${containsExactlyTest("5.0", "3 times")} throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 5.0 (2)") {
                    expect {
                        fluent.containsExactlyFun(3, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 5.0",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$exactly: 3")
                        }
                    }
                }

                it("${containsExactlyTest("5.0 and 4.0", "twice")} throws AssertionError") {
                    expect {
                        fluent.containsExactlyFun(2, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 4.0",
                                "$numberOfOccurrences: 3$separator"
                            )
                            endsWith("$exactly: 2")
                            containsNot("$anEntryWhichIs: 5.0")
                        }
                    }
                }
                it("${containsExactlyTest("4.0", "3 times")} does not throw") {
                    fluent.containsExactlyFun(3, 4.0)
                }
                it("${containsExactlyTest("5.0 and 4.0", "3 times")} throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 5.0 (2)") {
                    expect {
                        fluent.containsExactlyFun(3, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 5.0",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$exactly: 3")
                            containsNot("$anEntryWhichIs: 4.0")
                        }
                    }
                }
            }
        }
    }
})
