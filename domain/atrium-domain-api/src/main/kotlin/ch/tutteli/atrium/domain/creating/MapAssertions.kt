package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.SingleServiceLoader
import ch.tutteli.atrium.creating.AssertionPlant
import java.util.*

/**
 * The access point to an implementation of [MapAssertions].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val mapAssertions by lazy { SingleServiceLoader.load(MapAssertions::class.java) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Collection],
 * which an implementation of the domain of Atrium has to provide.
 */
interface MapAssertions {
    fun <T : Map<*, *>> hasSize(plant: AssertionPlant<T>, size: Int): Assertion
    fun <T : Map<*, *>> isEmpty(plant: AssertionPlant<T>): Assertion
    fun <T : Map<*, *>> isNotEmpty(plant: AssertionPlant<T>): Assertion
}
