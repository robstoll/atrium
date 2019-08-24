package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceContainsAtLeastAssertionsSpec(
    containsAtLeastPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    containsAtLeastIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    containsAtLeastButAtMostPair: Pair<(String, String, String) -> String, Fun4<CharSequence, Int, Int, Any, Array<out Any>>>,
    containsAtLeastButAtMostIgnoringCasePair: Pair<(String, String, String) -> String, Fun4<CharSequence, Int, Int, Any, Array<out Any>>>,
    containsNotPair: Pair<String, (Int) -> String>,
    exactlyPair: Pair<String, (Int) -> String>,
    errorMsgAtLeastButAtMost: (Int, Int) -> String,
    rootBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    val containsAtLeast = containsAtLeastPair.second
    val containsAtLeastIgnoringCase = containsAtLeastIgnoringCasePair.second
    val containsAtLeastButAtMost = containsAtLeastButAtMostPair.second
    val containsAtLeastButAtMostIgnoringCase = containsAtLeastButAtMostIgnoringCasePair.second

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        containsAtLeast.forSubjectLess(1, "2.3", arrayOf()),
        containsAtLeastIgnoringCase.forSubjectLess(1, 'a', arrayOf()),
        containsAtLeastButAtMost.forSubjectLess(1, 2, "aA", arrayOf()),
        containsAtLeastButAtMostIgnoringCase.forSubjectLess(1, 2, 2.3, arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val fluent = expect(text as CharSequence)
    val fluentHelloWorld = expect(helloWorld as CharSequence)


    fun Expect<CharSequence>.containsAtLeastFun(atLeast: Int, a: Any, vararg aX: Any) =
        containsAtLeast(this, atLeast, a, aX)

    fun Expect<CharSequence>.containsAtLeastIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any) =
        containsAtLeastIgnoringCase(this, atLeast, a, aX)

    fun Expect<CharSequence>.containsAtLeastButAtMostFun(atLeast: Int, atMost: Int, a: Any, vararg aX: Any) =
        containsAtLeastButAtMost(this, atLeast, atMost, a, aX)

    fun Expect<CharSequence>.containsAtLeastButAtMostIgnoringCaseFun(
        atLeast: Int, atMost: Int, a: Any, vararg aX: Any
    ) = containsAtLeastButAtMostIgnoringCase(this, atLeast, atMost, a, aX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val valueWithIndent = "$indentBulletPoint$listBulletPoint$value"

    describeFun(containsAtLeast.name, containsAtLeastButAtMost.name) {

        context("throws an $illegalArgumentException") {
            val (exactly, errorMsgExactly) = exactlyPair
            val (containsNot, errorMsgContainsNot) = containsNotPair

            it("for at least -1 -- only positive numbers") {
                expect {
                    fluent.containsAtLeastFun(-1, "")
                }.toThrow<IllegalArgumentException> { messageContains("positive number", -1) }
            }
            it("for at least 0 -- points to $containsNot") {
                expect {
                    fluent.containsAtLeastFun(0, "")
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
            it("if an object is passed as first expected") {
                expect {
                    fluent.containsAtLeastFun(1, fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            it("if an object is passed as second expected") {
                expect {
                    fluent.containsAtLeastFun(1, "that's fine", fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }

            context("using $containsAtLeastButAtMost") {
                it("for at least 1 but at most -1 -- since -1 is smaller than 1") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, -1, "")
                    }.toThrow<IllegalArgumentException> { message { toBe(errorMsgAtLeastButAtMost(1, -1)) } }
                }
                it("for at least 1 but at most 0 -- since 0 is smaller than 1") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, 0, "")
                    }.toThrow<IllegalArgumentException> { message { toBe(errorMsgAtLeastButAtMost(1, 0)) } }
                }
                it("for at least 2 but at most 1 -- since 1 is smaller than 2") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(2, 1, "")
                    }.toThrow<IllegalArgumentException> { message { toBe(errorMsgAtLeastButAtMost(2, 1)) } }
                }
                it("for at least 1 but at most 1 -- points to $exactly") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, 1, "")
                    }.toThrow<IllegalArgumentException> { message { toBe(errorMsgExactly(1)) } }
                }
                it("if an object is passed as first expected") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, 2, fluent)
                    }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
                }
                it("if an object is passed as second expected") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, 2, "that's fine", fluent)
                    }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
                }
            }

            it("searching for an empty String - warns the user that the assertion is useless") {
                expect {
                    fluent.containsAtLeastFun(1, "that's fine", "" /* <- that's not */)
                }.toThrow<IllegalArgumentException> { messageContains("empty string", "forgot") }
            }
            it("searching for an empty CharSequence - warns the user that the assertion is useless") {
                expect {
                    fluent.containsAtLeastFun(1, "that's fine", StringBuilder() /* <- that's not */)
                }.toThrow<IllegalArgumentException> { messageContains("empty CharSequence", "forgot") }
            }
        }

        context("text '$helloWorld'") {

            context("happy case with $containsAtLeast once") {
                it("${containsAtLeastPair.first("'H'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(1, 'H')
                }
                it("${containsAtLeastPair.first("'H' and 'e' and 'W'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(1, 'H', 'e', 'W')
                }
                it("${containsAtLeastPair.first("'W' and 'H' and 'e'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(1, 'W', 'H', 'e')
                }
            }

            context("failing cases; search string at different positions with $containsAtLeast once") {
                it("${containsAtLeastPair.first("'h'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(1, 'h')
                    }.toThrow<AssertionError> { messageContains("$atLeast: 1", "$valueWithIndent: 'h'") }
                }
                it("${containsAtLeastIgnoringCasePair.first("'h'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(1, 'h')
                }

                it("${containsAtLeastPair.first("'H', 'E'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(1, 'H', 'E')
                    }.toThrow<AssertionError> { messageContains(atLeast, 'E') }
                }
                it("${containsAtLeastIgnoringCasePair.first("'H', 'E'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(1, 'H', 'E')
                }

                it("${containsAtLeastPair.first("'E', 'H'", "once")} throws AssertionError mentioning only 'E'") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(1, 'E', 'H')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atLeast: 1", "$valueWithIndent: 'E'")
                            containsNot("$valueWithIndent: 'H'")
                        }
                    }
                }
                it("${containsAtLeastIgnoringCasePair.first("'E', 'H'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(1, 'E', 'H')
                }

                it(
                    "${containsAtLeastPair.first(
                        "'H', 'E', 'w' and 'r'",
                        "once"
                    )} throws AssertionError mentioning 'E' and 'w'"
                ) {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(1, 'H', 'E', 'w', 'r')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atLeast: 1", "$valueWithIndent: 'E'", "$valueWithIndent: 'w'")
                            containsNot("$valueWithIndent: 'H'", "$valueWithIndent: 'r'")
                        }
                    }
                }
                it("${containsAtLeastIgnoringCasePair.first("'H', 'E', 'w' and 'r'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(1, 'H', 'E', 'w', 'r')
                }
            }

            context("multiple occurrences of the search string") {
                it("${containsAtLeastPair.first("'o'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(1, 'o')
                }
                it("${containsAtLeastPair.first("'o'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(2, 'o')
                }

                it(
                    "${containsAtLeastPair.first("'o'", "3 times")} throws AssertionError and message contains both, " +
                        "how many times we expected (3) and how many times it actually contained 'o' (2)"
                ) {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(3, 'o')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsDescr: $separator" +
                                    "$valueWithIndent: 'o'",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$atLeast: 3")
                        }
                    }
                }
                it("${containsAtLeastIgnoringCasePair.first("'o'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(3, 'o')
                }

                it("${containsAtLeastPair.first("'o' and 'l'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(2, 'o', 'l')
                }
                it("${containsAtLeastPair.first("'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(3, 'l')
                }

                it(
                    "${containsAtLeastPair.first("'o' and 'l'", "3 times")} throws AssertionError " +
                        "and message contains both, at least: 3 and how many times it actually contained 'o' (2)"
                ) {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(3, 'o', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsDescr: $separator" +
                                    "$valueWithIndent: 'o'",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$atLeast: 3")
                            containsNot("$valueWithIndent: 'l'")
                        }
                    }
                }
                it("${containsAtLeastIgnoringCasePair.first("'o' and 'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(3, 'o', 'l')
                }
            }

            context("using $containsAtLeastButAtMost") {
                it("${containsAtLeastButAtMostPair.first("'o'", "once", "twice")} does not throw") {
                    fluentHelloWorld.containsAtLeastButAtMostFun(1, 2, 'o')
                }
                it(
                    "${containsAtLeastButAtMostPair.first("'o' and 'l'", "once", "twice")} throws AssertionError " +
                        "and message contains both, at most: 2 and how many times it actually contained 'l' (3)"
                ) {
                    expect {
                        fluentHelloWorld.containsAtLeastButAtMostFun(1, 2, 'o', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsDescr: $separator" +
                                    "$valueWithIndent: 'l'",
                                "$numberOfOccurrences: 3$separator"
                            )
                            endsWith("$atMost: 2")
                            containsNot(atLeast, "$valueWithIndent: 'o'")
                        }
                    }
                }
                it("${containsAtLeastButAtMostPair.first("'o' and 'l'", "twice", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastButAtMostFun(2, 3, 'o', 'l')
                }
                it(
                    "${containsAtLeastButAtMostIgnoringCasePair.first("'o' and 'l'", "twice", "3 times")} " +
                        "does not throw"
                ) {
                    fluentHelloWorld.containsAtLeastButAtMostIgnoringCaseFun(2, 3, 'o', 'l')
                }

                it(
                    "${containsAtLeastButAtMostPair.first("'o' and 'l'", "3 times", "4 times")} throws " +
                        "AssertionError and message contains both, at least: 3 and how many times it actually contained 'o' (2)"
                ) {
                    expect {
                        fluentHelloWorld.containsAtLeastButAtMostFun(3, 4, 'o', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsDescr: $separator" +
                                    "$valueWithIndent: 'o'",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$atLeast: 3")
                            containsNot(atMost, "$valueWithIndent: 'l'")
                        }
                    }
                }
                it("${containsAtLeastIgnoringCasePair.first("'o' and 'l'", " 3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastButAtMostIgnoringCaseFun(3, 4, 'o', 'l')
                }
            }
        }

        context("special cases") {
            context("string: \"\\0 hello\"") {
                it("${containsAtLeastPair.first("\"hello\" and '\\0'", "once")} does not throw") {
                    expect(('\u0000' + " hello") as CharSequence).containsAtLeastFun(1, "hello", 0.toChar())
                }
            }

            val aaaa: CharSequence = "aaaa"
            val aaaaFluent = expect(aaaa)
            context("string \"$aaaa\"") {
                it("${containsAtLeastPair.first("'a'", "4 times")} does not throw") {
                    aaaaFluent.containsAtLeastFun(4, 'a')
                }
                it("${containsAtLeastPair.first("'a'", "5 times")} throws AssertionError") {
                    expect {
                        aaaaFluent.containsAtLeastFun(5, 'a')
                    }.toThrow<AssertionError> { messageContains("$atLeast: 5", "$valueWithIndent: 'a'") }
                }
            }
        }
    }
})
