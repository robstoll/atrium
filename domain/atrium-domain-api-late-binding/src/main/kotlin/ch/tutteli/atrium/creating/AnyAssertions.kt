package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * A dummy implementation of [IAnyAssertions] which should be replaced by an actual implementation.
 */
object AnyAssertions : IAnyAssertions {
    override fun <T : Any> toBe(plant: AssertionPlant<T>, expected: T): Assertion
        = throwUnsupportedOperationException()
    override fun <T : Any> notToBe(plant: AssertionPlant<T>, expected: T): Assertion
        = throwUnsupportedOperationException()
    override fun <T : Any> isSame(plant: AssertionPlant<T>, expected: T): Assertion
        = throwUnsupportedOperationException()
    override fun <T : Any> isNotSame(plant: AssertionPlant<T>, expected: T): Assertion
        = throwUnsupportedOperationException()
    override fun <T : Any?> isNull(plant: AssertionPlantNullable<T>): Assertion
        = throwUnsupportedOperationException()
}
