package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * A dummy implementation of [IMapAssertions] which should be replaced by an actual implementation.
 */
object MapAssertions : IMapAssertions {
    override fun <T : Map<*, *>> hasSize(plant: AssertionPlant<T>, size: Int): Assertion
        = throwUnsupportedOperationException()
    override fun <T : Map<*, *>> isEmpty(plant: AssertionPlant<T>): Assertion
        = throwUnsupportedOperationException()
    override fun <T : Map<*, *>> isNotEmpty(plant: AssertionPlant<T>): Assertion
        = throwUnsupportedOperationException()
}
