package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3

class ListFeatureAssertionsSpec : ch.tutteli.atrium.spec.integration.ListFeatureAssertionsSpec(
    AssertionVerbFactory,
    getPlantFun.name to getPlantFun,
    getFun.name to getFun,
    getNullablePlantFun.name to getNullablePlantFun,
    getNullableFun.name to getNullableFun
){
    companion object {
        val getPlantFun: KFunction2<Assert<List<Int>>, Int, Assert<Int>> = Assert<List<Int>>::get
        val getFun: KFunction3<Assert<List<Int>>, Int, Assert<Int>.() -> Unit, Assert<List<Int>>> = Assert<List<Int>>::get
        val getNullablePlantFun: KFunction2<Assert<List<Int?>>, Int, AssertionPlantNullable<Int?>> = Assert<List<Int?>>::getNullable
        val getNullableFun: KFunction3<Assert<List<Int?>>, Int, AssertionPlantNullable<Int?>.() -> Unit, Assert<List<Int?>>> = Assert<List<Int?>>::getNullable
    }
}
