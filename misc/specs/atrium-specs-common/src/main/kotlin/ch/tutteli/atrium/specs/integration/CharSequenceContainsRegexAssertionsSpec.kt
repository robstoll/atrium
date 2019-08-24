package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceContainsRegexAssertionsSpec(
    containsRegex: String,
    containsAtLeastPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>>,
    containsAtLeastIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>>,
    //TODO usually the shortcut is tested individually on a level lower and not expected as we did it here (search for ShortcutSpec or similar)
    containsShortcutPair: Pair<(String, String) -> String, Fun2<CharSequence, String, Array<out String>>>,
    containsAtMostPair: Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>>,
    containsAtMostIgnoringCasePair: Pair<(String, String) -> String, Fun3<CharSequence, Int, String, Array<out String>>>,
    rootBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    val containsAtLeast = containsAtLeastPair.second
    val containsAtLeastIgnoringCase = containsAtLeastIgnoringCasePair.second
    val containsShortcut = containsShortcutPair.second
    val containsAtMost = containsAtMostPair.second
    val containsAtMostIgnoringCase = containsAtMostIgnoringCasePair.second


    include(object : SubjectLessSpec<CharSequence>(
        describePrefix,
        containsAtLeast.forSubjectLess(2, "a|b", arrayOf()),
        containsAtLeastIgnoringCase.forSubjectLess(2, "a|b", arrayOf()),
        containsShortcut.forSubjectLess("a|b", arrayOf()),
        containsAtMost.forSubjectLess(2, "a|b", arrayOf()),
        containsAtMostIgnoringCase.forSubjectLess(2, "a|b", arrayOf())
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)


    val text = "Hello my name is Robert"
    val hello = "[hH][ea]llo"
    val roberto = "Roberto?"
    val fluent = expect(text as CharSequence)

    fun Expect<CharSequence>.containsAtLeastFun(atLeast: Int, a: String, vararg aX: String) =
        containsAtLeast(this, atLeast, a, aX)

    fun Expect<CharSequence>.containsAtLeastIgnoringCaseFun(atLeast: Int, a: String, vararg aX: String) =
        containsAtLeastIgnoringCase(this, atLeast, a, aX)


    fun Expect<CharSequence>.containsShortcutFun(a: String, vararg aX: String) = containsShortcut(this, a, aX)

    fun Expect<CharSequence>.containsAtMostFun(atLeast: Int, a: String, vararg aX: String) =
        containsAtMost(this, atLeast, a, aX)

    fun Expect<CharSequence>.containsAtMostIgnoringCaseFun(atLeast: Int, a: String, vararg aX: String) =
        containsAtMostIgnoringCase(this, atLeast, a, aX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val regexWithIndent = "$indentBulletPoint$listBulletPoint$stringMatchingRegex"

    describeFun(containsRegex) {
        context("throws an ${IllegalArgumentException::class.simpleName}") {
            it("if an erroneous pattern is passed to `$containsAtLeast` as first argument") {
                expect {
                    expect("a" as CharSequence).containsAtLeastFun(1, "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }
            it("if an erroneous pattern is passed to `$containsAtLeast` as second argument") {
                expect {
                    expect("a" as CharSequence).containsAtLeastFun(1, "h(a|e)llo", "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }
            it("if an erroneous pattern is passed to `$containsAtLeastIgnoringCase` as first argument") {
                expect {
                    expect("a" as CharSequence).containsAtLeastIgnoringCaseFun(1, "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }
            it("if an erroneous pattern is passed to `$containsAtLeastIgnoringCase` as second argument") {
                expect {
                    expect("a" as CharSequence).containsAtLeastIgnoringCaseFun(1, "h(a|e)llo", "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }

            it("if an erroneous pattern is passed to `$containsShortcut` as first argument") {
                expect {
                    expect("a" as CharSequence).containsShortcutFun("notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }
            it("if an erroneous pattern is passed to `$containsShortcut` as second argument") {
                expect {
                    expect("a" as CharSequence).containsShortcutFun("h(a|e)llo", "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }

            it("if an erroneous pattern is passed to `$containsAtMost` as first argument") {
                expect {
                    expect("a" as CharSequence).containsAtMostFun(2, "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }
            it("if an erroneous pattern is passed to `$containsAtMost` as second argument") {
                expect {
                    expect("a" as CharSequence).containsAtMostFun(2, "h(a|e)llo", "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }

            it("if an erroneous pattern is passed to `$containsAtMostIgnoringCase` as first argument") {
                expect {
                    expect("a" as CharSequence).containsAtMostIgnoringCaseFun(2, "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }
            it("if an erroneous pattern is passed to `$containsAtMostIgnoringCase` as second argument") {
                expect {
                    expect("a" as CharSequence).containsAtMostIgnoringCaseFun(2, "h(a|e)llo", "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }
        }

        context("text $text") {
            it("${containsAtLeastPair.first("'$hello'", "once")} does not throw") {
                fluent.containsAtLeastFun(1, hello)
            }
            it("${containsAtLeastPair.first("'$hello', '$hello' and '$hello'", "once")} does not throw") {
                fluent.containsAtLeastFun(1, hello, hello, hello)
            }
            it("${containsAtLeastPair.first("'$hello' and '$roberto'", "once")} does not throw") {
                fluent.containsAtLeastFun(1, hello, roberto)
            }
            it("${containsAtLeastPair.first("'${roberto.toLowerCase()}'", "once")} throws AssertionError") {
                expect {
                    fluent.containsAtLeastFun(1, roberto.toLowerCase())
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            "$rootBulletPoint$containsDescr: $separator" +
                                "$regexWithIndent: \"${roberto.toLowerCase()}\"",
                            "$numberOfOccurrences: 0",
                            "$atLeast: 1"
                        )
                    }
                }
            }
            it("${containsAtLeastIgnoringCasePair.first("'${roberto.toLowerCase()}'", "once")} does not throw") {
                fluent.containsAtLeastIgnoringCaseFun(1, roberto.toLowerCase())
            }

            it("${containsShortcutPair.first("'$hello'", "once")} does not throw") {
                fluent.containsShortcutFun(hello)
            }
            it("${containsShortcutPair.first("'$hello', '$hello' and '$hello'", "once")} does not throw") {
                fluent.containsShortcutFun(hello, hello, hello)
            }
            it("${containsShortcutPair.first("'$hello' and '$roberto'", "once")} does not throw") {
                fluent.containsShortcutFun(hello, roberto)
            }

            it("${containsAtMostPair.first("'[a-z]'", "17 times")} does not throw") {
                fluent.containsAtMostFun(17, "[a-z]")
            }
            it("${containsAtMostIgnoringCasePair.first("'[a-z]'", "19 times")} does not throw") {
                fluent.containsAtMostIgnoringCaseFun(19, "[a-z]")
            }
            it("${containsAtMostIgnoringCasePair.first("'[a-z]' and '[A-Z]'", "19 times")} does not throw") {
                fluent.containsAtMostIgnoringCaseFun(19, "[a-z]", "[A-Z]")
            }

            it("${containsAtMostPair.first("'[a-z]'", "16 times")} throws AssertionError") {
                expect {
                    fluent.containsAtMostFun(16, "[a-z]")
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            "$rootBulletPoint$containsDescr: $separator" +
                                "$regexWithIndent: \"[a-z]\"",
                            "$numberOfOccurrences: 17",
                            "$atMost: 16"
                        )
                    }
                }
            }
            it("${containsAtMostIgnoringCasePair.first("'[a-z]'", "18 times")} throws AssertionError") {
                expect {
                    fluent.containsAtMostIgnoringCaseFun(18, "[a-z]")
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            "$rootBulletPoint$containsIgnoringCase: $separator" +
                                "$regexWithIndent: \"[a-z]\"",
                            "$numberOfOccurrences: 19",
                            "$atMost: 18"
                        )
                    }
                }
            }
        }
    }
})
