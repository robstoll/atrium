package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.*
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceToContainAtLeastExpectationsSpec(
    toContainAtLeastPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    toContainAtLeastIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    toContainAtLeastButAtMostPair: Pair<(String, String, String) -> String, Fun4<CharSequence, Int, Int, Any, Array<out Any>>>,
    toContainAtLeastButAtMostIgnoringCasePair: Pair<(String, String, String) -> String, Fun4<CharSequence, Int, Int, Any, Array<out Any>>>,
    notToContainPair: Pair<String, (Int) -> String>,
    exactlyPair: Pair<String, (Int) -> String>,
    errorMsgAtLeastButAtMost: (Int, Int) -> String,
    describePrefix: String = "[Atrium] "
) : CharSequenceToContainSpecBase({

    val toContainAtLeast = toContainAtLeastPair.second
    val toContainAtLeastIgnoringCase = toContainAtLeastIgnoringCasePair.second
    val toContainAtLeastButAtMost = toContainAtLeastButAtMostPair.second
    val toContainAtLeastButAtMostIgnoringCase = toContainAtLeastButAtMostIgnoringCasePair.second

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        toContainAtLeast.forSubjectLessTest(1, "2.3", arrayOf()),
        toContainAtLeastIgnoringCase.forSubjectLessTest(1, 'a', arrayOf()),
        toContainAtLeastButAtMost.forSubjectLessTest(1, 2, "aA", arrayOf()),
        toContainAtLeastButAtMostIgnoringCase.forSubjectLessTest(1, 2, 2.3, arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)


    fun Expect<CharSequence>.toContainAtLeastFun(atLeast: Int, a: Any, vararg aX: Any) =
        toContainAtLeast(this, atLeast, a, aX)

    fun Expect<CharSequence>.toContainAtLeastIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any) =
        toContainAtLeastIgnoringCase(this, atLeast, a, aX)

    fun Expect<CharSequence>.toContainAtLeastButAtMostFun(atLeast: Int, atMost: Int, a: Any, vararg aX: Any) =
        toContainAtLeastButAtMost(this, atLeast, atMost, a, aX)

    fun Expect<CharSequence>.toContainAtLeastButAtMostIgnoringCaseFun(
        atLeast: Int, atMost: Int, a: Any, vararg aX: Any
    ) = toContainAtLeastButAtMostIgnoringCase(this, atLeast, atMost, a, aX)

    describeFun(toContainAtLeast.name, toContainAtLeastButAtMost.name) {

        context("throws an $illegalArgumentException") {
            val (exactly, errorMsgExactly) = exactlyPair
            val (notToContain, errorMsgNotToContain) = notToContainPair

            it("for at least -1 -- only positive numbers") {
                expect {
                    expect(text).toContainAtLeastFun(-1, "")
                }.toThrow<IllegalArgumentException> { messageToContain("positive number", -1) }
            }
            it("for at least 0 -- points to $notToContain") {
                expect {
                    expect(text).toContainAtLeastFun(0, "")
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgNotToContain(0)) } }
            }
            it("if an object is passed as first expected") {
                expect {
                    expect(text).toContainAtLeastFun(1, expect(text))
                }.toThrow<IllegalArgumentException> { messageToContain(ERROR_MESSAGE_ONLY_CHARSEQUENCE_NUMBER_CHAR) }
            }
            it("if an object is passed as second expected") {
                expect {
                    expect(text).toContainAtLeastFun(1, "that's fine", expect(text))
                }.toThrow<IllegalArgumentException> { messageToContain(ERROR_MESSAGE_ONLY_CHARSEQUENCE_NUMBER_CHAR) }
            }

            context("using $toContainAtLeastButAtMost") {
                it("for at least 1 but at most -1 -- since -1 is smaller than 1") {
                    expect {
                        expect(text).toContainAtLeastButAtMostFun(1, -1, "")
                    }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgAtLeastButAtMost(1, -1)) } }
                }
                it("for at least 1 but at most 0 -- since 0 is smaller than 1") {
                    expect {
                        expect(text).toContainAtLeastButAtMostFun(1, 0, "")
                    }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgAtLeastButAtMost(1, 0)) } }
                }
                it("for at least 2 but at most 1 -- since 1 is smaller than 2") {
                    expect {
                        expect(text).toContainAtLeastButAtMostFun(2, 1, "")
                    }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgAtLeastButAtMost(2, 1)) } }
                }
                it("for at least 1 but at most 1 -- points to $exactly") {
                    expect {
                        expect(text).toContainAtLeastButAtMostFun(1, 1, "")
                    }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgExactly(1)) } }
                }
                it("if an object is passed as first expected") {
                    expect {
                        expect(text).toContainAtLeastButAtMostFun(1, 2, expect(text))
                    }.toThrow<IllegalArgumentException> { messageToContain(ERROR_MESSAGE_ONLY_CHARSEQUENCE_NUMBER_CHAR) }
                }
                it("if an object is passed as second expected") {
                    expect {
                        expect(text).toContainAtLeastButAtMostFun(1, 2, "that's fine", expect(text))
                    }.toThrow<IllegalArgumentException> { messageToContain(ERROR_MESSAGE_ONLY_CHARSEQUENCE_NUMBER_CHAR) }
                }
            }

            it("searching for an empty String - warns the user that the expectation is useless") {
                expect {
                    expect(text).toContainAtLeastFun(1, "that's fine", "" /* <- that's not */)
                }.toThrow<IllegalArgumentException> { messageToContain("empty string", "forgot") }
            }
            it("searching for an empty CharSequence - warns the user that the expectation is useless") {
                expect {
                    expect(text).toContainAtLeastFun(1, "that's fine", StringBuilder() /* <- that's not */)
                }.toThrow<IllegalArgumentException> { messageToContain("empty CharSequence", "forgot") }
            }
        }

        context("text '$helloWorld'") {

            context("happy case with $toContainAtLeast once") {
                it("${toContainAtLeastPair.first("'H'", "once")} does not throw") {
                    expect(helloWorld).toContainAtLeastFun(1, 'H')
                }
                it("${toContainAtLeastPair.first("'H' and 'e' and 'W'", "once")} does not throw") {
                    expect(helloWorld).toContainAtLeastFun(1, 'H', 'e', 'W')
                }
                it("${toContainAtLeastPair.first("'W' and 'H' and 'e'", "once")} does not throw") {
                    expect(helloWorld).toContainAtLeastFun(1, 'W', 'H', 'e')
                }
            }

            context("failing cases; search string at different positions with $toContainAtLeast once") {
                it("${toContainAtLeastPair.first("'h'", "once")} throws AssertionError") {
                    expect {
                        expect(helloWorld).toContainAtLeastFun(1, 'h')
                    }.toThrow<AssertionError> {
                        message.toMatch(
                            //TODO 1.3.0/1.4.0 expect (note the $x instead of $g), the
                            // » but no match was found is unnecessary now IMO
//                            Regex(
//                                "$expectationVerb : \"$helloWorld\"$lineSeparator" +
//                                    "$g${TO_CONTAIN.string} : $lineSeparator" +
//                                    "${indentG}${x}${VALUE.string} : 'h'$lineSeparator"
//                            )
                            Regex(
                                "$expectationVerb : \"$helloWorld\"$lineSeparator" +
                                    "$g${TO_CONTAIN.string} : $lineSeparator" +
                                    "${indentG}${g}${VALUE.string} : 'h'$lineSeparator" +
                                    "${indentG}${indentG}${explanatoryBulletPoint}${NOT_FOUND.string}"
                            )
                        )
                    }
                }
                it("${toContainAtLeastIgnoringCasePair.first("'h'", "once")} does not throw") {
                    expect(helloWorld).toContainAtLeastIgnoringCaseFun(1, 'h')
                }

                it("${toContainAtLeastPair.first("'H', 'E'", "once")} throws AssertionError") {
                    expect {
                        expect(helloWorld).toContainAtLeastFun(1, 'H', 'E')
                    }.toThrow<AssertionError> {
                        message {
                            notToContain("'H'")
                            toContainValue("'E'")
                        }
                    }
                }
                it("${toContainAtLeastIgnoringCasePair.first("'H', 'E'", "once")} does not throw") {
                    expect(helloWorld).toContainAtLeastIgnoringCaseFun(1, 'H', 'E')
                }

                it("${toContainAtLeastPair.first("'E', 'H'", "once")} throws AssertionError mentioning only 'E'") {
                    expect {
                        expect(helloWorld).toContainAtLeastFun(1, 'E', 'H')
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("'E'")
                            notToContain("'H'")
                        }
                    }
                }
                it("${toContainAtLeastIgnoringCasePair.first("'E', 'H'", "once")} does not throw") {
                    expect(helloWorld).toContainAtLeastIgnoringCaseFun(1, 'E', 'H')
                }

                it(
                    "${toContainAtLeastPair.first(
                        "'H', 'E', 'w' and 'r'",
                        "once"
                    )} throws AssertionError mentioning 'E' and 'w'"
                ) {
                    expect {
                        expect(helloWorld).toContainAtLeastFun(1, 'H', 'E', 'w', 'r')
                    }.toThrow<AssertionError> {
                        message {
                            notToContain("'H'")
                            toContainValue("'E'")
                            toContainValue("'w'")
                            notToContain("'r'")
                        }
                    }
                }
                it("${toContainAtLeastIgnoringCasePair.first("'H', 'E', 'w' and 'r'", "once")} does not throw") {
                    expect(helloWorld).toContainAtLeastIgnoringCaseFun(1, 'H', 'E', 'w', 'r')
                }
            }

            context("multiple occurrences of the search string") {
                it("${toContainAtLeastPair.first("'o'", "once")} does not throw") {
                    expect(helloWorld).toContainAtLeastFun(1, 'o')
                }
                it("${toContainAtLeastPair.first("'o'", "twice")} does not throw") {
                    expect(helloWorld).toContainAtLeastFun(2, 'o')
                }

                it(
                    "${toContainAtLeastPair.first("'o'", "3 times")} throws AssertionError and message contains both, " +
                        "how many times we expected (3) and how many times it actually contained 'o' (2)"
                ) {
                    expect {
                        expect(helloWorld).toContainAtLeastFun(3, 'o')
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("'o'")
                            toContainNumberOfMatches(2)
                            toContainDescr(AT_LEAST, 3)
                        }
                    }
                }
                it("${toContainAtLeastIgnoringCasePair.first("'o'", "3 times")} does not throw") {
                    expect(helloWorld).toContainAtLeastIgnoringCaseFun(3, 'o')
                }

                it("${toContainAtLeastPair.first("'o' and 'l'", "twice")} does not throw") {
                    expect(helloWorld).toContainAtLeastFun(2, 'o', 'l')
                }
                it("${toContainAtLeastPair.first("'l'", "3 times")} does not throw") {
                    expect(helloWorld).toContainAtLeastFun(3, 'l')
                }

                it(
                    "${toContainAtLeastPair.first("'o' and 'l'", "3 times")} throws AssertionError " +
                        "and message contains both, at least: 3 and how many times it actually contained 'o' (2)"
                ) {
                    expect {
                        expect(helloWorld).toContainAtLeastFun(3, 'o', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("'o'")
                            toContainNumberOfMatches(2)
                            toContainDescr(AT_LEAST, 3)
                            notToContain("'l'")
                        }
                    }
                }
                it("${toContainAtLeastIgnoringCasePair.first("'o' and 'l'", "3 times")} does not throw") {
                    expect(helloWorld).toContainAtLeastIgnoringCaseFun(3, 'o', 'l')
                }
            }

            context("using $toContainAtLeastButAtMost") {
                it("${toContainAtLeastButAtMostPair.first("'o'", "once", "twice")} does not throw") {
                    expect(helloWorld).toContainAtLeastButAtMostFun(1, 2, 'o')
                }
                it(
                    "${toContainAtLeastButAtMostPair.first("'o' and 'l'", "once", "twice")} throws AssertionError " +
                        "and message contains both, at most: 2 and how many times it actually contained 'l' (3)"
                ) {
                    expect {
                        expect(helloWorld).toContainAtLeastButAtMostFun(1, 2, 'o', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            notToContain("'o'")
                            toContainValue("'l'")
                            toContainNumberOfMatches(3)
                            notToContain(AT_LEAST.string)
                            toContainDescr(AT_MOST, 2)
                        }
                    }
                }
                it("${toContainAtLeastButAtMostPair.first("'o' and 'l'", "twice", "3 times")} does not throw") {
                    expect(helloWorld).toContainAtLeastButAtMostFun(2, 3, 'o', 'l')
                }
                it(
                    "${toContainAtLeastButAtMostIgnoringCasePair.first("'o' and 'l'", "twice", "3 times")} " +
                        "does not throw"
                ) {
                    expect(helloWorld).toContainAtLeastButAtMostIgnoringCaseFun(2, 3, 'o', 'l')
                }

                it(
                    "${toContainAtLeastButAtMostPair.first("'o' and 'l'", "3 times", "4 times")} throws " +
                        "AssertionError and message contains both, at least: 3 and how many times it actually contained 'o' (2)"
                ) {
                    expect {
                        expect(helloWorld).toContainAtLeastButAtMostFun(3, 4, 'o', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("'o'")
                            toContainNumberOfMatches(2)
                            toContainDescr(AT_LEAST, 3)
                            notToContain(AT_MOST.string)
                        }
                    }
                }
                it("${toContainAtLeastIgnoringCasePair.first("'o' and 'l'", " 3 times")} does not throw") {
                    expect(helloWorld).toContainAtLeastButAtMostIgnoringCaseFun(3, 4, 'o', 'l')
                }
            }
        }

        context("special cases") {
            context("string: \"\\0 hello\"") {
                it("${toContainAtLeastPair.first("\"hello\" and '\\0'", "once")} does not throw") {
                    expect(('\u0000' + " hello") as CharSequence).toContainAtLeastFun(1, "hello", 0.toChar())
                }
            }

            val aaaa: CharSequence = "aaaa"
            val aaaaFluent = expect(aaaa)
            context("string \"$aaaa\"") {
                it("${toContainAtLeastPair.first("'a'", "4 times")} does not throw") {
                    aaaaFluent.toContainAtLeastFun(4, 'a')
                }
                it("${toContainAtLeastPair.first("'a'", "5 times")} throws AssertionError") {
                    expect {
                        aaaaFluent.toContainAtLeastFun(5, 'a')
                    }.toThrow<AssertionError> {
                        message {
                            toContainValue("'a'")
                            toContainDescr(AT_LEAST, 5)
                        }
                    }
                }
            }
        }
    }
})
