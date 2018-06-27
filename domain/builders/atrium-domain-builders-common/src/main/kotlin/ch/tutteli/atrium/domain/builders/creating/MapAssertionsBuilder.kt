@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.MapAssertions
import ch.tutteli.atrium.domain.creating.mapAssertions
import ch.tutteli.atrium.core.polyfills.loadSingleService

/**
 * Delegates inter alia to the implementation of [MapAssertions].
 * In detail, it implements [MapAssertions] by delegating to [mapAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object MapAssertionsBuilder : MapAssertions {

    override inline fun <T : Map<*, *>> hasSize(plant: AssertionPlant<T>, size: Int)
        = mapAssertions.hasSize(plant, size)

    override inline fun <T : Map<*, *>> isEmpty(plant: AssertionPlant<T>)
        = mapAssertions.isEmpty(plant)

    override inline fun <T : Map<*, *>> isNotEmpty(plant: AssertionPlant<T>)
        = mapAssertions.isNotEmpty(plant)
}
