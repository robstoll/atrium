package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class IterableToContainInAnyOrderAtLeastValuesExpectationsSpec(
    toContainAtLeastPair: Pair<(String, String) -> String, Fun3<Iterable<Double>, Int, Double, Array<out Double>>>,
    toContainAtLeastButAtMostPair: Pair<(String, String, String) -> String, Fun4<Iterable<Double>, Int, Int, Double, Array<out Double>>>,
    notToContainPair: Pair<String, (Int) -> String>,
    exactlyPair: Pair<String, (Int) -> String>,
    errorMsgAtLeastButAtMost: (Int, Int) -> String,
    describePrefix: String = "[Atrium] "
) : IterableToContainSpecBase({

    val toContainAtLeast = toContainAtLeastPair.second
    val toContainAtLeastButAtMost = toContainAtLeastButAtMostPair.second

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        toContainAtLeast.forSubjectLess(1, 2.3, arrayOf()),
        toContainAtLeastButAtMost.forSubjectLess(1, 2, 2.3, arrayOf())
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    fun Expect<Iterable<Double>>.toContainAtLeastFun(atLeast: Int, a: Double, vararg aX: Double) =
        toContainAtLeast(this, atLeast, a, aX.toTypedArray())

    fun Expect<Iterable<Double>>.toContainAtLeastButAtMostFun(atLeast: Int, atMost: Int, a: Double, vararg aX: Double) =
        toContainAtLeastButAtMost(this, atLeast, atMost, a, aX.toTypedArray())

    val (notToContain, errorMsgContainsNot) = notToContainPair
    val (exactly, errorMsgExactly) = exactlyPair

    describeFun(toContainAtLeast, toContainAtLeastButAtMost) {
        context("throws an $illegalArgumentException") {
            it("for at least -1 -- only positive numbers") {
                expect {
                    expect(oneToSeven()).toContainAtLeastFun(-1, 9.0)
                }.toThrow<IllegalArgumentException> { messageToContain("positive number", -1) }
            }
            it("for at least 0 -- points to $notToContain") {
                expect {
                    expect(oneToSeven()).toContainAtLeastFun(0, 9.0)
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgContainsNot(0)) } }
            }
            context("using $toContainAtLeastButAtMost") {
                it("for at least 1 but at most -1 -- since -1 is smaller than 1") {
                    expect {
                        expect(oneToSeven()).toContainAtLeastButAtMostFun(1, -1, 9.0)
                    }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgAtLeastButAtMost(1, -1)) } }
                }
                it("for at least 1 but at most 0 -- since 0 is smaller than 1") {
                    expect {
                        expect(oneToSeven()).toContainAtLeastButAtMostFun(1, 0, 9.0)
                    }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgAtLeastButAtMost(1, 0)) } }
                }
                it("for at least 2 but at most 1 -- since 1 is smaller than 2") {
                    expect {
                        expect(oneToSeven()).toContainAtLeastButAtMostFun(2, 1, 9.0)
                    }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgAtLeastButAtMost(2, 1)) } }
                }
                it("for at least 1 but at most 1 -- points to $exactly") {
                    expect {
                        expect(oneToSeven()).toContainAtLeastButAtMostFun(1, 1, 9.0)
                    }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgExactly(1)) } }
                }
            }
        }

        context("iterable ${oneToSeven().toList()}") {

            context("happy case with $toContainAtLeast once") {
                it("${toContainAtLeastPair.first("1.0", "once")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastFun(1, 1.0)
                }
                it("${toContainAtLeastPair.first("1.0 and 2.0 and 3.0", "once")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastFun(1, 1.0, 2.0, 3.0)
                }
                it("${toContainAtLeastPair.first("3.0 and 1.0 and 2.0", "once")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastFun(1, 3.0, 1.0, 2.0)
                }
            }

            context("failing cases; search wrong number at different positions with $toContainAtLeast once") {
                it("${toContainAtLeastPair.first("1.1", "once")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toContainAtLeastFun(1, 1.1)
                    }.toThrow<AssertionError> { messageToContain(noSuchValueDescr, "$anElementWhichEquals: 1.1") }
                }
                it("${toContainAtLeastPair.first("1.0, 2.3", "once")} throws AssertionError mentioning only 2.3") {
                    expect {
                        expect(oneToSeven()).toContainAtLeastFun(1, 1.0, 2.3)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(noSuchValueDescr, "$anElementWhichEquals: 2.3")
                            notToContain("$anElementWhichEquals: 1.0")
                        }
                    }
                }
                it("${toContainAtLeastPair.first("2.3, 1.0", "once")} throws AssertionError mentioning only 2.3") {
                    expect {
                        expect(oneToSeven()).toContainAtLeastFun(1, 2.3, 1.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(noSuchValueDescr, "$anElementWhichEquals: 2.3")
                            notToContain("$anElementWhichEquals: 1.0")
                        }
                    }
                }
                it("${toContainAtLeastPair.first("1.0, 2.3, 3.1 and 6.0", "once")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toContainAtLeastFun(1, 1.0, 2.3, 3.1, 6.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(2).values(
                                noSuchValueDescr
                            )
                            toContain.exactly(1).values(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 2.3",
                                "$anElementWhichEquals: 3.1"
                            )
                        }
                    }
                }
            }

            context("multiple occurrences of the search string") {
                it("${toContainAtLeastPair.first("5.0", "once")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastFun(1, 5.0)
                }
                it("${toContainAtLeastPair.first("5.0", "twice")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastFun(2, 5.0)
                }

                it(
                    "${toContainAtLeastPair.first(
                        "5.0",
                        "3 times"
                    )} throws AssertionError and message toContain both, how many times we expected (3) and how many times it actually contained 5.0 (2)"
                ) {
                    expect {
                        expect(oneToSeven()).toContainAtLeastFun(3, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 5.0",
                                "$numberOfSuchElements: 2$separator"
                            )
                            toEndWith("$atLeastDescr: 3")
                        }
                    }
                }

                it("${toContainAtLeastPair.first("5.0 and 4.0", "twice")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastFun(2, 5.0, 4.0)
                }
                it("${toContainAtLeastPair.first("4.0", "3 times")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastFun(3, 4.0)
                }

                it(
                    "${toContainAtLeastPair.first(
                        "5.0 and 4.0",
                        "3 times"
                    )} throws AssertionError and message toContain both, at least: 3 and how many times it actually contained 5.0 (2)"
                ) {
                    expect {
                        expect(oneToSeven()).toContainAtLeastFun(3, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 5.0",
                                "$numberOfSuchElements: 2$separator"
                            )
                            toEndWith("$atLeastDescr: 3")
                            notToContain("$anElementWhichEquals: 4.0")
                        }
                    }
                }
            }

            context("using $toContainAtLeastButAtMost") {
                it("${toContainAtLeastButAtMostPair.first("5.0", "once", "twice")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastButAtMostFun(1, 2, 5.0)
                }
                it(
                    "${toContainAtLeastButAtMostPair.first(
                        "5.0 and 4.0",
                        "once",
                        "twice"
                    )} throws AssertionError and message toContain both, at most: 2 and how many times it actually contained 4.0 (3)"
                ) {
                    expect {
                        expect(oneToSeven()).toContainAtLeastButAtMostFun(1, 2, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 4.0",
                                "$numberOfSuchElements: 3$separator"
                            )
                            toEndWith("$atMostDescr: 2")
                            notToContain(atLeastDescr, "$anElementWhichEquals: 5.0")
                        }
                    }
                }
                it("${toContainAtLeastButAtMostPair.first("5.0 and 4.0", "twice", "3 times")} does not throw") {

                    expect(oneToSeven()).toContainAtLeastButAtMostFun(2, 3, 5.0, 4.0)
                }

                it(
                    "${toContainAtLeastButAtMostPair.first(
                        "5.0 and 4.0",
                        "3 times",
                        "4 times"
                    )} throws AssertionError and message toContain both, at least: 3 and how many times it actually contained 5.0 (2)"
                ) {
                    expect {
                        expect(oneToSeven()).toContainAtLeastButAtMostFun(3, 4, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 5.0",
                                "$numberOfSuchElements: 2$separator"
                            )
                            toEndWith("$atLeastDescr: 3")
                            notToContain(atMostDescr, "$anElementWhichEquals: 4.0")
                        }
                    }
                }
            }
        }
    }
})
