package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.builders.IterableContainsBuilder

fun <E, T : Iterable<E>> _containsInAnyOrder(
    builder: IterableContains.Builder<E, T, NoOpSearchBehaviour>
): IterableContains.Builder<E, T, InAnyOrderSearchBehaviour> =
    IterableContainsBuilder(builder.subjectProvider, InAnyOrderSearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsInAnyOrderOnly(
    builder: IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>
): IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour> =
    IterableContainsBuilder(builder.subjectProvider, InAnyOrderOnlySearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsInOrder(
    builder: IterableContains.Builder<E, T, NoOpSearchBehaviour>
): IterableContains.Builder<E, T, InOrderSearchBehaviour> =
    IterableContainsBuilder(builder.subjectProvider, InOrderSearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsInOrderOnly(
    builder: IterableContains.Builder<E, T, InOrderSearchBehaviour>
): IterableContains.Builder<E, T, InOrderOnlySearchBehaviour> =
    IterableContainsBuilder(builder.subjectProvider, InOrderOnlySearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsInOrderOnlyGrouped(
    builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>
): IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour> =
    IterableContainsBuilder(builder.subjectProvider, InOrderOnlyGroupedSearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsInOrderOnlyGroupedWithing(
    builder: IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour>
): IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour> =
    IterableContainsBuilder(builder.subjectProvider, InOrderOnlyGroupedWithinSearchBehaviourImpl())
