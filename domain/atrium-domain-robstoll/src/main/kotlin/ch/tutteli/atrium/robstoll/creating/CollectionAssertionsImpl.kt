package ch.tutteli.atrium.robstoll.creating

import ch.tutteli.atrium.creating.*

/**
 * Robstoll's implementation of [CollectionAssertions].
 */
class CollectionAssertionsImpl : CollectionAssertions {

    override fun <T : Collection<*>> hasSize(plant: AssertionPlant<T>, size: Int)
        = _hasSize(plant, size)

    override fun <T : Collection<*>> isEmpty(plant: AssertionPlant<T>)
        = _isEmpty(plant)

    override fun <T : Collection<*>> isNotEmpty(plant: AssertionPlant<T>)
        = _isNotEmpty(plant)
}
