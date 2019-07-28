package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.CollectionAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._hasSize
import ch.tutteli.atrium.domain.robstoll.lib.creating._size


abstract class CollectionAssertionsDeprecatedImpl : CollectionAssertions {

    override fun hasSize(plant: AssertionPlant<Collection<*>>, size: Int) = _hasSize(plant, size)

    override fun size(plant: AssertionPlant<Collection<*>>, assertionCreator: Assert<Int>.() -> Unit) =
        _size(plant, assertionCreator)
}
