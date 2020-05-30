package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.IterableAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._containsBuilder
import ch.tutteli.atrium.domain.robstoll.lib.creating._containsNotBuilder
import ch.tutteli.atrium.domain.robstoll.lib.creating._hasNext
import ch.tutteli.atrium.domain.robstoll.lib.creating._hasNotNext
import ch.tutteli.atrium.domain.robstoll.lib.creating._iterableAll
import ch.tutteli.atrium.domain.robstoll.lib.creating._max
import ch.tutteli.atrium.domain.robstoll.lib.creating._min


class IterableAssertionsImpl : IterableAssertions, IterableAssertionsDeprecatedImpl() {

    override fun <E, T : Iterable<E>> containsBuilder(subjectProvider: SubjectProvider<T>) =
        _containsBuilder(subjectProvider)

    override fun <E, T : Iterable<E>> containsNotBuilder(subjectProvider: SubjectProvider<T>) =
        _containsNotBuilder(subjectProvider)

    override fun <E : Any, T : Iterable<E?>> all(
        expect: Expect<T>,
        assertionCreator: (Expect<E>.() -> Unit)?
    ): Assertion = _iterableAll(expect, assertionCreator)

    override fun <E, T : Iterable<E>> hasNext(expect: Expect<T>): Assertion = _hasNext(expect)

    override fun <E, T : Iterable<E>> hasNotNext(expect: Expect<T>): Assertion = _hasNotNext(expect)

    override fun <E : Comparable<E>, T : Iterable<E>> min(expect: Expect<T>) =
        _min(expect)

    override fun <E : Comparable<E>, T : Iterable<E>> max(expect: Expect<T>) =
        _max(expect)

}
