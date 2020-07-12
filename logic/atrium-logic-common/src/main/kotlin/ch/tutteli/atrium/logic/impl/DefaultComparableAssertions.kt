package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.ComparableAssertions
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.translations.DescriptionComparableAssertion.*

class DefaultComparableAssertions : ComparableAssertions {

    override fun <T1 : Comparable<T2>, T2 : Any?> isLessThan(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion = container.createDescriptiveAssertion(IS_LESS_THAN, expected) { it < expected }

    override fun <T1 : Comparable<T2>, T2 : Any?> isLessThanOrEqual(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion = container.createDescriptiveAssertion(IS_LESS_OR_EQUALS, expected) { it <= expected }

    override fun <T1 : Comparable<T2>, T2 : Any?> isGreaterThan(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion = container.createDescriptiveAssertion(IS_GREATER_THAN, expected) { it > expected }

    override fun <T1 : Comparable<T2>, T2 : Any?> isGreaterThanOrEqual(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion = container.createDescriptiveAssertion(IS_GREATER_OR_EQUALS, expected) { it >= expected }

    override fun <T1 : Comparable<T2>, T2 : Any?> isEqualComparingTo(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion = container.createDescriptiveAssertion(IS_EQUAL, expected) { it.compareTo(expected) == 0 }
}
