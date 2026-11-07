package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class IterableToContainInAnyOrderAtMostValuesExpectationsSpec(
    toContainAtMostPair: Pair<(String, String) -> String, Fun3<Iterable<Double>, Int, Double, Array<out Double>>>,
    notToContainPair: Pair<String, (Int) -> String>,
    exactlyPair: Pair<String, (Int) -> String>,
    describePrefix: String = "[Atrium] "
) : IterableToContainSpecBase({

    val toContainAtMost = toContainAtMostPair.second

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        toContainAtMost.forSubjectLessTest(2, 2.3, arrayOf())
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    fun Expect<Iterable<Double>>.toContainAtMostFun(atLeast: Int, a: Double, vararg aX: Double) =
        toContainAtMost(this, atLeast, a, aX.toTypedArray())

    val (notToContain, errorMsgContainsNot) = notToContainPair
    val (exactly, errorMsgExactly) = exactlyPair

    describeFun(toContainAtMost) {

        context("throws an $illegalArgumentException") {
            it("for at most -1 -- only positive numbers") {
                expect {
                    expect(oneToSeven()).toContainAtMostFun(-1, 0.1)
                }.toThrow<IllegalArgumentException> { messageToContain("positive number", -1) }
            }
            it("for at most 0 -- points to $notToContain") {
                expect {
                    expect(oneToSeven()).toContainAtMostFun(0, 0.1)
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgContainsNot(0)) } }
            }
            it("for at most 1 -- points to $exactly") {
                expect {
                    expect(oneToSeven()).toContainAtMostFun(1, 0.1)
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgExactly(1)) } }
            }
        }

        context("iterable ${oneToSeven().toList()}") {
            context("happy case with $toContainAtMost twice") {
                it("${toContainAtMostPair.first("1.1", "once")} does not throw") {
                    expect(oneToSeven()).toContainAtMostFun(2, 1.1)
                }
                it("${toContainAtMostPair.first("1.1 and 2.1 and 3.1", "once")} does not throw") {
                    expect(oneToSeven()).toContainAtMostFun(2, 1.1, 2.1, 3.1)
                }
                it("${toContainAtMostPair.first("3.1 and 1.1 and 2.1", "once")} does not throw") {
                    expect(oneToSeven()).toContainAtMostFun(2, 3.1, 1.1, 2.1)
                }
            }

            context("failing cases; search string at different positions") {
                it("${toContainAtMostPair.first("4.1", "twice")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toContainAtMostFun(2, 4.1)
                    }.toThrow<AssertionError> { messageToContain("$atMostDescr: 2", "$anElementWhichEquals: 4.1") }
                }
                it("${toContainAtMostPair.first("1.1, 4.1", "twice")} throws AssertionError mentioning only 4.1") {
                    expect {
                        expect(oneToSeven()).toContainAtMostFun(2, 1.1, 4.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain("$atMostDescr: 2", "$anElementWhichEquals: 4.1")
                            notToContain("$anElementWhichEquals: 1.1")
                        }
                    }
                }
                it("${toContainAtMostPair.first("4.1, 1.1", "twice")} once throws AssertionError mentioning only 4.1") {
                    expect {
                        expect(oneToSeven()).toContainAtMostFun(2, 4.1, 1.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain("$atMostDescr: 2", "$anElementWhichEquals: 4.1")
                            notToContain("$anElementWhichEquals: 1.1")
                        }
                    }
                }
                it("${toContainAtMostPair.first("5.1, 3.2, 3.1, 4.1", "twice")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toContainAtMostFun(2, 5.1, 3.2, 3.1, 4.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(1).values(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 3.2",
                                "$numberOfSuchElements: 0",
                                "$atLeastDescr: 1",
                                "$anElementWhichEquals: 4.1",
                                "$numberOfSuchElements: 3",
                                "$atMostDescr: 2"
                            )
                        }
                    }
                }
                it("${toContainAtMostPair.first("21.1 and 34.1 and 11.23", "twice")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toContainAtMostFun(2, 21.1, 34.1, 11.23)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(3).values(
                                "$numberOfSuchElements: 0",
                                "$atLeastDescr: 1"
                            )
                            toContain.exactly(1).values(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 21.1",
                                "$anElementWhichEquals: 34.1",
                                "$anElementWhichEquals: 11.23"
                            )
                        }
                    }
                }
            }

            context("multiple occurrences of the search string") {

                it("${toContainAtMostPair.first("5.1", "twice")} does not throw") {
                    expect(oneToSeven()).toContainAtMostFun(2, 5.1)
                }

                it("${toContainAtMostPair.first("5.1", "3 times")} does not throw") {
                    expect(oneToSeven()).toContainAtMostFun(3, 5.1)
                }
                it(
                    "${toContainAtMostPair.first(
                        "5.1 and 4.1",
                        "twice"
                    )} throws AssertionError and message toContain both, how many times we expected (2) and how many times it actually contained 4.1 (3)"
                ) {
                    expect {
                        expect(oneToSeven()).toContainAtMostFun(2, 5.1, 4.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 4.1",
                                "$numberOfSuchElements: 3$separator"
                            )
                            toEndWith("$atMostDescr: 2")
                            notToContain("$anElementWhichEquals: 5.1")
                        }
                    }
                }
                it("${toContainAtMostPair.first("4.1", "3 times")} does not throw") {
                    expect(oneToSeven()).toContainAtMostFun(3, 4.1)
                }
                it("${toContainAtMostPair.first("5.1 and 4.1", "3 times")} does not throw") {
                    expect(oneToSeven()).toContainAtMostFun(3, 5.1, 4.1)
                }

            }
        }
    }
})
