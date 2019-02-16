package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Empty
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.mapArguments
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import kotlin.reflect.KFunction2

class MapAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAssertionsSpec(
    AssertionVerbFactory,
    containsFun.name to Companion::contains,
    containsNullableFun.name to Companion::containsNullable,
    "${containsKeyWithValueAssertionsFun.name} ${KeyValue::class.simpleName}" to Companion::containsKeyWithValueAssertions,
    "${containsKeyWithNullableValueAssertionsFun.name} ${KeyNullableValue::class.simpleName}" to Companion::containsKeyWithNullableValueAssertions,
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

        private val containsKeyWithNullableValueAssertionsFun : KFunction2<Assert<Map<out String?, Int?>>, KeyNullableValue<String, Int>, Assert<Map<out String?, Int?>>> = Assert<Map<out String?, Int?>>::contains
        private fun containsKeyWithNullableValueAssertions(plant: Assert<Map<out String?, Int?>>, keyValue: Pair<String?, (Assert<Int>.() -> Unit)?>, otherKeyValues: Array<out Pair<String?, (Assert<Int>.() -> Unit)?>>): Assert<Map<out String?, Int?>> {
            return if (otherKeyValues.isEmpty()) {
                plant contains KeyNullableValue(keyValue.first, keyValue.second)
            } else {
                mapArguments(keyValue, otherKeyValues).to { KeyNullableValue(it.first, it.second) }.let{ (first, others) ->
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
}

