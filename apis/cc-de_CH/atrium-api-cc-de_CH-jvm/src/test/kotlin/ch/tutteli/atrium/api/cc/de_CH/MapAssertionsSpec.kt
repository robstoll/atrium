package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.mapArguments
import ch.tutteli.atrium.esGilt
import kotlin.reflect.KFunction3

class MapAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAssertionsSpec(
    AssertionVerbFactory,
    containsFun.name to Assert<Map<out String, Int>>::enthaelt,
    containsNullableFun.name to Assert<Map<out String?, Int?>>::enthaelt,
    "${containsKeyWithValueAssertionsFun.name} ${KeyValue::class.simpleName}" to Companion::contains,
    "${containsKeyWithNullableValueAssertionsFun.name} ${KeyValue::class.simpleName}" to Companion::containsNullable,
    Assert<Map<out String, *>>::enthaeltKey.name to Assert<Map<out String, *>>::enthaeltKey,
    Assert<Map<out String?, *>>::enthaeltKey.name to Assert<Map<out String?, *>>::enthaeltKey,
    Assert<Map<out String, *>>::enthaeltNichtKey.name to Assert<Map<out String, *>>::enthaeltNichtKey,
    Assert<Map<out String?, *>>::enthaeltNichtKey.name to Assert<Map<out String?, *>>::enthaeltNichtKey,
    Assert<Map<*, *>>::hatDieGroesse.name to Assert<Map<*, *>>::hatDieGroesse,
    Assert<Map<*, *>>::istLeer.name to Assert<Map<*, *>>::istLeer,
    Assert<Map<*, *>>::istNichtLeer.name to Assert<Map<*, *>>::istNichtLeer
){
    companion object {
        private val containsFun : KFunction3<Assert<Map<out String, Int>>, Pair<String, Int>, Array<out Pair<String, Int>>, Assert<Map<out String, Int>>> = Assert<Map<out String, Int>>::enthaelt
        private val containsNullableFun : KFunction3<Assert<Map<out String?, Int?>>, Pair<String?, Int?>, Array<out Pair<String?, Int?>>, Assert<Map<out String?, Int?>>> = Assert<Map<out String?, Int?>>::enthaelt
        private val containsKeyWithValueAssertionsFun : KFunction3<Assert<Map<out String, Int>>, KeyValue<String, Int>, Array<out KeyValue<String, Int>>, Assert<Map<out String, Int>>> = Assert<Map<out String, Int>>::enthaelt
        private val containsKeyWithNullableValueAssertionsFun : KFunction3<Assert<Map<out String?, Int?>>, KeyValue<String?, Int>, Array<out KeyValue<String?, Int>>, Assert<Map<out String?, Int?>>> = Assert<Map<out String?, Int?>>::enthaelt

        fun contains(plant: Assert<Map<out String, Int>>, keyValue: Pair<String, Assert<Int>.() -> Unit>, otherKeyValues: Array<out Pair<String, Assert<Int>.() -> Unit>>)
            = mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                plant.enthaelt(first, *others)
            }

        fun containsNullable(plant: Assert<Map<out String?, Int?>>, keyValue: Pair<String?, (Assert<Int>.() -> Unit)?>, otherKeyValues: Array<out Pair<String?, (Assert<Int>.() -> Unit)?>>)
            = mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                plant.enthaelt(first, *others)
            }
    }

    @Suppress("unused")
    private fun ambiguityTest() {
        val map = mapOf(1 to "a")
        val nullableKeyMap = mapOf(1 as Int? to "a")
        val nullableValueMap = mapOf(1 to "a" as String?)
        val nullableKeyValueMap = mapOf(1 as Int? to "a" as String?)
        esGilt(map).enthaelt(1 to "a")
        esGilt(map).enthaelt(1 to "a", 2 to "b")
        esGilt(map).enthaelt(KeyValue(1){})
        esGilt(map).enthaelt(KeyValue(1){}, KeyValue(2){})

        esGilt(nullableKeyMap).enthaelt(1 to "a")
        esGilt(nullableKeyMap).enthaelt(1 to "a", 2 to "b")
        esGilt(nullableKeyMap).enthaelt(KeyValue(1){})
        esGilt(nullableKeyMap).enthaelt(KeyValue(1){}, KeyValue(2){})
        esGilt(nullableKeyMap).enthaelt(null to "a")
        esGilt(nullableKeyMap).enthaelt(null to "a", null to "b")
        esGilt(nullableKeyMap).enthaelt(null to "a", 2 to "b")
        esGilt(nullableKeyMap).enthaelt(KeyValue(null){})
        esGilt(nullableKeyMap).enthaelt(KeyValue(null){}, KeyValue(null){})
        esGilt(nullableKeyMap).enthaelt(KeyValue(null){}, KeyValue(2){})

        esGilt(nullableValueMap).enthaelt(1 to "a")
        esGilt(nullableValueMap).enthaelt(1 to "a", 2 to "b")
        esGilt(nullableValueMap).enthaelt(KeyValue(1){})
        esGilt(nullableValueMap).enthaelt(KeyValue(1){}, KeyValue(2){})
        esGilt(nullableValueMap).enthaelt(1 to null)
        esGilt(nullableValueMap).enthaelt(1 to null, 2 to null)
        esGilt(nullableValueMap).enthaelt(1 to null, 2 to "a")
        esGilt(nullableValueMap).enthaelt(KeyValue(1, null))
        esGilt(nullableValueMap).enthaelt(KeyValue(1, null), KeyValue(2, null))
        esGilt(nullableValueMap).enthaelt(KeyValue(1, null), KeyValue(2){})

        esGilt(nullableKeyValueMap).enthaelt(1 to "a")
        esGilt(nullableKeyValueMap).enthaelt(1 to "a", 2 to "b")
        esGilt(nullableKeyValueMap).enthaelt(KeyValue(1){})
        esGilt(nullableKeyValueMap).enthaelt(KeyValue(1){}, KeyValue(2){})

        esGilt(nullableKeyValueMap).enthaelt(null to "a")
        esGilt(nullableKeyValueMap).enthaelt(null to "a", null to "b")
        esGilt(nullableKeyValueMap).enthaelt(null to "a", 2 to "b")
        esGilt(nullableKeyValueMap).enthaelt(KeyValue(null){})
        esGilt(nullableKeyValueMap).enthaelt(KeyValue(null){}, KeyValue(null){})
        esGilt(nullableKeyValueMap).enthaelt(KeyValue(null){}, KeyValue(2){})

        esGilt(nullableKeyValueMap).enthaelt(1 to null)
        esGilt(nullableKeyValueMap).enthaelt(1 to null, 2 to null)
        esGilt(nullableKeyValueMap).enthaelt(1 to null, 2 to "a")
        esGilt(nullableKeyValueMap).enthaelt(KeyValue(1, null))
        esGilt(nullableKeyValueMap).enthaelt(KeyValue(1, null), KeyValue(2, null))
        esGilt(nullableKeyValueMap).enthaelt(KeyValue(1, null), KeyValue(2){})

        esGilt(nullableKeyValueMap).enthaelt(null to null)
        esGilt(nullableKeyValueMap).enthaelt(null to null, null to null)
        esGilt(nullableKeyValueMap).enthaelt(1 to null, null to "a")
        esGilt(nullableKeyValueMap).enthaelt(KeyValue(null, null))
        esGilt(nullableKeyValueMap).enthaelt(KeyValue(null, null), KeyValue(null, null))
        esGilt(nullableKeyValueMap).enthaelt(KeyValue(1, null), KeyValue(null){})
    }
}

