package ch.tutteli.atrium.robstoll.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.MapAssertions
import ch.tutteli.atrium.robstoll.lib.creating._hasSize
import ch.tutteli.atrium.robstoll.lib.creating._isEmpty
import ch.tutteli.atrium.robstoll.lib.creating._isNotEmpty

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
