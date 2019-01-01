package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import kotlin.reflect.KFunction3

class ListFeatureAssertionsSpec : ch.tutteli.atrium.spec.integration.ListFeatureAssertionsSpec(
    AssertionVerbFactory,
    getFun.name to getFun,
    getNullableFun.name to getNullableFun
){
    companion object {
        private val getFun: KFunction3<Assert<List<Int>>, Int, Assert<Int>.() -> Unit, Assert<List<Int>>> = Assert<List<Int>>::get
        private val getNullableFun: KFunction3<Assert<List<Int?>>, Int, AssertionPlantNullable<Int?>.() -> Unit, Assert<List<Int?>>> = Assert<List<Int?>>::getNullable
    }
}
