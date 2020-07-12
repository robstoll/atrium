package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

interface ComparableAssertions {
    fun <T1 : Comparable<T2>, T2 : Any?> isLessThan(container: AssertionContainer<T1>, expected: T2): Assertion

    fun <T1 : Comparable<T2>, T2 : Any?> isLessThanOrEqual(container: AssertionContainer<T1>, expected: T2): Assertion

    fun <T1 : Comparable<T2>, T2 : Any?> isGreaterThan(container: AssertionContainer<T1>, expected: T2): Assertion

    fun <T1 : Comparable<T2>, T2 : Any?> isGreaterThanOrEqual(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion

    fun <T1 : Comparable<T2>, T2 : Any?> isEqualComparingTo(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion
}
