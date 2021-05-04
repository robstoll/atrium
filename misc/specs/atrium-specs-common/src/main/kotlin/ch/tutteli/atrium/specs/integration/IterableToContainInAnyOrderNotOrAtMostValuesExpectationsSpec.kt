package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class IterableToContainInAnyOrderNotOrAtMostValuesExpectationsSpec(
    notToContainOrAtMostPair: Pair<(String, String) -> String, Fun3<Iterable<Double>, Int, Double, Array<out Double>>>,
    notToContainPair: Pair<String, (Int) -> String>,
    describePrefix: String = "[Atrium] "
) : IterableToContainSpecBase({

    val notToContainOrAtMost = notToContainOrAtMostPair.second

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        notToContainOrAtMost.forSubjectLess(2, 2.3, arrayOf())
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    fun Expect<Iterable<Double>>.notToContainOrAtMostFun(atLeast: Int, a: Double, vararg aX: Double) =
        notToContainOrAtMost(this, atLeast, a, aX.toTypedArray())

    val (notToContain, errorMsgContainsNot) = notToContainPair

    describeFun(notToContainOrAtMost) {

        context("throws an $illegalArgumentException") {
            it("for not at all or at most -1 -- only positive numbers") {
                expect {
                    expect(oneToSeven()).notToContainOrAtMostFun(-1, 0.0)
                }.toThrow<IllegalArgumentException> { messageContains("positive number", -1) }
            }
            it("for not at all or at most 0 -- points to $notToContain") {
                expect {
                    expect(oneToSeven()).notToContainOrAtMostFun(0, 0.0)
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgContainsNot(0)) } }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("happy case with $notToContainOrAtMost once") {
                it("${notToContainOrAtMostPair.first("1.0", "once")} does not throw") {
                    expect(oneToSeven()).notToContainOrAtMostFun(1, 1.0)
                }
                it("${notToContainOrAtMostPair.first("1.0 and 2.0 and 3.0", "once")} does not throw") {
                    expect(oneToSeven()).notToContainOrAtMostFun(1, 1.0, 2.0, 3.0)
                }
                it("${notToContainOrAtMostPair.first("3.0 and 1.0 and 2.0", "once")} does not throw") {
                    expect(oneToSeven()).notToContainOrAtMostFun(1, 3.0, 1.0, 2.0)
                }
                it("${notToContainOrAtMostPair.first("21.1 and 34.0 and 11.23", "twice")}  does not throw") {
                    expect(oneToSeven()).notToContainOrAtMostFun(2, 21.1, 34.0, 11.23)
                }
            }

            context("failing cases; search string at different positions") {
                it("${notToContainOrAtMostPair.first("4.0", "once")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainOrAtMostFun(1, 4.0)
                    }.toThrow<AssertionError> { messageContains("$atMostDescr: 1", "$anElementWhichIs: 4.0") }
                }
                it("${notToContainOrAtMostPair.first("1.0, 4.0", "once")} throws AssertionError mentioning only 4.0") {
                    expect {
                        expect(oneToSeven()).notToContainOrAtMostFun(1, 1.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain("$atMostDescr: 1", "$anElementWhichIs: 4.0")
                            notToContain("$anElementWhichIs: 1.0")
                        }
                    }
                }
                it(
                    "${notToContainOrAtMostPair.first(
                        "4.0, 1.0",
                        "once"
                    )} once throws AssertionError mentioning only 4.0"
                ) {
                    expect {
                        expect(oneToSeven()).notToContainOrAtMostFun(1, 4.0, 1.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain("$atMostDescr: 1", "$anElementWhichIs: 4.0")
                            notToContain("$anElementWhichIs: 1.0")
                        }
                    }
                }
                it("${notToContainOrAtMostPair.first("5.0, 3.1, 3.0, 4.0", "once")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).notToContainOrAtMostFun(1, 5.0, 3.1, 3.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(2).values(
                                "$atMostDescr: 1"
                            )
                            toContain.exactly(1).values(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichIs: 5.0",
                                "$numberOfOccurrences: 2",
                                "$anElementWhichIs: 4.0",
                                "$numberOfOccurrences: 3"
                            )
                        }
                    }
                }
            }

            context("multiple occurrences of the search string") {
                it(
                    "${notToContainOrAtMostPair.first(
                        "5.0",
                        "once"
                    )} throws AssertionError and message contains both, how many times we expected (1) and how many times it actually contained 5.0 (2)"
                ) {
                    expect {
                        expect(oneToSeven()).notToContainOrAtMostFun(1, 5.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichIs: 5.0",
                                "$numberOfOccurrences: 2$separator"
                            )
                            toEndWith("$atMostDescr: 1")
                        }
                    }
                }

                it("${notToContainOrAtMostPair.first("5.0", "twice")} does not throw") {
                    expect(oneToSeven()).notToContainOrAtMostFun(2, 5.0)
                }


                it("${notToContainOrAtMostPair.first("5.0", "3 times")} does not throw") {
                    expect(oneToSeven()).notToContainOrAtMostFun(3, 5.0)
                }
                it(
                    "${notToContainOrAtMostPair.first(
                        "5.0 and 4.0",
                        "twice"
                    )} throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 4.0 (3)"
                ) {
                    expect {
                        expect(oneToSeven()).notToContainOrAtMostFun(2, 5.0, 4.0)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichIs: 4.0",
                                "$numberOfOccurrences: 3$separator"
                            )
                            toEndWith("$atMostDescr: 2")
                            notToContain("$anElementWhichIs: 5.0")
                        }
                    }
                }
                it("${notToContainOrAtMostPair.first("4.0", "3 times")} does not throw") {
                    expect(oneToSeven()).notToContainOrAtMostFun(3, 4.0)
                }
                it("${notToContainOrAtMostPair.first("5.0 and 4.0", "3 times")} does not throw") {
                    expect(oneToSeven()).notToContainOrAtMostFun(3, 5.0, 4.0)
                }

            }
        }
    }
})
