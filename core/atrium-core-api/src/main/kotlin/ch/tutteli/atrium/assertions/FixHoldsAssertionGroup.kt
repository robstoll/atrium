package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a group of [IAssertion]s -- it has a certain [type], a [name], a [subject], [assertions] and a fixed
 * value for [holds] which does not depend on [assertions] (usually indirectly).
 *
 * The intention behind this [IAssertionGroup] is to use it when all [assertions] are [IAssertionGroup]s with an
 * [IExplanatoryAssertionGroupType].
 *
 * @constructor Represents a group of [IAssertion]s -- it has a certain [type], a [name], a [subject], [assertions] and a fixed
 * value for [holds] which does not depend on [assertions] (usually indirectly).
 * @param type The type of the group, e.g. [IFeatureAssertionGroupType].
 * @param name The name of the group.
 * @param subject The subject for which the [assertions] are defined.
 * @param assertions The assertions of this group, which are defined for [subject].
 * @param holds Indicates whether the assertions hold or not
 */
data class FixHoldsAssertionGroup(
    override val type: IAssertionGroupType,
    override val name: Translatable,
    override val subject: Any,
    override val assertions: List<IAssertion>,
    private val holds: Boolean) : IAssertionGroup {

    override fun holds(): Boolean {
        return holds
    }
}
