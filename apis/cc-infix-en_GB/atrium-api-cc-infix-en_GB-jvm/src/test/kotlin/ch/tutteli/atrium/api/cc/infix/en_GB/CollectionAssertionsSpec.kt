// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Empty
import ch.tutteli.atrium.api.infix.en_GB.empty
import ch.tutteli.atrium.api.infix.en_GB.hasSize
import ch.tutteli.atrium.api.infix.en_GB.notToBe
import ch.tutteli.atrium.api.infix.en_GB.toBe
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect

//TODO remove with 1.0.0, no need to migrate to Spek 2
class CollectionAssertionsSpec : ch.tutteli.atrium.spec.integration.CollectionAssertionsSpec(
    AssertionVerbFactory,
    Assert<Collection<Int>>::hasSize.name to Companion::hasSize,
    "${Assert<Collection<Int>>::toBe.name} ${Empty::class.simpleName}" to Companion::isEmpty,
    "${Assert<Collection<Int>>::notToBe.name} ${Empty::class.simpleName}" to Companion::isNotEmpty
) {
    companion object {
        private fun hasSize(plant: Assert<Collection<Int>>, size: Int): Assert<Collection<Int>>
            = plant.asExpect().hasSize(size).asAssert()

        private fun isEmpty(plant: Assert<Collection<Int>>)
            = plant.asExpect().toBe(empty).asAssert()

        private fun isNotEmpty(plant: Assert<Collection<Int>>)
            = plant.asExpect().notToBe(empty).asAssert()
    }
}

