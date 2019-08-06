package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.AT_MOST
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceContainsNotOrAtMostAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsNotOrAtMostTriple: Triple<String, (String, String) -> String, Expect<CharSequence>.(Int, Any, Array<out Any>) -> Expect<CharSequence>>,
    containsNotOrAtMostIgnoringCaseTriple: Triple<String, (String, String) -> String, Expect<CharSequence>.(Int, Any, Array<out Any>) -> Expect<CharSequence>>,
    containsNotPair: Pair<String, (Int) -> String>,
    rootBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(object : SubjectLessSpec<CharSequence>(describePrefix,
        containsNotOrAtMostTriple.first to expectLambda { containsNotOrAtMostTriple.third(this, 2, 2.3, arrayOf()) },
        containsNotOrAtMostIgnoringCaseTriple.first to expectLambda { containsNotOrAtMostIgnoringCaseTriple.third(this, 2, 2.3, arrayOf()) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val fluent = verbs.check(text as CharSequence)
    val fluentHelloWorld = verbs.check(helloWorld as CharSequence)

    val (containsNotOrAtMost, containsNotOrAtMostTest, containsNotOrAtMostFunArr) = containsNotOrAtMostTriple
    fun Expect<CharSequence>.containsNotOrAtMostFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsNotOrAtMostFunArr(atLeast, a, aX)

    val (_, containsNotOrAtMostIgnoringCase, containsNotOrAtMostIgnoringCaseFunArr) = containsNotOrAtMostIgnoringCaseTriple
    fun Expect<CharSequence>.containsNotOrAtMostIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsNotOrAtMostIgnoringCaseFunArr(atLeast, a, aX)

    val (containsNot, errorMsgContainsNot) = containsNotPair

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val valueWithIndent = "$indentBulletPoint$listBulletPoint$value"

    describeFun(containsNotOrAtMost) {

        context("throws an $illegalArgumentException") {
            it("for not at all or at most -1 -- only positive numbers") {
                expect {
                    fluent.containsNotOrAtMostFun(-1, "")
                }.toThrow<IllegalArgumentException> { messageContains("positive number", -1) }
            }
            it("for not at all or at most 0 -- points to $containsNot") {
                expect {
                    fluent.containsNotOrAtMostFun(0, "")
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
            it("if an object is passed as first expected") {
                expect {
                    fluent.containsNotOrAtMostFun(1, fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            it("if an object is passed as second expected") {
                expect {
                    fluent.containsNotOrAtMostFun(1, "that's fine", fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
        }

        context("text '$helloWorld'") {
            context("happy case with $containsNotOrAtMost once") {
                it("${containsNotOrAtMostTest("'H'", "once")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(1, 'H')
                }
                it("${containsNotOrAtMostTest("'H' and 'e' and 'W'", "once")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(1, 'H', 'e', 'W')
                }
                it("${containsNotOrAtMostTest("'W' and 'H' and 'e'", "once")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(1, 'W', 'H', 'e')
                }
                it("${containsNotOrAtMostTest("'x' and 'y' and 'z'", "twice")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(2, 'x', 'y', 'z')
                }
                it("${containsNotOrAtMostIgnoringCase("'x' and 'y' and 'z'", "twice")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostIgnoringCaseFun(2, 'x', 'y', 'z')
                }
            }

            context("failing cases; search string at different positions") {
                it("${containsNotOrAtMostTest("'l'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostFun(1, 'l')
                    }.toThrow<AssertionError> { messageContains("$atMost: 1", "$valueWithIndent: 'l'") }
                }
                it("${containsNotOrAtMostTest("'H', 'l'", "once")} throws AssertionError mentioning only 'l'") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostFun(1, 'H', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 1", "$valueWithIndent: 'l'")
                            containsNot(atLeast, "$valueWithIndent: 'H'")
                        }
                    }
                }
                it("${containsNotOrAtMostTest("'l', 'H'", "once")} once throws AssertionError mentioning only 'l'") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostFun(1, 'l', 'H')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 1", "$valueWithIndent: 'l'")
                            containsNot(atLeast, "$valueWithIndent: 'H'")
                        }
                    }
                }
                it("${containsNotOrAtMostTest("'o', 'E', 'W', 'l'", "once")} throws AssertionError mentioning 'l' and 'o'") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostIgnoringCaseFun(1, 'o', 'E', 'W', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 1", "$valueWithIndent: 'l'", "$valueWithIndent: 'o'")
                            containsNot(atLeast, "$valueWithIndent: 'E'", "$valueWithIndent: 'W'")
                        }
                    }
                }
            }

            context("multiple occurrences of the search string") {
                it("${containsNotOrAtMostTest("'o'", "once")} throws AssertionError and message contains both, how many times we expected (1) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostFun(1, 'o')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsDescr: $separator" +
                                    "$valueWithIndent: 'o'",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$atMost: 1")
                        }
                    }
                }

                it("${containsNotOrAtMostTest("'o'", "twice")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(2, 'o')
                }
                it("${containsNotOrAtMostIgnoringCase("'o'", "twice")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostIgnoringCaseFun(2, 'o')
                    }.toThrow<AssertionError> { messageContains(AT_MOST.getDefault()) }
                }

                it("${containsNotOrAtMostTest("'o'", "3 times")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(3, 'o')
                }
                it("${containsNotOrAtMostTest("'o' and 'l'", "twice")} throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 'l' (3)") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostFun(2, 'o', 'l')
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
                it("${containsNotOrAtMostTest("'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(3, 'l')
                }
                it("${containsNotOrAtMostTest("'o' and 'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(3, 'o', 'l')
                }

            }
        }
    }
})
