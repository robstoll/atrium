package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Defines the minimum set of assertion functions and builders applicable to [Collection],
 * which an implementation of the domain of Atrium has to provide.
 */
interface IMapAssertions {
    fun <T : Map<*, *>> hasSize(plant: AssertionPlant<T>, size: Int): Assertion
    fun <T : Map<*, *>> isEmpty(plant: AssertionPlant<T>): Assertion
    fun <T : Map<*, *>> isNotEmpty(plant: AssertionPlant<T>): Assertion
}
