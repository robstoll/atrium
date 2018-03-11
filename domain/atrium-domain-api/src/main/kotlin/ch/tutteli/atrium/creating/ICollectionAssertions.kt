package ch.tutteli.atrium.creating

import ch.tutteli.atrium.SingleServiceLoader
import ch.tutteli.atrium.assertions.Assertion
import java.util.*

/**
 * The access point to an implementation of [CollectionAssertions].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val collectionAssertions by lazy { SingleServiceLoader.load(CollectionAssertions::class.java) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Collection],
 * which an implementation of the domain of Atrium has to provide.
 */
interface CollectionAssertions {
    fun <T : Collection<*>> hasSize(plant: AssertionPlant<T>, size: Int): Assertion
    fun <T : Collection<*>> isEmpty(plant: AssertionPlant<T>): Assertion
    fun <T : Collection<*>> isNotEmpty(plant: AssertionPlant<T>): Assertion
}
