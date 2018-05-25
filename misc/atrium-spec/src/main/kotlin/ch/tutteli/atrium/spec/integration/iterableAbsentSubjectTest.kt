package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import kotlin.reflect.KFunction1

fun SpecBody.absentSubjectTests(
    verbs: AssertionVerbFactory,
    testeeFun: Assert<Iterable<Double?>>.((Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>) -> Assert<Iterable<Double?>>
) {
    val assert: (Iterable<Double?>) -> Assert<Iterable<Double?>> = verbs::checkImmediately
    val expect = verbs::checkException
    context("${IterableContainsEntriesSpecBase.returnValueOfFun}(...), absent subject and explanation required") {
        test("empty iterable, states that iterable was empty") {
            expect {
                //TODO replace with returnValueOf as soon as https://youtrack.jetbrains.com/issue/KT-17340 is fixed
                assert(setOf()).testeeFun({
                    AssertImpl.feature.returnValueOf1(
                        this,
                        subject::compareTo,
                        2.0,
                        "compareTo"
                    ).toBe(0)
                }, arrayOf())
            }.toThrow<AssertionError> { message { containsDefaultTranslationOf(DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE) } }
        }
        test("only null, states that iterable only returned null") {
            expect {
                assert(listOf(null, null)).testeeFun({
                    //TODO get rid of val as soon as https://youtrack.jetbrains.com/issue/KT-17340 is fixed
                    val f: KFunction1<Double, Int> = subject::compareTo
                    returnValueOf(f, 2.0)
                }, arrayOf())
            }.toThrow<AssertionError> { message { containsDefaultTranslationOf(DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_ONLY_NULL) } }
        }

        val list = listOf(null, 1.0, null, 3.0)
        test("$list, it outputs explanation (since we have a non-null entry)") {
            expect {
                //TODO replace with returnValueOf as soon as https://youtrack.jetbrains.com/issue/KT-17340 is fixed
                assert(list).testeeFun({
                    AssertImpl.feature.returnValueOf1(
                        this,
                        subject::compareTo,
                        2.0,
                        "compareTo"
                    ).toBe(0)
                }, arrayOf())
            }.toThrow<AssertionError> {
                messageContains(
                    "${IterableContainsEntriesSpecBase.anEntryWhich}: ${IterableContainsSpecBase.separator}",
                    "compareTo(2.0):",
                    "${DescriptionAnyAssertion.TO_BE.getDefault()}: 0"
                )
            }
        }
    }
}
