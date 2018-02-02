package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * A dummy implementation of [ICollectionAssertions] which should be replaced by an actual implementation.
 */
object CollectionAssertions : ICollectionAssertions {
    override fun <T : Collection<*>> hasSize(plant: AssertionPlant<T>, size: Int): Assertion
        = throwUnsupportedOperationException()
    override fun <T : Collection<*>> isEmpty(plant: AssertionPlant<T>): Assertion
        = throwUnsupportedOperationException()
    override fun <T : Collection<*>> isNotEmpty(plant: AssertionPlant<T>): Assertion
        = throwUnsupportedOperationException()
}
