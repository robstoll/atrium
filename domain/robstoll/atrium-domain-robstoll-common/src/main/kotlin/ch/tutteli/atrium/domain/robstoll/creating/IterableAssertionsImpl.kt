package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.IterableAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._containsBuilder
import ch.tutteli.atrium.domain.robstoll.lib.creating._containsNotBuilder


class IterableAssertionsImpl : IterableAssertions, IterableAssertionsDeprecatedImpl() {

    override fun <E, T : Iterable<E>> containsBuilder(subjectProvider: SubjectProvider<T>) =
        _containsBuilder(subjectProvider)

    override fun <E, T : Iterable<E>> containsNotBuilder(subjectProvider: SubjectProvider<T>) =
        _containsNotBuilder(subjectProvider)
}
