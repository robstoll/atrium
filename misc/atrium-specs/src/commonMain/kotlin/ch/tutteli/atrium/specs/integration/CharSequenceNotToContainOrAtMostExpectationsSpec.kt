package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.*
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceNotToContainOrAtMostExpectationsSpec(
    notToContainOrAtMostPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    notToContainOrAtMostIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    notToContainPair: Pair<String, (Int) -> String>,
    describePrefix: String = "[Atrium] "
) : CharSequenceToContainSpecBase({

    val notToContainOrAtMost = notToContainOrAtMostPair.second
    val notToContainOrAtMostIgnoringCase = notToContainOrAtMostIgnoringCasePair.second

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        notToContainOrAtMost.forSubjectLess(2, 2.3, arrayOf()),
        notToContainOrAtMostIgnoringCase.forSubjectLess(2, 2.3, arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    fun Expect<CharSequence>.notToContainOrAtMostFun(atLeast: Int, a: Any, vararg aX: Any) =
        notToContainOrAtMost(this, atLeast, a, aX)

    fun Expect<CharSequence>.notToContainOrAtMostIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any) =
        notToContainOrAtMostIgnoringCase(this, atLeast, a, aX)

    describeFun(notToContainOrAtMost.name, notToContainOrAtMostIgnoringCase.name) {

        context("throws an $illegalArgumentException") {
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
                }.toThrow<IllegalArgumentException> { messageToContain(ERROR_MESSAGE_ONLY_CHARSEQUENCE_NUMBER_CHAR) }
            }
            it("if an object is passed as second expected") {
                expect {
                    expect(text).notToContainOrAtMostFun(1, "that's fine", expect(text))
                }.toThrow<IllegalArgumentException> { messageToContain(ERROR_MESSAGE_ONLY_CHARSEQUENCE_NUMBER_CHAR) }
            }
        }

        context("text '$helloWorld'") {
            context("happy case with once") {
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

            context("failing cases; search string at different positions") {
                it("${notToContainOrAtMostPair.first("'l'", "once")} throws AssertionError") {
                    expect {
                        expect(helloWorld).notToContainOrAtMostFun(1, 'l')
                    }.toThrow<AssertionError> {
                        message.toMatch(
                            Regex(
                                "$expectationVerb : \"$helloWorld\"$lineSeparator" +
                                    "$g${TO_CONTAIN.string} : $lineSeparator" +
                                    "${indentG}${g}${VALUE.string} : 'l'$lineSeparator" +
                                    "${indentG}${indentG}${g}${f}${NUMBER_OF_MATCHES.string} : 3$lineSeparator" +
                                    "${indentG}${indentG}${indentG}${indentF}${x}${AT_MOST.string}\\s+: 1"
                            )
                        )
                    }
                }
                it("${notToContainOrAtMostPair.first("'H', 'l'", "once")} throws AssertionError mentioning only 'l'") {
                    expect {
                        expect(helloWorld).notToContainOrAtMostFun(1, 'H', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("'l'")
                            toContainNumberOfMatches(3)
                            toContainDescr(AT_MOST, 1)
                            notToContain("'H'")
                        }
                    }
                }
                it("${notToContainOrAtMostPair.first("'l', 'H'", "once")} throws AssertionError mentioning only 'l'") {
                    expect {
                        expect(helloWorld).notToContainOrAtMostFun(1, 'l', 'H')
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("'l'")
                            toContainNumberOfMatches(3)
                            toContainDescr(AT_MOST, 1)
                            notToContain("'H'")
                        }
                    }
                }
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
                            toContainValue("'o'")
                            toContainValue("'l'")
                            toContainNumberOfMatches(3, numOfMatches = 2)
                            toContainDescr(AT_MOST, 1, numOfMatches = 2)
                            notToContain("'E'", "'w'")
                        }
                    }
                }
            }

            context("multiple occurrences of the search string") {
                it(
                    "${notToContainOrAtMostPair.first("'o'", "once")} throws AssertionError and " +
                        "message contains both, how many times we expected (1) and how many times it actually contained 'o' (2)"
                ) {
                    expect {
                        expect(helloWorld).notToContainOrAtMostFun(1, 'o')
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("'o'")
                            toContainNumberOfMatches(2)
                            toContainDescr(AT_MOST, 1)
                        }
                    }
                }

                it("${notToContainOrAtMostPair.first("'o'", "twice")} does not throw") {
                    expect(helloWorld).notToContainOrAtMostFun(2, 'o')
                }
                it("${notToContainOrAtMostIgnoringCasePair.first("'o'", "twice")} throws AssertionError") {
                    expect {
                        expect(helloWorld).notToContainOrAtMostIgnoringCaseFun(2, 'o')
                    }.toThrow<AssertionError> {
                        message {
                            toContainDescr(AT_MOST, 2)
                        }
                    }
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
                            notToContain("'o'")
                            toContainValue("'l'")
                            toContainNumberOfMatches(3)
                            toContainDescr(AT_MOST, 2)
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
        }
    }
})
