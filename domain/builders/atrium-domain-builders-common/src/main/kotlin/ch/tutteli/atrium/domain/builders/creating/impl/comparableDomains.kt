package ch.tutteli.atrium.domain.builders.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.AnyDomain
import ch.tutteli.atrium.domain.creating.ComparableDomain
import ch.tutteli.atrium.domain.creating.ComparableOnlyDomain
import ch.tutteli.atrium.domain.creating.comparableAssertions

internal class ComparableDomainImpl<O, T : Comparable<O>>(
    comparableDomainOnlyImpl: ComparableOnlyDomain<O, T>,
    anyDomain: AnyDomain<T>
) : ComparableDomain<O, T>, ComparableOnlyDomain<O, T> by comparableDomainOnlyImpl, AnyDomain<T> by anyDomain {
    override val expect: Expect<T> = comparableDomainOnlyImpl.expect
}

internal class ComparableOnlyDomainImpl<O, T : Comparable<O>>(
    override val expect: Expect<T>
) : ComparableOnlyDomain<O, T> {
    override fun isLessThan(expected: O): Assertion = comparableAssertions.isLessThan(expect, expected)
    override fun isLessOrEquals(expected: O): Assertion = comparableAssertions.isLessOrEquals(expect, expected)
    override fun isGreaterThan(expected: O): Assertion = comparableAssertions.isGreaterThan(expect, expected)
    override fun isGreaterOrEquals(expected: O): Assertion = comparableAssertions.isGreaterOrEquals(expect, expected)
}
