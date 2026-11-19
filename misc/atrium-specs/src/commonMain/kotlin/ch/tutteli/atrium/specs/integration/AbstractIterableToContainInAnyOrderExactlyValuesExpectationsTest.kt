package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.anElementWhichEquals
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.illegalArgumentException
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.numberOfSuchElements
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSeven
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.separator
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.toContainInAnyOrder
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation.EXACTLY

@Suppress("FunctionName")
abstract class AbstractIterableToContainInAnyOrderExactlyValuesExpectationsTest(
    private val toContainExactlyPairSpec: Pair<(String, String) -> String, Fun3<Iterable<Double>, Int, Double, Array<out Double>>>,
    notToContainPair: Pair<String, (Int) -> String>
) : ExpectationFunctionBaseTest() {

    private val toContainExactlySpec = toContainExactlyPairSpec.second

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            toContainExactlySpec.forSubjectLessTest(2, 2.3, arrayOf())
        )
    )

    fun Expect<Iterable<Double>>.toContainExactlyFun(exactly: Int, a: Double, vararg aX: Double) =
        toContainExactlySpec(this, exactly, a, aX.toTypedArray())

    private val notToContain = notToContainPair.first
    private val errorMsgContainsNot = notToContainPair.second

    private val toBeExactlyDescr = EXACTLY.getDefault()

    @TestFactory
    fun illegal_exactly__throws() = testFactory(toContainExactlySpec) {
        describe("throws an $illegalArgumentException") {
            it("for exactly -1 -- only positive numbers") {
                expect {
                    expect(oneToSeven()).toContainExactlyFun(-1, 0.1)
                }.toThrow<IllegalArgumentException> { messageToContain("positive number", -1) }
            }
            it("for exactly 0 -- points to $notToContain") {
                expect {
                    expect(oneToSeven()).toContainExactlyFun(0, 0.1)
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgContainsNot(0)) } }
            }
        }
    }

    @TestFactory
    fun one_to_seven__happy_cases() = testFactory(toContainExactlySpec) {
        describeIterable(::oneToSeven) {
            describe("happy cases") {
                it("${toContainExactlyPairSpec.first("1.1", "once")} does not throw") {
                    expect(oneToSeven()).toContainExactlyFun(1, 1.1)
                }
                it("${toContainExactlyPairSpec.first("1.1 and 2.1 and 3.1", "once")} does not throw") {
                    expect(oneToSeven()).toContainExactlyFun(1, 1.1, 2.1, 3.1)
                }
                it("${toContainExactlyPairSpec.first("3.1 and 1.1 and 2.1", "once")} does not throw") {
                    expect(oneToSeven()).toContainExactlyFun(1, 3.1, 1.1, 2.1)
                }
            }
        }
    }

    @TestFactory
    fun one_to_seven__failure_cases() = testFactory(toContainExactlySpec) {
        describeIterable(::oneToSeven) {
            describe("failure cases") {
                it("${toContainExactlyPairSpec.first("4.1", "once")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toContainExactlyFun(1, 4.1)
                    }.toThrow<AssertionError> {
                        messageToContain("$toBeExactlyDescr: 1", "$anElementWhichEquals: 4.1")
                    }
                }

                it("reports missing 2.3 when requested second") {
                    expect {
                        expect(oneToSeven()).toContainExactlyFun(1, 1.1, 2.3)
                    }.toThrow<AssertionError> {
                        message {
                            toContain("$toBeExactlyDescr: 1", "$anElementWhichEquals: 2.3")
                            notToContain("$anElementWhichEquals: 1.1")
                        }
                    }
                }

                it("reports missing 2.3 when requested first") {
                    expect {
                        expect(oneToSeven()).toContainExactlyFun(1, 2.3, 1.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain("$toBeExactlyDescr: 1", "$anElementWhichEquals: 2.3")
                            notToContain("$anElementWhichEquals: 1.1")
                        }
                    }
                }

                it("${toContainExactlyPairSpec.first("1.1 and 2.3 and 3.2", "once")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toContainExactlyFun(1, 1.1, 2.3, 3.2)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(toBeExactlyDescr, 2.3, 3.2)
                            toContain.exactly(2).values(
                                "$numberOfSuchElements: 0",
                                "$toBeExactlyDescr: 1"
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
    fun one_to_seven__multiple_occurrences() = testFactory(toContainExactlySpec) {
        describeIterable(::oneToSeven) {
            describe("multiple occurrences of the search string") {
                it("${toContainExactlyPairSpec.first("5.1", "once")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toContainExactlyFun(1, 5.1)
                    }.toThrow<AssertionError> { messageToContain(toBeExactlyDescr) }
                }
                it("${toContainExactlyPairSpec.first("5.1", "twice")} does not throw") {
                    expect(oneToSeven()).toContainExactlyFun(2, 5.1)
                }

                it("exactly 3 occurrences of 5.1 expected, but only 2 found") {
                    expect {
                        expect(oneToSeven()).toContainExactlyFun(3, 5.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 5.1",
                                "$numberOfSuchElements: 2$separator"
                            )
                            toEndWith("$toBeExactlyDescr: 3")
                        }
                    }
                }

                it("${toContainExactlyPairSpec.first("5.1 and 4.1", "twice")} throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toContainExactlyFun(2, 5.1, 4.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 4.1",
                                "$numberOfSuchElements: 3$separator"
                            )
                            toEndWith("$toBeExactlyDescr: 2")
                            notToContain("$anElementWhichEquals: 5.1")
                        }
                    }
                }
                it("${toContainExactlyPairSpec.first("4.1", "3 times")} does not throw") {
                    expect(oneToSeven()).toContainExactlyFun(3, 4.1)
                }
                it("reports insufficient occurrences of 5.1") {
                    expect {
                        expect(oneToSeven()).toContainExactlyFun(3, 5.1, 4.1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainInAnyOrder: $separator",
                                "$anElementWhichEquals: 5.1",
                                "$numberOfSuchElements: 2$separator"
                            )
                            toEndWith("$toBeExactlyDescr: 3")
                            notToContain("$anElementWhichEquals: 4.1")
                        }
                    }
                }
            }
        }
    }
}
