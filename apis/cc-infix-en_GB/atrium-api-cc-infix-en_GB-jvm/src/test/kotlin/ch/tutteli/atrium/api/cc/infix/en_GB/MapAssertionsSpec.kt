package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Empty
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.creating.map.KeyNullableValue
import ch.tutteli.atrium.domain.creating.map.KeyValue
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import kotlin.reflect.KFunction2

class MapAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAssertionsSpec(
    AssertionVerbFactory,
    containsFun.name to Companion::contains,
    containsNullableFun.name to Companion::containsNullable,
    "${containsKeyWithValueAssertionsFun.name} ${KeyValue::class.simpleName}" to Companion::containsKeyWithValueAssertions,
    "${containsKeyWithNullableValueAssertionsFun.name} ${KeyNullableValue::class.simpleName}" to Companion::containsKeyWithNullableValueAssertions,
    Assert<Map<String, Int>>::containsKey.name to Companion::containsKey,
    "${Assert<Map<String?, *>>::containsKey.name} for nullable" to Companion::containsNullableKey,
    Assert<Map<*, *>>::hasSize.name to Companion::hasSize,
    "${Assert<Map<*, *>>::toBe.name} ${Empty::class.simpleName}" to Companion::isEmpty,
    "${Assert<Map<*, *>>::notToBe.name} ${Empty::class.simpleName}" to Companion::isNotEmpty
) {
    companion object {
        private val containsFun : KFunction2<Assert<Map<String, Int>>, Pair<String, Int>, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::contains
        private fun contains(plant: Assert<Map<String, Int>>, pair: Pair<String, Int>, otherPairs: Array<out Pair<String, Int>>): Assert<Map<String, Int>> {
            return if (otherPairs.isEmpty()) {
                plant contains pair
            } else {
                plant contains Pairs(pair, *otherPairs)
            }
        }

        private val containsNullableFun : KFunction2<Assert<Map<String?, Int?>>, Pair<String?, Int?>,Assert<Map<String?, Int?>>> = Assert<Map<String?, Int?>>::containsNullable
        private fun containsNullable(plant: Assert<Map<String?, Int?>>, pair: Pair<String?, Int?>, otherPairs: Array<out Pair<String?, Int?>>): Assert<Map<String?, Int?>> {
            return if (otherPairs.isEmpty()) {
                plant containsNullable pair
            } else {
                plant containsNullable Pairs(pair, *otherPairs)
            }
        }

        private val containsKeyWithValueAssertionsFun : KFunction2<Assert<Map<String, Int>>, KeyValue<String, Int>, Assert<Map<String, Int>>> = Assert<Map<String, Int>>::contains
        private fun containsKeyWithValueAssertions(plant: Assert<Map<String, Int>>, keyValue: KeyValue<String, Int>, otherKeyValues: Array<out KeyValue<String, Int>>): Assert<Map<String, Int>> {
            return if (otherKeyValues.isEmpty()) {
                plant contains keyValue
            } else {
                plant contains All(keyValue, *otherKeyValues)
            }
        }

        private val containsKeyWithNullableValueAssertionsFun : KFunction2<Assert<Map<String?, Int?>>, KeyNullableValue<String, Int>, Assert<Map<String?, Int?>>> = Assert<Map<String?, Int?>>::containsNullable
        private fun containsKeyWithNullableValueAssertions(plant: Assert<Map<String?, Int?>>, keyValue: KeyNullableValue<String?, Int>, otherKeyValues: Array<out KeyNullableValue<String?, Int>>): Assert<Map<String?, Int?>> {
            return if (otherKeyValues.isEmpty()) {
                plant containsNullable keyValue
            } else {
                plant containsNullable All(keyValue, *otherKeyValues)
            }
        }

        private fun containsKey(plant: Assert<Map<String, *>>, key: String)
            = plant containsKey key

        private fun containsNullableKey(plant: Assert<Map<String?, *>>, key: String?)
            = plant containsKey key

        private fun hasSize(plant: Assert<Map<*, *>>, size: Int)
            = plant hasSize size

        private fun isEmpty(plant: Assert<Map<*, *>>)
            = plant toBe Empty

        private fun isNotEmpty(plant: Assert<Map<*, *>>)
            = plant notToBe Empty
    }
}

