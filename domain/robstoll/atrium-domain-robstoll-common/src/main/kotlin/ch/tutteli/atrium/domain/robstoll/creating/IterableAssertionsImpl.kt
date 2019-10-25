package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.IterableAssertions
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.robstoll.lib.creating.*


class IterableAssertionsImpl : IterableAssertions, IterableAssertionsDeprecatedImpl() {

    override fun <E : Comparable<E>, T : Iterable<E>> min(assertionContainer: Expect<T>) =
        _min(assertionContainer)

    override fun <E, T : Iterable<E>> containsBuilder(subjectProvider: SubjectProvider<T>) =
        _containsBuilder(subjectProvider)

    override fun <E, T : Iterable<E>> containsNotBuilder(subjectProvider: SubjectProvider<T>) =
        _containsNotBuilder(subjectProvider)

    override fun <E : Any, T : Iterable<E?>> all(
        assertionContainer: Expect<T>,
        assertionCreator: (Expect<E>.() -> Unit)?
    ): Assertion = _iterableAll(assertionContainer, assertionCreator)

    override fun <E : Any> hasNext(expect: Expect<Iterable<E>>): Assertion = _hasNext(expect)

    override fun <E : Any> hasNotNext(expect: Expect<Iterable<E>>): Assertion = _hasNotNext(expect)
}
