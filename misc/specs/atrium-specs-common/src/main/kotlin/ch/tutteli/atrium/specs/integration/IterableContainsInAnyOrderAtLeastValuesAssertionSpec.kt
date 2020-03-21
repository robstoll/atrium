package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class IterableContainsInAnyOrderAtLeastValuesAssertionSpec(
    containsAtLeastPair: Pair<(String, String) -> String, Fun3<Iterable<Double>, Int, Double, Array<out Double>>>,
    containsAtLeastButAtMostPair: Pair<(String, String, String) -> String, Fun4<Iterable<Double>, Int, Int, Double, Array<out Double>>>,
    containsNotPair: Pair<String, (Int) -> String>,
    exactlyPair: Pair<String, (Int) -> String>,
    errorMsgAtLeastButAtMost: (Int, Int) -> String,
    rootBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    val containsAtLeast = containsAtLeastPair.second
    val containsAtLeastButAtMost = containsAtLeastButAtMostPair.second

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        containsAtLeast.forSubjectLess(1, 2.3, arrayOf()),
        containsAtLeastButAtMost.forSubjectLess(1, 2, 2.3, arrayOf())
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    fun Expect<Iterable<Double>>.containsAtLeastFun(atLeast: Int, a: Double, vararg aX: Double) =
        containsAtLeast(this, atLeast, a, aX.toTypedArray())

    fun Expect<Iterable<Double>>.containsAtLeastButAtMostFun(atLeast: Int, atMost: Int, a: Double, vararg aX: Double) =
        containsAtLeastButAtMost(this, atLeast, atMost, a, aX.toTypedArray())

    val (containsNot, errorMsgContainsNot) = containsNotPair
    val (exactly, errorMsgExactly) = exactlyPair

    describeFun(containsAtLeast, containsAtLeastButAtMost) {
        context("throws an $illegalArgumentException") {
            it("for at least -1 -- only positive numbers") {
                expect {
                    expect(oneToSeven()).containsAtLeastFun(-1, 9.0)
                }.toThrow<IllegalArgumentException> { messageContains("positive number", -1) }
            }
            it("for at least 0 -- points to $containsNot") {
                expect {
                    expect(oneToSeven()).containsAtLeastFun(0, 9.0)
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
            context("using $containsAtLeastButAtMost") {
                it("for at least 1 but at most -1 -- since -1 is smaller than 1") {
                    expect {
                        expect(oneToSeven()).containsAtLeastButAtMostFun(1, -1, 9.0)
                    }.toThrow<IllegalArgumentException> { message { toBe(errorMsgAtLeastButAtMost(1, -1)) } }
                }
                it("for at least 1 but at most 0 -- since 0 is smaller than 1") {
                    expect {
                        expect(oneToSeven()).containsAtLeastButAtMostFun(1, 0, 9.0)
                    }.toThrow<IllegalArgumentException> { message { toBe(errorMsgAtLeastButAtMost(1, 0)) } }
                }
                it("for at least 2 but at most 1 -- since 1 is smaller than 2") {
                    expect {
                        expect(oneToSeven()).containsAtLeastButAtMostFun(2, 1, 9.0)
                    }.toThrow<IllegalArgumentException> { message { toBe(errorMsgAtLeastButAtMost(2, 1)) } }
                }
                it("for at least 1 but at most 1 -- points to $exactly") {
                    expect {
                        expect(oneToSeven()).containsAtLeastButAtMostFun(1, 1, 9.0)
                    }.toThrow<IllegalArgumentException> { message { toBe(errorMsgExactly(1)) } }
                }
            }
        }

        context("iterable ${oneToSeven().toList()}") {

            context("happy case with $containsAtLeast once") {
                it("${containsAtLeastPair.first("1.0", "once")} does not throw") {
                    expect(oneToSeven()).containsAtLeastFun(1, 1.0)
                }
                it("${containsAtLeastPair.first("1.0 and 2.0 and 3.0", "once")} does not throw") {
                    expect(oneToSeven()).containsAtLeastFun(1, 1.0, 2.0, 3.0)
                }
                it("${containsAtLeastPair.first("3.0 and 1.0 and 2.0", "once")} does not throw") {
                    expect(oneToSeven()).containsAtLeastFun(1, 3.0, 1.0, 2.0)
                }
            }

            context("failing cases; search wrong number at different positions with $containsAtLeast once") {
                it("${containsAtLeastPair.first("1.1", "once")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).containsAtLeastFun(1, 1.1)
                    }.toThrow<AssertionError> { messageContains("$atLeast: 1", "$anEntryWhichIs: 1.1") }
                }
                it("${containsAtLeastPair.first("1.0, 2.3", "once")} throws AssertionError mentioning only 2.3") {
                    expect {
                        expect(oneToSeven()).containsAtLeastFun(1, 1.0, 2.3)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atLeast: 1", "$anEntryWhichIs: 2.3")
                            containsNot("$anEntryWhichIs: 1.0")
                        }
                    }
                }
                it("${containsAtLeastPair.first("2.3, 1.0", "once")} throws AssertionError mentioning only 2.3") {
                    expect {
                        expect(oneToSeven()).containsAtLeastFun(1, 2.3, 1.0)
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atLeast: 1", "$anEntryWhichIs: 2.3")
                            containsNot("$anEntryWhichIs: 1.0")
                        }
                    }
                }
                it("${containsAtLeastPair.first("1.0, 2.3, 3.1 and 6.0", "once")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).containsAtLeastFun(1, 1.0, 2.3, 3.1, 6.0)
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

            context("multiple occurrences of the search string") {
                it("${containsAtLeastPair.first("5.0", "once")} does not throw") {
                    expect(oneToSeven()).containsAtLeastFun(1, 5.0)
                }
                it("${containsAtLeastPair.first("5.0", "twice")} does not throw") {
                    expect(oneToSeven()).containsAtLeastFun(2, 5.0)
                }

                it(
                    "${containsAtLeastPair.first(
                        "5.0",
                        "3 times"
                    )} throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 5.0 (2)"
                ) {
                    expect {
                        expect(oneToSeven()).containsAtLeastFun(3, 5.0)
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

                it("${containsAtLeastPair.first("5.0 and 4.0", "twice")} does not throw") {
                    expect(oneToSeven()).containsAtLeastFun(2, 5.0, 4.0)
                }
                it("${containsAtLeastPair.first("4.0", "3 times")} does not throw") {
                    expect(oneToSeven()).containsAtLeastFun(3, 4.0)
                }

                it(
                    "${containsAtLeastPair.first(
                        "5.0 and 4.0",
                        "3 times"
                    )} throws AssertionError and message contains both, at least: 3 and how many times it actually contained 5.0 (2)"
                ) {
                    expect {
                        expect(oneToSeven()).containsAtLeastFun(3, 5.0, 4.0)
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

            context("using $containsAtLeastButAtMost") {
                it("${containsAtLeastButAtMostPair.first("5.0", "once", "twice")} does not throw") {
                    expect(oneToSeven()).containsAtLeastButAtMostFun(1, 2, 5.0)
                }
                it(
                    "${containsAtLeastButAtMostPair.first(
                        "5.0 and 4.0",
                        "once",
                        "twice"
                    )} throws AssertionError and message contains both, at most: 2 and how many times it actually contained 4.0 (3)"
                ) {
                    expect {
                        expect(oneToSeven()).containsAtLeastButAtMostFun(1, 2, 5.0, 4.0)
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
                it("${containsAtLeastButAtMostPair.first("5.0 and 4.0", "twice", "3 times")} does not throw") {

                    expect(oneToSeven()).containsAtLeastButAtMostFun(2, 3, 5.0, 4.0)
                }

                it(
                    "${containsAtLeastButAtMostPair.first(
                        "5.0 and 4.0",
                        "3 times",
                        "4 times"
                    )} throws AssertionError and message contains both, at least: 3 and how many times it actually contained 5.0 (2)"
                ) {
                    expect {
                        expect(oneToSeven()).containsAtLeastButAtMostFun(3, 4, 5.0, 4.0)
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
