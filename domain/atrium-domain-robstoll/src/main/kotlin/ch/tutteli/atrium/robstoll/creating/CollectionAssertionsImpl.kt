package ch.tutteli.atrium.robstoll.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.CollectionAssertions
import ch.tutteli.atrium.robstoll.lib.creating._hasSize
import ch.tutteli.atrium.robstoll.lib.creating._isEmpty
import ch.tutteli.atrium.robstoll.lib.creating._isNotEmpty

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
