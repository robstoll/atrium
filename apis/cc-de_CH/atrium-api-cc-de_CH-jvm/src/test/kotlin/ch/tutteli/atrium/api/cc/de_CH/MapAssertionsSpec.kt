package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.mapArguments
import kotlin.reflect.KFunction3

class MapAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAssertionsSpec(
    AssertionVerbFactory,
    containsFun.name to Assert<Map<out String, Int>>::enthaelt,
    containsNullableFun.name to Assert<Map<out String?, Int?>>::enthaeltNullable,
    "${containsKeyWithValueAssertionsFun.name} ${KeyValue::class.simpleName}" to Companion::contains,
    "${containsKeyWithNullableValueAssertionsFun.name} ${KeyNullableValue::class.simpleName}" to Companion::containsNullable,
    Assert<Map<out String, *>>::enthaeltKey.name to Assert<Map<out String, *>>::enthaeltKey,
    "${Assert<Map<out String?, *>>::enthaeltKey.name} for nullable" to Assert<Map<out String?, *>>::enthaeltKey,
    Assert<Map<out String, *>>::enthaeltNichtKey.name to Assert<Map<out String, *>>::enthaeltNichtKey,
    "${Assert<Map<out String?, *>>::enthaeltNichtKey.name} for nullable" to Assert<Map<out String?, *>>::enthaeltNichtKey,
    Assert<Map<*, *>>::hatDieGroesse.name to Assert<Map<*, *>>::hatDieGroesse,
    Assert<Map<*, *>>::istLeer.name to Assert<Map<*, *>>::istLeer,
    Assert<Map<*, *>>::istNichtLeer.name to Assert<Map<*, *>>::istNichtLeer
){
    companion object {
        private val containsFun : KFunction3<Assert<Map<out String, Int>>, Pair<String, Int>, Array<out Pair<String, Int>>, Assert<Map<out String, Int>>> = Assert<Map<out String, Int>>::enthaelt
        private val containsNullableFun : KFunction3<Assert<Map<out String?, Int?>>, Pair<String?, Int?>, Array<out Pair<String?, Int?>>, Assert<Map<out String?, Int?>>> = Assert<Map<out String?, Int?>>::enthaeltNullable
        private val containsKeyWithValueAssertionsFun : KFunction3<Assert<Map<out String, Int>>, KeyValue<String, Int>, Array<out KeyValue<String, Int>>, Assert<Map<out String, Int>>> = Assert<Map<out String, Int>>::enthaelt
        private val containsKeyWithNullableValueAssertionsFun : KFunction3<Assert<Map<out String?, Int?>>, KeyNullableValue<String?, Int>, Array<out KeyNullableValue<String?, Int>>, Assert<Map<out String?, Int?>>> = Assert<Map<out String?, Int?>>::enthaeltNullable

        fun contains(plant: Assert<Map<out String, Int>>, keyValue: Pair<String, Assert<Int>.() -> Unit>, otherKeyValues: Array<out Pair<String, Assert<Int>.() -> Unit>>)
            = mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            plant.enthaelt(first, *others)
        }

        fun containsNullable(plant: Assert<Map<out String?, Int?>>, keyValue: Pair<String?, (Assert<Int>.() -> Unit)?>, otherKeyValues: Array<out Pair<String?, (Assert<Int>.() -> Unit)?>>)
            = mapArguments(keyValue, otherKeyValues).to { KeyNullableValue(it.first, it.second) }.let { (first, others) ->
            plant.enthaeltNullable(first, *others)
        }
    }
}

