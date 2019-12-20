package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviourImpl
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.searchbehaviours.NotSearchBehaviourImpl

fun <E, T : Iterable<E>> _containsBuilder(subjectProvider: SubjectProvider<T>): IterableContains.Builder<E, T, NoOpSearchBehaviour> =
    IterableContainsBuilder(subjectProvider, NoOpSearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsNotBuilder(subjectProvider: SubjectProvider<T>): IterableContains.Builder<E, T, NotSearchBehaviour> =
    IterableContainsBuilder(subjectProvider, NotSearchBehaviourImpl())
