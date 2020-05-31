// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import kotlin.reflect.KFunction2
import kotlin.reflect.KProperty1

//TODO remove with 1.0.0, no need to migrate to Spek 2
class MapEntryFeatureAssertionsSpec : ch.tutteli.atrium.spec.integration.MapEntryFeatureAssertionsSpec(
    AssertionVerbFactory,
    keyVal.name to keyVal,
    keyFun.name to Companion::key,
    valueVal.name to valueVal,
    valueFun.name to Companion::value,
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

        fun key(plant: Assert<Map.Entry<String, Int>>, assertionCreator: Assert<String>.() -> Unit)
            = plant key { assertionCreator() }

        fun value(plant: Assert<Map.Entry<String, Int>>, assertionCreator: Assert<Int>.() -> Unit)
            = plant value { assertionCreator() }
    }
}
