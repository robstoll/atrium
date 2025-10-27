package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.helloWorld
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.helloMyNameIsRobert
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation

@Suppress("FunctionName")
abstract class AbstractCharSequenceNotToContainOrAtMostExpectationsTest(
    private val notToContainOrAtMostPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    private val notToContainOrAtMostIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    private val notToContainPair: Pair<String, (Int) -> String>,
) : ExpectationFunctionBaseTest() {

    private val notToContainOrAtMostSpec = notToContainOrAtMostPair.second
    private val notToContainOrAtMostIgnoringCaseSpec = notToContainOrAtMostIgnoringCasePair.second

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        notToContainOrAtMostSpec.forSubjectLessTest(2, 2.3, arrayOf()),
        notToContainOrAtMostIgnoringCaseSpec.forSubjectLessTest(2, 2.3, arrayOf())
    )

    fun Expect<CharSequence>.notToContainOrAtMostFun(atLeast: Int, a: Any, vararg aX: Any) =
        notToContainOrAtMostSpec(this, atLeast, a, aX)

    fun Expect<CharSequence>.notToContainOrAtMostIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any) =
        notToContainOrAtMostIgnoringCaseSpec(this, atLeast, a, aX)

    private val valueWithIndent = "$indentRootBulletPoint$listBulletPoint$value"

    @TestFactory
    fun notToContainOrAtMost__illegal_subject__throws_an_IllegalArgumentException() = testFactoryDescribeSameBehaviour(
        notToContainOrAtMostSpec, notToContainOrAtMostIgnoringCaseSpec
    ) { notToContainOrAtMostSameBehaviourFun ->
        val (notToContain, errorMsgContainsNot) = notToContainPair

        it("for not at all or at most -1 -- only positive numbers") {
            expect {
                expect(helloMyNameIsRobert).notToContainOrAtMostSameBehaviourFun(-1, "", emptyArray())
            }.toThrow<IllegalArgumentException> { messageToContain("positive number", -1) }
        }

        it("for not at all or at most 0 -- points to $notToContain") {
            expect {
                expect(helloMyNameIsRobert).notToContainOrAtMostSameBehaviourFun(0, "", emptyArray())
            }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgContainsNot(0)) } }
        }

        it("if an object is passed as first expected") {
            expect {
                expect(helloMyNameIsRobert).notToContainOrAtMostSameBehaviourFun(1, expect(helloMyNameIsRobert), emptyArray())
            }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
        }

        it("if an object is passed as second expected") {
            expect {
                expect(helloMyNameIsRobert).notToContainOrAtMostSameBehaviourFun(
                    1, "that's fine", arrayOf(expect(helloMyNameIsRobert))
                )
            }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
        }
    }

    @TestFactory
    fun notToContainOrAtMost__notToContainOrAtMostIgnoringCase__happy_cases() = testFactory(
        notToContainOrAtMostSpec, notToContainOrAtMostIgnoringCaseSpec
    ) {
        describe("text '$helloWorld'") {
            it("${notToContainOrAtMostPair.first("'H'", "once")} does not throw") {
                expect(helloWorld).notToContainOrAtMostFun(1, 'H')
            }

            it("${notToContainOrAtMostPair.first("'H' and 'e' and 'W'", "once")} does not throw") {
                expect(helloWorld).notToContainOrAtMostFun(1, 'H', 'e', 'W')
            }

            it("${notToContainOrAtMostPair.first("'W' and 'H' and 'e'", "once")} does not throw") {
                expect(helloWorld).notToContainOrAtMostFun(1, 'W', 'H', 'e')
            }

            it("${notToContainOrAtMostPair.first("'x' and 'y' and 'z'", "twice")} does not throw") {
                expect(helloWorld).notToContainOrAtMostFun(2, 'x', 'y', 'z')
            }
            it("${notToContainOrAtMostIgnoringCasePair.first("'x' and 'y' and 'z'", "twice")} does not throw") {
                expect(helloWorld).notToContainOrAtMostIgnoringCaseFun(2, 'x', 'y', 'z')
            }
        }
    }


    @TestFactory
    fun notToContainOrAtMost_notToContainOrAtMostIgnoringCaseSpec__failing_cases() = testFactory(
        notToContainOrAtMostSpec, notToContainOrAtMostIgnoringCaseSpec
    ) {
        it("${notToContainOrAtMostPair.first("'l'", "once")} throws AssertionError") {
            expect {
                expect(helloWorld).notToContainOrAtMostFun(1, 'l')
            }.toThrow<AssertionError> { messageToContain("$atMost: 1", "$valueWithIndent: 'l'") }
        }

        it("${notToContainOrAtMostPair.first("'H', 'l'", "once")} throws AssertionError mentioning only 'l'") {
            expect {
                expect(helloWorld).notToContainOrAtMostFun(1, 'H', 'l')
            }.toThrow<AssertionError> {
                message {
                    toContain("$atMost: 1", "$valueWithIndent: 'l'")
                    notToContain(atLeast, "$valueWithIndent: 'H'")
                }
            }
        }

        it("${notToContainOrAtMostPair.first("'l', 'H'", "once")} throws AssertionError mentioning only 'l'") {
            expect {
                expect(helloWorld).notToContainOrAtMostFun(1, 'l', 'H')
            }.toThrow<AssertionError> {
                message {
                    toContain("$atMost: 1", "$valueWithIndent: 'l'")
                    notToContain(atLeast, "$valueWithIndent: 'H'")
                }
            }
        }

        it(
            "${
                notToContainOrAtMostIgnoringCasePair.first("'o', 'E', 'W', 'l'", "once")
            } throws AssertionError mentioning 'l' and 'o'"
        ) {
            expect {
                expect(helloWorld).notToContainOrAtMostIgnoringCaseFun(1, 'o', 'E', 'W', 'l')
            }.toThrow<AssertionError> {
                message {
                    toContain("$atMost: 1", "$valueWithIndent: 'l'", "$valueWithIndent: 'o'")
                    notToContain(atLeast, "$valueWithIndent: 'E'", "$valueWithIndent: 'W'")
                }
            }
        }
    }

    @TestFactory
    fun notToContainOrAtMost_notToContainOrAtMostIgnoringCase__multiple_occurrences_of_the_search_string() =
        testFactory(
            notToContainOrAtMostSpec, notToContainOrAtMostIgnoringCaseSpec
        ) {
            it(
                "${notToContainOrAtMostPair.first("'o'", "once")} throws AssertionError and " +
                    "message contains both, how many times we expected (1) and how many times it actually contained 'o' (2)"
            ) {
                expect {
                    expect(helloWorld).notToContainOrAtMostFun(1, 'o')
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            "$rootBulletPoint$toContainDescr: $separator" +
                                "$valueWithIndent: 'o'",
                            "$numberOfOccurrences: 2$separator"
                        )
                        toEndWith("$atMost: 1")
                    }
                }
            }

            it("${notToContainOrAtMostPair.first("'o'", "twice")} does not throw") {
                expect(helloWorld).notToContainOrAtMostFun(2, 'o')
            }
            it("${notToContainOrAtMostIgnoringCasePair.first("'o'", "twice")} throws AssertionError") {
                expect {
                    expect(helloWorld).notToContainOrAtMostIgnoringCaseFun(2, 'o')
                }.toThrow<AssertionError> { messageToContain(atMost) }
            }

            it("${notToContainOrAtMostPair.first("'o'", "3 times")} does not throw") {
                expect(helloWorld).notToContainOrAtMostFun(3, 'o')
            }

            it(
                "${notToContainOrAtMostPair.first("'o' and 'l'", "twice")} throws AssertionError " +
                    "and message contains both, how many times we expected (2) and how many times it actually contained 'l' (3)"
            ) {
                expect {
                    expect(helloWorld).notToContainOrAtMostFun(2, 'o', 'l')
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            "$rootBulletPoint$toContainDescr: $separator" +
                                "$valueWithIndent: 'l'",
                            "$numberOfOccurrences: 3$separator"
                        )
                        toEndWith("$atMost: 2")
                        notToContain("$valueWithIndent: 'o'")
                    }
                }
            }

            it("${notToContainOrAtMostPair.first("'l'", "3 times")} does not throw") {
                expect(helloWorld).notToContainOrAtMostFun(3, 'l')
            }

            it("${notToContainOrAtMostPair.first("'o' and 'l'", "3 times")} does not throw") {
                expect(helloWorld).notToContainOrAtMostFun(3, 'o', 'l')
            }
        }

    companion object {
        val atLeast = DescriptionCharSequenceExpectation.AT_LEAST.getDefault()
        val atMost = DescriptionCharSequenceExpectation.AT_MOST.getDefault()
        val numberOfOccurrences = DescriptionCharSequenceExpectation.NUMBER_OF_MATCHES.getDefault()
        val value = DescriptionCharSequenceExpectation.VALUE.getDefault()
        val toContainDescr = DescriptionCharSequenceExpectation.TO_CONTAIN.getDefault()
        val separator = lineSeparator
    }
}
