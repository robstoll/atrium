package ch.tutteli.atrium.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder

fun <E, T : Iterable<E>> _containsInAnyOrder(
    containsBuilder: IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>
): IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>
    = IterableContainsBuilder(containsBuilder.plant, IterableContainsInAnyOrderSearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsInAnyOrderOnly(
    containsBuilder: IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>
): IterableContains.Builder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>
    = IterableContainsBuilder(containsBuilder.plant, IterableContainsInAnyOrderOnlySearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsInOrder(
    containsBuilder: IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>
): IterableContains.Builder<E, T, IterableContainsInOrderSearchBehaviour>
    = IterableContainsBuilder(containsBuilder.plant, IterableContainsInOrderSearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsInOrderOnly(
    containsBuilder: IterableContains.Builder<E, T, IterableContainsInOrderSearchBehaviour>
): IterableContains.Builder<E, T, IterableContainsInOrderOnlySearchBehaviour>
    = IterableContainsBuilder(containsBuilder.plant, IterableContainsInOrderOnlySearchBehaviourImpl())
