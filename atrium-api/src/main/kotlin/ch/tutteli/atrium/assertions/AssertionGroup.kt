package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Represents a group of [IAssertion]s -- it has a certain [type], a [name], a [subject] and of course [assertions].
 *
 * @constructor Represents a group of [IAssertion]s with a certain [type], a [name], a [subject]
 *              and of course [assertions].
 * @param name The name of the group.
 * @param type The type of the group, e.g. [IFeatureAssertionGroupType].
 * @param subject The subject for which the [assertions] are defined.
 * @param assertions The assertions of this group, which are defined for [subject].
 */
data class AssertionGroup(
    override val type: IAssertionGroupType,
    override val name: ITranslatable,
    override val subject: Any,
    override val assertions: List<IAssertion>) : IAssertionGroup
