//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions.builders.impl.partiallyFixedClaimGroup

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType

internal data class PartiallyFixedClaimAssertionGroup(
    override val type: AssertionGroupType,
    @Suppress("DEPRECATION") override val description: ch.tutteli.atrium.reporting.translating.Translatable,
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
