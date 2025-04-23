package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation.EXACTLY
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceToContainExactlyExpectationsSpec(
    toContainExactlyPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    toContainExactlyIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, Any, Array<out Any>>>,
    notToContainPair: Pair<String, (Int) -> String>,
    describePrefix: String = "[Atrium] "
) : CharSequenceToContainSpecBase({

    val toContainExactly = toContainExactlyPair.second
    val toContainExactlyIgnoringCase = toContainExactlyIgnoringCasePair.second

    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        toContainExactly.forSubjectLessTest(2, 2.3, arrayOf()),
        toContainExactlyIgnoringCase.forSubjectLessTest(2, 2.3, arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    fun Expect<CharSequence>.toContainExactlyFun(exactly: Int, a: Any, vararg aX: Any) =
        toContainExactly(this, exactly, a, aX)

    fun Expect<CharSequence>.toContainExactlyIgnoringCaseFun(exactly: Int, a: Any, vararg aX: Any) =
        toContainExactlyIgnoringCase(this, exactly, a, aX)

    val exactlyDescr = EXACTLY.getDefault()
    val valueWithIndent = "$indentRootBulletPoint$listBulletPoint$value"

    describeFun(toContainExactly.name, toContainExactlyIgnoringCase.name) {

        context("throws an $illegalArgumentException") {
            val (notToContain, errorMsgContainsNot) = notToContainPair

            it("for exactly -1 -- only positive numbers") {
                expect {
                    expect(text).toContainExactlyFun(-1, "")
                }.toThrow<IllegalArgumentException> { messageToContain("positive number", -1) }
            }
            it("for exactly 0 -- points to $notToContain") {
                expect {
                    expect(text).toContainExactlyFun(0, "")
                }.toThrow<IllegalArgumentException> { message { toEqual(errorMsgContainsNot(0)) } }
            }
            it("if an object is passed as first expected") {
                expect {
                    expect(text).toContainExactlyFun(1, expect(text))
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }
            it("if an object is passed as second expected") {
                expect {
                    expect(text).toContainExactlyFun(1, "that's fine", expect(text))
                }.toThrow<IllegalArgumentException> { messageToContain("CharSequence", "Number", "Char") }
            }
        }

        context("text 'aaaa'") {
            it("search for 'aa' finds 3 hits since we want non-disjoint matches") {
                expect("aaaa" as CharSequence).toContainExactlyFun(3, "aa")
            }        }

        context("text '$helloWorld'") {

            context("happy case with $toContainExactly once") {
                it("${toContainExactlyPair.first("'H'", "once")} does not throw") {
                    expect(helloWorld).toContainExactlyFun(1, 'H')
                }
                it("${toContainExactlyPair.first("'H' and 'e' and 'W'", "once")} does not throw") {
                    expect(helloWorld).toContainExactlyFun(1, 'H', 'e', 'W')
                }
                it("${toContainExactlyPair.first("'W' and 'H' and 'e'", "once")} does not throw") {
                    expect(helloWorld).toContainExactlyFun(1, 'W', 'H', 'e')
                }
            }

            context("failing cases; search string at different positions with $toContainExactly once") {
                it("${toContainExactlyPair.first("'h'", "once")} throws AssertionError") {
                    expect {
                        expect(helloWorld).toContainExactlyFun(1, 'h')
                    }.toThrow<AssertionError> { messageToContain("$exactlyDescr: 1", "$valueWithIndent: 'h'") }
                }
                it("${toContainExactlyIgnoringCasePair.first("'h'", "once")} throws AssertionError") {
                    expect(helloWorld).toContainExactlyIgnoringCaseFun(1, 'h')
                }

                it("${toContainExactlyPair.first("'H', 'E'", "once")} throws AssertionError mentioning only 'E'") {
                    expect {
                        expect(helloWorld).toContainExactlyFun(1, 'H', 'E')
                    }.toThrow<AssertionError> {
                        message {
                            toContain("$exactlyDescr: 1", "$valueWithIndent: 'E'")
                            notToContain("$valueWithIndent: 'H'")
                        }
                    }
                }
                it("${toContainExactlyIgnoringCasePair.first("'H', 'E'", "once")} throws AssertionError") {
                    expect(helloWorld).toContainExactlyIgnoringCaseFun(1, 'H', 'E')
                }

                it("${toContainExactlyPair.first("'E', 'H'", "once")} throws AssertionError mentioning only 'E'") {
                    expect {
                        expect(helloWorld).toContainExactlyFun(1, 'E', 'H')
                    }.toThrow<AssertionError> {
                        message {
                            toContain("$exactlyDescr: 1", "$valueWithIndent: 'E'")
                            notToContain("$valueWithIndent: 'H'")
                        }
                    }
                }
                it("${toContainExactlyIgnoringCasePair.first("'E', 'H'", "once")} throws AssertionError") {
                    expect(helloWorld).toContainExactlyIgnoringCaseFun(1, 'E', 'H')
                }

                it("${toContainExactlyPair.first("'H' and 'E' and 'w'", "once")} throws AssertionError") {
                    expect {
                        expect(helloWorld).toContainExactlyFun(1, 'H', 'E', 'w')
                    }.toThrow<AssertionError> { messageToContain(exactlyDescr, 'E', 'w') }
                }
                it("${toContainExactlyIgnoringCasePair.first("'H' and 'E' and 'w'", "once")} throws AssertionError") {
                    expect(helloWorld).toContainExactlyIgnoringCaseFun(1, 'H', 'E', 'w')
                }
            }

            context("multiple occurrences of the search string") {
                it("${toContainExactlyPair.first("'o'", "once")} throws AssertionError") {
                    expect {
                        expect(helloWorld).toContainExactlyFun(1, 'o')
                    }.toThrow<AssertionError> { messageToContain("$exactlyDescr: 1", "$valueWithIndent: 'o'") }
                }
                it("${toContainExactlyPair.first("'o'", "twice")} does not throw") {
                    expect(helloWorld).toContainExactlyFun(2, 'o')
                }
                it("${toContainExactlyIgnoringCasePair.first("'o'", "twice")} throws") {
                    expect {
                        expect(helloWorld).toContainExactlyIgnoringCaseFun(2, 'o')
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainIgnoringCase: $separator" +
                                    "$valueWithIndent: 'o'",
                                "$numberOfOccurrences: 3$separator"
                            )
                            toEndWith("$exactlyDescr: 2")
                        }
                    }
                }

                it(
                    "${toContainExactlyPair.first("'o'", "3 times")} throws AssertionError and message contains both, " +
                        "how many times we expected (3) and how many times it actually contained 'o' (2)"
                ) {
                    expect {
                        expect(helloWorld).toContainExactlyFun(3, 'o')
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainDescr: $separator" +
                                    "$valueWithIndent: 'o'",
                                "$numberOfOccurrences: 2$separator"
                            )
                            toEndWith("$exactlyDescr: 3")
                        }
                    }
                }
                it("${toContainExactlyIgnoringCasePair.first("'o'", "3 times")} does not throw") {
                    expect(helloWorld).toContainExactlyIgnoringCaseFun(3, 'o')
                }
                it("${toContainExactlyIgnoringCasePair.first("'o' and 'o'", "3 times")} does not throw") {
                    expect(helloWorld).toContainExactlyIgnoringCaseFun(3, 'o', 'o')
                }

                it("${toContainExactlyPair.first("'o' and 'l'", "twice")} throws AssertionError") {
                    expect {
                        expect(helloWorld).toContainExactlyFun(2, 'o', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainDescr: $separator" +
                                    "$valueWithIndent: 'l'",
                                "$numberOfOccurrences: 3$separator"
                            )
                            toEndWith("$exactlyDescr: 2")
                            notToContain("$valueWithIndent: 'o'")
                        }
                    }
                }
                it("${toContainExactlyPair.first("'l'", "3 times")} does not throw") {
                    expect(helloWorld).toContainExactlyFun(3, 'l')
                }
                it(
                    "${toContainExactlyPair.first(
                        "'o' and 'l'",
                        "3 times"
                    )} throws AssertionError and message contains both, how many times we expected (3) and how many times it actually contained 'o' (2)"
                ) {
                    expect {
                        expect(helloWorld).toContainExactlyFun(3, 'o', 'l')
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "$rootBulletPoint$toContainDescr: $separator" +
                                    "$valueWithIndent: 'o'",
                                "$numberOfOccurrences: 2$separator"
                            )
                            toEndWith("$exactlyDescr: 3")
                            notToContain("$valueWithIndent: 'l'")
                        }
                    }
                }
            }
        }
    }
})
