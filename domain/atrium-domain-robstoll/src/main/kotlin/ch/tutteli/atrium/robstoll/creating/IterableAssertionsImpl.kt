package ch.tutteli.atrium.robstoll.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.IterableAssertions
import ch.tutteli.atrium.creating._containsBuilder
import ch.tutteli.atrium.creating._containsNotBuilder
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.NotSearchBehaviour

/**
 * Robstoll's implementation of [IterableAssertions].
 */
class IterableAssertionsImpl : IterableAssertions {

    override fun <E, T : Iterable<E>> containsBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, NoOpSearchBehaviour>
        = _containsBuilder(plant)

    override fun <E, T : Iterable<E>> containsNotBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, NotSearchBehaviour>
        = _containsNotBuilder(plant)
}
