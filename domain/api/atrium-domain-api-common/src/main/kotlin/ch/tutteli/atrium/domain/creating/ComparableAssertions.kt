package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.SubjectProvider

/**
 * The access point to an implementation of [ComparableAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val comparableAssertions by lazy { loadSingleService(ComparableAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Comparable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface ComparableAssertions {
    fun <T1 : Comparable<T2>, T2 : Any?> isLessThan(subjectProvider: SubjectProvider<T1>, expected: T2): Assertion
    fun <T1 : Comparable<T2>, T2 : Any?> isLessOrEquals(subjectProvider: SubjectProvider<T1>, expected: T2): Assertion
    fun <T1 : Comparable<T2>, T2 : Any?> isGreaterThan(subjectProvider: SubjectProvider<T1>, expected: T2): Assertion
    fun <T1 : Comparable<T2>, T2 : Any?> isGreaterOrEquals(
        subjectProvider: SubjectProvider<T1>, expected: T2
    ): Assertion
}
