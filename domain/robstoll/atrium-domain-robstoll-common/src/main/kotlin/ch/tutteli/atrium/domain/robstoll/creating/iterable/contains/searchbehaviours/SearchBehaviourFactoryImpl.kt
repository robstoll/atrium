package ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.searchbehaviours.*


class SearchBehaviourFactoryImpl : SearchBehaviourFactory {

    override fun <E, T : Iterable<E>> inAnyOrder(
        builder: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    ): IterableContains.Builder<E, T, InAnyOrderSearchBehaviour> = _containsInAnyOrder(builder)

    override fun <E, T : Iterable<E>> inAnyOrderOnly(
        builder: IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>
    ): IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour> = _containsInAnyOrderOnly(builder)

    override fun <E, T : Iterable<E>> inOrder(
        builder: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    ): IterableContains.Builder<E, T, InOrderSearchBehaviour> = _containsInOrder(builder)

    override fun <E, T : Iterable<E>> inOrderOnly(
        builder: IterableContains.Builder<E, T, InOrderSearchBehaviour>
    ): IterableContains.Builder<E, T, InOrderOnlySearchBehaviour> = _containsInOrderOnly(builder)

    override fun <E, T : Iterable<E>> inOrderOnlyGrouped(
        builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>
    ): IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour> = _containsInOrderOnlyGrouped(builder)

    override fun <E, T : Iterable<E>> inOrderOnlyGroupedWithin(
        builder: IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour>
    ): IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour> =
        _containsInOrderOnlyGroupedWithing(builder)
}
