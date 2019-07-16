@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.ComparableAssertions
import ch.tutteli.atrium.domain.creating.comparableAssertions

/**
 * Delegates inter alia to the implementation of [ComparableAssertions].
 * In detail, it implements [ComparableAssertions] by delegating to [comparableAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object ComparableAssertionsBuilder : ComparableAssertions {

    override inline fun <T1 : Comparable<T2>, T2 : Any?> isLessThan(
        subjectProvider: SubjectProvider<T1>,
        expected: T2
    ) = comparableAssertions.isLessThan(subjectProvider, expected)

    override inline fun <T1 : Comparable<T2>, T2 : Any?> isLessOrEquals(
        subjectProvider: SubjectProvider<T1>,
        expected: T2
    ) = comparableAssertions.isLessOrEquals(subjectProvider, expected)

    override inline fun <T1 : Comparable<T2>, T2 : Any?> isGreaterThan(
        subjectProvider: SubjectProvider<T1>,
        expected: T2
    ) = comparableAssertions.isGreaterThan(subjectProvider, expected)

    override inline fun <T1 : Comparable<T2>, T2 : Any?> isGreaterOrEquals(
        subjectProvider: SubjectProvider<T1>,
        expected: T2
    ) = comparableAssertions.isGreaterOrEquals(subjectProvider, expected)
}
