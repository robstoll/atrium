package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.EXACTLY
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceContainsExactlyAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsExactlyTriple: Triple<String, (String, String) -> String, Expect<CharSequence>.(Int, Any, Array<out Any>) -> Expect<CharSequence>>,
    containsExactlyIgnoringCaseTriple: Triple<String, (String, String) -> String, Expect<CharSequence>.(Int, Any, Array<out Any>) -> Expect<CharSequence>>,
    containsNotPair: Pair<String, (Int) -> String>,
    rootBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(object : SubjectLessSpec<CharSequence>(describePrefix,
        containsExactlyTriple.first to expectLambda { containsExactlyTriple.third(this, 2, 2.3, arrayOf()) },
        containsExactlyIgnoringCaseTriple.first to expectLambda { containsExactlyIgnoringCaseTriple.third(this, 2, 2.3, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<CharSequence>(verbs, describePrefix,
        checkingTriple(containsExactlyTriple.first, { containsExactlyTriple.third(this, 2, 2.3, arrayOf()) }, "2.3 / 2.3" as CharSequence, "2.3"),
        checkingTriple(containsExactlyIgnoringCaseTriple.first, { containsExactlyIgnoringCaseTriple.third(this, 2, 2.3, arrayOf()) }, "2.3 / 2.3" as CharSequence, "2.3")
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val fluent = verbs.check(text as CharSequence)
    val fluentHelloWorld = verbs.check(helloWorld as CharSequence)

    val (containsExactly, containsExactlyTest, containsExactlyFunArr) = containsExactlyTriple
    fun Expect<CharSequence>.containsExactlyFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsExactlyFunArr(atLeast, a, aX)

    val (_, containsExactlyIgnoringCase, containsExactlyIgnoringCaseFunArr) = containsExactlyIgnoringCaseTriple
    fun Expect<CharSequence>.containsExactlyIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsExactlyIgnoringCaseFunArr(atLeast, a, aX)

    val (containsNot, errorMsgContainsNot) = containsNotPair

    val exactly = EXACTLY.getDefault()
    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val valueWithIndent = "$indentBulletPoint$listBulletPoint$value"

    describeFun(containsExactly) {
        context("throws an $illegalArgumentException") {
            it("for exactly -1 -- only positive numbers") {
                expect {
                    fluent.containsExactlyFun(-1, "")
                }.toThrow<IllegalArgumentException> { messageContains("positive number", -1) }
            }
            it("for exactly 0 -- points to $containsNot") {
                expect {
                    fluent.containsExactlyFun(0, "")
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
            it("if an object is passed as first expected") {
                expect {
                    fluent.containsExactlyFun(1, fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            it("if an object is passed as second expected") {
                expect {
                    fluent.containsExactlyFun(1, "that's fine", fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
        }

        context("text '$helloWorld'") {

            context("happy case with $containsExactly once") {
                it("${containsExactlyTest("'H'", "once")} does not throw") {
                    fluentHelloWorld.containsExactlyFun(1, 'H')
                }
                it("${containsExactlyTest("'H' and 'e' and 'W'", "once")} does not throw") {
                    fluentHelloWorld.containsExactlyFun(1, 'H', 'e', 'W')
                }
                it("${containsExactlyTest("'W' and 'H' and 'e'", "once")} does not throw") {
                    fluentHelloWorld.containsExactlyFun(1, 'W', 'H', 'e')
                }
            }

            context("failing cases; search string at different positions with $containsExactly once") {
                it("${containsExactlyTest("'h'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(1, 'h')
                    }.toThrow<AssertionError> { messageContains("$exactly: 1", "$valueWithIndent: 'h'") }
                }
                it("${containsExactlyIgnoringCase("'h'", "once")} throws AssertionError") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(1, 'h')
                }

                it("${containsExactlyTest("'H', 'E'", "once")} throws AssertionError mentioning only 'E'") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(1, 'H', 'E')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$exactly: 1", "$valueWithIndent: 'E'")
                            containsNot("$valueWithIndent: 'H'")
                        }
                    }
                }
                it("${containsExactlyIgnoringCase("'H', 'E'", "once")} throws AssertionError") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(1, 'H', 'E')
                }

                it("${containsExactlyTest("'E', 'H'", "once")} throws AssertionError mentioning only 'E'") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(1, 'E', 'H')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$exactly: 1", "$valueWithIndent: 'E'")
                            containsNot("$valueWithIndent: 'H'")
                        }
                    }
                }
                it("${containsExactlyIgnoringCase("'E', 'H'", "once")} throws AssertionError") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(1, 'E', 'H')
                }

                it("${containsExactlyTest("'H' and 'E' and 'w'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(1, 'H', 'E', 'w')
                    }.toThrow<AssertionError> { messageContains(exactly, 'E', 'w') }
                }
                it("${containsExactlyIgnoringCase("'H' and 'E' and 'w'", "once")} throws AssertionError") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(1, 'H', 'E', 'w')
                }
            }

            context("multiple occurrences of the search string") {
                it("${containsExactlyTest("'o'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(1, 'o')
                    }.toThrow<AssertionError> { messageContains("$exactly: 1", "$valueWithIndent: 'o'") }
                }
                it("${containsExactlyTest("'o'", "twice")} does not throw") {
                    fluentHelloWorld.containsExactlyFun(2, 'o')
                }
                it("${containsExactlyIgnoringCase("'o'", "twice")} throws") {
                    expect {
                        fluentHelloWorld.containsExactlyIgnoringCaseFun(2, 'o')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsIgnoringCase: $separator" +
                                    "$valueWithIndent: 'o'",
                                "$numberOfOccurrences: 3$separator"
                            )
                            endsWith("$exactly: 2")
                        }
                    }
                }

                it("${containsExactlyTest("'o'", "3 times")} throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(3, 'o')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsDescr: $separator" +
                                    "$valueWithIndent: 'o'",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$exactly: 3")
                        }
                    }
                }
                it("${containsExactlyIgnoringCase("'o'", "3 times")} does not throw") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(3, 'o')
                }
                it("${containsExactlyIgnoringCase("'o' and 'o'", "3 times")} does not throw") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(3, 'o', 'o')
                }

                it("${containsExactlyTest("'o' and 'l'", "twice")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(2, 'o', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsDescr: $separator" +
                                    "$valueWithIndent: 'l'",
                                "$numberOfOccurrences: 3$separator"
                            )
                            endsWith("$exactly: 2")
                            containsNot("$valueWithIndent: 'o'")
                        }
                    }
                }
                it("${containsExactlyTest("'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsExactlyFun(3, 'l')
                }
                it("${containsExactlyTest("'o' and 'l'", "3 times")} throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(3, 'o', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$containsDescr: $separator" +
                                    "$valueWithIndent: 'o'",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$exactly: 3")
                            containsNot("$valueWithIndent: 'l'")
                        }
                    }
                }
            }
        }
    }
})
