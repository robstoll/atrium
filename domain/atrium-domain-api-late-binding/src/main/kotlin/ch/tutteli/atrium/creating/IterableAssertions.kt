package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.NotSearchBehaviour

/**
 * A dummy implementation of [IIterableAssertions] which should be replaced by an actual implementation.
 */
object IterableAssertions : IIterableAssertions{
    override fun <E, T : Iterable<E>> containsBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, NoOpSearchBehaviour>
        = throwUnsupportedOperationException()
    override fun <E, T : Iterable<E>> containsNotBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, NotSearchBehaviour>
        = throwUnsupportedOperationException()
}
