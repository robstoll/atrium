package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Robstoll's implementation of [ICollectionAssertions].
 */
object CollectionAssertions : ICollectionAssertions {
    override fun <T : Collection<*>> hasSize(plant: AssertionPlant<T>, size: Int): Assertion
        = _hasSize(plant, size)

    override fun <T : Collection<*>> isEmpty(plant: AssertionPlant<T>): Assertion
        = _isEmpty(plant)

    override fun <T : Collection<*>> isNotEmpty(plant: AssertionPlant<T>): Assertion
        = _isNotEmpty(plant)
}
