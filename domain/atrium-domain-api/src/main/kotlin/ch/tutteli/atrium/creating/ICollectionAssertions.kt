package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Defines the minimum set of assertion functions and builders applicable to [Collection] type,
 * which an implementation of the domain of Atrium has to provide.
 */
interface ICollectionAssertions {
    fun <T : Collection<*>> hasSize(plant: AssertionPlant<T>, size: Int): Assertion
    fun <T : Collection<*>> isEmpty(plant: AssertionPlant<T>): Assertion
    fun <T : Collection<*>> isNotEmpty(plant: AssertionPlant<T>): Assertion
}
