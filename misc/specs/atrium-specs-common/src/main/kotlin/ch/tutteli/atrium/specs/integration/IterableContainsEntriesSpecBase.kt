package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.isGreaterThan
import ch.tutteli.atrium.api.fluent.en_GB.isLessThan
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.spekframework.spek2.dsl.Root

abstract class IterableContainsEntriesSpecBase(
    spec: Root.() -> Unit
) : IterableContainsSpecBase(spec) {
    init {
        isLessThanFun = Expect<Double>::isLessThan.name
        isGreaterThanFun = Expect<Double>::isGreaterThan.name
        toBeFun = fun1<Double, Double>(Expect<Double>::toBe).name
        //TODO remove with 1.0.0
        returnValueOfFun = "returnValueOf"
    }

    companion object {
        var isLessThanFun = ""
        var isGreaterThanFun = ""
        var toBeFun = ""
        var returnValueOfFun = ""
        val anEntryWhich = DescriptionIterableAssertion.AN_ENTRY_WHICH.getDefault()
        val isLessThanDescr = DescriptionComparableAssertion.IS_LESS_THAN.getDefault()
        val isGreaterThanDescr = DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()
    }
}
