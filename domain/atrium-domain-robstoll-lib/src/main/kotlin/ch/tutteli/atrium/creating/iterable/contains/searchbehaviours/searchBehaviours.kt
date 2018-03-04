package ch.tutteli.atrium.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder

fun <E, T : Iterable<E>> _containsInAnyOrder(
    containsBuilder: IterableContains.Builder<E, T, NoOpSearchBehaviour>
): IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>
    = IterableContainsBuilder(containsBuilder.plant, InAnyOrderSearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsInAnyOrderOnly(
    containsBuilder: IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>
): IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>
    = IterableContainsBuilder(containsBuilder.plant, InAnyOrderOnlySearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsInOrder(
    containsBuilder: IterableContains.Builder<E, T, NoOpSearchBehaviour>
): IterableContains.Builder<E, T, InOrderSearchBehaviour>
    = IterableContainsBuilder(containsBuilder.plant, InOrderSearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsInOrderOnly(
    containsBuilder: IterableContains.Builder<E, T, InOrderSearchBehaviour>
): IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>
    = IterableContainsBuilder(containsBuilder.plant, InOrderOnlySearchBehaviourImpl())
