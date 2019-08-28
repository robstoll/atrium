@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.messageContains
import ch.tutteli.atrium.api.cc.en_GB.returnValueOf
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import kotlin.reflect.KFunction1

//TODO remove with 1.0.0
fun SpecBody.absentSubjectTests(
    verbs: AssertionVerbFactory,
    testeeFun: Assert<Iterable<Double?>>.((Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>) -> Assert<Iterable<Double?>>
) {
    val assert: (Iterable<Double?>) -> Assert<Iterable<Double?>> = verbs::checkImmediately
    val expect = verbs::checkException
    context("${IterableContainsEntriesSpecBase.returnValueOfFun}(...), absent subject and explanation required") {
        test("empty iterable, states that iterable was empty") {
            expect {
                assert(setOf()).testeeFun({
                    AssertImpl.feature.returnValueOf1(
                        this,
                        subject::compareTo,
                        2.0,
                        "compareTo"
                    ).toBe(0)
                }, arrayOf())
            }.toThrow<AssertionError> { messageContains(DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE.getDefault()) }
        }
        test("only null, states that iterable only returned null") {
            expect {
                assert(listOf(null, null)).testeeFun({
                    val f: KFunction1<Double, Int> = subject::compareTo
                    returnValueOf(f, 2.0)
                }, arrayOf())
            }.toThrow<AssertionError> { messageContains(DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_ONLY_NULL.getDefault()) }
        }

        val list = listOf(null, 1.0, null, 3.0)
        test("$list, it outputs explanation (since we have a non-null entry)") {
            expect {
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
                    "${TO_BE.getDefault()}: 0"
                )
            }
        }
    }
}
