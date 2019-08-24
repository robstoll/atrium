package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.AT_MOST
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceContainsNotOrAtMostAssertionsSpec(
    containsNotOrAtMostPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    containsNotOrAtMostIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    containsNotPair: Pair<String, (Int) -> String>,
    rootBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    val containsNotOrAtMost = containsNotOrAtMostPair.second
    val containsNotOrAtMostIgnoringCase = containsNotOrAtMostIgnoringCasePair.second

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        containsNotOrAtMost.forSubjectLess(2, 2.3, arrayOf()),
        containsNotOrAtMostIgnoringCase.forSubjectLess(2, 2.3, arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val fluent = expect(text as CharSequence)
    val fluentHelloWorld = expect(helloWorld as CharSequence)

    fun Expect<CharSequence>.containsNotOrAtMostFun(atLeast: Int, a: Any, vararg aX: Any) =
        containsNotOrAtMost(this, atLeast, a, aX)

    fun Expect<CharSequence>.containsNotOrAtMostIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any) =
        containsNotOrAtMostIgnoringCase(this, atLeast, a, aX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val valueWithIndent = "$indentBulletPoint$listBulletPoint$value"

    describeFun(containsNotOrAtMost.name, containsNotOrAtMostIgnoringCase.name) {

        context("throws an $illegalArgumentException") {
            val (containsNot, errorMsgContainsNot) = containsNotPair

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
            context("happy case with once") {
                it("${containsNotOrAtMostPair.first("'H'", "once")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(1, 'H')
                }
                it("${containsNotOrAtMostPair.first("'H' and 'e' and 'W'", "once")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(1, 'H', 'e', 'W')
                }
                it("${containsNotOrAtMostPair.first("'W' and 'H' and 'e'", "once")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(1, 'W', 'H', 'e')
                }
                it("${containsNotOrAtMostPair.first("'x' and 'y' and 'z'", "twice")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(2, 'x', 'y', 'z')
                }
                it("${containsNotOrAtMostIgnoringCasePair.first("'x' and 'y' and 'z'", "twice")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostIgnoringCaseFun(2, 'x', 'y', 'z')
                }
            }

            context("failing cases; search string at different positions") {
                it("${containsNotOrAtMostPair.first("'l'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostFun(1, 'l')
                    }.toThrow<AssertionError> { messageContains("$atMost: 1", "$valueWithIndent: 'l'") }
                }
                it("${containsNotOrAtMostPair.first("'H', 'l'", "once")} throws AssertionError mentioning only 'l'") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostFun(1, 'H', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 1", "$valueWithIndent: 'l'")
                            containsNot(atLeast, "$valueWithIndent: 'H'")
                        }
                    }
                }
                it("${containsNotOrAtMostPair.first("'l', 'H'", "once")} throws AssertionError mentioning only 'l'") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostFun(1, 'l', 'H')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 1", "$valueWithIndent: 'l'")
                            containsNot(atLeast, "$valueWithIndent: 'H'")
                        }
                    }
                }
                it(
                    "${containsNotOrAtMostPair.first(
                        "'o', 'E', 'W', 'l'",
                        "once"
                    )} throws AssertionError mentioning 'l' and 'o'"
                ) {
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
                it(
                    "${containsNotOrAtMostPair.first("'o'", "once")} throws AssertionError and " +
                        "message contains both, how many times we expected (1) and how many times it actually contained 'o' (2)"
                ) {
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

                it("${containsNotOrAtMostPair.first("'o'", "twice")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(2, 'o')
                }
                it("${containsNotOrAtMostIgnoringCasePair.first("'o'", "twice")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsNotOrAtMostIgnoringCaseFun(2, 'o')
                    }.toThrow<AssertionError> { messageContains(AT_MOST.getDefault()) }
                }

                it("${containsNotOrAtMostPair.first("'o'", "3 times")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(3, 'o')
                }
                it(
                    "${containsNotOrAtMostPair.first("'o' and 'l'", "twice")} throws AssertionError " +
                        "and message contains both, how many times we expected (2) and how many times it actually contained 'l' (3)"
                ) {
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
                it("${containsNotOrAtMostPair.first("'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(3, 'l')
                }
                it("${containsNotOrAtMostPair.first("'o' and 'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsNotOrAtMostFun(3, 'o', 'l')
                }

            }
        }
    }
})
