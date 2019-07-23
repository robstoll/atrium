package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.ComparableAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isGreaterOrEquals
import ch.tutteli.atrium.domain.robstoll.lib.creating._isGreaterThan
import ch.tutteli.atrium.domain.robstoll.lib.creating._isLessOrEquals
import ch.tutteli.atrium.domain.robstoll.lib.creating._isLessThan


class ComparableAssertionsImpl : ComparableAssertions {

    override fun <T1 : Comparable<T2>, T2 : Any?> isLessThan(subjectProvider: SubjectProvider<T1>, expected: T2) =
        _isLessThan(subjectProvider, expected)

    override fun <T1 : Comparable<T2>, T2 : Any?> isLessOrEquals(subjectProvider: SubjectProvider<T1>, expected: T2) =
        _isLessOrEquals(subjectProvider, expected)

    override fun <T1 : Comparable<T2>, T2 : Any?> isGreaterThan(subjectProvider: SubjectProvider<T1>, expected: T2) =
        _isGreaterThan(subjectProvider, expected)

    override fun <T1 : Comparable<T2>, T2 : Any?> isGreaterOrEquals(
        subjectProvider: SubjectProvider<T1>,
        expected: T2
    ) = _isGreaterOrEquals(subjectProvider, expected)
}
