package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.AssertionVerbFactory
import org.spekframework.spek2.style.specification.Suite

abstract class IterableContainsInAnyOrderAtMostValuesAssertionSpec(
    verbs: AssertionVerbFactory,
    containsAtMostPair: Pair<(String, String) -> String, Fun3<Iterable<Double>, Int, Double, Array<out Double>>>,
    containsNotPair: Pair<String, (Int) -> String>,
    exactlyPair: Pair<String, (Int) -> String>,
    rootBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    val containsAtMost = containsAtMostPair.second

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        containsAtMost.forSubjectLess(2, 2.3, arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val assert: (Iterable<Double>) -> Expect<Iterable<Double>> = verbs::check
    val expect = verbs::checkException
    val fluent = assert(oneToSeven)

    fun Expect<Iterable<Double>>.containsAtMostFun(atLeast: Int, a: Double, vararg aX: Double) =
        containsAtMost(this, atLeast, a, aX.toTypedArray())

    val (containsNot, errorMsgContainsNot) = containsNotPair
    val (exactly, errorMsgExactly) = exactlyPair

    describeFun(containsAtMost.name) {

        context("throws an $illegalArgumentException") {
            it("for at most -1 -- only positive numbers") {
                expect {
                    fluent.containsAtMostFun(-1, 0.0)
                }.toThrow<IllegalArgumentException> { messageContains("positive number", -1) }
            }
            it("for at most 0 -- points to $containsNot") {
                expect {
                    fluent.containsAtMostFun(0, 0.0)
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
            it("for at most 1 -- points to $exactly") {
                expect {
                    fluent.containsAtMostFun(1, 0.0)
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgExactly(1)) } }
            }
        }

        context("iterable $oneToSeven") {
            context("happy case with $containsAtMost twice") {
                it("${containsAtMostPair.first("1.0", "once")} does not throw") {
                    fluent.containsAtMostFun(2, 1.0)
                }
                it("${containsAtMostPair.first("1.0 and 2.0 and 3.0", "once")} does not throw") {
                    fluent.containsAtMostFun(2, 1.0, 2.0, 3.0)
                }
                it("${containsAtMostPair.first("3.0 and 1.0 and 2.0", "once")} does not throw") {
                    fluent.containsAtMostFun(2, 3.0, 1.0, 2.0)
                }
            }

            context("failing cases; search string at different positions") {
                it("${containsAtMostPair.first("4.0", "twice")} throws AssertionError") {
                    expect {
                        fluent.containsAtMostFun(2, 4.0)
                    }.toThrow<AssertionError> { messageContains("$atMost: 2", "$anEntryWhichIs: 4.0") }
                }
                it("${containsAtMostPair.first("1.0, 4.0", "twice")} throws AssertionError mentioning only 4.0") {
                    expect {
                        fluent.containsAtMostFun(2, 1.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 2", "$anEntryWhichIs: 4.0")
                            containsNot("$anEntryWhichIs: 1.0")
                        }
                    }
                }
                it("${containsAtMostPair.first("4.0, 1.0", "twice")} once throws AssertionError mentioning only 4.0") {
                    expect {
                        fluent.containsAtMostFun(2, 4.0, 1.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 2", "$anEntryWhichIs: 4.0")
                            containsNot("$anEntryWhichIs: 1.0")
                        }
                    }
                }
                it("${containsAtMostPair.first("5.0, 3.1, 3.0, 4.0", "twice")} throws AssertionError") {
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
                it("${containsAtMostPair.first("21.1 and 34.0 and 11.23", "twice")} throws AssertionError") {
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

            context("multiple occurrences of the search string") {

                it("${containsAtMostPair.first("5.0", "twice")} does not throw") {
                    fluent.containsAtMostFun(2, 5.0)
                }

                it("${containsAtMostPair.first("5.0", "3 times")} does not throw") {
                    fluent.containsAtMostFun(3, 5.0)
                }
                it(
                    "${containsAtMostPair.first(
                        "5.0 and 4.0",
                        "twice"
                    )} throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 4.0 (3)"
                ) {
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
                it("${containsAtMostPair.first("4.0", "3 times")} does not throw") {
                    fluent.containsAtMostFun(3, 4.0)
                }
                it("${containsAtMostPair.first("5.0 and 4.0", "3 times")} does not throw") {
                    fluent.containsAtMostFun(3, 5.0, 4.0)
                }

            }
        }
    }
})
