package ch.tutteli.atrium.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder

fun <E, T : Iterable<E>> _containsInAnyOrder(
    containsBuilder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>
): IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>
    = IterableContainsBuilder(containsBuilder.plant, IterableContainsInAnyOrderSearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsInAnyOrderOnly(
    containsBuilder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>
): IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>
    = IterableContainsBuilder(containsBuilder.plant, IterableContainsInAnyOrderOnlySearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsInOrder(
    containsBuilder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>
): IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>
    = IterableContainsBuilder(containsBuilder.plant, IterableContainsInOrderSearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsInOrderOnly(
    containsBuilder: IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>
): IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>
    = IterableContainsBuilder(containsBuilder.plant, IterableContainsInOrderOnlySearchBehaviourImpl())
