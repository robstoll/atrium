package ch.tutteli.atrium.domain.builders.assertions

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a group of [Assertion]s -- it has a certain [type], a [name], a [subject], [assertions] and a fixed
 * value for [holds] which does not depend on [assertions] (usually indirectly).
 *
 * The intention behind this [AssertionGroup] is to use it when all [assertions] are [AssertionGroup]s with an
 * [ExplanatoryAssertionGroupType].
 *
 * @constructor Represents a group of [Assertion]s -- it has a certain [type], a [name], a [subject], [assertions] and a fixed
 *   value for [holds] which does not depend on [assertions] (usually indirectly).
 * @param type The type of the group, e.g. [FeatureAssertionGroupType].
 * @param name The name of the group.
 * @param subject The subject for which the [assertions] are defined.
 * @param assertions The assertions of this group, which are defined for [subject].
 * @param holds Indicates whether the assertions hold or not
 */
internal data class FixHoldsAssertionGroup(
    override val type: AssertionGroupType,
    override val name: Translatable,
    override val subject: Any,
    override val assertions: List<Assertion>,
    private val holds: Boolean
) : AssertionGroup {

    /**
     * Returns what was passed into the constructor as `holds`.
     * @return `true` if the [AssertionGroup] holds; `false` otherwise.
     */
    override fun holds() = holds
}
