package ch.tutteli.atrium.assertions.builders.impl.fixedClaimGroup

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.translating.Translatable

internal data class FixedClaimAssertionGroup(
    override val type: AssertionGroupType,
    override val description: Translatable,
    override val representation: Any,
    override val assertions: List<Assertion>,
    private val holds: Boolean
) : AssertionGroup {

    /**
     * Returns what was passed into the constructor as `holds`.
     * @return `true` if the [AssertionGroup] holds; `false` otherwise.
     */
    override fun holds() = holds
}
