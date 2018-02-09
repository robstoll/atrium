package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Defines the minimum set of assertion functions and builders applicable to [Any] type,
 * which an implementation of the domain of Atrium has to provide.
 */
interface IAnyAssertions {
    fun <T : Any> toBe(plant: AssertionPlant<T>, expected: T): Assertion
    fun <T : Any> notToBe(plant: AssertionPlant<T>, expected: T): Assertion
    fun <T : Any> isSame(plant: AssertionPlant<T>, expected: T): Assertion
    fun <T : Any> isNotSame(plant: AssertionPlant<T>, expected: T): Assertion
    fun <T : Any?> isNull(plant: AssertionPlantNullable<T>): Assertion
}
