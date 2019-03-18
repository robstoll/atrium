package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.mapArguments
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.verbs.internal.assert
import kotlin.reflect.KFunction3

class MapAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAssertionsSpec(
    AssertionVerbFactory,
    containsFun.name to Assert<Map<out String, Int>>::contains,
    containsNullableFun.name to Assert<Map<out String?, Int?>>::contains,
    "${containsKeyWithValueAssertionsFun.name} ${KeyValue::class.simpleName}" to Companion::contains,
    "${containsKeyWithNullableValueAssertionsFun.name} ${KeyValue::class.simpleName}" to Companion::containsNullable,
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
        private val containsKeyWithValueAssertionsFun : KFunction3<Assert<Map<out String, Int>>, KeyValue<String, Int>, Array<out KeyValue<String, Int>>, Assert<Map<out String, Int>>> = Assert<Map<out String, Int>>::contains
        private val containsKeyWithNullableValueAssertionsFun : KFunction3<Assert<Map<out String?, Int?>>, KeyValue<String?, Int>, Array<out KeyValue<String?, Int>>, Assert<Map<out String?, Int?>>> = Assert<Map<out String?, Int?>>::contains

        fun contains(plant: Assert<Map<out String, Int>>, keyValue: Pair<String, Assert<Int>.() -> Unit>, otherKeyValues: Array<out Pair<String, Assert<Int>.() -> Unit>>)
            = mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                plant.contains(first, *others)
            }

        fun containsNullable(plant: Assert<Map<out String?, Int?>>, keyValue: Pair<String?, (Assert<Int>.() -> Unit)?>, otherKeyValues: Array<out Pair<String?, (Assert<Int>.() -> Unit)?>>)
            = mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                plant.contains(first, *others)
            }
    }

    @Suppress("unused")
    private fun ambiguityTest() {
        val map = mapOf(1 to "a")
        val nullableKeyMap = mapOf(1 as Int? to "a")
        val nullableValueMap = mapOf(1 to "a" as String?)
        val nullableKeyValueMap = mapOf(1 as Int? to "a" as String?)
        assert(map).contains(1 to "a")
        assert(map).contains(1 to "a", 2 to "b")
        assert(map).contains(KeyValue(1){})
        assert(map).contains(KeyValue(1){}, KeyValue(2){})

        assert(nullableKeyMap).contains(1 to "a")
        assert(nullableKeyMap).contains(1 to "a", 2 to "b")
        assert(nullableKeyMap).contains(KeyValue(1){})
        assert(nullableKeyMap).contains(KeyValue(1){}, KeyValue(2){})
        assert(nullableKeyMap).contains(null to "a")
        assert(nullableKeyMap).contains(null to "a", null to "b")
        assert(nullableKeyMap).contains(null to "a", 2 to "b")
        assert(nullableKeyMap).contains(KeyValue(null){})
        assert(nullableKeyMap).contains(KeyValue(null){}, KeyValue(null){})
        assert(nullableKeyMap).contains(KeyValue(null){}, KeyValue(2){})

        assert(nullableValueMap).contains(1 to "a")
        assert(nullableValueMap).contains(1 to "a", 2 to "b")
        assert(nullableValueMap).contains(KeyValue(1){})
        assert(nullableValueMap).contains(KeyValue(1){}, KeyValue(2){})
        assert(nullableValueMap).contains(1 to null)
        assert(nullableValueMap).contains(1 to null, 2 to null)
        assert(nullableValueMap).contains(1 to null, 2 to "a")
        assert(nullableValueMap).contains(KeyValue(1, null))
        assert(nullableValueMap).contains(KeyValue(1, null), KeyValue(2, null))
        assert(nullableValueMap).contains(KeyValue(1, null), KeyValue(2){})

        assert(nullableKeyValueMap).contains(1 to "a")
        assert(nullableKeyValueMap).contains(1 to "a", 2 to "b")
        assert(nullableKeyValueMap).contains(KeyValue(1){})
        assert(nullableKeyValueMap).contains(KeyValue(1){}, KeyValue(2){})

        assert(nullableKeyValueMap).contains(null to "a")
        assert(nullableKeyValueMap).contains(null to "a", null to "b")
        assert(nullableKeyValueMap).contains(null to "a", 2 to "b")
        assert(nullableKeyValueMap).contains(KeyValue(null){})
        assert(nullableKeyValueMap).contains(KeyValue(null){}, KeyValue(null){})
        assert(nullableKeyValueMap).contains(KeyValue(null){}, KeyValue(2){})

        assert(nullableKeyValueMap).contains(1 to null)
        assert(nullableKeyValueMap).contains(1 to null, 2 to null)
        assert(nullableKeyValueMap).contains(1 to null, 2 to "a")
        assert(nullableKeyValueMap).contains(KeyValue(1, null))
        assert(nullableKeyValueMap).contains(KeyValue(1, null), KeyValue(2, null))
        assert(nullableKeyValueMap).contains(KeyValue(1, null), KeyValue(2){})

        assert(nullableKeyValueMap).contains(null to null)
        assert(nullableKeyValueMap).contains(null to null, null to null)
        assert(nullableKeyValueMap).contains(1 to null, null to "a")
        assert(nullableKeyValueMap).contains(KeyValue(null, null))
        assert(nullableKeyValueMap).contains(KeyValue(null, null), KeyValue(null, null))
        assert(nullableKeyValueMap).contains(KeyValue(1, null), KeyValue(null){})
    }
}
