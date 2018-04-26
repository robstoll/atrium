package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class CollectionAssertionsSpec : ch.tutteli.atrium.spec.integration.CollectionAssertionsSpec(
    AssertionVerbFactory,
    Assert<List<Int>>::hasSize.name to Assert<List<Int>>::hasSize,
    "${Assert<List<Int>>::toBe.name} ${Empty::class.simpleName}" to Companion::isEmpty,
    "${Assert<List<Int>>::notToBe.name} ${Empty::class.simpleName}" to Companion::isNotEmpty
) {
    companion object {
        fun isEmpty(plant: Assert<List<Int>>)
            = plant toBe Empty

        fun isNotEmpty(plant: Assert<List<Int>>)
            = plant notToBe Empty
    }
}

