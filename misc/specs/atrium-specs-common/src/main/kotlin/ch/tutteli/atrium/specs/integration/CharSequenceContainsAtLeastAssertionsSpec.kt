package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceContainsAtLeastAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsAtLeastTriple: Triple<String, (String, String) -> String, Expect<CharSequence>.(Int, Any, Array<out Any>) -> Expect<CharSequence>>,
    containsAtLeastIgnoringCaseTriple: Triple<String, (String, String) -> String, Expect<CharSequence>.(Int, Any, Array<out Any>) -> Expect<CharSequence>>,
    containsAtLeastButAtMostTriple: Triple<String, (String, String, String) -> String, Expect<CharSequence>.(Int, Int, Any, Array<out Any>) -> Expect<CharSequence>>,
    containsAtLeastButAtMostIgnoringCaseTriple: Triple<String, (String, String, String) -> String, Expect<CharSequence>.(Int, Int, Any, Array<out Any>) -> Expect<CharSequence>>,
    containsNotPair: Pair<String, (Int) -> String>,
    exactlyPair: Pair<String, (Int) -> String>,
    errorMsgAtLeastButAtMost: (Int, Int) -> String,
    rootBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(object : SubjectLessSpec<CharSequence>(describePrefix,
        containsAtLeastTriple.first to expectLambda { containsAtLeastTriple.third(this, 1, "2.3", arrayOf()) },
        containsAtLeastIgnoringCaseTriple.first to expectLambda { containsAtLeastIgnoringCaseTriple.third(this, 1, 'a', arrayOf()) },
        containsAtLeastButAtMostTriple.first to expectLambda { containsAtLeastButAtMostTriple.third(this, 1, 2, "aA", arrayOf()) },
        containsAtLeastButAtMostIgnoringCaseTriple.first to expectLambda { containsAtLeastButAtMostIgnoringCaseTriple.third(this, 1, 2, 2.3, arrayOf()) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val fluent = verbs.check(text as CharSequence)
    val fluentHelloWorld = verbs.check(helloWorld as CharSequence)

    val (containsAtLeast, containsAtLeastTest, containsAtLeastFunArr) = containsAtLeastTriple
    fun Expect<CharSequence>.containsAtLeastFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtLeastFunArr(atLeast, a, aX)

    val (_, containsAtLeastIgnoringCase, containsAtLeastIgnoringCaseFunArr) = containsAtLeastIgnoringCaseTriple
    fun Expect<CharSequence>.containsAtLeastIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtLeastIgnoringCaseFunArr(atLeast, a, aX)

    val (containsAtLeastButAtMost, containsAtLeastButAtMostTest, containsAtLeastButAtMostFunArr) = containsAtLeastButAtMostTriple
    fun Expect<CharSequence>.containsAtLeastButAtMostFun(atLeast: Int, atMost: Int, a: Any, vararg aX: Any)
        = containsAtLeastButAtMostFunArr(atLeast, atMost, a, aX)

    val (_, containsAtLeastButAtMostIgnoringCase, containsAtLeastButAtMostIgnoringCaseFunArr) = containsAtLeastButAtMostIgnoringCaseTriple
    fun Expect<CharSequence>.containsAtLeastButAtMostIgnoringCaseFun(atLeast: Int, atMost: Int, a: Any, vararg aX: Any)
        = containsAtLeastButAtMostIgnoringCaseFunArr(atLeast, atMost, a, aX)

    val (containsNot, errorMsgContainsNot) = containsNotPair
    val (exactly, errorMsgExactly) = exactlyPair

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val valueWithIndent = "$indentBulletPoint$listBulletPoint$value"

    describeFun(containsAtLeast, containsAtLeastButAtMost) {

        context("throws an $illegalArgumentException") {
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
                expect{
                    fluent.containsAtLeastFun(1, "that's fine", "" /* <- that's not */)
                }.toThrow<IllegalArgumentException> { messageContains("empty string", "forgot")  }
            }
            it("searching for an empty CharSequence - warns the user that the assertion is useless") {
                expect{
                    fluent.containsAtLeastFun(1, "that's fine", StringBuilder() /* <- that's not */)
                }.toThrow<IllegalArgumentException> { messageContains("empty CharSequence", "forgot")  }
            }
        }

        context("text '$helloWorld'") {

            context("happy case with $containsAtLeast once") {
                it("${containsAtLeastTest("'H'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(1, 'H')
                }
                it("${containsAtLeastTest("'H' and 'e' and 'W'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(1, 'H', 'e', 'W')
                }
                it("${containsAtLeastTest("'W' and 'H' and 'e'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(1, 'W', 'H', 'e')
                }
            }

            context("failing cases; search string at different positions with $containsAtLeast once") {
                it("${containsAtLeastTest("'h'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(1, 'h')
                    }.toThrow<AssertionError> { messageContains("$atLeast: 1", "$valueWithIndent: 'h'") }
                }
                it("${containsAtLeastIgnoringCase("'h'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(1, 'h')
                }

                it("${containsAtLeastTest("'H', 'E'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(1, 'H', 'E')
                    }.toThrow<AssertionError> { messageContains(atLeast, 'E') }
                }
                it("${containsAtLeastIgnoringCase("'H', 'E'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(1, 'H', 'E')
                }

                it("${containsAtLeastTest("'E', 'H'", "once")} throws AssertionError mentioning only 'E'") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(1, 'E', 'H')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atLeast: 1", "$valueWithIndent: 'E'")
                            containsNot("$valueWithIndent: 'H'")
                        }
                    }
                }
                it("${containsAtLeastIgnoringCase("'E', 'H'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(1, 'E', 'H')
                }

                it("${containsAtLeastTest("'H', 'E', 'w' and 'r'", "once")} throws AssertionError mentioning 'E' and 'w'") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(1, 'H', 'E', 'w', 'r')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atLeast: 1", "$valueWithIndent: 'E'", "$valueWithIndent: 'w'")
                            containsNot("$valueWithIndent: 'H'", "$valueWithIndent: 'r'")
                        }
                    }
                }
                it("${containsAtLeastIgnoringCase("'H', 'E', 'w' and 'r'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(1, 'H', 'E', 'w', 'r')
                }
            }

            context("multiple occurrences of the search string") {
                it("${containsAtLeastTest("'o'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(1, 'o')
                }
                it("${containsAtLeastTest("'o'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(2, 'o')
                }

                it("${containsAtLeastTest("'o'", "3 times")} throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 'o' (2)") {
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
                it("${containsAtLeastIgnoringCase("'o'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(3, 'o')
                }

                it("${containsAtLeastTest("'o' and 'l'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(2, 'o', 'l')
                }
                it("${containsAtLeastTest("'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(3, 'l')
                }

                it("${containsAtLeastTest("'o' and 'l'", "3 times")} throws AssertionError and message contains both, at least: 3 and how many times it actually contained 'o' (2)") {
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
                it("${containsAtLeastIgnoringCase("'o' and 'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(3, 'o', 'l')
                }
            }

            context("using $containsAtLeastButAtMost") {
                it("${containsAtLeastButAtMostTest("'o'", "once", "twice")} does not throw") {
                    fluentHelloWorld.containsAtLeastButAtMostFun(1, 2, 'o')
                }
                it("${containsAtLeastButAtMostTest("'o' and 'l'", "once", "twice")} throws AssertionError and message contains both, at most: 2 and how many times it actually contained 'l' (3)") {
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
                it("${containsAtLeastButAtMostTest("'o' and 'l'", "twice", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastButAtMostFun(2, 3, 'o', 'l')
                }
                it("${containsAtLeastButAtMostIgnoringCase("'o' and 'l'", "twice", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastButAtMostIgnoringCaseFun(2, 3, 'o', 'l')
                }

                it("${containsAtLeastButAtMostTest("'o' and 'l'", "3 times", "4 times")} throws AssertionError and message contains both, at least: 3 and how many times it actually contained 'o' (2)") {
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
                it("${containsAtLeastIgnoringCase("'o' and 'l'", " 3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastButAtMostIgnoringCaseFun(3, 4, 'o', 'l')
                }
            }
        }

        context("special cases") {
            context("string: \"\\0 hello\"") {
                it("${containsAtLeastTest("\"hello\" and '\\0'", "once")} does not throw") {
                    verbs.check(('\u0000' + " hello") as CharSequence).containsAtLeastFun(1, "hello", 0.toChar())
                }
            }

            val aaaa: CharSequence = "aaaa"
            val aaaaFluent = verbs.check(aaaa )
            context("string \"$aaaa\""){
                it("${containsAtLeastTest("'a'", "4 times")} does not throw") {
                    aaaaFluent.containsAtLeastFun(4, 'a')
                }
                it("${containsAtLeastTest("'a'", "5 times")} throws AssertionError") {
                    expect {
                        aaaaFluent.containsAtLeastFun(5, 'a')
                    }.toThrow<AssertionError> { messageContains("$atLeast: 5", "$valueWithIndent: 'a'") }
                }
            }
        }
    }
})
