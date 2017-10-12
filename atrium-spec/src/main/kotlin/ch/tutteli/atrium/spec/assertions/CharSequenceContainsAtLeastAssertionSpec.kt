package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

abstract class CharSequenceContainsAtLeastAssertionSpec(
    verbs: IAssertionVerbFactory,
    containsAtLeastPair: Triple<String, (String, String) -> String, IAssertionPlant<CharSequence>.(Int, Any, Array<out Any>) -> IAssertionPlant<CharSequence>>,
    containsAtLeastIgnoringCasePair: Pair<(String, String) -> String, IAssertionPlant<CharSequence>.(Int, Any, Array<out Any>) -> IAssertionPlant<CharSequence>>,
    containsAtLeastButAtMostPair: Triple<String, (String, String, String) -> String, IAssertionPlant<CharSequence>.(Int, Int, Any, Array<out Any>) -> IAssertionPlant<CharSequence>>,
    containsAtLeastButAtMostIgnoringCasePair: Pair<(String, String, String) -> String, IAssertionPlant<CharSequence>.(Int, Int, Any, Array<out Any>) -> IAssertionPlant<CharSequence>>,
    containsNotPair: Pair<String, (Int) -> String>,
    exactlyPair: Pair<String, (Int) -> String>,
    errorMsgAtLeastButAtMost: (Int, Int) -> String
) : CharSequenceContainsSpecBase({

    val assert: (CharSequence) -> IAssertionPlant<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(text)
    val fluentHelloWorld = assert(helloWorld)

    val (containsAtLeast, containsAtLeastTest, containsAtLeastFunArr) = containsAtLeastPair
    fun IAssertionPlant<CharSequence>.containsAtLeastFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtLeastFunArr(atLeast, a, aX)

    val (containsAtLeastIgnoringCase, containsAtLeastIgnoringCaseFunArr) = containsAtLeastIgnoringCasePair
    fun IAssertionPlant<CharSequence>.containsAtLeastIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtLeastIgnoringCaseFunArr(atLeast, a, aX)

    val (containsAtLeastButAtMost, containsAtLeastButAtMostTest, containsAtLeastButAtMostFunArr) = containsAtLeastButAtMostPair
    fun IAssertionPlant<CharSequence>.containsAtLeastButAtMostFun(atLeast: Int, atMost: Int, a: Any, vararg aX: Any)
        = containsAtLeastButAtMostFunArr(atLeast, atMost, a, aX)

    val (containsAtLeastButAtMostIgnoringCase, containsAtLeastButAtMostIgnoringCaseFunArr) = containsAtLeastButAtMostIgnoringCasePair
    fun IAssertionPlant<CharSequence>.containsAtLeastButAtMostIgnoringCaseFun(atLeast: Int, atMost: Int, a: Any, vararg aX: Any)
        = containsAtLeastButAtMostIgnoringCaseFunArr(atLeast, atMost, a, aX)

    val (containsNot, errorMsgContainsNot) = containsNotPair
    val (exactly, errorMsgExactly) = exactlyPair

    describe("fun $containsAtLeast (and sometimes $containsAtLeastButAtMost)") {
        context("throws an $illegalArgumentException") {
            test("for at least -1 -- only positive numbers") {
                expect {
                    fluent.containsAtLeastFun(-1, "")
                }.toThrow<IllegalArgumentException>().and.message.contains("positive number", -1)
            }
            test("for at least 0 -- points to $containsNot") {
                expect {
                    fluent.containsAtLeastFun(0, "")
                }.toThrow<IllegalArgumentException>().and.message.toBe(errorMsgContainsNot(0))
            }
            group("using $containsAtLeastButAtMost") {
                test("for at least 1 but at most -1 -- since -1 is smaller than 1") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, -1, "")
                    }.toThrow<IllegalArgumentException>().and.message.toBe(errorMsgAtLeastButAtMost(1, -1))
                }
                test("for at least 1 but at most 0 -- since 0 is smaller than 1") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, 0, "")
                    }.toThrow<IllegalArgumentException>().and.message.toBe(errorMsgAtLeastButAtMost(1, 0))
                }
                test("for at least 2 but at most 1 -- since 1 is smaller than 2") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(2, 1, "")
                    }.toThrow<IllegalArgumentException>().and.message.toBe(errorMsgAtLeastButAtMost(2, 1))
                }
                test("for at least 1 but at most 1 -- points to $exactly") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, 1, "")
                    }.toThrow<IllegalArgumentException>().and.message.toBe(errorMsgExactly(1))
                }
            }
        }

        context("text '$helloWorld'") {

            group("happy case with $containsAtLeast once") {
                test("${containsAtLeastTest("'H'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(1, 'H')
                }
                test("${containsAtLeastTest("'H' and 'e' and 'W'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(1, 'H', 'e', 'W')
                }
                test("${containsAtLeastTest("'W' and 'H' and 'e'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(1, 'W', 'H', 'e')
                }
            }

            group("failing assertions; search string at different positions with $containsAtLeast once") {
                test("${containsAtLeastTest("'h'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(1, 'h')
                    }.toThrow<AssertionError>().message.containsDefaultTranslationOf(AT_LEAST)
                }
                test("${containsAtLeastIgnoringCase("'h'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(1, 'h')
                }

                test("${containsAtLeastTest("'H', 'E'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(1, 'H', 'E')
                    }.toThrow<AssertionError>().message.contains(atLeast, 'E')
                }
                test("${containsAtLeastIgnoringCase("'H', 'E'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(1, 'H', 'E')
                }

                test("${containsAtLeastTest("'E', 'H'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(1, 'E', 'H')
                    }.toThrow<AssertionError>().message.contains(atLeast, 'E')
                }
                test("${containsAtLeastIgnoringCase("'E', 'H'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(1, 'E', 'H')
                }

                test("${containsAtLeastTest("'H', 'E', 'w' and 'r'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(1, 'H', 'E', 'w', 'r')
                    }.toThrow<AssertionError>().message.contains(atLeast, 'E', 'w')
                }
                test("${containsAtLeastIgnoringCase("'H', 'E', 'w' and 'r'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(1, 'H', 'E', 'w', 'r')
                }
            }

            group("multiple occurrences of the search string") {
                test("${containsAtLeastTest("'o'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(1, 'o')
                }
                test("${containsAtLeastTest("'o'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(2, 'o')
                }

                test("${containsAtLeastTest("'o'", "3 times")} throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(3, 'o')
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            "$containsDescr: 'o'",
                            "$numberOfOccurrences: 2$separator"
                        )
                        endsWith("$atLeast: 3")
                    }
                }
                test("${containsAtLeastIgnoringCase("'o'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(3, 'o')
                }

                test("${containsAtLeastTest("'o' and 'l'", "twice")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(2, 'o', 'l')
                }
                test("${containsAtLeastTest("'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastFun(3, 'l')
                }

                test("${containsAtLeastTest("'o' and 'l'", "3 times")} throws AssertionError and message contains both, at least: 3 and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(3, 'o', 'l')
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            "$containsDescr: 'o'",
                            "$numberOfOccurrences: 2$separator"
                        )
                        endsWith("$atLeast: 3")
                        containsNot("$containsDescr 'l'")
                    }
                }
                test("${containsAtLeastIgnoringCase("'o' and 'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(3, 'o', 'l')
                }
            }

            group("using $containsAtLeastButAtMost") {
                test("${containsAtLeastButAtMostTest("'o'", "once", "twice")} does not throw") {
                    fluentHelloWorld.contains.atLeast(1).butAtMost(2).value('o')
                }
                test("${containsAtLeastButAtMostTest("'o' and 'l'", "once", "twice")} throws AssertionError and message contains both, at most: 2 and how many times it actually contained 'l' (3)") {
                    expect {
                        fluentHelloWorld.contains.atLeast(1).butAtMost(2).values('o', 'l')
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            "$containsDescr: 'l'",
                            "$numberOfOccurrences: 3$separator"
                        )
                        endsWith("$atMost: 2")
                        containsNot("$containsDescr 'o'")
                        containsNotDefaultTranslationOf(AT_LEAST)
                    }
                }
                test("${containsAtLeastButAtMostTest("'o' and 'l'", "twice", "3 times")} does not throw") {
                    fluentHelloWorld.contains.atLeast(2).butAtMost(3).values('o', 'l')
                }
                test("${containsAtLeastButAtMostIgnoringCase("'o' and 'l'", "twice", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastButAtMostIgnoringCaseFun(2, 3, 'o', 'l')
                }

                test("${containsAtLeastButAtMostTest("'o' and 'l'", "3 times", "4 times")} throws AssertionError and message contains both, at least: 3 and how many times it actually contained 'o' (2)") {
                    expect {
                        fluentHelloWorld.contains.atLeast(3).butAtMost(4).values('o', 'l')
                    }.toThrow<AssertionError>().and.message {
                        contains(
                            "$containsDescr: 'o'",
                            "$numberOfOccurrences: 2$separator"
                        )
                        endsWith("$atLeast: 3")
                        containsNot("$containsDescr 'l'")
                        containsNotDefaultTranslationOf(AT_MOST)
                    }
                }
                test("${containsAtLeastIgnoringCase("'o' and 'l'", " 3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastButAtMostIgnoringCaseFun(3, 4, 'o', 'l')
                }
            }
        }
    }
})
