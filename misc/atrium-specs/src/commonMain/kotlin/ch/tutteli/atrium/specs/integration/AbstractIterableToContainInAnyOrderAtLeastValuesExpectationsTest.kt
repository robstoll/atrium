package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.anElementWhichEquals
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.atLeastDescr
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.atMostDescr
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.illegalArgumentException
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.noSuchValueDescr
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.numberOfSuchElements
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSeven
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.separator
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.toContainInAnyOrder
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory

@Suppress("FunctionName")
abstract class AbstractIterableToContainInAnyOrderAtLeastValuesExpectationsTest(
    private val toContainAtLeastPairSpec: Pair<(String, String) -> String, Fun3<Iterable<Double>, Int, Double, Array<out Double>>>,
    private val toContainAtLeastButAtMostPairSpec: Pair<(String, String, String) -> String, Fun4<Iterable<Double>, Int, Int, Double, Array<out Double>>>,
    notToContainPair: Pair<String, (Int) -> String>,
    exactlyPair: Pair<String, (Int) -> String>,
    private val errorMsgAtLeastButAtMost: (Int, Int) -> String
) : ExpectationFunctionBaseTest() {

    private val toContainAtLeastSpec = toContainAtLeastPairSpec.second
    private val toContainAtLeastButAtMostSpec = toContainAtLeastButAtMostPairSpec.second

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            toContainAtLeastSpec.forSubjectLessTest(1, 2.3, arrayOf())
        ),
        SubjectLessTestData(
            toContainAtLeastButAtMostSpec.forSubjectLessTest(1, 2, 2.3, arrayOf())
        )
    )

    fun Expect<Iterable<Double>>.toContainAtLeastFun(atLeast: Int, a: Double, vararg aX: Double) =
        toContainAtLeastSpec(this, atLeast, a, aX.toTypedArray())

    fun Expect<Iterable<Double>>.toContainAtLeastButAtMostFun(atLeast: Int, atMost: Int, a: Double, vararg aX: Double) =
        toContainAtLeastButAtMostSpec(this, atLeast, atMost, a, aX.toTypedArray())

    private val notToContain = notToContainPair.first
    private val errorMsgContainsNot = notToContainPair.second

    private val exactly = exactlyPair.first
    private val errorMsgExactly = exactlyPair.second

    @TestFactory
    fun illegal_at_least__throws() = testFactory(toContainAtLeastSpec) {
        describe("throws an $illegalArgumentException") {
            it("for at least -1 -- only positive numbers") {
                expect {
                    expect(oneToSeven()).toContainAtLeastFun(-1, 9.1)
                }.toThrow<IllegalArgumentException> { messageToContain("positive number", -1) }
            }
            it("for at least 0 -- points to $notToContain") {
                expect {
                    expect(oneToSeven()).toContainAtLeastFun(0, 9.1)
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgContainsNot(0)) } }
            }
        }
    }

    @TestFactory
    fun illegal_at_least_but_at_most__throws() = testFactory(toContainAtLeastButAtMostSpec) {
        describe("throws an $illegalArgumentException") {
            it("for at least 1 but at most -1 -- since -1 is smaller than 1") {
                expect {
                    expect(oneToSeven()).toContainAtLeastButAtMostFun(1, -1, 9.1)
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgAtLeastButAtMost(1, -1)) } }
            }
            it("for at least 1 but at most 0 -- since 0 is smaller than 1") {
                expect {
                    expect(oneToSeven()).toContainAtLeastButAtMostFun(1, 0, 9.1)
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgAtLeastButAtMost(1, 0)) } }
            }
            it("for at least 2 but at most 1 -- since 1 is smaller than 2") {
                expect {
                    expect(oneToSeven()).toContainAtLeastButAtMostFun(2, 1, 9.1)
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgAtLeastButAtMost(2, 1)) } }
            }
            it("for at least 1 but at most 1 -- points to $exactly") {
                expect {
                    expect(oneToSeven()).toContainAtLeastButAtMostFun(1, 1, 9.1)
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgExactly(1)) } }
            }
        }
    }

    @TestFactory
    fun one_to_seven__happy_cases() = testFactory(toContainAtLeastSpec) {
        describeIterable(::oneToSeven) {
            describe("happy cases") {
                it("${toContainAtLeastPairSpec.first("1.1", "once")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastFun(1, 1.1)
                }
                it("${toContainAtLeastPairSpec.first("1.1 and 2.1 and 3.1", "once")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastFun(1, 1.1, 2.1, 3.1)
                }
                it("${toContainAtLeastPairSpec.first("3.1 and 1.1 and 2.1", "once")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastFun(1, 3.1, 1.1, 2.1)
                }
            }
        }
    }

    @TestFactory
    fun one_to_seven__failure_cases() = testFactory(toContainAtLeastSpec) {
        describeIterable(::oneToSeven) {
            describe("failing cases; search wrong number at different positions with ${toContainAtLeastSpec.name}") {
                it("${toContainAtLeastPairSpec.first("1.2", "once")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toContainAtLeastFun(1, 1.2)
                    }.toThrow<AssertionError> { messageToContain(noSuchValueDescr, "$anElementWhichEquals: 1.2") }
                }
                it("${toContainAtLeastPairSpec.first("1.2, 2.3", "once")} throws AssertionError mentioning only 2.3") {
                    expect {
                        expect(oneToSeven()).toContainAtLeastFun(1, 1.2, 2.3)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(noSuchValueDescr, "$anElementWhichEquals: 2.3")
                            notToContain("$anElementWhichEquals: 1.1")
                        }
                    }
                }
                it("${toContainAtLeastPairSpec.first("2.3, 1.1", "once")} throws AssertionError mentioning only 2.3") {
                    expect {
                        expect(oneToSeven()).toContainAtLeastFun(1, 2.3, 1.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(noSuchValueDescr, "$anElementWhichEquals: 2.3")
                            notToContain("$anElementWhichEquals: 1.1")
                        }
                    }
                }
                it("${toContainAtLeastPairSpec.first("1.1, 2.3, 3.2 and 6.1", "once")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toContainAtLeastFun(1, 1.1, 2.3, 3.2, 6.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain.exactly(2).values(
                                noSuchValueDescr
                            )
                            toContain.exactly(1).values(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 2.3",
                                "$anElementWhichEquals: 3.2"
                            )
                        }
                    }
                }
            }
        }
    }

    @TestFactory
    fun one_to_seven__multiple_occurrences() = testFactory(toContainAtLeastSpec) {
        describeIterable(::oneToSeven) {
            describe("multiple occurrences of the search string") {
                it("${toContainAtLeastPairSpec.first("5.1", "once")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastFun(1, 5.1)
                }
                it("${toContainAtLeastPairSpec.first("5.1", "twice")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastFun(2, 5.1)
                }
                it(
                    "${toContainAtLeastPairSpec.first("5.1", "3 times")} throws AssertionError and message toContain both, how many times we expected (3) and how many times it actually contained 5.1 (2)"
                ) {
                    expect {
                        expect(oneToSeven()).toContainAtLeastFun(3, 5.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 5.1",
                                "$numberOfSuchElements: 2$separator"
                            )
                            toEndWith("$atLeastDescr: 3")
                        }
                    }
                }
                it("${toContainAtLeastPairSpec.first("5.1 and 4.1", "twice")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastFun(2, 5.1, 4.1)
                }
                it("${toContainAtLeastPairSpec.first("4.1", "3 times")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastFun(3, 4.1)
                }
                it(
                    "${toContainAtLeastPairSpec.first("5.1 and 4.1", "3 times")} throws AssertionError and message toContain both, at least: 3 and how many times it actually contained 5.1 (2)"
                ) {
                    expect {
                        expect(oneToSeven()).toContainAtLeastFun(3, 5.1, 4.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 5.1",
                                "$numberOfSuchElements: 2$separator"
                            )
                            toEndWith("$atLeastDescr: 3")
                            notToContain("$anElementWhichEquals: 4.1")
                        }
                    }
                }
            }
        }
    }

    @TestFactory
    fun one_to_seven__at_least_but_at_most() = testFactory(toContainAtLeastButAtMostSpec) {
        describeIterable(::oneToSeven) {
            describe("using ${toContainAtLeastButAtMostSpec.name}") {
                it("${toContainAtLeastButAtMostPairSpec.first("5.1", "once", "twice")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastButAtMostFun(1, 2, 5.1)
                }
                it(
                    "${toContainAtLeastButAtMostPairSpec.first("5.1 and 4.1", "once", "twice")} throws AssertionError and message toContain both, at most: 2 and how many times it actually contained 4.1 (3)"
                ) {
                    expect {
                        expect(oneToSeven()).toContainAtLeastButAtMostFun(1, 2, 5.1, 4.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 4.1",
                                "$numberOfSuchElements: 3$separator"
                            )
                            toEndWith("$atMostDescr: 2")
                            notToContain(atLeastDescr, "$anElementWhichEquals: 5.1")
                        }
                    }
                }
                it("${toContainAtLeastButAtMostPairSpec.first("5.1 and 4.1", "twice", "3 times")} does not throw") {
                    expect(oneToSeven()).toContainAtLeastButAtMostFun(2, 3, 5.1, 4.1)
                }
                it(
                    "${toContainAtLeastButAtMostPairSpec.first("5.1 and 4.1", "3 times", "4 times")} throws AssertionError and message toContain both, at least: 3 and how many times it actually contained 5.1 (2)"
                ) {
                    expect {
                        expect(oneToSeven()).toContainAtLeastButAtMostFun(3, 4, 5.1, 4.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 5.1",
                                "$numberOfSuchElements: 2$separator"
                            )
                            toEndWith("$atLeastDescr: 3")
                            notToContain(atMostDescr, "$anElementWhichEquals: 4.1")
                        }
                    }
                }
            }
        }
    }
}
