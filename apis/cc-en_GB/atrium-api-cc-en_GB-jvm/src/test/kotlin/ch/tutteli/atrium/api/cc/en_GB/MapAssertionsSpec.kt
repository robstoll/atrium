package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.mapArguments
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import kotlin.reflect.KFunction3

class MapAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAssertionsSpec(
    AssertionVerbFactory,
    containsFun.name to Assert<Map<out String, Int>>::contains,
    containsNullableFun.name to Assert<Map<out String?, Int?>>::contains,
    "${containsKeyWithValueAssertionsFun.name} ${KeyValue::class.simpleName}" to Companion::contains,
    "${containsKeyWithNullableValueAssertionsFun.name} ${KeyNullableValue::class.simpleName}" to Companion::containsNullable,
    Assert<Map<out String, *>>::containsKey.name to Assert<Map<out String, *>>::containsKey,
    Assert<Map<out String?, *>>::containsKey.name to Assert<Map<out String?, *>>::containsKey,
    Assert<Map<out String, *>>::containsKey.name to Assert<Map<out String, *>>::containsNotKey,
    Assert<Map<out String?, *>>::containsKey.name to Assert<Map<out String?, *>>::containsNotKey,
    Assert<Map<*, *>>::hasSize.name to Assert<Map<*, *>>::hasSize,
    Assert<Map<*, *>>::isEmpty.name to Assert<Map<*, *>>::isEmpty,
    Assert<Map<*, *>>::isNotEmpty.name to Assert<Map<*, *>>::isNotEmpty
){
    companion object {
        private val containsFun : KFunction3<Assert<Map<out String, Int>>, Pair<String, Int>, Array<out Pair<String, Int>>, Assert<Map<out String, Int>>> = Assert<Map<out String, Int>>::contains
        private val containsNullableFun : KFunction3<Assert<Map<out String?, Int?>>, Pair<String?, Int?>, Array<out Pair<String?, Int?>>, Assert<Map<out String?, Int?>>> = Assert<Map<out String?, Int?>>::contains
        private val containsKeyWithValueAssertionsFun : KFunction3<Assert<Map<out String, Int>>, KeyValue<String, Int, Assert<Int>.() -> Unit>, Array<out KeyValue<String, Int, Assert<Int>.() -> Unit>>, Assert<Map<out String, Int>>> = Assert<Map<out String, Int>>::contains
        private val containsKeyWithNullableValueAssertionsFun : KFunction3<Assert<Map<out String?, Int?>>, KeyValue<String?, Int, (Assert<Int>.() -> Unit)?>, Array<out KeyValue<String?, Int, (Assert<Int>.() -> Unit)?>>, Assert<Map<out String?, Int?>>> = Assert<Map<out String?, Int?>>::contains

        fun contains(plant: Assert<Map<out String, Int>>, keyValue: Pair<String, Assert<Int>.() -> Unit>, otherKeyValues: Array<out Pair<String, Assert<Int>.() -> Unit>>)
            = mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                plant.contains(first, *others)
            }

        fun containsNullable(plant: Assert<Map<out String?, Int?>>, keyValue: Pair<String?, (Assert<Int>.() -> Unit)?>, otherKeyValues: Array<out Pair<String?, (Assert<Int>.() -> Unit)?>>)
            = mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                plant.contains(first, *others)
            }
    }
}
