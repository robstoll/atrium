//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion

/**
 * Final step in the [AssertionBuilder] process, creates the desired [Assertion] of type [T].
 * @param T the type of the [Assertion].
 */
interface AssertionBuilderFinalStep<T : Assertion> {

    /**
     * Creates and returns the new [Assertion] of type [T].
     */
    fun build(): T
}
