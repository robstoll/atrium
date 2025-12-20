package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.anElementWhichEquals
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.atMostDescr
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.numberOfSuchElements
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSeven
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.separator
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.toContainInAnyOrder
import ch.tutteli.atrium.testfactories.TestFactory

@Suppress("FunctionName")
abstract class AbstractIterableToContainInAnyOrderNotOrAtMostValuesExpectationsTest(
    private val notToContainOrAtMostPairSpec: Pair<(String, String) -> String, Fun3<Iterable<Double>, Int, Double, Array<out Double>>>,
    private val notToContainPair: Pair<String, (Int) -> String>
) : ExpectationFunctionBaseTest() {

    val notToContainOrAtMostSpec = notToContainOrAtMostPairSpec.second

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        notToContainOrAtMostSpec.forSubjectLessTest(2, 2.3, arrayOf())
    )

    fun Expect<Iterable<Double>>.notToContainOrAtMostFun(atLeast: Int, a: Double, vararg aX: Double) =
        notToContainOrAtMostSpec(this, atLeast, a, aX.toTypedArray())

    val notToContain = notToContainPair.first
    val errorMsgContainsNot = notToContainPair.second

    @TestFactory
    fun test_throws_an_illegalArgumentException() = testFactory(notToContainOrAtMostSpec) {
        it ("for not at all or at most -1 -- only positive numbers") {
            expect {
                expect(oneToSeven()).notToContainOrAtMostFun(-1, 0.1)
            }.toThrow<IllegalArgumentException> { messageToContain("positive number", -1) }
        }
        it ("for not at all or at most 0 -- points to $notToContain") {
            expect {
                expect(oneToSeven()).notToContainOrAtMostFun(0, 0.1)
            }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgContainsNot(0)) } }
        }
    }

    @TestFactory
    fun test_happy_case() = testFactory(notToContainOrAtMostSpec) {
        it("${notToContainOrAtMostPairSpec.first("1.1", "once")} does not throw") {
            ch.tutteli.atrium.api.verbs.internal.expect(oneToSeven()).notToContainOrAtMostFun(1, 1.1)
        }
        it("${notToContainOrAtMostPairSpec.first("1.1 and 2.1 and 3.1", "once")} does not throw") {
            ch.tutteli.atrium.api.verbs.internal.expect(oneToSeven()).notToContainOrAtMostFun(1, 1.1, 2.1, 3.1)
        }
        it("${notToContainOrAtMostPairSpec.first("3.1 and 1.1 and 2.1", "once")} does not throw") {
            ch.tutteli.atrium.api.verbs.internal.expect(oneToSeven()).notToContainOrAtMostFun(1, 3.1, 1.1, 2.1)
        }
        it("${notToContainOrAtMostPairSpec.first("21.1 and 34.1 and 11.23", "twice")}  does not throw") {
            ch.tutteli.atrium.api.verbs.internal.expect(oneToSeven()).notToContainOrAtMostFun(2, 21.1, 34.1, 11.23)
        }
    }

    @TestFactory
    fun test_failure_cases() = testFactory(notToContainOrAtMostSpec) {
        it("${notToContainOrAtMostPairSpec.first("4.1", "once")} throws AssertionError") {
            ch.tutteli.atrium.api.verbs.internal.expect {
                expect(oneToSeven()).notToContainOrAtMostFun(1, 4.1)
            }.toThrow<AssertionError> { messageToContain("$atMostDescr: 1", "$anElementWhichEquals: 4.1") }
        }
        it("${notToContainOrAtMostPairSpec.first("1.1, 4.1", "once")} throws AssertionError mentioning only 4.1") {
            expect {
                expect(oneToSeven()).notToContainOrAtMostFun(1, 1.1, 4.1)
            }.toThrow<AssertionError> {
                message {
                    toContain("$atMostDescr: 1", "$anElementWhichEquals: 4.1")
                    notToContain("$anElementWhichEquals: 1.1")
                }
            }
        }
        it(
            "${notToContainOrAtMostPairSpec.first(
                "4.1, 1.1",
                "once"
            )} once throws AssertionError mentioning only 4.1"
        ) {
            expect {
                expect(oneToSeven()).notToContainOrAtMostFun(1, 4.1, 1.1)
            }.toThrow<AssertionError> {
                message {
                    toContain("$atMostDescr: 1", "$anElementWhichEquals: 4.1")
                    notToContain("$anElementWhichEquals: 1.1")
                }
            }
        }
        it("${notToContainOrAtMostPairSpec.first("5.1, 3.1, 3.1, 4.1", "once")} throws AssertionError") {
            expect {
                expect(oneToSeven()).notToContainOrAtMostFun(1, 5.1, 3.1, 3.1, 4.1)
            }.toThrow<AssertionError> {
                message {
                    toContain.exactly(2).values(
                        "$atMostDescr: 1"
                    )
                    toContain.exactly(1).values(
                        "$rootBulletPoint$toContainInAnyOrder: $separator",
                        "$anElementWhichEquals: 5.1",
                        "$numberOfSuchElements: 2",
                        "$anElementWhichEquals: 4.1",
                        "$numberOfSuchElements: 3"
                    )
                }
            }
        }
        it(
            "${notToContainOrAtMostPairSpec.first(
                "5.1",
                "once"
            )} throws AssertionError and message contains both, how many times we expected (1) and how many times it actually contained 5.1 (2)"
        ) {
            ch.tutteli.atrium.api.verbs.internal.expect {
                expect(oneToSeven()).notToContainOrAtMostFun(1, 5.1)
            }.toThrow<AssertionError> {
                message {
                    toContain(
                        "$rootBulletPoint$toContainInAnyOrder: $separator",
                        "$anElementWhichEquals: 5.1",
                        "$numberOfSuchElements: 2$separator"
                    )
                    toEndWith("$atMostDescr: 1")
                }
            }
        }

        it("${notToContainOrAtMostPairSpec.first("5.1", "twice")} does not throw") {
            expect(oneToSeven()).notToContainOrAtMostFun(2, 5.1)
        }


        it("${notToContainOrAtMostPairSpec.first("5.1", "3 times")} does not throw") {
            expect(oneToSeven()).notToContainOrAtMostFun(3, 5.1)
        }
        it(
            "${notToContainOrAtMostPairSpec.first(
                "5.1 and 4.1",
                "twice"
            )} throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 4.1 (3)"
        ) {
            expect {
                expect(oneToSeven()).notToContainOrAtMostFun(2, 5.1, 4.1)
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
        it("${notToContainOrAtMostPairSpec.first("4.1", "3 times")} does not throw") {
            expect(oneToSeven()).notToContainOrAtMostFun(3, 4.1)
        }
        it("${notToContainOrAtMostPairSpec.first("5.1 and 4.1", "3 times")} does not throw") {
            expect(oneToSeven()).notToContainOrAtMostFun(3, 5.1, 4.1)
        }
    }
}
