package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNotSearchBehaviour

/**
 * A dummy implementation of [IIterableAssertions] which should be replaced by an actual implementation.
 */
object IterableAssertions : IIterableAssertions{
    override fun <E, T : Iterable<E>> containsBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>
        = throwUnsupportedOperationException()
    override fun <E, T : Iterable<E>> containsNotBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, IterableContainsNotSearchBehaviour>
        = throwUnsupportedOperationException()
}
