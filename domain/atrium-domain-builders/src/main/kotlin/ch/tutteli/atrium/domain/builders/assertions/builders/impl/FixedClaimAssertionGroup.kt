package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a group of [Assertion]s -- it has a certain [type], a [name], a [representation], [assertions] and a fixed
 * value for [holds] which does not depend on [assertions] (usually indirectly).
 *
 * The intention behind this [AssertionGroup] is to use it when all [assertions] are [AssertionGroup]s with an
 * [ExplanatoryAssertionGroupType].
 *
 * @constructor Represents a group of [Assertion]s -- it has a certain [type], a [name], a [representation], [assertions] and a fixed
 *   value for [holds] which does not depend on [assertions] (usually indirectly).
 * @param type The type of the group, e.g. [FeatureAssertionGroupType].
 * @param name The name of the group.
 * @param representation The representation of the subject for which the [assertions] are defined.
 * @param assertions The assertions of this group, which are defined for [representation].
 * @param holds Indicates whether the [assertions] hold or not.
 */
internal data class FixedClaimAssertionGroup(
    override val type: AssertionGroupType,
    override val name: Translatable,
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
