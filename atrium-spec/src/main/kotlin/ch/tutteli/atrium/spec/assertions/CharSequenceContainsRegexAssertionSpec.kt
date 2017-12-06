package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.AT_MOST
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include
import java.util.regex.PatternSyntaxException

abstract class CharSequenceContainsRegexAssertionSpec(
    verbs: IAssertionVerbFactory,
    containsRegex: String,
    containsAtLeastTriple: Triple<String, (String, String) -> String, IAssertionPlant<CharSequence>.(Int, String, Array<out String>) -> IAssertionPlant<CharSequence>>,
    containsAtMostTriple: Triple<String, (String, String) -> String, IAssertionPlant<CharSequence>.(Int, String, Array<out String>) -> IAssertionPlant<CharSequence>>,
    containsAtMostIgnoringCaseTriple: Triple<String, (String, String) -> String, IAssertionPlant<CharSequence>.(Int, String, Array<out String>) -> IAssertionPlant<CharSequence>>,
    containsExactlyFunArr: IAssertionPlant<CharSequence>.(Int, String, Array<out String>) -> IAssertionPlant<CharSequence>
) : Spek({

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<CharSequence>(
        containsAtLeastTriple.first to mapToCreateAssertion { containsAtLeastTriple.third(this, 2, "a|b", arrayOf()) },
        containsAtMostTriple.first to mapToCreateAssertion { containsAtMostTriple.third(this, 2, "a|b", arrayOf()) },
        containsAtMostIgnoringCaseTriple.first to mapToCreateAssertion { containsAtMostIgnoringCaseTriple.third(this, 2, "a|b", arrayOf()) }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<String>(verbs,
        checkingTriple(containsAtLeastTriple.first, { containsAtLeastTriple.third(this, 2, "a|b", arrayOf()) }, "a, b", "a"),
        checkingTriple(containsAtMostTriple.first, { containsAtMostTriple.third(this, 2, "a|b", arrayOf()) }, "a", "a,ba"),
        checkingTriple(containsAtMostIgnoringCaseTriple.first, { containsAtMostIgnoringCaseTriple.third(this, 2, "a|b", arrayOf()) }, "a", "bbb")
    ) {})

    val assert: (CharSequence) -> IAssertionPlant<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException

    val text = "Hello my name is Robert"
    val hello = "[hH][ea]llo"
    val robert = "Roberto?"
    val fluent = assert(text)

    val (_, containsAtLeastTest, containsAtLeastFunArr) = containsAtLeastTriple
    fun IAssertionPlant<CharSequence>.containsAtLeastFun(atLeast: Int, a: String, vararg aX: String)
        = containsAtLeastFunArr(atLeast, a, aX)

    val (_, containsAtMostTest, containsAtMostFunArr) = containsAtMostTriple
    fun IAssertionPlant<CharSequence>.containsAtMostFun(atLeast: Int, a: String, vararg aX: String)
        = containsAtMostFunArr(atLeast, a, aX)

    val (_, containsAtMostIgnoringCase, containsAtMostIgnoringCaseFunArr) = containsAtMostIgnoringCaseTriple
    fun IAssertionPlant<CharSequence>.containsAtMostIgnoringCaseFun(atLeast: Int, a: String, vararg aX: String)
        = containsAtMostIgnoringCaseFunArr(atLeast, a, aX)

    fun IAssertionPlant<CharSequence>.containsExactlyFun(atLeast: Int, a: String, vararg aX: String)
        = containsExactlyFunArr(atLeast, a, aX)

    describe("fun $containsRegex") {
        context("throws an ${PatternSyntaxException::class.simpleName}") {
            test("if an erroneous pattern is passed") {
                expect {
                    assert("a").containsExactlyFun(1, "notA(validPattern")
                }.toThrow<PatternSyntaxException>()
            }
            test("if an erroneous pattern is passed as second regex") {
                expect {
                    assert("a").containsExactlyFun(1, "h(a|e)llo", "notA(validPattern")
                }.toThrow<PatternSyntaxException>()
            }
        }

        context("text $text") {
            test("${containsAtLeastTest("'$hello'", "once")} does not throw") {
                fluent.containsAtLeastFun(1, hello)
            }
            test("${containsAtLeastTest("'$hello', '$hello' and '$hello'", "once")} does not throw") {
                fluent.containsAtLeastFun(1, hello, hello, hello)
            }

            test("${containsAtLeastTest("'$hello' and '$robert'", "once")} does not throw") {
                fluent.containsAtLeastFun(1, hello, robert)
            }

            test("${containsAtMostTest("'[a-z]'", "17 times")} does not throw") {
                fluent.containsAtMostFun(17, "[a-z]")
            }
            test("${containsAtMostIgnoringCase("'[a-z]'", "19 times")} does not throw") {
                fluent.containsAtMostIgnoringCaseFun(19, "[a-z]")
            }
            test("${containsAtMostIgnoringCase("'[a-z]' and '[A-Z]'", "19 times")} does not throw") {
                fluent.containsAtMostIgnoringCaseFun(19, "[a-z]", "[A-Z]")
            }

            test("${containsAtMostTest("'[a-z]'", "16 times")} does not throw") {
                expect {
                    fluent.containsAtMostFun(16, "[a-z]")
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(AT_MOST) } }
            }
            test("${containsAtMostIgnoringCase("'[a-z]'", "18 times")} does not throw") {
                expect {
                    fluent.containsAtMostIgnoringCaseFun(18, "[a-z]")
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(AT_MOST) } }
            }
        }
    }
})
