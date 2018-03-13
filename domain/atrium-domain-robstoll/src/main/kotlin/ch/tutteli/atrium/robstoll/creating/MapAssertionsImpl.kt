package ch.tutteli.atrium.robstoll.creating

import ch.tutteli.atrium.creating.*

/**
 * Robstoll's implementation of [MapAssertions].
 */
class MapAssertionsImpl : MapAssertions {

    override fun <T : Map<*, *>> hasSize(plant: AssertionPlant<T>, size: Int)
        = _hasSize(plant, size)

    override fun <T : Map<*, *>> isEmpty(plant: AssertionPlant<T>)
        = _isEmpty(plant)

    override fun <T : Map<*, *>> isNotEmpty(plant: AssertionPlant<T>)
        = _isNotEmpty(plant)
}
