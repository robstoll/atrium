package ch.tutteli.atrium.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.throwUnsupportedOperationException

/**
 * A dummy implementation of [ISearchBehaviourFactory] which should be replaced by an actual implementation.
 */
object SearchBehaviourFactory : ISearchBehaviourFactory {
    override fun <E, T : Iterable<E>> inAnyOrder(
        builder: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    ): IterableContains.Builder<E, T, InAnyOrderSearchBehaviour> = throwUnsupportedOperationException()

    override fun <E, T : Iterable<E>> inAnyOrderOnly(
        builder: IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>
    ): IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour> =
        throwUnsupportedOperationException()

    override fun <E, T : Iterable<E>> inOrder(
        containsBuilder: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    ): IterableContains.Builder<E, T, InOrderSearchBehaviour> = throwUnsupportedOperationException()

    override fun <E, T : Iterable<E>> inOrderOnly(
        builder: IterableContains.Builder<E, T, InOrderSearchBehaviour>
    ): IterableContains.Builder<E, T, InOrderOnlySearchBehaviour> = throwUnsupportedOperationException()
}
