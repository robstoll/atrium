package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.ListGetNullableOption
import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.ListGetOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import kotlin.reflect.KFunction2

class ListFeatureAssertionsSpec : ch.tutteli.atrium.spec.integration.ListFeatureAssertionsSpec(
    AssertionVerbFactory,
    getPlantFun.name to Companion::getPlant,
    getFun.name to Companion::get,
    getNullablePlantFun.name to Companion::getNullablePlant,
    getNullableFun.name to Companion::getNullable

){
    companion object {
        val getPlantFun: KFunction2<Assert<List<Int>>, Int, Assert<Int>> = Assert<List<Int>>::get
        val getFun: KFunction2<Assert<List<Int>>, Index, ListGetOption<Int, List<Int>>> = Assert<List<Int>>::get
        val getNullablePlantFun: KFunction2<Assert<List<Int?>>, Int, AssertionPlantNullable<Int?>> = Assert<List<Int?>>::getNullable
        val getNullableFun: KFunction2<Assert<List<Int?>>, Index, ListGetNullableOption<Int?, List<Int?>>> = Assert<List<Int?>>::getNullable

        fun getPlant(plant: Assert<List<Int>>, index: Int)
            = plant get index

        fun get(plant: Assert<List<Int>>, index: Int, assertionCreator: Assert<Int>.() -> Unit)
            = plant get Index(index) assertIt { assertionCreator() }

        fun getNullablePlant(plant: Assert<List<Int?>>, index: Int)
            = plant getNullable index

        fun getNullable(plant: Assert<List<Int?>>, index: Int, assertionCreator: AssertionPlantNullable<Int?>.() -> Unit)
            = plant getNullable Index(index) assertIt { assertionCreator() }
    }
}
