package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Empty
import ch.tutteli.atrium.creating.Assert

class CollectionAssertionsSpec : ch.tutteli.atrium.spec.integration.CollectionAssertionsSpec(
    AssertionVerbFactory,
    Assert<Collection<Int>>::hasSize.name to Companion::hasSize,
    "${Assert<Collection<Int>>::toBe.name} ${Empty::class.simpleName}" to Companion::isEmpty,
    "${Assert<Collection<Int>>::notToBe.name} ${Empty::class.simpleName}" to Companion::isNotEmpty
) {
    companion object {
        private fun hasSize(plant: Assert<Collection<Int>>, size: Int): Assert<Collection<Int>>
            = plant hasSize size

        private fun isEmpty(plant: Assert<Collection<Int>>)
            = plant toBe Empty

        private fun isNotEmpty(plant: Assert<Collection<Int>>)
            = plant notToBe Empty
    }
}

