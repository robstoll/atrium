package ch.tutteli.atrium.domain.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.*

internal class FloatDomainImpl(
    floatDomainSubImpl: FloatSubDomain,
    comparableDomain: ComparableDomain<Float, Float>
) : FloatDomain, FloatSubDomain by floatDomainSubImpl, ComparableDomain<Float, Float> by comparableDomain {
    override val expect: Expect<Float> = floatDomainSubImpl.expect
}

internal class FloatSubDomainImpl(
    override val expect: Expect<Float>
) : FloatSubDomain {
    override fun toBeWithErrorTolerance(expected: Float, tolerance: Float): Assertion =
        floatingPointAssertions.toBeWithErrorTolerance(expect, expected, tolerance)
}


internal class DoubleDomainImpl(
    doubleDomainSubImpl: DoubleSubDomain,
    comparableDomain: ComparableDomain<Double, Double>
) : DoubleDomain, DoubleSubDomain by doubleDomainSubImpl, ComparableDomain<Double, Double> by comparableDomain {
    override val expect: Expect<Double> = doubleDomainSubImpl.expect
}

internal class DoubleSubDomainImpl(
    override val expect: Expect<Double>
) : DoubleSubDomain {
    override fun toBeWithErrorTolerance(expected: Double, tolerance: Double): Assertion =
        floatingPointAssertions.toBeWithErrorTolerance(expect, expected, tolerance)
}
