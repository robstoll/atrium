package ch.tutteli.atrium.domain.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.*

internal class FloatDomainImpl(
    floatDomainOnlyImpl: FloatOnlyDomain,
    anyDomain: AnyDomain<Float>
) : FloatDomain, FloatOnlyDomain by floatDomainOnlyImpl, AnyDomain<Float> by anyDomain {
    override val expect: Expect<Float> = floatDomainOnlyImpl.expect
}

internal class FloatOnlyDomainImpl(
    override val expect: Expect<Float>
) : FloatOnlyDomain {
    override fun toBeWithErrorTolerance(expected: Float, tolerance: Float): Assertion =
        floatingPointAssertions.toBeWithErrorTolerance(expect, expected, tolerance)
}


internal class DoubleDomainImpl(
    doubleDomainOnlyImpl: DoubleOnlyDomain,
    anyDomain: AnyDomain<Double>
) : DoubleDomain, DoubleOnlyDomain by doubleDomainOnlyImpl, AnyDomain<Double> by anyDomain {
    override val expect: Expect<Double> = doubleDomainOnlyImpl.expect
}

internal class DoubleOnlyDomainImpl(
    override val expect: Expect<Double>
) : DoubleOnlyDomain {
    override fun toBeWithErrorTolerance(expected: Double, tolerance: Double): Assertion =
        floatingPointAssertions.toBeWithErrorTolerance(expect, expected, tolerance)
}
