package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

class CollectionAssertionsSpec : ch.tutteli.atrium.spec.assertions.CollectionAssertionsSpec(
    AssertionVerbFactory,
    AssertionPlant<List<Int>>::hasSize.name to AssertionPlant<List<Int>>::hasSize,
    "${AssertionPlant<List<Int>>::toBe.name} ${Empty::class.simpleName}" to Companion::isEmpty,
    "${AssertionPlant<List<Int>>::notToBe.name} ${Empty::class.simpleName}" to Companion::isNotEmpty
) {
    companion object {
        fun isEmpty(plant: AssertionPlant<List<Int>>)
            = plant toBe Empty

        fun isNotEmpty(plant: AssertionPlant<List<Int>>)
            = plant notToBe Empty
    }
}

