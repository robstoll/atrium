//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [Comparable] type.
 */
//TODO 1.3.0 deprecate
interface ComparableAssertions {
    fun <T1 : Comparable<T2>, T2 : Any?> isLessThan(container: AssertionContainer<T1>, expected: T2): Assertion

    fun <T1 : Comparable<T2>, T2 : Any?> isLessThanOrEqual(container: AssertionContainer<T1>, expected: T2): Assertion

    fun <T1 : Comparable<T2>, T2 : Any?> isNotGreaterThan(container: AssertionContainer<T1>, expected: T2): Assertion

    fun <T1 : Comparable<T2>, T2 : Any?> isEqualComparingTo(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion

    fun <T1 : Comparable<T2>, T2 : Any?> isGreaterThanOrEqual(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion

    fun <T1 : Comparable<T2>, T2 : Any?> isNotLessThan(container: AssertionContainer<T1>, expected: T2): Assertion

    fun <T1 : Comparable<T2>, T2 : Any?> isGreaterThan(container: AssertionContainer<T1>, expected: T2): Assertion
}
