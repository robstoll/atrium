package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.ComparableAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isGreaterThanOrEqual
import ch.tutteli.atrium.domain.robstoll.lib.creating._isGreaterThan
import ch.tutteli.atrium.domain.robstoll.lib.creating._isLessThanOrEqual
import ch.tutteli.atrium.domain.robstoll.lib.creating._isLessThan
import ch.tutteli.atrium.domain.robstoll.lib.creating._isEqualComparingTo


class ComparableAssertionsImpl : ComparableAssertions {

    override fun <T1 : Comparable<T2>, T2 : Any?> isLessThan(subjectProvider: SubjectProvider<T1>, expected: T2) =
        _isLessThan(subjectProvider, expected)

    override fun <T1 : Comparable<T2>, T2 : Any?> isLessOrEquals(subjectProvider: SubjectProvider<T1>, expected: T2) =
        _isLessThanOrEqual(subjectProvider, expected)

    override fun <T1 : Comparable<T2>, T2 : Any?> isGreaterThan(subjectProvider: SubjectProvider<T1>, expected: T2) =
        _isGreaterThan(subjectProvider, expected)

    override fun <T1 : Comparable<T2>, T2 : Any?> isGreaterOrEquals(
        subjectProvider: SubjectProvider<T1>,
        expected: T2
    ) = _isGreaterThanOrEqual(subjectProvider, expected)

    override fun <T1 : Comparable<T2>, T2 : Any?> isEqualComparingTo(
        expect: Expect<T1>,
        expected: T2
    ) = _isEqualComparingTo(expect, expected)
}
