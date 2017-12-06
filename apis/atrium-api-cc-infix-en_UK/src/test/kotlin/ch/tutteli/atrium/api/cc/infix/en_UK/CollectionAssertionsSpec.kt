package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

class CollectionAssertionsSpec : ch.tutteli.atrium.spec.assertions.CollectionAssertionsSpec(
    AssertionVerbFactory,
    IAssertionPlant<List<Int>>::hasSize.name to IAssertionPlant<List<Int>>::hasSize,
    "${IAssertionPlant<List<Int>>::toBe.name} ${Empty::class.simpleName}" to Companion::isEmpty,
    "${IAssertionPlant<List<Int>>::notToBe.name} ${Empty::class.simpleName}" to Companion::isNotEmpty
) {
    companion object {
        fun isEmpty(plant: IAssertionPlant<List<Int>>)
            = plant toBe Empty

        fun isNotEmpty(plant: IAssertionPlant<List<Int>>)
            = plant notToBe Empty
    }
}

