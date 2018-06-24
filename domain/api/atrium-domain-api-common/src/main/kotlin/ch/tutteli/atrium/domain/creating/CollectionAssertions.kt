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
    fun <T : Collection<*>> hasSize(plant: AssertionPlant<T>, size: Int): Assertion
    fun <T : Collection<*>> isEmpty(plant: AssertionPlant<T>): Assertion
    fun <T : Collection<*>> isNotEmpty(plant: AssertionPlant<T>): Assertion
}
