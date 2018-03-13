@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.CollectionAssertions
import ch.tutteli.atrium.creating.collectionAssertions

object CollectionAssertionsBuilder : CollectionAssertions {
    override inline fun <T : Collection<*>> hasSize(plant: AssertionPlant<T>, size: Int)
        = collectionAssertions.hasSize(plant, size)

    override inline fun <T : Collection<*>> isEmpty(plant: AssertionPlant<T>)
        = collectionAssertions.isEmpty(plant)

    override inline fun <T : Collection<*>> isNotEmpty(plant: AssertionPlant<T>)
        = collectionAssertions.isNotEmpty(plant)
}
