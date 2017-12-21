package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * A default implementation for [AssertionGroup] -- it has a certain [type], a [name], a [subject]
 * and of course [assertions].
 *
 * @constructor Use [AssertionGroup.Builder] to create a [BasicAssertionGroup], an [AssertionGroup] respectively.
 * @param type The type of the group, e.g. [FeatureAssertionGroupType].
 * @param name The name of the group.
 * @param subject The subject for which the [assertions] are defined.
 * @param assertions The assertions of this group, which are defined for [subject].
 */
data class BasicAssertionGroup internal constructor(
    override val type: AssertionGroupType,
    override val name: Translatable,
    override val subject: Any,
    override val assertions: List<Assertion>) : AssertionGroup
