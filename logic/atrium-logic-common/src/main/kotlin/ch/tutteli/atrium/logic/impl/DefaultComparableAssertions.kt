package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.ComparableAssertions
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.translations.DescriptionComparableExpectation.*

class DefaultComparableAssertions : ComparableAssertions {

    override fun <T1 : Comparable<T2>, T2 : Any?> isLessThan(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion = container.createDescriptiveAssertion(TO_BE_LESS_THAN, expected) { it < expected }

    override fun <T1 : Comparable<T2>, T2 : Any?> isLessThanOrEqual(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion = container.createDescriptiveAssertion(TO_BE_LESS_THAN_OR_EQUAL_TO, expected) { it <= expected }

    override fun <T1 : Comparable<T2>, T2 : Any?> isNotGreaterThan(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion = container.createDescriptiveAssertion(NOT_TO_BE_GREATER_THAN, expected) { it <= expected }

    override fun <T1 : Comparable<T2>, T2 : Any?> isEqualComparingTo(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion = container.createDescriptiveAssertion(TO_BE_EQUAL_COMPARING_TO, expected) { it.compareTo(expected) == 0 }


    override fun <T1 : Comparable<T2>, T2 : Any?> isGreaterThanOrEqual(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion = container.createDescriptiveAssertion(TO_BE_GREATER_THAN_OR_EQUAL_TO, expected) { it >= expected }

    override fun <T1 : Comparable<T2>, T2 : Any?> isNotLessThan(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion = container.createDescriptiveAssertion(NOT_TO_BE_LESS_THAN, expected) { it >= expected }


    override fun <T1 : Comparable<T2>, T2 : Any?> isGreaterThan(
        container: AssertionContainer<T1>,
        expected: T2
    ): Assertion = container.createDescriptiveAssertion(TO_BE_GREATER_THAN, expected) { it > expected }

}
