package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.builders

import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.robstoll.lib.creating.basic.contains.builders.ContainsBuilder

class IterableContainsBuilder<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>(
    subjectProvider: SubjectProvider<T>, searchBehaviour: S
) : ContainsBuilder<T, S>(subjectProvider, searchBehaviour), IterableContains.Builder<E, T, S>
