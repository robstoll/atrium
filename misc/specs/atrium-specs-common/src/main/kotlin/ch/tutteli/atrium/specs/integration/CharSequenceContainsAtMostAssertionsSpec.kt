package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceContainsAtMostAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsAtMostTriple: Triple<String, (String, String) -> String, Expect<CharSequence>.(Int, Any, Array<out Any>) -> Expect<CharSequence>>,
    containsAtMostIgnoringCaseTriple: Triple<String, (String, String) -> String, Expect<CharSequence>.(Int, Any, Array<out Any>) -> Expect<CharSequence>>,
    containsNotPair: Pair<String, (Int) -> String>,
    exactlyPair: Pair<String, (Int) -> String>,
    rootBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(object : SubjectLessSpec<CharSequence>(describePrefix,
        containsAtMostTriple.first to expectLambda { containsAtMostTriple.third(this, 2, 2.3, arrayOf()) },
        containsAtMostIgnoringCaseTriple.first to expectLambda { containsAtMostIgnoringCaseTriple.third(this, 2, 2.3, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<CharSequence>(verbs, describePrefix,
        checkingTriple(containsAtMostTriple.first, { containsAtMostTriple.third(this, 2, 2.3, arrayOf()) }, "2.3 / 2.3" as CharSequence, "2.3 / 2.3 / 2.3"),
        checkingTriple(containsAtMostIgnoringCaseTriple.first, { containsAtMostIgnoringCaseTriple.third(this, 2, 2.3, arrayOf()) }, "2.3 / 2.3" as CharSequence, "2.3 / 2.3 / 2.3")
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val fluent = verbs.check(text as CharSequence)
    val fluentHelloWorld = verbs.check(helloWorld as CharSequence)

    val (containsAtMost, containsAtMostTest, containsAtMostFunArr) = containsAtMostTriple
    fun Expect<CharSequence>.containsAtMostFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtMostFunArr(atLeast, a, aX)

    val (_, containsAtMostIgnoringCase, containsAtMostIgnoringCaseFunArr) = containsAtMostIgnoringCaseTriple
    fun Expect<CharSequence>.containsAtMostIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtMostIgnoringCaseFunArr(atLeast, a, aX)

    val (containsNot, errorMsgContainsNot) = containsNotPair
    val (exactly, errorMsgExactly) = exactlyPair

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val valueWithIndent = "$indentBulletPoint$listBulletPoint$value"

    describeFun(containsAtMost) {

        context("throws an $illegalArgumentException") {
            it("for at most -1 -- only positive numbers") {
                expect {
                    fluent.containsAtMostFun(-1, "")
                }.toThrow<IllegalArgumentException> { messageContains("positive number", -1) }
            }
            it("for at most 0 -- points to $containsNot") {
                expect {
                    fluent.containsAtMostFun(0, "")
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
            it("for at most 1 -- points to $exactly") {
                expect {
                    fluent.containsAtMostFun(1, "")
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgExactly(1)) } }
            }
            it("if an object is passed as first expected") {
                expect {
                    fluent.containsAtMostFun(2, fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            it("if an object is passed as second expected") {
                expect {
                    fluent.containsAtMostFun(2, "that's fine", fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
        }

        context("text '$helloWorld'") {
            context("happy case with $containsAtMost twice") {
                it("${containsAtMostTest("'H'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(2, 'H')
                }
                it("${containsAtMostTest("'H' and 'e' and 'W'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(2, 'H', 'e', 'W')
                }
                it("${containsAtMostTest("'W' and 'H' and 'e'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(2, 'W', 'H', 'e')
                }
            }

            context("failing cases; search string at different positions") {
                it("${containsAtMostTest("'l'", "twice")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(2, 'l')
                    }.toThrow<AssertionError> { messageContains("$atMost: 2", "$valueWithIndent: 'l'") }
                }
                it("${containsAtMostTest("'H', 'l'", "twice")} throws AssertionError mentioning only 'l'") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(2, 'H', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 2", "$valueWithIndent: 'l'")
                            containsNot(atLeast, "$valueWithIndent: 'H'")
                        }
                    }
                }
                it("${containsAtMostTest("'l', 'H'", "twice")} once throws AssertionError mentioning only 'l'") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(2, 'l', 'H')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 2", "$valueWithIndent: 'l'")
                            containsNot(atLeast, "$valueWithIndent: 'H'")
                        }
                    }
                }
                it("${containsAtMostTest("'o', 'E', 'W', 'l'", "twice")} throws AssertionError mentioning 'l' and 'o'") {
                    expect {
                        fluentHelloWorld.containsAtMostIgnoringCaseFun(2, 'o', 'E', 'W', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 2", "$valueWithIndent: 'l'", "$valueWithIndent: 'o'")
                            containsNot(atLeast, "$valueWithIndent: 'E'", "$valueWithIndent: 'W'")
                        }
                    }
                }
                it("${containsAtMostTest("'x' and 'y' and 'z'", "twice")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(2, 'x', 'y', 'z')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atLeast: 1", "$valueWithIndent: 'x'", "$valueWithIndent: 'y'", "$valueWithIndent: 'z'")
                            containsNot(atMost)
                        }
                    }
                }
            }

            context("multiple occurrences of the search string") {

                it("${containsAtMostTest("'o'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(2, 'o')
                }
                it("${containsAtMostIgnoringCase("'o'", "twice")} throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 'o' ignoring case (3)") {
                    expect {
                        fluentHelloWorld.containsAtMostIgnoringCaseFun(2, 'o')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsIgnoringCase: $separator" +
                                    "$valueWithIndent: 'o'",
                                "$numberOfOccurrences: 3$separator"
                            )
                            endsWith("$atMost: 2")
                        }
                    }
                }

                it("${containsAtMostTest("'o'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(3, 'o')
                }
                it("${containsAtMostTest("'o' and 'l'", "twice")} throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 'l' (3)") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(2, 'o', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsDescr: $separator" +
                                    "$valueWithIndent: 'l'",
                                "$numberOfOccurrences: 3$separator"
                            )
                            endsWith("$atMost: 2")
                            containsNot("$valueWithIndent: 'o'")
                        }
                    }
                }
                it("${containsAtMostTest("'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(3, 'l')
                }
                it("${containsAtMostTest("'o' and 'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(3, 'o', 'l')
                }

            }
        }

        context("special cases") {
            context("string: \"\\0 hello\"") {
                it("${containsAtMostTest("\"hello\" and '\\0'", "twice")} does not throw") {
                    verbs.check(('\u0000' + " hello") as CharSequence).containsAtMostFun(2, "hello", 0.toChar())
                }
            }

            val aaaa: CharSequence = "aaaa"
            val aaaaFluent = verbs.check(aaaa)
            context("string \"$aaaa\""){
                it("${containsAtMostTest("'a'", "4 times")} does not throw") {
                    aaaaFluent.containsAtMostFun(4, 'a')
                }
                it("${containsAtMostTest("'a'", "3 times")} throws AssertionError") {
                    expect {
                        aaaaFluent.containsAtMostFun(3, 'a')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$valueWithIndent: 'a'", "$numberOfOccurrences: 4", "$atMost: 3")
                            containsNot(atLeast)
                        }
                    }
                }
            }
        }
    }
})
