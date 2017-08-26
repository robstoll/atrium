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
import java.util.regex.PatternSyntaxException

abstract class CharSequenceContainsRegexAssertionSpec(
    verbs: IAssertionVerbFactory,
    containsRegex: String,
    containsAtLeastPair: Pair<(String, String) -> String, IAssertionPlant<CharSequence>.(Int, Any, Array<out Any>) -> IAssertionPlant<CharSequence>>,
    containsAtMostPair: Pair<(String, String) -> String, IAssertionPlant<CharSequence>.(Int, Any, Array<out Any>) -> IAssertionPlant<CharSequence>>,
    containsAtMostIgnoringCasePair: Pair<(String, String) -> String, IAssertionPlant<CharSequence>.(Int, Any, Array<out Any>) -> IAssertionPlant<CharSequence>>,
    containsExactlyFunArr: IAssertionPlant<CharSequence>.(Int, Any, Array<out Any>) -> IAssertionPlant<CharSequence>
) : Spek({

    val assert: (CharSequence) -> IAssertionPlant<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException

    val text = "Hello my name is Robert"
    val hello = "[hH][ea]llo"
    val robert = "Roberto?"
    val fluent = assert(text)

    val (containsAtLeastTest, containsAtLeastFunArr) = containsAtLeastPair
    fun IAssertionPlant<CharSequence>.containsAtLeastFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtLeastFunArr(atLeast, a, aX)

    val (containsAtMostTest, containsAtMostFunArr) = containsAtMostPair
    fun IAssertionPlant<CharSequence>.containsAtMostFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtMostFunArr(atLeast, a, aX)

    val (containsAtMostIgnoringCase, containsAtMostIgnoringCaseFunArr) = containsAtMostIgnoringCasePair
    fun IAssertionPlant<CharSequence>.containsAtMostIgnoringCaseFun(atLeast: Int, a: Any, vararg aX: Any)
        = containsAtMostIgnoringCaseFunArr(atLeast, a, aX)

    fun IAssertionPlant<CharSequence>.containsExactlyFun(atLeast: Int, a: Any, vararg aX: Any)
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

            test("${containsAtLeastTest("'$hello' and '$robert'", "once")} does not throw") {
                fluent.containsAtLeastFun(1, hello, robert)
            }

            test("${containsAtMostTest("'[a-z]'", "17 times")} does not throw") {
                fluent.containsAtMostFun(17, "[a-z]")
            }
            test("${containsAtMostIgnoringCase("'[a-z]'", "19 times")} does not throw") {
                fluent.containsAtMostIgnoringCaseFun(19, "[a-z]")
            }

            test("${containsAtMostTest("'[a-z]'", "16 times")} does not throw") {
                expect {
                    fluent.containsAtMostFun(16, "[a-z]")
                }.toThrow<AssertionError>().message.containsDefaultTranslationOf(AT_MOST)
            }
            test("${containsAtMostIgnoringCase("'[a-z]'", "18 times")} does not throw") {
                expect {
                    fluent.containsAtMostIgnoringCaseFun(18, "[a-z]")
                }.toThrow<AssertionError>().message.containsDefaultTranslationOf(AT_MOST)
            }
        }
    }
})
