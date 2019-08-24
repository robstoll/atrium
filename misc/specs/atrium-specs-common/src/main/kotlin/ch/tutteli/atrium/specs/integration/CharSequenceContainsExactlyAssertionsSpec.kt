package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.EXACTLY
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceContainsExactlyAssertionsSpec(
    containsExactlyPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    containsExactlyIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    containsNotPair: Pair<String, (Int) -> String>,
    rootBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    val containsExactly = containsExactlyPair.second
    val containsExactlyIgnoringCase = containsExactlyIgnoringCasePair.second

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        containsExactly.forSubjectLess(2, 2.3, arrayOf()),
        containsExactlyIgnoringCase.forSubjectLess(2, 2.3, arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val fluent = expect(text as CharSequence)
    val fluentHelloWorld = expect(helloWorld as CharSequence)

    fun Expect<CharSequence>.containsExactlyFun(atLeast: Int, a: Any, vararg aX: Any) =
        containsExactly(this, atLeast, a, aX)

    fun Expect<CharSequence>.containsExactlyIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any) =
        containsExactlyIgnoringCase(this, atLeast, a, aX)

    val exactly = EXACTLY.getDefault()
    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val valueWithIndent = "$indentBulletPoint$listBulletPoint$value"

    describeFun(containsExactly.name, containsExactlyIgnoringCase.name) {

        context("throws an $illegalArgumentException") {
            val (containsNot, errorMsgContainsNot) = containsNotPair

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
                it("${containsExactlyPair.first("'H'", "once")} does not throw") {
                    fluentHelloWorld.containsExactlyFun(1, 'H')
                }
                it("${containsExactlyPair.first("'H' and 'e' and 'W'", "once")} does not throw") {
                    fluentHelloWorld.containsExactlyFun(1, 'H', 'e', 'W')
                }
                it("${containsExactlyPair.first("'W' and 'H' and 'e'", "once")} does not throw") {
                    fluentHelloWorld.containsExactlyFun(1, 'W', 'H', 'e')
                }
            }

            context("failing cases; search string at different positions with $containsExactly once") {
                it("${containsExactlyPair.first("'h'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(1, 'h')
                    }.toThrow<AssertionError> { messageContains("$exactly: 1", "$valueWithIndent: 'h'") }
                }
                it("${containsExactlyIgnoringCasePair.first("'h'", "once")} throws AssertionError") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(1, 'h')
                }

                it("${containsExactlyPair.first("'H', 'E'", "once")} throws AssertionError mentioning only 'E'") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(1, 'H', 'E')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$exactly: 1", "$valueWithIndent: 'E'")
                            containsNot("$valueWithIndent: 'H'")
                        }
                    }
                }
                it("${containsExactlyIgnoringCasePair.first("'H', 'E'", "once")} throws AssertionError") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(1, 'H', 'E')
                }

                it("${containsExactlyPair.first("'E', 'H'", "once")} throws AssertionError mentioning only 'E'") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(1, 'E', 'H')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$exactly: 1", "$valueWithIndent: 'E'")
                            containsNot("$valueWithIndent: 'H'")
                        }
                    }
                }
                it("${containsExactlyIgnoringCasePair.first("'E', 'H'", "once")} throws AssertionError") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(1, 'E', 'H')
                }

                it("${containsExactlyPair.first("'H' and 'E' and 'w'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(1, 'H', 'E', 'w')
                    }.toThrow<AssertionError> { messageContains(exactly, 'E', 'w') }
                }
                it("${containsExactlyIgnoringCasePair.first("'H' and 'E' and 'w'", "once")} throws AssertionError") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(1, 'H', 'E', 'w')
                }
            }

            context("multiple occurrences of the search string") {
                it("${containsExactlyPair.first("'o'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.containsExactlyFun(1, 'o')
                    }.toThrow<AssertionError> { messageContains("$exactly: 1", "$valueWithIndent: 'o'") }
                }
                it("${containsExactlyPair.first("'o'", "twice")} does not throw") {
                    fluentHelloWorld.containsExactlyFun(2, 'o')
                }
                it("${containsExactlyIgnoringCasePair.first("'o'", "twice")} throws") {
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

                it(
                    "${containsExactlyPair.first("'o'", "3 times")} throws AssertionError and message contains both, " +
                        "how many times we expected (3) and how many times it actually contained 'o' (2)"
                ) {
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
                it("${containsExactlyIgnoringCasePair.first("'o'", "3 times")} does not throw") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(3, 'o')
                }
                it("${containsExactlyIgnoringCasePair.first("'o' and 'o'", "3 times")} does not throw") {
                    fluentHelloWorld.containsExactlyIgnoringCaseFun(3, 'o', 'o')
                }

                it("${containsExactlyPair.first("'o' and 'l'", "twice")} throws AssertionError") {
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
                it("${containsExactlyPair.first("'l'", "3 times")} does not throw") {
                    fluentHelloWorld.containsExactlyFun(3, 'l')
                }
                it(
                    "${containsExactlyPair.first(
                        "'o' and 'l'",
                        "3 times"
                    )} throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 'o' (2)"
                ) {
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
