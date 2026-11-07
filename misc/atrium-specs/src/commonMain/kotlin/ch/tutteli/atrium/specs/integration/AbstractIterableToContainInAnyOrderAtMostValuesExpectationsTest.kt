package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.anElementWhichEquals
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.atLeastDescr
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.atMostDescr
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.numberOfSuchElements
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSeven
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.separator
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.toContainInAnyOrder
import ch.tutteli.atrium.testfactories.TestFactory

@Suppress("FunctionName")
abstract class AbstractIterableToContainInAnyOrderAtMostValuesExpectationsTest(
    private val toContainAtMostPairSpec: Pair<(String, String) -> String, Fun3<Iterable<Double>, Int, Double, Array<out Double>>>,
    private val notToContainPair: Pair<String, (Int) -> String>,
    private val exactlyPair: Pair<String, (Int) -> String>
) : ExpectationFunctionBaseTest() {

    val toContainAtMostSpec = toContainAtMostPairSpec.second

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        toContainAtMostSpec.forSubjectLessTest(2, 2.3, arrayOf())
    )

    fun Expect<Iterable<Double>>.toContainAtMostFun(atMost: Int, a: Double, vararg aX: Double) =
        toContainAtMostSpec(this, atMost, a, aX.toTypedArray())

    val notToContain = notToContainPair.first
    val errorMsgContainsNot = notToContainPair.second
    val exactly = exactlyPair.first
    val errorMsgExactly = exactlyPair.second

    @TestFactory
    fun illegalAtMost__throws() = testFactory(toContainAtMostSpec) {
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

    @TestFactory
    fun one_to_seven__happy_cases() = testFactory(toContainAtMostSpec) {
        it("${toContainAtMostPairSpec.first("1.1", "once")} does not throw") {
            expect(oneToSeven()).toContainAtMostFun(2, 1.1)
        }
        it("${toContainAtMostPairSpec.first("1.1 and 2.1 and 3.1", "once")} does not throw") {
            expect(oneToSeven()).toContainAtMostFun(2, 1.1, 2.1, 3.1)
        }
        it("${toContainAtMostPairSpec.first("3.1 and 1.1 and 2.1", "once")} does not throw") {
            expect(oneToSeven()).toContainAtMostFun(2, 3.1, 1.1, 2.1)
        }
    }

    @TestFactory
    fun one_to_seven__failing_cases() = testFactory(toContainAtMostSpec) {
        it("${toContainAtMostPairSpec.first("4.1", "twice")} throws AssertionError") {
            expect {
                expect(oneToSeven()).toContainAtMostFun(2, 4.1)
            }.toThrow<AssertionError> { messageToContain("$atMostDescr: 2", "$anElementWhichEquals: 4.1") }
        }
        it("${toContainAtMostPairSpec.first("1.1, 4.1", "twice")} throws AssertionError mentioning only 4.1") {
            expect {
                expect(oneToSeven()).toContainAtMostFun(2, 1.1, 4.1)
            }.toThrow<AssertionError> {
                message {
                    toContain("$atMostDescr: 2", "$anElementWhichEquals: 4.1")
                    notToContain("$anElementWhichEquals: 1.1")
                }
            }
        }
        it("${toContainAtMostPairSpec.first("4.1, 1.1", "twice")} once throws AssertionError mentioning only 4.1") {
            expect {
                expect(oneToSeven()).toContainAtMostFun(2, 4.1, 1.1)
            }.toThrow<AssertionError> {
                message {
                    toContain("$atMostDescr: 2", "$anElementWhichEquals: 4.1")
                    notToContain("$anElementWhichEquals: 1.1")
                }
            }
        }
        it("${toContainAtMostPairSpec.first("5.1, 3.2, 3.1, 4.1", "twice")} throws AssertionError") {
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
        it("${toContainAtMostPairSpec.first("21.1 and 34.1 and 11.23", "twice")} throws AssertionError") {
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

    @TestFactory
    fun one_to_seven__multiple_occurrences_of_the_search_string() = testFactory(toContainAtMostSpec) {
        it("${toContainAtMostPairSpec.first("5.1", "twice")} does not throw") {
            expect(oneToSeven()).toContainAtMostFun(2, 5.1)
        }

        it("${toContainAtMostPairSpec.first("5.1", "3 times")} does not throw") {
            expect(oneToSeven()).toContainAtMostFun(3, 5.1)
        }
        it(
            "${
                toContainAtMostPairSpec.first(
                    "5.1 and 4.1",
                    "twice"
                )
            } throws AssertionError and message toContain both, how many times we expected (2) and how many times it actually contained 4.1 (3)"
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
        it("${toContainAtMostPairSpec.first("4.1", "3 times")} does not throw") {
            expect(oneToSeven()).toContainAtMostFun(3, 4.1)
        }
        it("${toContainAtMostPairSpec.first("5.1 and 4.1", "3 times")} does not throw") {
            expect(oneToSeven()).toContainAtMostFun(3, 5.1, 4.1)
        }

    }
}
