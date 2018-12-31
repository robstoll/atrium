package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import kotlin.reflect.KProperty1
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3

class MapFeatureAssertionsSpec : ch.tutteli.atrium.spec.integration.MapFeatureAssertionsSpec(
    AssertionVerbFactory,
    keysVal.name to keysVal,
    keysFun.name to keysFun,
    valuesVal.name to valuesVal,
    valuesFun.name to valuesFun,
    getExistingFun.name to getExistingFun,
    getNullableFun.name to getNullableFun
){
    companion object {
        private val keysVal: KProperty1<Assert<Map<String, Int>>, Assert<Set<String>>> = Assert<Map<String, Int>>::keys
        private val keysFun: KFunction2<Assert<Map<String, Int>>, Assert<Set<String>>.() -> Unit, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::keys
        private val valuesVal: KProperty1<Assert<Map<String, Int>>, Assert<Collection<Int>>> = Assert<Map<String, Int>>::values
        private val valuesFun: KFunction2<Assert<Map<String, Int>>, Assert<Collection<Int>>.() -> Unit, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::values
        private val getExistingFun: KFunction3<Assert<Map<String, Int>>, String, Assert<Int>.() -> Unit, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::getExistierend
        private val getNullableFun: KFunction3<Assert<Map<String, Int?>>, String, AssertionPlantNullable<Int?>.() -> Unit, Assert<Map<String, Int?>>> = Assert<Map<String, Int?>>::getExistierendNullable
    }
}
