package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.AT_LEAST
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.AT_MOST
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

abstract class CharSequenceContainsAtLeastAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsAtLeastTriple: Triple<String, (String, String) -> String, Assert<CharSequence>.(Int, Any, Array<out Any>) -> Assert<CharSequence>>,
    containsAtLeastIgnoringCaseTriple: Triple<String, (String, String) -> String, Assert<CharSequence>.(Int, Any, Array<out Any>) -> Assert<CharSequence>>,
    containsAtLeastButAtMostTriple: Triple<String, (String, String, String) -> String, Assert<CharSequence>.(Int, Int, Any, Array<out Any>) -> Assert<CharSequence>>,
    containsAtLeastButAtMostIgnoringCaseTriple: Triple<String, (String, String, String) -> String, Assert<CharSequence>.(Int, Int, Any, Array<out Any>) -> Assert<CharSequence>>,
    containsNotPair: Pair<String, (Int) -> String>,
    exactlyPair: Pair<String, (Int) -> String>,
    errorMsgAtLeastButAtMost: (Int, Int) -> String,
    rootBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(object : SubjectLessAssertionSpec<CharSequence>(describePrefix,
        containsAtLeastTriple.first to mapToCreateAssertion { containsAtLeastTriple.third(this, 1, "2.3", arrayOf()) },
        containsAtLeastIgnoringCaseTriple.first to mapToCreateAssertion { containsAtLeastIgnoringCaseTriple.third(this, 1, 'a', arrayOf()) },
        containsAtLeastButAtMostTriple.first to mapToCreateAssertion { containsAtLeastButAtMostTriple.third(this, 1, 2, "aA", arrayOf()) },
        containsAtLeastButAtMostIgnoringCaseTriple.first to mapToCreateAssertion { containsAtLeastButAtMostIgnoringCaseTriple.third(this, 1, 2, 2.3, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<String>(verbs, describePrefix,
        checkingTriple(containsAtLeastTriple.first, { containsAtLeastTriple.third(this, 1, "2.3", arrayOf()) }, "string with 2.3", "string with 0.0"),
        checkingTriple(containsAtLeastIgnoringCaseTriple.first, { containsAtLeastIgnoringCaseTriple.third(this, 1, 'a', arrayOf()) }, "a", "bbb"),
        checkingTriple(containsAtLeastButAtMostTriple.first, { containsAtLeastButAtMostTriple.third(this, 1, 2, "aa", arrayOf()) }, "aaa", "aaaa"),
        checkingTriple(containsAtLeastButAtMostIgnoringCaseTriple.first, { containsAtLeastButAtMostIgnoringCaseTriple.third(this, 1, 2, "aA", arrayOf()) }, "aaa", "aaaa")
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (CharSequence) -> Assert<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(text)
    val fluentHelloWorld = assert(helloWorld)

    val (containsAtLeast, containsAtLeastTest, containsAtLeastFunArr) = containsAtLeastTriple
    fun Assert<CharSequence>.containsAtLeastFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtLeastFunArr(atLeast, a, aX)

    val (_, containsAtLeastIgnoringCase, containsAtLeastIgnoringCaseFunArr) = containsAtLeastIgnoringCaseTriple
    fun Assert<CharSequence>.containsAtLeastIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtLeastIgnoringCaseFunArr(atLeast, a, aX)

    val (containsAtLeastButAtMost, containsAtLeastButAtMostTest, containsAtLeastButAtMostFunArr) = containsAtLeastButAtMostTriple
    fun Assert<CharSequence>.containsAtLeastButAtMostFun(atLeast: Int, atMost: Int, a: Any, vararg aX: Any)
        = containsAtLeastButAtMostFunArr(atLeast, atMost, a, aX)

    val (_, containsAtLeastButAtMostIgnoringCase, containsAtLeastButAtMostIgnoringCaseFunArr) = containsAtLeastButAtMostIgnoringCaseTriple
    fun Assert<CharSequence>.containsAtLeastButAtMostIgnoringCaseFun(atLeast: Int, atMost: Int, a: Any, vararg aX: Any)
        = containsAtLeastButAtMostIgnoringCaseFunArr(atLeast, atMost, a, aX)

    val (containsNot, errorMsgContainsNot) = containsNotPair
    val (exactly, errorMsgExactly) = exactlyPair

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val valueWithIndent = "$indentBulletPoint$listBulletPoint$value"

    describeFun(containsAtLeast, containsAtLeastButAtMost) {
        context("throws an $illegalArgumentException") {
            test("for at least -1 -- only positive numbers") {
                expect {
                    fluent.containsAtLeastFun(-1, "")
                }.toThrow<IllegalArgumentException> { messageContains("positive number", -1) }
            }
            test("for at least 0 -- points to $containsNot") {
                expect {
                    fluent.containsAtLeastFun(0, "")
                }.toThrow<IllegalArgumentException> { message { toBe(errorMsgContainsNot(0)) } }
            }
            test("if an object is passed as first expected") {
                expect {
                    fluent.containsAtLeastFun(1, fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            test("if an object is passed as second expected") {
                expect {
                    fluent.containsAtLeastFun(1, "that's fine", fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }

            group("using $containsAtLeastButAtMost") {
                test("for at least 1 but at most -1 -- since -1 is smaller than 1") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, -1, "")
                    }.toThrow<IllegalArgumentException> { message { toBe(errorMsgAtLeastButAtMost(1, -1)) } }
                }
                test("for at least 1 but at most 0 -- since 0 is smaller than 1") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, 0, "")
                    }.toThrow<IllegalArgumentException> { message { toBe(errorMsgAtLeastButAtMost(1, 0)) } }
                }
                test("for at least 2 but at most 1 -- since 1 is smaller than 2") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(2, 1, "")
                    }.toThrow<IllegalArgumentException> { message { toBe(errorMsgAtLeastButAtMost(2, 1)) } }
                }
                test("for at least 1 but at most 1 -- points to $exactly") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, 1, "")
                    }.toThrow<IllegalArgumentException> { message { toBe(errorMsgExactly(1)) } }
                }
                test("if an object is passed as first expected") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, 2, fluent)
                    }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
                }
                test("if an object is passed as second expected") {
                    expect {
                        fluent.containsAtLeastButAtMostFun(1, 2, "that's fine", fluent)
                    }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
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

            group("failing cases; search string at different positions with $containsAtLeast once") {
                test("${containsAtLeastTest("'h'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(1, 'h')
                    }.toThrow<AssertionError> { message { containsDefaultTranslationOf(AT_LEAST) } }
                }
                test("${containsAtLeastIgnoringCase("'h'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(1, 'h')
                }

                test("${containsAtLeastTest("'H', 'E'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(1, 'H', 'E')
                    }.toThrow<AssertionError> { messageContains(atLeast, 'E') }
                }
                test("${containsAtLeastIgnoringCase("'H', 'E'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(1, 'H', 'E')
                }

                test("${containsAtLeastTest("'E', 'H'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(1, 'E', 'H')
                    }.toThrow<AssertionError> { messageContains(atLeast, 'E') }
                }
                test("${containsAtLeastIgnoringCase("'E', 'H'", "once")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(1, 'E', 'H')
                }

                test("${containsAtLeastTest("'H', 'E', 'w' and 'r'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsAtLeastFun(1, 'H', 'E', 'w', 'r')
                    }.toThrow<AssertionError> { messageContains(atLeast, 'E', 'w') }
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
                test("${containsAtLeastIgnoringCase("'o' and 'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastIgnoringCaseFun(3, 'o', 'l')
                }
            }

            group("using $containsAtLeastButAtMost") {
                test("${containsAtLeastButAtMostTest("'o'", "once", "twice")} does not throw") {
                    fluentHelloWorld.containsAtLeastButAtMostFun(1, 2, 'o')
                }
                test("${containsAtLeastButAtMostTest("'o' and 'l'", "once", "twice")} throws AssertionError and message contains both, at most: 2 and how many times it actually contained 'l' (3)") {
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
                            containsNot("$valueWithIndent: 'o'")
                            containsNotDefaultTranslationOf(AT_LEAST)
                        }
                    }
                }
                test("${containsAtLeastButAtMostTest("'o' and 'l'", "twice", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastButAtMostFun(2, 3, 'o', 'l')
                }
                test("${containsAtLeastButAtMostIgnoringCase("'o' and 'l'", "twice", "3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastButAtMostIgnoringCaseFun(2, 3, 'o', 'l')
                }

                test("${containsAtLeastButAtMostTest("'o' and 'l'", "3 times", "4 times")} throws AssertionError and message contains both, at least: 3 and how many times it actually contained 'o' (2)") {
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
                            containsNot("$valueWithIndent: 'l'")
                            containsNotDefaultTranslationOf(AT_MOST)
                        }
                    }
                }
                test("${containsAtLeastIgnoringCase("'o' and 'l'", " 3 times")} does not throw") {
                    fluentHelloWorld.containsAtLeastButAtMostIgnoringCaseFun(3, 4, 'o', 'l')
                }
            }
        }
    }
})
