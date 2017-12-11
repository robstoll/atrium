package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.AT_MOST
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

abstract class CharSequenceContainsDefaultTranslationAssertionSpec(
    verbs: IAssertionVerbFactory,
    containsDefaultTranslationOf: String,
    containsAtLeastTriple: Triple<String, (String, String) -> String, IAssertionPlant<CharSequence>.(Int, ITranslatable, Array<out ITranslatable>) -> IAssertionPlant<CharSequence>>,
    containsAtMostTriple: Triple<String, (String, String) -> String, IAssertionPlant<CharSequence>.(Int, ITranslatable, Array<out ITranslatable>) -> IAssertionPlant<CharSequence>>,
    containsAtMostIgnoringCaseTriple: Triple<String, (String, String) -> String, IAssertionPlant<CharSequence>.(Int, ITranslatable, Array<out ITranslatable>) -> IAssertionPlant<CharSequence>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<CharSequence>(
        containsAtLeastTriple.first to mapToCreateAssertion { containsAtLeastTriple.third(this, 2, AssertionVerb.ASSERT, arrayOf()) },
        containsAtMostTriple.first to mapToCreateAssertion { containsAtMostTriple.third(this, 2, AssertionVerb.ASSERT, arrayOf()) },
        containsAtMostIgnoringCaseTriple.first to mapToCreateAssertion { containsAtMostIgnoringCaseTriple.third(this, 2, AssertionVerb.ASSERT, arrayOf()) }
    ) {})

    include(object : ch.tutteli.atrium.spec.assertions.CheckingAssertionSpec<String>(verbs,
        checkingTriple(containsAtLeastTriple.first, { containsAtLeastTriple.third(this, 2, AssertionVerb.ASSERT, arrayOf()) }, "assert a, assert b", "a"),
        checkingTriple(containsAtMostTriple.first, { containsAtMostTriple.third(this, 2, AssertionVerb.ASSERT, arrayOf()) }, "assert", "assert, assert and assert"),
        checkingTriple(containsAtMostIgnoringCaseTriple.first, { containsAtMostIgnoringCaseTriple.third(this, 2, AssertionVerb.ASSERT, arrayOf()) }, "Assert aSSert", "assert Assert AsSert")
    ) {})

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val assert: (CharSequence) -> IAssertionPlant<CharSequence> = verbs::checkImmediately
    val expect = verbs::checkException

    val text = "Assert - assert, assert, assert - ASSERT; expect the thrown exception"
    val fluent = assert(text)

    val (_, containsAtLeastTest, containsAtLeastFunArr) = containsAtLeastTriple
    fun IAssertionPlant<CharSequence>.containsAtLeastFun(atLeast: Int, a: ITranslatable, vararg aX: ITranslatable)
        = containsAtLeastFunArr(atLeast, a, aX)

    val (_, containsAtMostTest, containsAtMostFunArr) = containsAtMostTriple
    fun IAssertionPlant<CharSequence>.containsAtMostFun(atLeast: Int, a: ITranslatable, vararg aX: ITranslatable)
        = containsAtMostFunArr(atLeast, a, aX)

    val (_, containsAtMostIgnoringCase, containsAtMostIgnoringCaseFunArr) = containsAtMostIgnoringCaseTriple
    fun IAssertionPlant<CharSequence>.containsAtMostIgnoringCaseFun(atLeast: Int, a: ITranslatable, vararg aX: ITranslatable)
        = containsAtMostIgnoringCaseFunArr(atLeast, a, aX)

    prefixedDescribe("fun $containsDefaultTranslationOf") {

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
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(AT_MOST) } }
            }
            test("${containsAtMostIgnoringCase(AssertionVerb.ASSERT.toString(), "4 times")} throws AssertionError") {
                expect {
                    fluent.containsAtMostIgnoringCaseFun(4, AssertionVerb.ASSERT)
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(AT_MOST) } }
            }
        }
    }
})
