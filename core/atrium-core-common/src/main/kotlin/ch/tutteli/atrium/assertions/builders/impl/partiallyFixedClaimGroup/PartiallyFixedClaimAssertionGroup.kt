package ch.tutteli.atrium.assertions.builders.impl.partiallyFixedClaimGroup

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.reporting.translating.Translatable

internal data class PartiallyFixedClaimAssertionGroup(
    override val type: AssertionGroupType,
    override val description: Translatable,
    override val representation: Any,
    override val assertions: List<Assertion>,
    private val preTransformationHolds: Boolean
) : AssertionGroup {

    /**
     * Holds if [preTransformationHolds] and all its [assertions] hold.
     *
     * @return `true` if [preTransformationHolds] and all [assertions] hold; `false` otherwise.
     */
    override fun holds() = preTransformationHolds && super.holds()
}
