package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable

/**
 * The access point to an implementation of [AnyAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val anyAssertions by lazy { loadSingleService(AnyAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Any] type,
 * which an implementation of the domain of Atrium has to provide.
 */
interface AnyAssertions {
    fun <T : Any> toBe(plant: AssertionPlant<T>, expected: T): Assertion
    fun <T : Any> notToBe(plant: AssertionPlant<T>, expected: T): Assertion
    fun <T : Any> isSame(plant: AssertionPlant<T>, expected: T): Assertion
    fun <T : Any> isNotSame(plant: AssertionPlant<T>, expected: T): Assertion
    fun <T : Any?> isNull(plant: AssertionPlantNullable<T>): Assertion
}

