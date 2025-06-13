package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation

abstract class AbstractCharSequenceNotToContainOrAtMostExpectationsTest(
    private val notToContainOrAtMostPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    private val notToContainOrAtMostIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    private val notToContainPair: Pair<String, (Int) -> String>,
) : ExpectationFunctionBaseTest() {

    private val notToContainOrAtMostSpec = notToContainOrAtMostPair.second
    private val notToContainOrAtMostIgnoringCaseSpec = notToContainOrAtMostIgnoringCasePair.second

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
    fun notToContainOrAtMost__subject_throws_an_IllegalArgumentException() =
        testFactory(notToContainOrAtMostSpec) { notToContainOrAtMostFun ->
            val (notToContain, errorMsgContainsNot) = notToContainPair

            it("for not at all or at most -1 -- only positive numbers") {
                expect {
                    expect(text).notToContainOrAtMostFun(-1, "")
                }.toThrow<IllegalArgumentException> { messageToContain("positive number", -1) }
            }
            it("for not at all or at most 0 -- points to $notToContain") {
                expect {
                    expect(text).notToContainOrAtMostFun(0, "")
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgContainsNot(0)) } }
            }
            it("if an object is passed as first expected") {
                expect {
                    expect(text).notToContainOrAtMostFun(1, expect(text))
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }
            it("if an object is passed as second expected") {
                expect {
                    expect(text).notToContainOrAtMostFun(1, "that's fine", expect(text))
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }
        }

    @TestFactory
    fun notToContainOrAtMost__subject_happy_case_with_notToContainOrAtMost_once() =
        testFactory(notToContainOrAtMostSpec) { notToContainOrAtMostFun ->
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
        }

    @TestFactory
    fun notToContainOrAtMostIgnoringCase__subject_happy_case_with_notToContainOrAtMostIgnoringCase_once() =
        testFactory(notToContainOrAtMostIgnoringCaseSpec) { notToContainOrAtMostIgnoringCaseFun ->
            it("${notToContainOrAtMostIgnoringCasePair.first("'x' and 'y' and 'z'", "twice")} does not throw") {
                expect(helloWorld).notToContainOrAtMostIgnoringCaseFun(2, 'x', 'y', 'z')
            }
        }

    @TestFactory
    fun notToContainOrAtMost__subject_failing_cases__search_string_at_different_positions() =
        testFactory(notToContainOrAtMostSpec) { notToContainOrAtMostFun ->
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
        }

    @TestFactory
    fun notToContainOrAtMostIgnoringCase__subject_failing_cases__search_string_at_different_positions() =
        testFactory(notToContainOrAtMostIgnoringCaseSpec) { notToContainOrAtMostIgnoringCaseFun ->
            it(
                "${
                    notToContainOrAtMostPair.first(
                        "'o', 'E', 'W', 'l'",
                        "once"
                    )
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
    fun notToContainOrAtMost__multiple_occurrences_of_the_search_string() =
        testFactory(notToContainOrAtMostSpec) { notToContainOrAtMostFun ->
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

    @TestFactory
    fun notToContainOrAtMostIgnoringCase__multiple_occurrences_of_the_search_string() =
        testFactory(notToContainOrAtMostIgnoringCaseSpec) { notToContainOrAtMostIgnoringCaseFun ->
            it("${notToContainOrAtMostIgnoringCasePair.first("'o'", "twice")} throws AssertionError") {
                expect {
                    expect(helloWorld).notToContainOrAtMostIgnoringCaseFun(2, 'o')
                }.toThrow<AssertionError> { messageToContain(atMost) }
            }
        }

    companion object {
        val text: CharSequence = "Hello my name is Robert"
        val helloWorld: CharSequence = "Hello World, I am Oskar"

        val atLeast = DescriptionCharSequenceExpectation.AT_LEAST.getDefault()
        val atMost = DescriptionCharSequenceExpectation.AT_MOST.getDefault()
        val numberOfOccurrences = DescriptionCharSequenceExpectation.NUMBER_OF_MATCHES.getDefault()
        val value = DescriptionCharSequenceExpectation.VALUE.getDefault()
        val toContainDescr = DescriptionCharSequenceExpectation.TO_CONTAIN.getDefault()
        val separator = lineSeparator
    }
}
