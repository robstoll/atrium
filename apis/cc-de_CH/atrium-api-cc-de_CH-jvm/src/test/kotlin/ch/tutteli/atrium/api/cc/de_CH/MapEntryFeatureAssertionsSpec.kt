package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import kotlin.reflect.KProperty1
import kotlin.reflect.KFunction2

class MapEntryFeatureAssertionsSpec : ch.tutteli.atrium.spec.integration.MapEntryFeatureAssertionsSpec(
    AssertionVerbFactory,
    keyVal.name to keyVal,
    keyFun.name to keyFun,
    valueVal.name to valueVal,
    valueFun.name to valueFun,
    nullableKeyVal.name to nullableKeyVal,
    nullableValueVal.name to nullableValueVal
){
    companion object {
        private val keyVal: KProperty1<Assert<Map.Entry<String, Int>>, Assert<String>> = Assert<Map.Entry<String, Int>>::key
        private val keyFun: KFunction2<Assert<Map.Entry<String, Int>>, Assert<String>.() -> Unit, Assert<Map.Entry<String, Int>>> = Assert<Map.Entry<String, Int>>::key
        private val valueVal: KProperty1<Assert<Map.Entry<String, Int>>, Assert<Int>> = Assert<Map.Entry<String, Int>>::value
        private val valueFun: KFunction2<Assert<Map.Entry<String, Int>>, Assert<Int>.() -> Unit, Assert<Map.Entry<String, Int>>> = Assert<Map.Entry<String, Int>>::value
        private val nullableKeyVal: KProperty1<Assert<Map.Entry<String?, Int?>>, AssertionPlantNullable<String?>> = Assert<Map.Entry<String?, Int?>>::key
        private val nullableValueVal: KProperty1<Assert<Map.Entry<String?, Int?>>, AssertionPlantNullable<Int?>> = Assert<Map.Entry<String?, Int?>>::value
    }
}
