package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.isGreaterThan
import ch.tutteli.atrium.api.cc.en_UK.isLessThan
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.assertions.DescriptionNumberAssertion
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.dsl.Spec

abstract class IterableContainsEntriesSpecBase(verbs: IAssertionVerbFactory, spec: Spec.() -> Unit) : IterableContainsSpecBase(spec) {
    init {
        isLessThanFun = verbs.checkImmediately(1.0)::isLessThan.name
        isGreaterThanFun = verbs.checkImmediately(1.0)::isGreaterThan.name
        toBeFun = verbs.checkImmediately(1.0)::toBe.name
    }

    companion object {
        var isLessThanFun = ""
        var isGreaterThanFun = ""
        var toBeFun = ""
        val anEntryWhich = DescriptionIterableAssertion.AN_ENTRY_WHICH.getDefault()
        val isLessThanDescr = DescriptionNumberAssertion.IS_LESS_THAN.getDefault()
        val isGreaterThanDescr = DescriptionNumberAssertion.IS_GREATER_THAN.getDefault()
        val toBeDescr = DescriptionAnyAssertion.TO_BE.getDefault()
    }
}
