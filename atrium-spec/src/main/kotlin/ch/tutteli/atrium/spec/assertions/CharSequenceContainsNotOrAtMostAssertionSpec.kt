package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.AT_MOST
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

abstract class CharSequenceContainsNotOrAtMostAssertionSpec(
    verbs: IAssertionVerbFactory,
    containsNotOrAtMostTriple: Triple<String, (String, String) -> String, IAssertionPlant<CharSequence>.(Int, Any, Array<out Any>) -> IAssertionPlant<CharSequence>>,
    containsNotOrAtMostIgnoringCaseTriple: Triple<String, (String, String) -> String, IAssertionPlant<CharSequence>.(Int, Any, Array<out Any>) -> IAssertionPlant<CharSequence>>,
    containsNotPair: Pair<String, (Int) -> String>
) : CharSequenceContainsSpecBase({

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<CharSequence>(
        containsNotOrAtMostTriple.first to mapToCreateAssertion { containsNotOrAtMostTriple.third(this, 2, 2.3, arrayOf()) },
        containsNotOrAtMostIgnoringCaseTriple.first to mapToCreateAssertion { containsNotOrAtMostIgnoringCaseTriple.third(this, 2, 2.3, arrayOf()) }
    ) {})

    val assert: (CharSequence) -> IAssertionPlant<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(text)
    val fluentHelloWorld = assert(helloWorld)

    val (containsNotOrAtMost, containsNotOrAtMostTest, containsNotOrAtMostFunArr) = containsNotOrAtMostTriple
    fun IAssertionPlant<CharSequence>.containsNotOrAtMostFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsNotOrAtMostFunArr(atLeast, a, aX)

    val (_, containsNotOrAtMostIgnoringCase, containsNotOrAtMostIgnoringCaseFunArr) = containsNotOrAtMostIgnoringCaseTriple
    fun IAssertionPlant<CharSequence>.containsNotOrAtMostIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsNotOrAtMostIgnoringCaseFunArr(atLeast, a, aX)

    val (containsNot, errorMsgContainsNot) = containsNotPair

    describe("fun $containsNotOrAtMost") {

        context("throws an $illegalArgumentException") {
            test("for not at all or at most -1 -- only positive numbers") {
                expect {
                    fluent.containsNotOrAtMostFun(-1, "")
                }.toThrow<IllegalArgumentException>().and.message.contains("positive number", -1)
            }
            test("for not at all or at most 0 -- points to $containsNot") {
                expect {
                    fluent.containsNotOrAtMostFun(0, "")
                }.toThrow<IllegalArgumentException>().and.message.toBe(errorMsgContainsNot(0))
            }
        }

        context("text '$helloWorld'") {
            group("happy case with $containsNotOrAtMost once") {
                test("${containsNotOrAtMostTest("'H'", "once")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(1, 'H')
                }
                test("${containsNotOrAtMostTest("'H' and 'e' and 'W'", "once")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(1, 'H', 'e', 'W')
                }
                test("${containsNotOrAtMostTest("'W' and 'H' and 'e'", "once")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(1, 'W', 'H', 'e')
                }
                test("${containsNotOrAtMostTest("'x' and 'y' and 'z'", "once")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(1, 'x', 'y', 'z')
                }
            }

            group("failing assertions; search string at different positions") {
                test("${containsNotOrAtMostTest("'l'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostFun(1, 'l')
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(AT_MOST)
                }
                test("${containsNotOrAtMostTest("'H', 'l'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostFun(1, 'H', 'l')
                    }.toThrow<AssertionError>().message.contains(atMost, 'l')
                }
                test("${containsNotOrAtMostTest("'l', 'H'", "once")} once throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostFun(1, 'l', 'H')
                    }.toThrow<AssertionError>().message.contains(atMost, 'l')
                }
                test("${containsNotOrAtMostTest("'o', 'E', 'W', 'l'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostFun(1, 'o', 'E', 'W', 'l')
                    }.toThrow<AssertionError>().message.contains(atMost, 'o', 'l')
                }
            }

            group("multiple occurrences of the search string") {
                test("${containsNotOrAtMostTest("'o'", "once")} throws AssertionError and message contains both, how many times we expected (1) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostFun(1, 'o')
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            "$containsDescr: 'o'",
                            "$numberOfOccurrences: 2$separator"
                        )
                        endsWith("$atMost: 1")
                    }
                }

                test("${containsNotOrAtMostTest("'o'", "twice")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(2, 'o')
                }
                test("${containsNotOrAtMostIgnoringCase("'o'", "twice")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostIgnoringCaseFun(2, 'o')
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(AT_MOST)
                }

                test("${containsNotOrAtMostTest("'o'", "3 times")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(3, 'o')
                }
                test("${containsNotOrAtMostTest("'o' and 'l'", "twice")} throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 'l' (3)") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostFun(2, 'o', 'l')
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            "$containsDescr: 'l'",
                            "$numberOfOccurrences: 3$separator"
                        )
                        endsWith("$atMost: 2")
                        containsNot("$containsDescr 'o'")
                    }
                }
                test("${containsNotOrAtMostTest("'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(3, 'l')
                }
                test("${containsNotOrAtMostTest("'o' and 'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(3, 'o', 'l')
                }

            }
        }
    }
})
