package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.isGreaterThan
import ch.tutteli.atrium.api.cc.en_GB.isLessThan
import ch.tutteli.atrium.api.cc.en_GB.returnValueOf
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.Spec
import kotlin.reflect.KFunction
import kotlin.reflect.KFunction0

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
        val toBeDescr = DescriptionAnyAssertion.TO_BE.getDefault()
        val isDescr = DescriptionBasic.IS.getDefault()
    }
}
