package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import kotlin.reflect.KProperty1
import kotlin.reflect.KFunction2

class CollectionFeatureAssertionsSpec : ch.tutteli.atrium.spec.integration.CollectionFeatureAssertionsSpec(
    AssertionVerbFactory,
    sizeVal.name to sizeVal,
    sizeFun.name to sizeFun
){
    companion object {
        private val sizeVal: KProperty1<Assert<Collection<String>>, Assert<Int>> = Assert<Collection<String>>::size
        private val sizeFun: KFunction2<Assert<Collection<String>>, Assert<Int>.() -> Unit, Assert<Collection<String>>> = Assert<Collection<String>>::size
    }
}
