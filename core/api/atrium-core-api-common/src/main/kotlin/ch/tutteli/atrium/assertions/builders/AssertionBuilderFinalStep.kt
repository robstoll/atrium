package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion

/**
 * Final step in the [AssertionBuilder] process, creates the desired [Assertion] of type [T].
 */
interface AssertionBuilderFinalStep<T: Assertion> {

    /**
     * Creates and returns the new [Assertion].
     */
    fun build(): T
}
