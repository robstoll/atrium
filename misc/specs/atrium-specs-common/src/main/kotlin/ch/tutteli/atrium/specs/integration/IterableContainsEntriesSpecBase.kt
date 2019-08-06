package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.returnValueOf
import ch.tutteli.atrium.api.fluent.en_GB.isGreaterThan
import ch.tutteli.atrium.api.fluent.en_GB.isLessThan
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.dsl.Root
import org.spekframework.spek2.style.specification.Suite
import org.spekframework.spek2.style.specification.describe
import kotlin.reflect.KFunction
import kotlin.reflect.KFunction0

abstract class IterableContainsEntriesSpecBase(verbs: AssertionVerbFactory, spec: Root.() -> Unit) : IterableContainsSpecBase(spec) {
    init {
        val plant: Expect<Double> = verbs.check(1.0)
        isLessThanFun = plant::isLessThan.name
        isGreaterThanFun = plant::isGreaterThan.name
        toBeFun = plant::toBe.name
        //TODO make simpler once https://youtrack.jetbrains.com/issue/KT-12963 is fixed
        val f: (KFunction0<Int>) -> Assert<Int> = plant.asAssert()::returnValueOf
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

        fun Root.nonNullableCases(
            describePrefix: String,
            containsPair: Pair<String, Expect<Iterable<Double>>.(Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>) -> Expect<Iterable<Double>>>,
            containsNullablePair: Pair<String, Expect<Iterable<Double?>>.((Expect<Double>.() -> Unit)?, Array<out (Expect<Double>.() -> Unit)?>) -> Expect<Iterable<Double?>>>,
            action: Suite.(Expect<Iterable<Double>>.(Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>) -> Any) -> Unit
        ) {
            describe("$describePrefix describe non-nullable cases") {
                mapOf<String, Expect<Iterable<Double>>.(Expect<Double>.() -> Unit, Array<out Expect<Double>.() -> Unit>) -> Any>(
                    containsPair.first to { a, aX -> containsPair.second(this, a, aX) },
                    containsNullablePair.first to { a, aX ->
                        @Suppress("UNCHECKED_CAST")
                        containsNullablePair.second(this as Expect<Iterable<Double?>>, a, aX)
                    }
                ).forEach { (describe, containsEntriesFunArr) ->
                    describeFun(describe) {
                        action(containsEntriesFunArr)
                    }
                }
            }
        }
    }
}
