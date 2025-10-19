package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.text
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.helloWorld
import ch.tutteli.atrium.specs.integration.CharSequenceToContainSpecBase.Companion.toContainIgnoringCase

@Suppress("FunctionName")
abstract class AbstractCharSequenceToContainAtMostExpectationsTest(
    private val toContainAtMostPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    private val toContainAtMostIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    private val notToContainPair: Pair<String, (Int) -> String>,
    private val exactlyPair: Pair<String, (Int) -> String>,
) : ExpectationFunctionBaseTest() {

    val toContainAtMostSpec = toContainAtMostPair.second
    val toContainAtMostIgnoringCaseSpec = toContainAtMostIgnoringCasePair.second

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        toContainAtMostSpec.forSubjectLessTest(2, 2.3, arrayOf()),
        toContainAtMostIgnoringCaseSpec.forSubjectLessTest(2, 2.3, arrayOf())
    )

    fun Expect<CharSequence>.toContainAtMostFun(atLeast: Int, a: Any, vararg aX: Any) =
        toContainAtMostSpec(this, atLeast, a, aX)

    fun Expect<CharSequence>.toContainAtMostIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any) =
        toContainAtMostIgnoringCaseSpec(this, atLeast, a, aX)

    val valueWithIndent = "$indentRootBulletPoint$listBulletPoint$value"

    @TestFactory
    fun toContainAtMost__subject_throws_an_IllegalArgumentException() = testFactory(toContainAtMostSpec) {
        val (notToContain, errorMsgContainsNot) = notToContainPair
        val (exactly, errorMsgExactly) = exactlyPair

        it("for at most -1 -- only positive numbers") {
            expect {
                expect(text).toContainAtMostFun(-1, "")
            }.toThrow<IllegalArgumentException> { messageToContain("positive number", -1) }
        }
        it("for at most 0 -- points to $notToContain") {
            expect {
                expect(text).toContainAtMostFun(0, "")
            }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgContainsNot(0)) } }
        }
        it("for at most 1 -- points to $exactly") {
            expect {
                expect(text).toContainAtMostFun(1, "")
            }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgExactly(1)) } }
        }
        it("if an object is passed as first expected") {
            expect {
                expect(text).toContainAtMostFun(2, expect(text))
            }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
        }
        it("if an object is passed as second expected") {
            expect {
                expect(text).toContainAtMostFun(2, "that's fine", expect(text))
            }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
        }
    }

    @TestFactory
    fun toContainAtMost__subject_happy_case_with_toContainAtMost_twice() = testFactory(toContainAtMostSpec) {
        it("${toContainAtMostPair.first("'H'", "twice")} does not throw") {
            expect(helloWorld).toContainAtMostFun(2, 'H')
        }
        it("${toContainAtMostPair.first("'H' and 'e' and 'W'", "twice")} does not throw") {
            expect(helloWorld).toContainAtMostFun(2, 'H', 'e', 'W')
        }
        it("${toContainAtMostPair.first("'W' and 'H' and 'e'", "twice")} does not throw") {
            expect(helloWorld).toContainAtMostFun(2, 'W', 'H', 'e')
        }
    }

    @TestFactory
    fun toContainAtMost__subject_failing_cases__search_string_at_different_positions() =
        testFactory(toContainAtMostSpec) {
            it("${toContainAtMostPair.first("'l'", "twice")} throws AssertionError") {
                expect {
                    expect(helloWorld).toContainAtMostFun(2, 'l')
                }.toThrow<AssertionError> { messageToContain("$atMost: 2", "$valueWithIndent: 'l'") }
            }
            it("${toContainAtMostPair.first("'H', 'l'", "twice")} throws AssertionError mentioning only 'l'") {
                expect {
                    expect(helloWorld).toContainAtMostFun(2, 'H', 'l')
                }.toThrow<AssertionError> {
                    message {
                        toContain("$atMost: 2", "$valueWithIndent: 'l'")
                        notToContain(atLeast, "$valueWithIndent: 'H'")
                    }
                }
            }
            it("${toContainAtMostPair.first("'l', 'H'", "twice")} once throws AssertionError mentioning only 'l'") {
                expect {
                    expect(helloWorld).toContainAtMostFun(2, 'l', 'H')
                }.toThrow<AssertionError> {
                    message {
                        toContain("$atMost: 2", "$valueWithIndent: 'l'")
                        notToContain(atLeast, "$valueWithIndent: 'H'")
                    }
                }
            }
            it("${toContainAtMostPair.first("'x' and 'y' and 'z'", "twice")} throws AssertionError") {
                expect {
                    expect(helloWorld).toContainAtMostFun(2, 'x', 'y', 'z')
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            "$atLeast: 1",
                            "$valueWithIndent: 'x'",
                            "$valueWithIndent: 'y'",
                            "$valueWithIndent: 'z'"
                        )
                        notToContain(atMost)
                    }
                }
            }
        }

    @TestFactory
    fun toContainAtMostIgnoringCase__subject_failing_cases__search_string_at_different_positions() =
        testFactory(toContainAtMostIgnoringCaseSpec) {
            it(
                "${
                    toContainAtMostPair.first(
                        "'o', 'E', 'W', 'l'",
                        "twice"
                    )
                } throws AssertionError mentioning 'l' and 'o'"
            ) {
                expect {
                    expect(helloWorld).toContainAtMostIgnoringCaseFun(2, 'o', 'E', 'W', 'l')
                }.toThrow<AssertionError> {
                    message {
                        toContain("$atMost: 2", "$valueWithIndent: 'l'", "$valueWithIndent: 'o'")
                        notToContain(atLeast, "$valueWithIndent: 'E'", "$valueWithIndent: 'W'")
                    }
                }
            }
        }

    @TestFactory
    fun toContainAtMost__multiple_occurrences_of_the_search_string() =
        testFactory(toContainAtMostSpec) {
            it("${toContainAtMostPair.first("'o'", "twice")} does not throw") {
                expect(helloWorld).toContainAtMostFun(2, 'o')
            }
            it("${toContainAtMostPair.first("'o'", "3 times")} does not throw") {
                expect(helloWorld).toContainAtMostFun(3, 'o')
            }
            it(
                "${toContainAtMostPair.first("'o' and 'l'", "twice")} throws AssertionError " +
                    "and message contains both, how many times we expected (2) and how many times it actually contained 'l' (3)"
            ) {
                expect {
                    expect(helloWorld).toContainAtMostFun(2, 'o', 'l')
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
            it("${toContainAtMostPair.first("'l'", "3 times")} does not throw") {
                expect(helloWorld).toContainAtMostFun(3, 'l')
            }
            it("${toContainAtMostPair.first("'o' and 'l'", "3 times")} does not throw") {
                expect(helloWorld).toContainAtMostFun(3, 'o', 'l')
            }
        }

    @TestFactory
    fun toContainAtMostIgnoringCase__multiple_occurrences_of_the_search_string() =
        testFactory(toContainAtMostIgnoringCaseSpec) {
            it(
                "${toContainAtMostIgnoringCasePair.first("'o'", "twice")} throws AssertionError " +
                    "and message contains both, how many times we expected (2) and how many times it actually contained 'o' ignoring case (3)"
            ) {
                expect {
                    expect(helloWorld).toContainAtMostIgnoringCaseFun(2, 'o')
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            "$rootBulletPoint$toContainIgnoringCase: $separator$valueWithIndent: 'o'",
                            "$numberOfOccurrences: 3$separator"
                        )
                        toEndWith("$atMost: 2")
                    }
                }
            }
        }

    @TestFactory
    fun toContainAtMost__subject_special_cases() = testFactory(toContainAtMostSpec) {
        // string: "\0 hello"
        it("${toContainAtMostPair.first("\"hello\" and '\\0'", "twice")} does not throw") {
            expect(('\u0000' + " hello") as CharSequence)
                .toContainAtMostFun(2, "hello", 0.toChar())
        }

        val aaaa: CharSequence = "aaaa"

        // string: "aaaa"

        it("${toContainAtMostPair.first("'a'", "4 times")} does not throw") {
            expect(aaaa).toContainAtMostFun(4, 'a')
        }
        it("${toContainAtMostPair.first("'a'", "3 times")} throws AssertionError") {
            expect {
                expect(aaaa).toContainAtMostFun(3, 'a')
            }.toThrow<AssertionError> {
                message {
                    toContain("$valueWithIndent: 'a'", "$numberOfOccurrences: 4", "$atMost: 3")
                    notToContain(atLeast)
                }
            }
        }
    }

    companion object {
        val exactly = DescriptionCharSequenceExpectation.EXACTLY.getDefault()
        val atLeast = DescriptionCharSequenceExpectation.AT_LEAST.getDefault()
        val atMost = DescriptionCharSequenceExpectation.AT_MOST.getDefault()
        val numberOfOccurrences = DescriptionCharSequenceExpectation.NUMBER_OF_MATCHES.getDefault()
        val value = DescriptionCharSequenceExpectation.VALUE.getDefault()
        val toContainDescr = DescriptionCharSequenceExpectation.TO_CONTAIN.getDefault()
        val separator = lineSeparator
    }
}
