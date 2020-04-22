// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.AT_MOST
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.include

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class CharSequenceContainsAtMostAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsAtMostTriple: Triple<String, (String, String) -> String, Assert<CharSequence>.(Int, Any, Array<out Any>) -> Assert<CharSequence>>,
    containsAtMostIgnoringCaseTriple: Triple<String, (String, String) -> String, Assert<CharSequence>.(Int, Any, Array<out Any>) -> Assert<CharSequence>>,
    containsNotPair: Pair<String, (Int) -> String>,
    exactlyPair: Pair<String, (Int) -> String>,
    rootBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<CharSequence>(describePrefix,
        containsAtMostTriple.first to mapToCreateAssertion { containsAtMostTriple.third(this, 2, 2.3, arrayOf()) },
        containsAtMostIgnoringCaseTriple.first to mapToCreateAssertion { containsAtMostIgnoringCaseTriple.third(this, 2, 2.3, arrayOf()) }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<String>(verbs, describePrefix,
        checkingTriple(containsAtMostTriple.first, { containsAtMostTriple.third(this, 2, 2.3, arrayOf()) }, "2.3 / 2.3", "2.3 / 2.3 / 2.3"),
        checkingTriple(containsAtMostIgnoringCaseTriple.first, { containsAtMostIgnoringCaseTriple.third(this, 2, 2.3, arrayOf()) }, "2.3 / 2.3", "2.3 / 2.3 / 2.3")
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (CharSequence) -> Assert<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(text)
    val fluentHelloWorld = assert(helloWorld)

    val (containsAtMost, containsAtMostTest, containsAtMostFunArr) = containsAtMostTriple
    fun Assert<CharSequence>.containsAtMostFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtMostFunArr(atLeast, a, aX)

    val (_, containsAtMostIgnoringCase, containsAtMostIgnoringCaseFunArr) = containsAtMostIgnoringCaseTriple
    fun Assert<CharSequence>.containsAtMostIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtMostIgnoringCaseFunArr(atLeast, a, aX)

    val (containsNot, errorMsgContainsNot) = containsNotPair
    val (exactly, errorMsgExactly) = exactlyPair

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val valueWithIndent = "$indentBulletPoint$listBulletPoint$value"

    describeFun(containsAtMost) {

        context("throws an $illegalArgumentException") {
            test("for at most -1 -- only positive numbers") {
                expect {
                    fluent.containsAtMostFun(-1, "")
                }.toThrow<IllegalArgumentException> { messageContains("positive number", -1) }
            }
            test("for at most 0 -- points to $containsNot") {
                expect {
                    fluent.containsAtMostFun(0, "")
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
            test("for at most 1 -- points to $exactly") {
                expect {
                    fluent.containsAtMostFun(1, "")
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgExactly(1)) } }
            }
            test("if an object is passed as first expected") {
                expect {
                    fluent.containsAtMostFun(2, fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            test("if an object is passed as second expected") {
                expect {
                    fluent.containsAtMostFun(2, "that's fine", fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
        }

        context("text '$helloWorld'") {
            group("happy case with $containsAtMost twice") {
                test("${containsAtMostTest("'H'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(2, 'H')
                }
                test("${containsAtMostTest("'H' and 'e' and 'W'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(2, 'H', 'e', 'W')
                }
                test("${containsAtMostTest("'W' and 'H' and 'e'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(2, 'W', 'H', 'e')
                }
            }

            group("failing cases; search string at different positions") {
                test("${containsAtMostTest("'l'", "twice")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(2, 'l')
                    }.toThrow<AssertionError> { messageContains("$atMost: 2", "$valueWithIndent: 'l'") }
                }
                test("${containsAtMostTest("'H', 'l'", "twice")} throws AssertionError mentioning only 'l'") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(2, 'H', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 2", "$valueWithIndent: 'l'")
                            containsNot(atLeast, "$valueWithIndent: 'H'")
                        }
                    }
                }
                test("${containsAtMostTest("'l', 'H'", "twice")} once throws AssertionError mentioning only 'l'") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(2, 'l', 'H')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 2", "$valueWithIndent: 'l'")
                            containsNot(atLeast, "$valueWithIndent: 'H'")
                        }
                    }
                }
                test("${containsAtMostTest("'o', 'E', 'W', 'l'", "twice")} throws AssertionError mentioning 'l' and 'o'") {
                    expect {
                        fluentHelloWorld.containsAtMostIgnoringCaseFun(2, 'o', 'E', 'W', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 2", "$valueWithIndent: 'l'", "$valueWithIndent: 'o'")
                            containsNot(atLeast, "$valueWithIndent: 'E'", "$valueWithIndent: 'W'")
                        }
                    }
                }
                test("${containsAtMostTest("'x' and 'y' and 'z'", "twice")} throws AssertionError") {
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

            group("multiple occurrences of the search string") {

                test("${containsAtMostTest("'o'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(2, 'o')
                }
                test("${containsAtMostIgnoringCase("'o'", "twice")} throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 'o' ignoring case (3)") {
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

                test("${containsAtMostTest("'o'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(3, 'o')
                }
                test("${containsAtMostTest("'o' and 'l'", "twice")} throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 'l' (3)") {
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
                test("${containsAtMostTest("'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(3, 'l')
                }
                test("${containsAtMostTest("'o' and 'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(3, 'o', 'l')
                }

            }
        }

        group("special cases") {
            given("string: \"\\0 hello\"") {
                test("${containsAtMostTest("\"hello\" and '\\0'", "twice")} does not throw") {
                    verbs.checkImmediately('\u0000' + " hello").containsAtMostFun(2, "hello", 0.toChar())
                }
            }

            val aaaa = "aaaa"
            val aaaaFluent = verbs.checkImmediately(aaaa)
            given("string \"$aaaa\""){
                test("${containsAtMostTest("'a'", "4 times")} does not throw") {
                    aaaaFluent.containsAtMostFun(4, 'a')
                }
                test("${containsAtMostTest("'a'", "3 times")} throws AssertionError") {
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
