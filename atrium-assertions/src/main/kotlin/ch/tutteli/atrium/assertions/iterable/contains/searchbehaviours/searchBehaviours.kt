package ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder

@Deprecated("use IterableContainsSearchBehaviours.inAnyOrder, will be removed with 1.0.0", ReplaceWith("IterableContainsSearchBehaviours.inAnyOrder(containsBuilder)", "ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsSearchBehaviours"))
fun <E, T : Iterable<E>> _containsInAnyOrder(containsBuilder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>)
    = IterableContainsBuilder(containsBuilder.plant, IterableContainsInAnyOrderSearchBehaviour())

@Deprecated("use IterableContainsSearchBehaviours.inAnyOrderOnly, will be removed with 1.0.0", ReplaceWith("IterableContainsSearchBehaviours.inAnyOrderOnly(containsBuilder)", "ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsSearchBehaviours"))
fun <E, T : Iterable<E>> _containsInAnyOrderOnly(containsBuilder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>)
    = IterableContainsBuilder(containsBuilder.plant, IterableContainsInAnyOrderOnlySearchBehaviour())

@Deprecated("use IterableContainsSearchBehaviours.inOrder, will be removed with 1.0.0", ReplaceWith("IterableContainsSearchBehaviours.inOrder(containsBuilder)", "ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsSearchBehaviours"))
fun <E, T : Iterable<E>> _containsInOrder(containsBuilder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>)
    = IterableContainsBuilder(containsBuilder.plant, IterableContainsInOrderSearchBehaviour())

@Deprecated("use IterableContainsSearchBehaviours.inOrderOnly, will be removed with 1.0.0", ReplaceWith("IterableContainsSearchBehaviours.inOrderOnly(containsBuilder)", "ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsSearchBehaviours"))
fun <E, T : Iterable<E>> _containsInOrderOnly(containsBuilder: IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>)
    = IterableContainsBuilder(containsBuilder.plant, IterableContainsInOrderOnlySearchBehaviour())
