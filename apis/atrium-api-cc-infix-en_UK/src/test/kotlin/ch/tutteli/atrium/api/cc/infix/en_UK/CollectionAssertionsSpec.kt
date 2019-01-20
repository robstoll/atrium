@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
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

        fun isEmpty(plant: Assert<Collection<Int>>)
            = plant toBe Empty

        fun isNotEmpty(plant: Assert<Collection<Int>>)
            = plant notToBe Empty
    }
}

