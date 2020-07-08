//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider

/**
 * The access point to an implementation of [ComparableAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
@Deprecated("Use _logic from ch.tutteli.atrium.logic instead; will be removed with 1.0.0")
val comparableAssertions by lazy { loadSingleService(ComparableAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Comparable],
 * which an implementation of the domain of Atrium has to provide.
 */
@Deprecated(
    "Use ComparableAssertions from atrium-logic; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.logic.ComparableAssertions")
)
interface ComparableAssertions {
    fun <T1 : Comparable<T2>, T2 : Any?> isLessThan(subjectProvider: SubjectProvider<T1>, expected: T2): Assertion

    //TODO rename to isLessThanOrEqual with 1.0.0
    fun <T1 : Comparable<T2>, T2 : Any?> isLessOrEquals(subjectProvider: SubjectProvider<T1>, expected: T2): Assertion

    fun <T1 : Comparable<T2>, T2 : Any?> isGreaterThan(subjectProvider: SubjectProvider<T1>, expected: T2): Assertion

    //TODO rename to isGreaterThanOrEqual with 1.0.0
    fun <T1 : Comparable<T2>, T2 : Any?> isGreaterOrEquals(
        subjectProvider: SubjectProvider<T1>,
        expected: T2
    ): Assertion

    fun <T1 : Comparable<T2>, T2 : Any?> isEqualComparingTo(
        expect: Expect<T1>,
        expected: T2
    ): Assertion
}
