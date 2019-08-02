package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import org.spekframework.spek2.style.specification.Suite

abstract class CharSequenceContainsRegexAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsRegex: String,
    containsAtLeastTriple: Triple<String, (String, String) -> String, Expect<CharSequence>.(Int, String, Array<out String>) -> Expect<CharSequence>>,
    containsAtLeastIgnoringCaseTriple: Triple<String, (String, String) -> String, Expect<CharSequence>.(Int, String, Array<out String>) -> Expect<CharSequence>>,
    //Usually the shortcut is tested individually on a level lower and not expected as we did it here (search for ShortcutSpec or similar)
    containsShortcutTriple: Triple<String, (String, String) -> String, Expect<CharSequence>.(String, Array<out String>) -> Expect<CharSequence>>,
    containsAtMostTriple: Triple<String, (String, String) -> String, Expect<CharSequence>.(Int, String, Array<out String>) -> Expect<CharSequence>>,
    containsAtMostIgnoringCaseTriple: Triple<String, (String, String) -> String, Expect<CharSequence>.(Int, String, Array<out String>) -> Expect<CharSequence>>,
    rootBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(object : SubjectLessSpec<CharSequence>(describePrefix,
        containsAtLeastTriple.first to expectLambda { containsAtLeastTriple.third(this, 2, "a|b", arrayOf()) },
        containsAtLeastIgnoringCaseTriple.first to expectLambda { containsAtLeastIgnoringCaseTriple.third(this, 2, "a|b", arrayOf()) },
        containsShortcutTriple.first to expectLambda { containsShortcutTriple.third(this, "a|b", arrayOf()) },
        containsAtMostTriple.first to expectLambda { containsAtMostTriple.third(this, 2, "a|b", arrayOf()) },
        containsAtMostIgnoringCaseTriple.first to expectLambda { containsAtMostIgnoringCaseTriple.third(this, 2, "a|b", arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<CharSequence>(verbs, describePrefix,
        checkingTriple(containsAtLeastTriple.first, { containsAtLeastTriple.third(this, 2, "a|b", arrayOf()) }, "a, b" as CharSequence, "a"),
        checkingTriple(containsAtLeastIgnoringCaseTriple.first, { containsAtLeastIgnoringCaseTriple.third(this, 2, "a|b", arrayOf()) }, "A, B" as CharSequence, "a"),
        checkingTriple(containsShortcutTriple.first, { containsShortcutTriple.third(this, "a|b", arrayOf()) }, "a, b" as CharSequence, "c"),
        checkingTriple(containsAtMostTriple.first, { containsAtMostTriple.third(this, 2, "a|b", arrayOf()) }, "a" as CharSequence, "a,ba"),
        checkingTriple(containsAtMostIgnoringCaseTriple.first, { containsAtMostIgnoringCaseTriple.third(this, 2, "a|b", arrayOf()) }, "A" as CharSequence, "bbb")
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException

    val text = "Hello my name is Robert"
    val hello = "[hH][ea]llo"
    val roberto = "Roberto?"
    val fluent = verbs.check(text as CharSequence)

    val (containsAtLeast, containsAtLeastTest, containsAtLeastFunArr) = containsAtLeastTriple
    fun Expect<CharSequence>.containsAtLeastFun(atLeast: Int, a: String, vararg aX: String)
        = containsAtLeastFunArr(atLeast, a, aX)

    val (containsAtLeastIgnoringCase, containsAtLeastIgnoringCaseTest, containsAtLeastIgnoringCaseFunArr) = containsAtLeastIgnoringCaseTriple
    fun Expect<CharSequence>.containsAtLeastIgnoringCaseFun(atLeast: Int, a: String, vararg aX: String)
        = containsAtLeastIgnoringCaseFunArr(atLeast, a, aX)


    val (containsShortcut, containsShortcutTest, containsShortcutLeastFunArr) = containsShortcutTriple
    fun Expect<CharSequence>.containsShortcutFun(a: String, vararg aX: String)
        = containsShortcutLeastFunArr(a, aX)

    val (containsAtMost, containsAtMostTest, containsAtMostFunArr) = containsAtMostTriple
    fun Expect<CharSequence>.containsAtMostFun(atLeast: Int, a: String, vararg aX: String)
        = containsAtMostFunArr(atLeast, a, aX)

    val (containsAtMostIgnoringCase, containsAtMostIgnoringCaseTest, containsAtMostIgnoringCaseFunArr) = containsAtMostIgnoringCaseTriple
    fun Expect<CharSequence>.containsAtMostIgnoringCaseFun(atLeast: Int, a: String, vararg aX: String)
        = containsAtMostIgnoringCaseFunArr(atLeast, a, aX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val regexWithIndent = "$indentBulletPoint$listBulletPoint$stringMatchingRegex"

    describeFun(containsRegex) {
        context("throws an ${IllegalArgumentException::class.simpleName}") {
            it("if an erroneous pattern is passed to `$containsAtLeast` as first argument") {
                expect {
                    verbs.check("a" as CharSequence).containsAtLeastFun(1, "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }
            it("if an erroneous pattern is passed to `$containsAtLeast` as second argument") {
                expect {
                    verbs.check("a" as CharSequence).containsAtLeastFun(1, "h(a|e)llo", "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }
            it("if an erroneous pattern is passed to `$containsAtLeastIgnoringCase` as first argument") {
                expect {
                    verbs.check("a" as CharSequence).containsAtLeastIgnoringCaseFun(1, "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }
            it("if an erroneous pattern is passed to `$containsAtLeastIgnoringCase` as second argument") {
                expect {
                    verbs.check("a" as CharSequence).containsAtLeastIgnoringCaseFun(1, "h(a|e)llo", "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }

            it("if an erroneous pattern is passed to `$containsShortcut` as first argument") {
                expect {
                    verbs.check("a" as CharSequence).containsShortcutFun("notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }
            it("if an erroneous pattern is passed to `$containsShortcut` as second argument") {
                expect {
                    verbs.check("a" as CharSequence).containsShortcutFun("h(a|e)llo", "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }

            it("if an erroneous pattern is passed to `$containsAtMost` as first argument") {
                expect {
                    verbs.check("a" as CharSequence).containsAtMostFun(2, "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }
            it("if an erroneous pattern is passed to `$containsAtMost` as second argument") {
                expect {
                    verbs.check("a" as CharSequence).containsAtMostFun(2, "h(a|e)llo", "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }

            it("if an erroneous pattern is passed to `$containsAtMostIgnoringCase` as first argument") {
                expect {
                    verbs.check("a" as CharSequence).containsAtMostIgnoringCaseFun(2, "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }
            it("if an erroneous pattern is passed to `$containsAtMostIgnoringCase` as second argument") {
                expect {
                    verbs.check("a" as CharSequence).containsAtMostIgnoringCaseFun(2, "h(a|e)llo", "notA(validPattern")
                }.toThrow<IllegalArgumentException>()
            }
        }

        context("text $text") {
            it("${containsAtLeastTest("'$hello'", "once")} does not throw") {
                fluent.containsAtLeastFun(1, hello)
            }
            it("${containsAtLeastTest("'$hello', '$hello' and '$hello'", "once")} does not throw") {
                fluent.containsAtLeastFun(1, hello, hello, hello)
            }
            it("${containsAtLeastTest("'$hello' and '$roberto'", "once")} does not throw") {
                fluent.containsAtLeastFun(1, hello, roberto)
            }
            it("${containsAtLeastTest("'${roberto.toLowerCase()}'", "once")} throws AssertionError") {
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
            it("${containsAtLeastIgnoringCaseTest("'${roberto.toLowerCase()}'", "once")} does not throw") {
                fluent.containsAtLeastIgnoringCaseFun(1, roberto.toLowerCase())
            }

            it("${containsShortcutTest("'$hello'", "once")} does not throw") {
                fluent.containsShortcutFun(hello)
            }
            it("${containsShortcutTest("'$hello', '$hello' and '$hello'", "once")} does not throw") {
                fluent.containsShortcutFun(hello, hello, hello)
            }
            it("${containsShortcutTest("'$hello' and '$roberto'", "once")} does not throw") {
                fluent.containsShortcutFun(hello, roberto)
            }

            it("${containsAtMostTest("'[a-z]'", "17 times")} does not throw") {
                fluent.containsAtMostFun(17, "[a-z]")
            }
            it("${containsAtMostIgnoringCaseTest("'[a-z]'", "19 times")} does not throw") {
                fluent.containsAtMostIgnoringCaseFun(19, "[a-z]")
            }
            it("${containsAtMostIgnoringCaseTest("'[a-z]' and '[A-Z]'", "19 times")} does not throw") {
                fluent.containsAtMostIgnoringCaseFun(19, "[a-z]", "[A-Z]")
            }

            it("${containsAtMostTest("'[a-z]'", "16 times")} throws AssertionError") {
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
            it("${containsAtMostIgnoringCaseTest("'[a-z]'", "18 times")} throws AssertionError") {
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
