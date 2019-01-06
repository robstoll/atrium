package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant

/**
 * The access point to an implementation of [CollectionAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val collectionAssertions by lazy { loadSingleService(CollectionAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Collection],
 * which an implementation of the domain of Atrium has to provide.
 */
interface CollectionAssertions {
    fun hasSize(plant: AssertionPlant<Collection<*>>, size: Int): Assertion
    fun isEmpty(plant: AssertionPlant<Collection<*>>): Assertion
    fun isNotEmpty(plant: AssertionPlant<Collection<*>>): Assertion
}
