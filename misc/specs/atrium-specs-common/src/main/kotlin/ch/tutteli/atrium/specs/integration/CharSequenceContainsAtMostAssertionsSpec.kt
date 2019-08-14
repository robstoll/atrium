package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.AssertionVerbFactory
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceContainsAtMostAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsAtMostPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    containsAtMostIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    containsNotPair: Pair<String, (Int) -> String>,
    exactlyPair: Pair<String, (Int) -> String>,
    rootBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({
    
    val containsAtMost = containsAtMostPair.second
    val containsAtMostIgnoringCase = containsAtMostIgnoringCasePair.second
    
    include(object : SubjectLessSpec<CharSequence>(describePrefix,
        containsAtMost.forSubjectLess(2, 2.3, arrayOf()),
        containsAtMostIgnoringCase.forSubjectLess(2, 2.3, arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val fluent = verbs.check(text as CharSequence)
    val fluentHelloWorld = verbs.check(helloWorld as CharSequence)

    fun Expect<CharSequence>.containsAtMostFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtMost(this, atLeast, a, aX)

    fun Expect<CharSequence>.containsAtMostIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtMostIgnoringCase(this, atLeast, a, aX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val valueWithIndent = "$indentBulletPoint$listBulletPoint$value"

    describeFun(containsAtMost.name, containsAtMostIgnoringCase.name) {

        context("throws an $illegalArgumentException") {
            val (containsNot, errorMsgContainsNot) = containsNotPair
            val (exactly, errorMsgExactly) = exactlyPair

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
                it("${containsAtMostPair.first("'H'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(2, 'H')
                }
                it("${containsAtMostPair.first("'H' and 'e' and 'W'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(2, 'H', 'e', 'W')
                }
                it("${containsAtMostPair.first("'W' and 'H' and 'e'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(2, 'W', 'H', 'e')
                }
            }

            context("failing cases; search string at different positions") {
                it("${containsAtMostPair.first("'l'", "twice")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(2, 'l')
                    }.toThrow<AssertionError> { messageContains("$atMost: 2", "$valueWithIndent: 'l'") }
                }
                it("${containsAtMostPair.first("'H', 'l'", "twice")} throws AssertionError mentioning only 'l'") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(2, 'H', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 2", "$valueWithIndent: 'l'")
                            containsNot(atLeast, "$valueWithIndent: 'H'")
                        }
                    }
                }
                it("${containsAtMostPair.first("'l', 'H'", "twice")} once throws AssertionError mentioning only 'l'") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(2, 'l', 'H')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 2", "$valueWithIndent: 'l'")
                            containsNot(atLeast, "$valueWithIndent: 'H'")
                        }
                    }
                }
                it("${containsAtMostPair.first("'o', 'E', 'W', 'l'", "twice")} throws AssertionError mentioning 'l' and 'o'") {
                    expect {
                        fluentHelloWorld.containsAtMostIgnoringCaseFun(2, 'o', 'E', 'W', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 2", "$valueWithIndent: 'l'", "$valueWithIndent: 'o'")
                            containsNot(atLeast, "$valueWithIndent: 'E'", "$valueWithIndent: 'W'")
                        }
                    }
                }
                it("${containsAtMostPair.first("'x' and 'y' and 'z'", "twice")} throws AssertionError") {
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

                it("${containsAtMostPair.first("'o'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(2, 'o')
                }
                it("${containsAtMostIgnoringCasePair.first("'o'", "twice")} throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 'o' ignoring case (3)") {
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

                it("${containsAtMostPair.first("'o'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(3, 'o')
                }
                it("${containsAtMostPair.first("'o' and 'l'", "twice")} throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 'l' (3)") {
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
                it("${containsAtMostPair.first("'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(3, 'l')
                }
                it("${containsAtMostPair.first("'o' and 'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(3, 'o', 'l')
                }

            }
        }

        context("special cases") {
            context("string: \"\\0 hello\"") {
                it("${containsAtMostPair.first("\"hello\" and '\\0'", "twice")} does not throw") {
                    verbs.check(('\u0000' + " hello") as CharSequence).containsAtMostFun(2, "hello", 0.toChar())
                }
            }

            val aaaa: CharSequence = "aaaa"
            val aaaaFluent = verbs.check(aaaa)
            context("string \"$aaaa\""){
                it("${containsAtMostPair.first("'a'", "4 times")} does not throw") {
                    aaaaFluent.containsAtMostFun(4, 'a')
                }
                it("${containsAtMostPair.first("'a'", "3 times")} throws AssertionError") {
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
