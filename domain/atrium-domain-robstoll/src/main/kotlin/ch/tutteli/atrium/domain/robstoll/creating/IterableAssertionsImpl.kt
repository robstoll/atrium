package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.IterableAssertions
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating._containsBuilder
import ch.tutteli.atrium.domain.robstoll.lib.creating._containsNotBuilder

/**
 * Robstoll's implementation of [IterableAssertions].
 */
class IterableAssertionsImpl : IterableAssertions {

    override fun <E, T : Iterable<E>> containsBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, NoOpSearchBehaviour>
        = _containsBuilder(plant)

    override fun <E, T : Iterable<E>> containsNotBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, NotSearchBehaviour>
        = _containsNotBuilder(plant)
}
