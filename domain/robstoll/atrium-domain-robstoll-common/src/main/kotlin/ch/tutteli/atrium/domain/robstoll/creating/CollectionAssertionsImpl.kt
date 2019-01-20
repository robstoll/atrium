package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.CollectionAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._hasSize
import ch.tutteli.atrium.domain.robstoll.lib.creating._isEmpty
import ch.tutteli.atrium.domain.robstoll.lib.creating._isNotEmpty
import ch.tutteli.atrium.domain.robstoll.lib.creating._size

/**
 * Robstoll's implementation of [CollectionAssertions].
 */
class CollectionAssertionsImpl : CollectionAssertions {

    override fun hasSize(plant: AssertionPlant<Collection<*>>, size: Int)
        = _hasSize(plant, size)

    override fun isEmpty(plant: AssertionPlant<Collection<*>>)
        = _isEmpty(plant)

    override fun isNotEmpty(plant: AssertionPlant<Collection<*>>)
        = _isNotEmpty(plant)

    override fun size(plant: AssertionPlant<Collection<*>>, assertionCreator: Assert<Int>.() -> Unit)
        = _size(plant, assertionCreator)
}
