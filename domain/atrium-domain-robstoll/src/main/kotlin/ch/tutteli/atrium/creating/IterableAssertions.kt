package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.NotSearchBehaviour

/**
 * Robstoll's implementation of [IIterableAssertions].
 */
object IterableAssertions : IIterableAssertions {

    override fun <E, T : Iterable<E>> containsBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, NoOpSearchBehaviour>
        = _containsBuilder(plant)

    override fun <E, T : Iterable<E>> containsNotBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, NotSearchBehaviour>
        = _containsNotBuilder(plant)
}
