// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Empty
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.mapArguments
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.verbs.internal.assert
import kotlin.reflect.KFunction2

//TODO remove with 1.0.0, no need to migrate to Spek 2
class MapAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAssertionsSpec(
    AssertionVerbFactory,
    containsFun.name to Companion::contains,
    containsNullableFun.name to Companion::containsNullable,
    "${containsKeyWithValueAssertionsFun.name} ${KeyValue::class.simpleName}" to Companion::containsKeyWithValueAssertions,
    "${containsKeyWithNullableValueAssertionsFun.name} ${KeyValue::class.simpleName}" to Companion::containsKeyWithNullableValueAssertions,
    Assert<Map<out String, Int>>::containsKey.name to Companion::containsKey,
    Assert<Map<out String?, *>>::containsKey.name to Companion::containsNullableKey,
    Assert<Map<out String, Int>>::containsNotKey.name to Companion::containsNotKey,
    Assert<Map<out String?, *>>::containsNotKey.name to Companion::containsNotNullableKey,
    Assert<Map<*, *>>::hasSize.name to Companion::hasSize,
    "${Assert<Map<*, *>>::toBe.name} ${Empty::class.simpleName}" to Companion::isEmpty,
    "${Assert<Map<*, *>>::notToBe.name} ${Empty::class.simpleName}" to Companion::isNotEmpty
) {
    companion object {
        private val containsFun : KFunction2<Assert<Map<out String, Int>>, Pair<String, Int>, Assert<Map<out String, Int>>> = Assert<Map<out String, Int>>::contains
        private fun contains(plant: Assert<Map<out String, Int>>, pair: Pair<String, Int>, otherPairs: Array<out Pair<String, Int>>): Assert<Map<out String, Int>> {
            return if (otherPairs.isEmpty()) {
                plant contains (pair.first to pair.second)
            } else {
                plant contains Pairs(pair, *otherPairs)
            }
        }

        private val containsNullableFun : KFunction2<Assert<Map<out String?, Int?>>, Pair<String?, Int?>,Assert<Map<out String?, Int?>>> = Assert<Map<out String?, Int?>>::contains
        private fun containsNullable(plant: Assert<Map<out String?, Int?>>, pair: Pair<String?, Int?>, otherPairs: Array<out Pair<String?, Int?>>): Assert<Map<out String?, Int?>> {
            return if (otherPairs.isEmpty()) {
                plant contains (pair.first to pair.second)
            } else {
                plant contains Pairs(pair, *otherPairs)
            }
        }

        private val containsKeyWithValueAssertionsFun : KFunction2<Assert<Map<out String, Int>>, KeyValue<String, Int>, Assert<Map<out String, Int>>> = Assert<Map<out String, Int>>::contains
        private fun containsKeyWithValueAssertions(plant: Assert<Map<out String, Int>>, keyValue: Pair<String, Assert<Int>.() -> Unit>, otherKeyValues: Array<out Pair<String, Assert<Int>.() -> Unit>>) : Assert<Map<out String, Int>> {
            return if (otherKeyValues.isEmpty()) {
                plant contains KeyValue(keyValue.first, keyValue.second)
            } else {
                mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let{ (first, others) ->
                    plant contains All(first, *others)
                }
            }
        }

        private val containsKeyWithNullableValueAssertionsFun : KFunction2<Assert<Map<out String?, Int?>>, KeyValue<String, Int>, Assert<Map<out String?, Int?>>> = Assert<Map<out String?, Int?>>::contains
        private fun containsKeyWithNullableValueAssertions(plant: Assert<Map<out String?, Int?>>, keyValue: Pair<String?, (Assert<Int>.() -> Unit)?>, otherKeyValues: Array<out Pair<String?, (Assert<Int>.() -> Unit)?>>): Assert<Map<out String?, Int?>> {
            return if (otherKeyValues.isEmpty()) {
                plant contains KeyValue(keyValue.first, keyValue.second)
            } else {
                mapArguments(keyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let{ (first, others) ->
                    plant contains All(first, *others)
                }
            }
        }

        private fun containsKey(plant: Assert<Map<out String, *>>, key: String)
            = plant containsKey key

        private fun containsNullableKey(plant: Assert<Map<out String?, *>>, key: String?)
            = plant containsKey key

        private fun containsNotKey(plant: Assert<Map<out String, *>>, key: String)
            = plant containsNotKey key

        private fun containsNotNullableKey(plant: Assert<Map<out String?, *>>, key: String?)
            = plant containsNotKey key

        private fun hasSize(plant: Assert<Map<*, *>>, size: Int)
            = plant hasSize size

        private fun isEmpty(plant: Assert<Map<*, *>>)
            = plant toBe Empty

        private fun isNotEmpty(plant: Assert<Map<*, *>>)
            = plant notToBe Empty
    }

    @Suppress("unused")
    private fun ambiguityTest() {
        val map = mapOf(1 to "a")
        val nullableKeyMap = mapOf(1 as Int? to "a")
        val nullableValueMap = mapOf(1 to "a" as String?)
        val nullableKeyValueMap = mapOf(1 as Int? to "a" as String?)
        assert(map) contains (1 to "a")
        assert(map) contains Pairs(1 to "a", 2 to "b")
        assert(map) contains KeyValue(1){}
        assert(map) contains All(KeyValue(1){}, KeyValue(2){})

        assert(nullableKeyMap) contains (1 to "a")
        assert(nullableKeyMap) contains Pairs(1 to "a", 2 to "b")
        assert(nullableKeyMap) contains KeyValue(1){}
        assert(nullableKeyMap) contains All(KeyValue(1){}, KeyValue(2){})
        assert(nullableKeyMap) contains (null to "a")
        assert(nullableKeyMap) contains Pairs(null to "a", null to "b")
        assert(nullableKeyMap) contains Pairs(null to "a", 2 to "b")
        assert(nullableKeyMap) contains KeyValue(null){}
        assert(nullableKeyMap) contains All(KeyValue(null){}, KeyValue(null){})
        assert(nullableKeyMap) contains All(KeyValue(null){}, KeyValue(2){})

        assert(nullableValueMap) contains (1 to "a")
        assert(nullableValueMap) contains Pairs(1 to "a", 2 to "b")
        assert(nullableValueMap) contains KeyValue(1){}
        assert(nullableValueMap) contains All(KeyValue(1){}, KeyValue(2){})
        assert(nullableValueMap) contains (1 to null)
        assert(nullableValueMap) contains Pairs(1 to null, 2 to null)
        assert(nullableValueMap) contains Pairs(1 to null, 2 to "a")
        assert(nullableValueMap) contains KeyValue(1, null)
        assert(nullableValueMap) contains All(KeyValue(1, null), KeyValue(2, null))
        assert(nullableValueMap) contains All(KeyValue(1, null), KeyValue(2){})

        assert(nullableKeyValueMap) contains (1 to "a")
        assert(nullableKeyValueMap) contains Pairs(1 to "a", 2 to "b")
        assert(nullableKeyValueMap) contains All(KeyValue(1){})
        assert(nullableKeyValueMap) contains All(KeyValue(1){}, KeyValue(2){})

        assert(nullableKeyValueMap) contains (null to "a")
        assert(nullableKeyValueMap) contains Pairs(null to "a", null to "b")
        assert(nullableKeyValueMap) contains Pairs(null to "a", 2 to "b")
        assert(nullableKeyValueMap) contains KeyValue(null){}
        assert(nullableKeyValueMap) contains All(KeyValue(null){}, KeyValue(null){})
        assert(nullableKeyValueMap) contains All(KeyValue(null){}, KeyValue(2){})

        assert(nullableKeyValueMap) contains (1 to null)
        assert(nullableKeyValueMap) contains Pairs(1 to null, 2 to null)
        assert(nullableKeyValueMap) contains Pairs(1 to null, 2 to "a")
        assert(nullableKeyValueMap) contains KeyValue(1, null)
        assert(nullableKeyValueMap) contains All(KeyValue(1, null), KeyValue(2, null))
        assert(nullableKeyValueMap) contains All(KeyValue(1, null), KeyValue(2){})

        //TODO don't use Pair but null to null as soon as https://youtrack.jetbrains.com/issue/KT-30496 is fiex
        assert(nullableKeyValueMap) contains Pair(null, null)
        assert(nullableKeyValueMap) contains Pairs(Pair(null, null), null to null as String?)
        assert(nullableKeyValueMap) contains Pairs(1 to null, null to "a")
        assert(nullableKeyValueMap) contains KeyValue(null, null)
        assert(nullableKeyValueMap) contains All(KeyValue(null, null), KeyValue(null, null))
        assert(nullableKeyValueMap) contains All(KeyValue(1, null), KeyValue(null){})
    }
}

