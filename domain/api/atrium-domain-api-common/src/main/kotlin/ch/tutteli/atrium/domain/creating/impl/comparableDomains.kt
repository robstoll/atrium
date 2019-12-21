package ch.tutteli.atrium.domain.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.AnyDomain
import ch.tutteli.atrium.domain.creating.ComparableDomain
import ch.tutteli.atrium.domain.creating.ComparableSubDomain
import ch.tutteli.atrium.domain.creating.comparableAssertions

internal class ComparableDomainImpl<O, T : Comparable<O>>(
    comparableDomainSubImpl: ComparableSubDomain<O, T>,
    anyDomain: AnyDomain<T>
) : ComparableDomain<O, T>, ComparableSubDomain<O, T> by comparableDomainSubImpl, AnyDomain<T> by anyDomain {
    override val expect: Expect<T> = comparableDomainSubImpl.expect
}

internal class ComparableSubDomainImpl<O, T : Comparable<O>>(
    override val expect: Expect<T>
) : ComparableSubDomain<O, T> {
    override fun isLessThan(expected: O): Assertion = comparableAssertions.isLessThan(expect, expected)
    override fun isLessOrEquals(expected: O): Assertion = comparableAssertions.isLessOrEquals(expect, expected)
    override fun isGreaterThan(expected: O): Assertion = comparableAssertions.isGreaterThan(expect, expected)
    override fun isGreaterOrEquals(expected: O): Assertion = comparableAssertions.isGreaterOrEquals(expect, expected)
}
