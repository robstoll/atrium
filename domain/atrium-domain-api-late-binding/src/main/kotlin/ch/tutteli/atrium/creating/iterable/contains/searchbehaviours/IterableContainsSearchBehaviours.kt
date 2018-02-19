package ch.tutteli.atrium.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.throwUnsupportedOperationException

/**
 * A dummy implementation of [IIterableContainsSearchBehaviours] which should be replaced by an actual implementation.
 */
object IterableContainsSearchBehaviours : IIterableContainsSearchBehaviours {
    override fun <E, T : Iterable<E>> inAnyOrder(
        builder: IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>
    ): IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour> = throwUnsupportedOperationException()

    override fun <E, T : Iterable<E>> inAnyOrderOnly(
        builder: IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>
    ): IterableContains.Builder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour> =
        throwUnsupportedOperationException()

    override fun <E, T : Iterable<E>> inOrder(
        containsBuilder: IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>
    ): IterableContains.Builder<E, T, IterableContainsInOrderSearchBehaviour> = throwUnsupportedOperationException()

    override fun <E, T : Iterable<E>> inOrderOnly(
        builder: IterableContains.Builder<E, T, IterableContainsInOrderSearchBehaviour>
    ): IterableContains.Builder<E, T, IterableContainsInOrderOnlySearchBehaviour> = throwUnsupportedOperationException()
}
