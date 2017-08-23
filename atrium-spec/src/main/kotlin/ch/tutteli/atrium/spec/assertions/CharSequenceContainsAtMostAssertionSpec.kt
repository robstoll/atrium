package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

abstract class CharSequenceContainsAtMostAssertionSpec(
    verbs: IAssertionVerbFactory,
    containsAtMostPair: Triple<String, (String, String) -> String, IAssertionPlant<CharSequence>.(Int, Any, Array<out Any>) -> IAssertionPlant<CharSequence>>,
    containsAtMostIgnoringCasePair: Pair<(String, String) -> String, IAssertionPlant<CharSequence>.(Int, Any, Array<out Any>) -> IAssertionPlant<CharSequence>>,
    containsNotPair: Pair<String, (Int) -> String>
) : CharSequenceContainsSpecBase({

    val assert: (CharSequence) -> IAssertionPlant<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(text)
    val fluentHelloWorld = assert(helloWorld)

    val (containsAtMost, containsAtMostTest, containsAtMostFunArr) = containsAtMostPair
    fun IAssertionPlant<CharSequence>.containsAtMostFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtMostFunArr(atLeast, a, aX)

    val (containsAtMostIgnoringCase, containsAtMostIgnoringCaseFunArr) = containsAtMostIgnoringCasePair
    fun IAssertionPlant<CharSequence>.containsAtMostIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtMostIgnoringCaseFunArr(atLeast, a, aX)

    val (containsNot, errorMsgContainsNot) = containsNotPair

    describe("fun $containsAtMost") {

        context("throws an $illegalArgumentException") {
            test("for at most -1 -- only positive numbers") {
                expect {
                    fluent.containsAtMostFun(-1, "")
                }.toThrow<IllegalArgumentException>().and.message.contains("positive number", -1)
            }
            test("for at most 0 -- points to $containsNot") {
                expect {
                    fluent.containsAtMostFun(0, "")
                }.toThrow<IllegalArgumentException>().and.message.toBe(errorMsgContainsNot(0))
            }
        }

        context("text '$helloWorld'") {
            group("happy case with $containsAtMost once") {
                test("${containsAtMostTest("'H'", "once")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(1, 'H')
                }
                test("${containsAtMostTest("'H' and 'e' and 'W'", "once")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(1, 'H', 'e', 'W')
                }
                test("${containsAtMostTest("'W' and 'H' and 'e'", "once")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(1, 'W', 'H', 'e')
                }
                test("${containsAtMostTest("'x' and 'y' and 'z'", "once")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(1, 'x', 'y', 'z')
                }
            }

            group("failing assertions; search string at different positions") {
                test("${containsAtMostTest("'l'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(1, 'l')
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(AT_MOST)
                }
                test("${containsAtMostTest("'H', 'l'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(1, 'H', 'l')
                    }.toThrow<AssertionError>().message.contains(AT_MOST.getDefault(), 'l')
                }
                test("${containsAtMostTest("'l', 'H'", "once")} once throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(1, 'l', 'H')
                    }.toThrow<AssertionError>().message.contains(AT_MOST.getDefault(), 'l')
                }
                test("${containsAtMostTest("'o', 'E', 'W', 'l'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(1, 'o', 'E', 'W', 'l')
                    }.toThrow<AssertionError>().message.contains(AT_MOST.getDefault(), 'o', 'l')
                }
            }

            group("multiple occurrences of the search string") {
                test("${containsAtMostTest("'o'", "once")} throws AssertionError and message contains both, how many times we expected (1) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(1, 'o')
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            CONTAINS.getDefault() + ": 'o'",
                            NUMBER_OF_OCCURRENCES.getDefault() + ": 2$separator"
                        )
                        endsWith(AT_MOST.getDefault() + ": 1")
                    }
                }

                test("${containsAtMostTest("'o'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(2, 'o')
                }
                test("${containsAtMostIgnoringCase("'o'", "twice")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtMostIgnoringCaseFun(2, 'o')
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(AT_MOST)
                }

                test("${containsAtMostTest("'o'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtMostFun(3, 'o')
                }
                test("${containsAtMostTest("'o' and 'l'", "twice")} throws AssertionError and message contains both, how many times we expected (2) and how many times it actually contained 'l' (3)") {
                    expect {
                        fluentHelloWorld.containsAtMostFun(2, 'o', 'l')
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            CONTAINS.getDefault() + ": 'l'",
                            NUMBER_OF_OCCURRENCES.getDefault() + ": 3$separator"
                        )
                        endsWith(AT_MOST.getDefault() + ": 2")
                        containsNot(CONTAINS.getDefault() + ": 'o'")
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
    }
})
