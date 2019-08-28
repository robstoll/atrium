@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.isGreaterThan
import ch.tutteli.atrium.api.cc.en_GB.isLessThan
import ch.tutteli.atrium.api.cc.en_GB.returnValueOf
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.Spec
import org.jetbrains.spek.api.dsl.SpecBody
import kotlin.reflect.KFunction
import kotlin.reflect.KFunction0

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class IterableContainsEntriesSpecBase(verbs: AssertionVerbFactory, spec: Spec.() -> Unit) : IterableContainsSpecBase(spec) {
    init {
        val plant: Assert<Double> = verbs.checkImmediately(1.0)
        isLessThanFun = plant::isLessThan.name
        isGreaterThanFun = plant::isGreaterThan.name
        toBeFun = plant::toBe.name
        //TODO make simpler once https://youtrack.jetbrains.com/issue/KT-12963 is fixed
        val f: (KFunction0<Int>) -> Assert<Int> = plant::returnValueOf
        returnValueOfFun = (f as KFunction<*>).name
    }

    companion object {
        var isLessThanFun = ""
        var isGreaterThanFun = ""
        var toBeFun = ""
        var returnValueOfFun = ""
        val anEntryWhich = DescriptionIterableAssertion.AN_ENTRY_WHICH.getDefault()
        val isLessThanDescr = DescriptionComparableAssertion.IS_LESS_THAN.getDefault()
        val isGreaterThanDescr = DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()
        val toBeDescr = TO_BE.getDefault()
        val isDescr = IS.getDefault()

        fun SpecBody.nonNullableCases(
            describePrefix: String,
            containsPair: Pair<String, Assert<Iterable<Double>>.(Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>) -> Assert<Iterable<Double>>>,
            containsNullablePair: Pair<String, Assert<Iterable<Double?>>.((Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>) -> Assert<Iterable<Double?>>>,
            action: SpecBody.(Assert<Iterable<Double>>.(Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>) -> Any) -> Unit
        ) {
            group("$describePrefix describe non-nullable cases") {
                mapOf<String, Assert<Iterable<Double>>.(Assert<Double>.() -> Unit, Array<out Assert<Double>.() -> Unit>) -> Any>(
                    containsPair.first to { a, aX -> containsPair.second(this, a, aX) },
                    containsNullablePair.first to { a, aX -> containsNullablePair.second(this, a, aX) }
                ).forEach { (describe, containsEntriesFunArr) ->
                    describeFun(describe) {
                        action(containsEntriesFunArr)
                    }
                }
            }
        }
    }
}
