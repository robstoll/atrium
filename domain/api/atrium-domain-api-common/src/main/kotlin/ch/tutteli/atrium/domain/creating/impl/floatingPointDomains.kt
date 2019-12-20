package ch.tutteli.atrium.domain.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.*

internal class FloatDomainImpl(
    FloatDomainOnlyImpl: FloatOnlyDomain,
    anyDomain: AnyDomain<Float>
) : FloatDomain, FloatOnlyDomain by FloatDomainOnlyImpl, AnyDomain<Float> by anyDomain {
    override val expect: Expect<Float> = FloatDomainOnlyImpl.expect
}

internal class FloatOnlyDomainImpl(
    override val expect: Expect<Float>
) : FloatOnlyDomain {
    override fun toBeWithErrorTolerance(expected: Float, tolerance: Float): Assertion =
        floatingPointAssertions.toBeWithErrorTolerance(expect, expected, tolerance)
}


internal class DoubleDomainImpl(
    DoubleDomainOnlyImpl: DoubleOnlyDomain,
    anyDomain: AnyDomain<Double>
) : DoubleDomain, DoubleOnlyDomain by DoubleDomainOnlyImpl, AnyDomain<Double> by anyDomain {
    override val expect: Expect<Double> = DoubleDomainOnlyImpl.expect
}

internal class DoubleOnlyDomainImpl(
    override val expect: Expect<Double>
) : DoubleOnlyDomain {
    override fun toBeWithErrorTolerance(expected: Double, tolerance: Double): Assertion =
        floatingPointAssertions.toBeWithErrorTolerance(expect, expected, tolerance)
}
