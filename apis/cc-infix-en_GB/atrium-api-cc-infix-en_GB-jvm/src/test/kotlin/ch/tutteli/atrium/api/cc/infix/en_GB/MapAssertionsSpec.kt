package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Empty
import ch.tutteli.atrium.creating.Assert

class MapAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAssertionsSpec(
    AssertionVerbFactory,
    Assert<Map<String, Int>>::containsKey.name to Companion::containsKey,
    Assert<Map<String, Int>>::hasSize.name to Companion::hasSize,
    "${Assert<Map<String, Int>>::toBe.name} ${Empty::class.simpleName}" to Companion::isEmpty,
    "${Assert<Map<String, Int>>::notToBe.name} ${Empty::class.simpleName}" to Companion::isNotEmpty
) {
    companion object {
        private fun containsKey(plant: Assert<Map<String, Int>>, key: String): Assert<Map<String, Int>>
            = plant containsKey key

        private fun hasSize(plant: Assert<Map<String, Int>>, size: Int): Assert<Map<String, Int>>
            = plant hasSize size

        private fun isEmpty(plant: Assert<Map<String, Int>>)
            = plant toBe Empty

        private fun isNotEmpty(plant: Assert<Map<String, Int>>)
            = plant notToBe Empty
    }
}

