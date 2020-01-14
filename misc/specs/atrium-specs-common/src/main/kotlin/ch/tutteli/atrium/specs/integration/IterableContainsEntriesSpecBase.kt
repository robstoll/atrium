package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.cc.en_GB.returnValueOf
import ch.tutteli.atrium.api.fluent.en_GB.isGreaterThan
import ch.tutteli.atrium.api.fluent.en_GB.isLessThan
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.dsl.Root
import org.spekframework.spek2.style.specification.Suite
import org.spekframework.spek2.style.specification.describe
import kotlin.reflect.KFunction
import kotlin.reflect.KFunction0

abstract class IterableContainsEntriesSpecBase(
    spec: Root.() -> Unit
) : IterableContainsSpecBase(spec) {
    init {
        isLessThanFun = Expect<Double>::isLessThan.name
        isGreaterThanFun = Expect<Double>::isGreaterThan.name
        toBeFun = fun1<Double, Double>(Expect<Double>::toBe).name
        //TODO remove with 0.10.0
        @Suppress("DEPRECATION") val f: (KFunction0<Int>) -> Assert<Int> = expect(1).asAssert()::returnValueOf
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
