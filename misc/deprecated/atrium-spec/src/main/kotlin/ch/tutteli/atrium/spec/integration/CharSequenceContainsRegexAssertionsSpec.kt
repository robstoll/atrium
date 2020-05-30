// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.api.cc.en_GB.message
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include
import java.util.regex.PatternSyntaxException

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class CharSequenceContainsRegexAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsRegex: String,
    containsAtLeastTriple: Triple<String, (String, String) -> String, Assert<CharSequence>.(Int, String, Array<out String>) -> Assert<CharSequence>>,
    containsAtLeastIgnoringCaseTriple: Triple<String, (String, String) -> String, Assert<CharSequence>.(Int, String, Array<out String>) -> Assert<CharSequence>>,
    //Usually the shortcut is tested individually on a level lower and not expected as we did it here (search for ShortcutSpec or similar)
    containsShortcutTriple: Triple<String, (String, String) -> String, Assert<CharSequence>.(String, Array<out String>) -> Assert<CharSequence>>,
    containsAtMostTriple: Triple<String, (String, String) -> String, Assert<CharSequence>.(Int, String, Array<out String>) -> Assert<CharSequence>>,
    containsAtMostIgnoringCaseTriple: Triple<String, (String, String) -> String, Assert<CharSequence>.(Int, String, Array<out String>) -> Assert<CharSequence>>,
    rootBulletPoint: String,
    listBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : CharSequenceContainsSpecBase({

    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<CharSequence>(describePrefix,
        containsAtLeastTriple.first to mapToCreateAssertion { containsAtLeastTriple.third(this, 2, "a|b", arrayOf()) },
        containsAtLeastIgnoringCaseTriple.first to mapToCreateAssertion { containsAtLeastIgnoringCaseTriple.third(this, 2, "a|b", arrayOf()) },
        containsShortcutTriple.first to mapToCreateAssertion { containsShortcutTriple.third(this, "a|b", arrayOf()) },
        containsAtMostTriple.first to mapToCreateAssertion { containsAtMostTriple.third(this, 2, "a|b", arrayOf()) },
        containsAtMostIgnoringCaseTriple.first to mapToCreateAssertion { containsAtMostIgnoringCaseTriple.third(this, 2, "a|b", arrayOf()) }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<String>(verbs, describePrefix,
        checkingTriple(containsAtLeastTriple.first, { containsAtLeastTriple.third(this, 2, "a|b", arrayOf()) }, "a, b", "a"),
        checkingTriple(containsAtLeastIgnoringCaseTriple.first, { containsAtLeastIgnoringCaseTriple.third(this, 2, "a|b", arrayOf()) }, "A, B", "a"),
        checkingTriple(containsShortcutTriple.first, { containsShortcutTriple.third(this, "a|b", arrayOf()) }, "a, b", "c"),
        checkingTriple(containsAtMostTriple.first, { containsAtMostTriple.third(this, 2, "a|b", arrayOf()) }, "a", "a,ba"),
        checkingTriple(containsAtMostIgnoringCaseTriple.first, { containsAtMostIgnoringCaseTriple.third(this, 2, "a|b", arrayOf()) }, "A", "bbb")
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (CharSequence) -> Assert<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException

    val text = "Hello my name is Robert"
    val hello = "[hH][ea]llo"
    val roberto = "Roberto?"
    val fluent = assert(text)

    val (containsAtLeast, containsAtLeastTest, containsAtLeastFunArr) = containsAtLeastTriple
    fun Assert<CharSequence>.containsAtLeastFun(atLeast: Int, a: String, vararg aX: String)
        = containsAtLeastFunArr(atLeast, a, aX)

    val (containsAtLeastIgnoringCase, containsAtLeastIgnoringCaseTest, containsAtLeastIgnoringCaseFunArr) = containsAtLeastIgnoringCaseTriple
    fun Assert<CharSequence>.containsAtLeastIgnoringCaseFun(atLeast: Int, a: String, vararg aX: String)
        = containsAtLeastIgnoringCaseFunArr(atLeast, a, aX)


    val (containsShortcut, containsShortcutTest, containsShortcutLeastFunArr) = containsShortcutTriple
    fun Assert<CharSequence>.containsShortcutFun(a: String, vararg aX: String)
        = containsShortcutLeastFunArr(a, aX)

    val (containsAtMost, containsAtMostTest, containsAtMostFunArr) = containsAtMostTriple
    fun Assert<CharSequence>.containsAtMostFun(atLeast: Int, a: String, vararg aX: String)
        = containsAtMostFunArr(atLeast, a, aX)

    val (containsAtMostIgnoringCase, containsAtMostIgnoringCaseTest, containsAtMostIgnoringCaseFunArr) = containsAtMostIgnoringCaseTriple
    fun Assert<CharSequence>.containsAtMostIgnoringCaseFun(atLeast: Int, a: String, vararg aX: String)
        = containsAtMostIgnoringCaseFunArr(atLeast, a, aX)

    val indentBulletPoint = " ".repeat(rootBulletPoint.length)
    val regexWithIndent = "$indentBulletPoint$listBulletPoint$stringMatchingRegex"

    describeFun(containsRegex) {
        context("throws an ${PatternSyntaxException::class.simpleName}") {
            test("if an erroneous pattern is passed to `$containsAtLeast` as first argument") {
                expect {
                    assert("a").containsAtLeastFun(1, "notA(validPattern")
                }.toThrow<PatternSyntaxException>{}
            }
            test("if an erroneous pattern is passed to `$containsAtLeast` as second argument") {
                expect {
                    assert("a").containsAtLeastFun(1, "h(a|e)llo", "notA(validPattern")
                }.toThrow<PatternSyntaxException>{}
            }
            test("if an erroneous pattern is passed to `$containsAtLeastIgnoringCase` as first argument") {
                expect {
                    assert("a").containsAtLeastIgnoringCaseFun(1, "notA(validPattern")
                }.toThrow<PatternSyntaxException>{}
            }
            test("if an erroneous pattern is passed to `$containsAtLeastIgnoringCase` as second argument") {
                expect {
                    assert("a").containsAtLeastIgnoringCaseFun(1, "h(a|e)llo", "notA(validPattern")
                }.toThrow<PatternSyntaxException>{}
            }

            test("if an erroneous pattern is passed to `$containsShortcut` as first argument") {
                expect {
                    assert("a").containsShortcutFun("notA(validPattern")
                }.toThrow<PatternSyntaxException>{}
            }
            test("if an erroneous pattern is passed to `$containsShortcut` as second argument") {
                expect {
                    assert("a").containsShortcutFun("h(a|e)llo", "notA(validPattern")
                }.toThrow<PatternSyntaxException>{}
            }

            test("if an erroneous pattern is passed to `$containsAtMost` as first argument") {
                expect {
                    assert("a").containsAtMostFun(2, "notA(validPattern")
                }.toThrow<PatternSyntaxException>{}
            }
            test("if an erroneous pattern is passed to `$containsAtMost` as second argument") {
                expect {
                    assert("a").containsAtMostFun(2, "h(a|e)llo", "notA(validPattern")
                }.toThrow<PatternSyntaxException>{}
            }

            test("if an erroneous pattern is passed to `$containsAtMostIgnoringCase` as first argument") {
                expect {
                    assert("a").containsAtMostIgnoringCaseFun(2, "notA(validPattern")
                }.toThrow<PatternSyntaxException>{}
            }
            test("if an erroneous pattern is passed to `$containsAtMostIgnoringCase` as second argument") {
                expect {
                    assert("a").containsAtMostIgnoringCaseFun(2, "h(a|e)llo", "notA(validPattern")
                }.toThrow<PatternSyntaxException>{}
            }
        }

        context("text $text") {
            test("${containsAtLeastTest("'$hello'", "once")} does not throw") {
                fluent.containsAtLeastFun(1, hello)
            }
            test("${containsAtLeastTest("'$hello', '$hello' and '$hello'", "once")} does not throw") {
                fluent.containsAtLeastFun(1, hello, hello, hello)
            }
            test("${containsAtLeastTest("'$hello' and '$roberto'", "once")} does not throw") {
                fluent.containsAtLeastFun(1, hello, roberto)
            }
            test("${containsAtLeastTest("'${roberto.toLowerCase()}'", "once")} throws AssertionError") {
                expect {
                    fluent.containsAtLeastFun(1, roberto.toLowerCase())
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            "$rootBulletPoint$containsDescr: $separator" +
                                "$regexWithIndent: ${roberto.toLowerCase()}",
                            "$numberOfOccurrences: 0",
                            "$atLeast: 1"
                        )
                    }
                }
            }
            test("${containsAtLeastIgnoringCaseTest("'${roberto.toLowerCase()}'", "once")} does not throw") {
                fluent.containsAtLeastIgnoringCaseFun(1, roberto.toLowerCase())
            }

            test("${containsShortcutTest("'$hello'", "once")} does not throw") {
                fluent.containsShortcutFun(hello)
            }
            test("${containsShortcutTest("'$hello', '$hello' and '$hello'", "once")} does not throw") {
                fluent.containsShortcutFun(hello, hello, hello)
            }
            test("${containsShortcutTest("'$hello' and '$roberto'", "once")} does not throw") {
                fluent.containsShortcutFun(hello, roberto)
            }

            test("${containsAtMostTest("'[a-z]'", "17 times")} does not throw") {
                fluent.containsAtMostFun(17, "[a-z]")
            }
            test("${containsAtMostIgnoringCaseTest("'[a-z]'", "19 times")} does not throw") {
                fluent.containsAtMostIgnoringCaseFun(19, "[a-z]")
            }
            test("${containsAtMostIgnoringCaseTest("'[a-z]' and '[A-Z]'", "19 times")} does not throw") {
                fluent.containsAtMostIgnoringCaseFun(19, "[a-z]", "[A-Z]")
            }

            test("${containsAtMostTest("'[a-z]'", "16 times")} throws AssertionError") {
                expect {
                    fluent.containsAtMostFun(16, "[a-z]")
                }.toThrow<AssertionError> {
                    message {
                        contains(
                            "$rootBulletPoint$containsDescr: $separator" +
                                "$regexWithIndent: [a-z]",
                                "$numberOfOccurrences: 17",
                                "$atMost: 16"
                        )
                    }
                }
            }
            test("${containsAtMostIgnoringCaseTest("'[a-z]'", "18 times")} throws AssertionError") {
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
