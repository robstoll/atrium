package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Empty
import ch.tutteli.atrium.creating.Assert

class MapAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAssertionsSpec(
    AssertionVerbFactory,
    Assert<Map<String, Int>>::hasSize.name to Assert<Map<String, Int>>::hasSize,
    "${Assert<Map<String, Int>>::toBe.name} ${Empty::class.simpleName}" to Companion::isEmpty,
    "${Assert<Map<String, Int>>::notToBe.name} ${Empty::class.simpleName}" to Companion::isNotEmpty
) {
    companion object {
        fun isEmpty(plant: Assert<Map<String, Int>>)
            = plant toBe Empty

        fun isNotEmpty(plant: Assert<Map<String, Int>>)
            = plant notToBe Empty
    }
}

