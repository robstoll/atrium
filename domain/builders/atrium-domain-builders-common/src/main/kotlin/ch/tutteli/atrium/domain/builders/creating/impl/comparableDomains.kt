package ch.tutteli.atrium.domain.builders.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ComparableDomain
import ch.tutteli.atrium.domain.creating.comparableAssertions

internal class ComparableDomainImpl<O, T : Comparable<O>>(override val expect: Expect<T>) : ComparableDomain<O, T> {
    override fun isLessThan(expected: O): Assertion = comparableAssertions.isLessThan(expect, expected)
    override fun isLessOrEquals(expected: O): Assertion = comparableAssertions.isLessOrEquals(expect, expected)
    override fun isGreaterThan(expected: O): Assertion = comparableAssertions.isGreaterThan(expect, expected)
    override fun isGreaterOrEquals(expected: O): Assertion = comparableAssertions.isGreaterOrEquals(expect, expected)
}
