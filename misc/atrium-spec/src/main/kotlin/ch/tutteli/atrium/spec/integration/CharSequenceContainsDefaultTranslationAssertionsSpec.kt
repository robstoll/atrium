@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.messageContains
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion.AT_MOST
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

//TODO remove with 1.0.0
@Deprecated("Will be removed with 1.0.0")
abstract class CharSequenceContainsDefaultTranslationAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsDefaultTranslationOf: String,
    containsAtLeastTriple: Triple<String, (String, String) -> String, Assert<CharSequence>.(Int, Translatable, Array<out Translatable>) -> Assert<CharSequence>>,
    containsAtMostTriple: Triple<String, (String, String) -> String, Assert<CharSequence>.(Int, Translatable, Array<out Translatable>) -> Assert<CharSequence>>,
    containsAtMostIgnoringCaseTriple: Triple<String, (String, String) -> String, Assert<CharSequence>.(Int, Translatable, Array<out Translatable>) -> Assert<CharSequence>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<CharSequence>(describePrefix,
        containsAtLeastTriple.first to mapToCreateAssertion { containsAtLeastTriple.third(this, 2, AssertionVerb.ASSERT, arrayOf()) },
        containsAtMostTriple.first to mapToCreateAssertion { containsAtMostTriple.third(this, 2, AssertionVerb.ASSERT, arrayOf()) },
        containsAtMostIgnoringCaseTriple.first to mapToCreateAssertion { containsAtMostIgnoringCaseTriple.third(this, 2, AssertionVerb.ASSERT, arrayOf()) }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<String>(verbs, describePrefix,
        checkingTriple(containsAtLeastTriple.first, { containsAtLeastTriple.third(this, 2, AssertionVerb.ASSERT, arrayOf()) }, "assert a, assert b", "a"),
        checkingTriple(containsAtMostTriple.first, { containsAtMostTriple.third(this, 2, AssertionVerb.ASSERT, arrayOf()) }, "assert", "assert, assert and assert"),
        checkingTriple(containsAtMostIgnoringCaseTriple.first, { containsAtMostIgnoringCaseTriple.third(this, 2, AssertionVerb.ASSERT, arrayOf()) }, "Assert aSSert", "assert Assert AsSert")
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (CharSequence) -> Assert<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException

    val text = "Assert - assert, assert, assert - ASSERT; expect the thrown exception"
    val fluent = assert(text)

    val (_, containsAtLeastTest, containsAtLeastFunArr) = containsAtLeastTriple
    fun Assert<CharSequence>.containsAtLeastFun(atLeast: Int, a: Translatable, vararg aX: Translatable)
        = containsAtLeastFunArr(atLeast, a, aX)

    val (_, containsAtMostTest, containsAtMostFunArr) = containsAtMostTriple
    fun Assert<CharSequence>.containsAtMostFun(atLeast: Int, a: Translatable, vararg aX: Translatable)
        = containsAtMostFunArr(atLeast, a, aX)

    val (_, containsAtMostIgnoringCase, containsAtMostIgnoringCaseFunArr) = containsAtMostIgnoringCaseTriple
    fun Assert<CharSequence>.containsAtMostIgnoringCaseFun(atLeast: Int, a: Translatable, vararg aX: Translatable)
        = containsAtMostIgnoringCaseFunArr(atLeast, a, aX)

    describeFun(containsDefaultTranslationOf) {

        context("text $text") {
            test("${containsAtLeastTest("${AssertionVerb.ASSERT}", "once")} does not throw") {
                fluent.containsAtLeastFun(1, AssertionVerb.ASSERT)
            }
            test("${containsAtLeastTest("${AssertionVerb.ASSERT}, ${AssertionVerb.ASSERT} and ${AssertionVerb.ASSERT}", "once")} does not throw") {
                fluent.containsAtLeastFun(1, AssertionVerb.ASSERT, AssertionVerb.ASSERT, AssertionVerb.ASSERT)
            }

            test("${containsAtLeastTest("'${AssertionVerb.ASSERT}' and ${AssertionVerb.EXPECT_THROWN}", "once")} does not throw") {
                fluent.containsAtLeastFun(1, AssertionVerb.ASSERT, AssertionVerb.EXPECT_THROWN)
            }

            test("${containsAtMostTest(AssertionVerb.ASSERT.toString(), "3 times")} does not throw") {
                fluent.containsAtMostFun(3, AssertionVerb.ASSERT)
            }
            test("${containsAtMostIgnoringCase(AssertionVerb.ASSERT.toString(), "5 times")} does not throw") {
                fluent.containsAtMostIgnoringCaseFun(5, AssertionVerb.ASSERT)
            }
            test("${containsAtMostIgnoringCase("${AssertionVerb.ASSERT} and ${Untranslatable("Assert")}", "4 times")} does not throw") {
                fluent.containsAtMostIgnoringCaseFun(5, AssertionVerb.ASSERT, Untranslatable("Assert"))
            }

            test("${containsAtMostTest(AssertionVerb.ASSERT.toString(), "twice")} throws AssertionError") {
                expect {
                    fluent.containsAtMostFun(2, AssertionVerb.ASSERT)
                }.toThrow<AssertionError> { messageContains(AT_MOST.getDefault()) }
            }
            test("${containsAtMostIgnoringCase(AssertionVerb.ASSERT.toString(), "4 times")} throws AssertionError") {
                expect {
                    fluent.containsAtMostIgnoringCaseFun(4, AssertionVerb.ASSERT)
                }.toThrow<AssertionError> { messageContains(AT_MOST.getDefault()) }
            }
        }
    }
})
