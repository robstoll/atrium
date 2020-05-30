@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder

@Deprecated("Use AssertImpl.iterable.contains.searchBehaviours.inAnyOrder; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.iterable.contains.searchBehaviours.inAnyOrder(containsBuilder)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <E, T : Iterable<E>> _containsInAnyOrder(containsBuilder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>)
    = IterableContainsBuilder(containsBuilder.subjectProvider, IterableContainsInAnyOrderSearchBehaviour())

@Deprecated("Use AssertImpl.iterable.contains.searchBehaviours.inAnyOrderOnly; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.iterable.contains.searchBehaviours.inAnyOrderOnly(containsBuilder)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <E, T : Iterable<E>> _containsInAnyOrderOnly(containsBuilder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>)
    = IterableContainsBuilder(containsBuilder.subjectProvider, IterableContainsInAnyOrderOnlySearchBehaviour())

@Deprecated("Use AssertImpl.iterable.contains.searchBehaviours.inOrder; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.iterable.contains.searchBehaviours.inOrder(containsBuilder)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <E, T : Iterable<E>> _containsInOrder(containsBuilder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>)
    = IterableContainsBuilder(containsBuilder.subjectProvider, IterableContainsInOrderSearchBehaviour())

@Deprecated("Use AssertImpl.iterable.contains.searchBehaviours.inOrderOnly; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.iterable.contains.searchBehaviours.inOrderOnly(containsBuilder)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <E, T : Iterable<E>> _containsInOrderOnly(containsBuilder: IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>)
    = IterableContainsBuilder(containsBuilder.subjectProvider, IterableContainsInOrderOnlySearchBehaviour())
