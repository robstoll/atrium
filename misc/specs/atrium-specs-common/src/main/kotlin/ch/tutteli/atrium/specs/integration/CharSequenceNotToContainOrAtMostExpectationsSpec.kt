package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.AT_MOST
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceNotToContainOrAtMostExpectationsSpec(
    notToContainOrAtMostPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    notToContainOrAtMostIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    notToContainPair: Pair<String, (Int) -> String>,
    describePrefix: String = "[Atrium] "
) : CharSequenceToContainSpecBase({

    val notToContainOrAtMost = notToContainOrAtMostPair.second
    val notToContainOrAtMostIgnoringCase = notToContainOrAtMostIgnoringCasePair.second

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        notToContainOrAtMost.forSubjectLess(2, 2.3, arrayOf()),
        notToContainOrAtMostIgnoringCase.forSubjectLess(2, 2.3, arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val fluent = expect(text as CharSequence)
    val fluentHelloWorld = expect(helloWorld as CharSequence)

    fun Expect<CharSequence>.notToContainOrAtMostFun(atLeast: Int, a: Any, vararg aX: Any) =
        notToContainOrAtMost(this, atLeast, a, aX)

    fun Expect<CharSequence>.notToContainOrAtMostIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any) =
        notToContainOrAtMostIgnoringCase(this, atLeast, a, aX)

    val valueWithIndent = "$indentRootBulletPoint$listBulletPoint$value"

    describeFun(notToContainOrAtMost.name, notToContainOrAtMostIgnoringCase.name) {

        context("throws an $illegalArgumentException") {
            val (notToContain, errorMsgContainsNot) = notToContainPair

            it("for not at all or at most -1 -- only positive numbers") {
                expect {
                    fluent.notToContainOrAtMostFun(-1, "")
                }.toThrow<IllegalArgumentException> { messageContains("positive number", -1) }
            }
            it("for not at all or at most 0 -- points to $notToContain") {
                expect {
                    fluent.notToContainOrAtMostFun(0, "")
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgContainsNot(0)) } }
            }
            it("if an object is passed as first expected") {
                expect {
                    fluent.notToContainOrAtMostFun(1, fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
            it("if an object is passed as second expected") {
                expect {
                    fluent.notToContainOrAtMostFun(1, "that's fine", fluent)
                }.toThrow<IllegalArgumentException> { messageContains("CharSequence", "Number", "Char") }
            }
        }

        context("text '$helloWorld'") {
            context("happy case with once") {
                it("${notToContainOrAtMostPair.first("'H'", "once")} does not throw") {
                    fluentHelloWorld.notToContainOrAtMostFun(1, 'H')
                }
                it("${notToContainOrAtMostPair.first("'H' and 'e' and 'W'", "once")} does not throw") {
                    fluentHelloWorld.notToContainOrAtMostFun(1, 'H', 'e', 'W')
                }
                it("${notToContainOrAtMostPair.first("'W' and 'H' and 'e'", "once")} does not throw") {
                    fluentHelloWorld.notToContainOrAtMostFun(1, 'W', 'H', 'e')
                }
                it("${notToContainOrAtMostPair.first("'x' and 'y' and 'z'", "twice")} does not throw") {
                    fluentHelloWorld.notToContainOrAtMostFun(2, 'x', 'y', 'z')
                }
                it("${notToContainOrAtMostIgnoringCasePair.first("'x' and 'y' and 'z'", "twice")} does not throw") {
                    fluentHelloWorld.notToContainOrAtMostIgnoringCaseFun(2, 'x', 'y', 'z')
                }
            }

            context("failing cases; search string at different positions") {
                it("${notToContainOrAtMostPair.first("'l'", "once")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.notToContainOrAtMostFun(1, 'l')
                    }.toThrow<AssertionError> { messageContains("$atMost: 1", "$valueWithIndent: 'l'") }
                }
                it("${notToContainOrAtMostPair.first("'H', 'l'", "once")} throws AssertionError mentioning only 'l'") {
                    expect {
                        fluentHelloWorld.notToContainOrAtMostFun(1, 'H', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 1", "$valueWithIndent: 'l'")
                            notToContain(atLeast, "$valueWithIndent: 'H'")
                        }
                    }
                }
                it("${notToContainOrAtMostPair.first("'l', 'H'", "once")} throws AssertionError mentioning only 'l'") {
                    expect {
                        fluentHelloWorld.notToContainOrAtMostFun(1, 'l', 'H')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 1", "$valueWithIndent: 'l'")
                            notToContain(atLeast, "$valueWithIndent: 'H'")
                        }
                    }
                }
                it(
                    "${notToContainOrAtMostPair.first(
                        "'o', 'E', 'W', 'l'",
                        "once"
                    )} throws AssertionError mentioning 'l' and 'o'"
                ) {
                    expect {
                        fluentHelloWorld.notToContainOrAtMostIgnoringCaseFun(1, 'o', 'E', 'W', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains("$atMost: 1", "$valueWithIndent: 'l'", "$valueWithIndent: 'o'")
                            notToContain(atLeast, "$valueWithIndent: 'E'", "$valueWithIndent: 'W'")
                        }
                    }
                }
            }

            context("multiple occurrences of the search string") {
                it(
                    "${notToContainOrAtMostPair.first("'o'", "once")} throws AssertionError and " +
                        "message contains both, how many times we expected (1) and how many times it actually contained 'o' (2)"
                ) {
                    expect {
                        fluentHelloWorld.notToContainOrAtMostFun(1, 'o')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$toContainDescr: $separator" +
                                    "$valueWithIndent: 'o'",
                                "$numberOfOccurrences: 2$separator"
                            )
                            endsWith("$atMost: 1")
                        }
                    }
                }

                it("${notToContainOrAtMostPair.first("'o'", "twice")} does not throw") {
                    fluentHelloWorld.notToContainOrAtMostFun(2, 'o')
                }
                it("${notToContainOrAtMostIgnoringCasePair.first("'o'", "twice")} throws AssertionError") {
                    expect {
                        fluentHelloWorld.notToContainOrAtMostIgnoringCaseFun(2, 'o')
                    }.toThrow<AssertionError> { messageContains(AT_MOST.getDefault()) }
                }

                it("${notToContainOrAtMostPair.first("'o'", "3 times")} does not throw") {
                    fluentHelloWorld.notToContainOrAtMostFun(3, 'o')
                }
                it(
                    "${notToContainOrAtMostPair.first("'o' and 'l'", "twice")} throws AssertionError " +
                        "and message contains both, how many times we expected (2) and how many times it actually contained 'l' (3)"
                ) {
                    expect {
                        fluentHelloWorld.notToContainOrAtMostFun(2, 'o', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                "$rootBulletPoint$toContainDescr: $separator" +
                                    "$valueWithIndent: 'l'",
                                "$numberOfOccurrences: 3$separator"
                            )
                            endsWith("$atMost: 2")
                            notToContain("$valueWithIndent: 'o'")
                        }
                    }
                }
                it("${notToContainOrAtMostPair.first("'l'", "3 times")} does not throw") {
                    fluentHelloWorld.notToContainOrAtMostFun(3, 'l')
                }
                it("${notToContainOrAtMostPair.first("'o' and 'l'", "3 times")} does not throw") {
                    fluentHelloWorld.notToContainOrAtMostFun(3, 'o', 'l')
                }

            }
        }
    }
})
