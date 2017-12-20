package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.isGreaterThan
import ch.tutteli.atrium.api.cc.en_UK.isLessThan
import ch.tutteli.atrium.api.cc.en_UK.returnValueOf
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.assertions.DescriptionNumberAssertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.dsl.Spec
import kotlin.reflect.KFunction
import kotlin.reflect.KFunction0

abstract class IterableContainsEntriesSpecBase(verbs: IAssertionVerbFactory, spec: Spec.() -> Unit) : IterableContainsSpecBase(spec) {
    init {
        val plant: AssertionPlant<Double> = verbs.checkImmediately(1.0)
        isLessThanFun = plant::isLessThan.name
        isGreaterThanFun = plant::isGreaterThan.name
        toBeFun = plant::toBe.name
        //TODO make simpler once https://youtrack.jetbrains.com/issue/KT-12963 is fixed
        val f: (KFunction0<Int>) -> AssertionPlant<Int> = plant::returnValueOf
        returnValueOfFun = (f as KFunction<*>).name
    }

    companion object {
        var isLessThanFun = ""
        var isGreaterThanFun = ""
        var toBeFun = ""
        var returnValueOfFun = ""
        val anEntryWhich = DescriptionIterableAssertion.AN_ENTRY_WHICH.getDefault()
        val isLessThanDescr = DescriptionNumberAssertion.IS_LESS_THAN.getDefault()
        val isGreaterThanDescr = DescriptionNumberAssertion.IS_GREATER_THAN.getDefault()
        val toBeDescr = DescriptionAnyAssertion.TO_BE.getDefault()
    }
}
