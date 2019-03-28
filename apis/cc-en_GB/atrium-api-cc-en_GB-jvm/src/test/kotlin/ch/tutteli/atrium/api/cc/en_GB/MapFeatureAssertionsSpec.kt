package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import kotlin.reflect.KProperty1
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3

class MapFeatureAssertionsSpec : ch.tutteli.atrium.spec.integration.MapFeatureAssertionsSpec(
    AssertionVerbFactory,
    keysVal.name to keysVal,
    keysFun.name to keysFun,
    valuesVal.name to valuesVal,
    valuesFun.name to valuesFun,
    getExistingPlantFun.name to getExistingPlantFun,
    getExistingFun.name to getExistingFun,
    getExistingNullablePlantFun.name to getExistingNullablePlantFun,
    "getExistingNullable fun with creator not implemented in this API" to Companion::getExistingNullable
){
    companion object {
        val keysVal: KProperty1<Assert<Map<String, Int>>, Assert<Set<String>>> = Assert<Map<String, Int>>::keys
        val keysFun: KFunction2<Assert<Map<String, Int>>, Assert<Set<String>>.() -> Unit, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::keys
        val valuesVal: KProperty1<Assert<Map<String, Int>>, Assert<Collection<Int>>> = Assert<Map<String, Int>>::values
        val valuesFun: KFunction2<Assert<Map<String, Int>>, Assert<Collection<Int>>.() -> Unit, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::values
        val getExistingPlantFun: KFunction2<Assert<Map<String, Int>>, String, Assert<Int>> = Assert<Map<String, Int>>::getExisting
        val getExistingFun: KFunction3<Assert<Map<String, Int>>, String, Assert<Int>.() -> Unit, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::getExisting
        val getExistingNullablePlantFun: KFunction2<Assert<Map<String, Int?>>, String, AssertionPlantNullable<Int?>> = Assert<Map<String, Int?>>::getExisting

        fun getExistingNullable(plant: Assert<Map<String, Int?>>, key: String, assertionCreator: AssertionPlantNullable<Int?>.() -> Unit): Assert<Map<String, Int?>>
            = plant.apply { getExisting(key).assertionCreator() }
    }
}
