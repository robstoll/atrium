package ch.tutteli.atrium.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder

fun <E, T : Iterable<E>> _containsInAnyOrder(containsBuilder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>)
    = IterableContainsBuilder(containsBuilder.plant, IterableContainsInAnyOrderSearchBehaviour())

fun <E, T : Iterable<E>> _containsInAnyOrderOnly(containsBuilder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>)
    = IterableContainsBuilder(containsBuilder.plant, IterableContainsInAnyOrderOnlySearchBehaviour())

fun <E, T : Iterable<E>> _containsInOrder(containsBuilder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>)
    = IterableContainsBuilder(containsBuilder.plant, IterableContainsInOrderSearchBehaviour())

fun <E, T : Iterable<E>> _containsInOrderOnly(containsBuilder: IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>)
    = IterableContainsBuilder(containsBuilder.plant, IterableContainsInOrderOnlySearchBehaviour())
