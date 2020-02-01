package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * A default implementation for [AssertionGroup] -- it has a certain [type], a [description], a [representation]
 * and of course [assertions].
 *
 * @constructor A default implementation for [AssertionGroup].
 * @param type The type of the group, e.g. [FeatureAssertionGroupType].
 * @param description The description of the group.
 * @param representation The representation of the subject for which the [assertions] are defined.
 * @param assertions The assertions of this group, which are defined for the subject represented by [representation].
 */
@Deprecated("Use AssertionGroup, do not rely on this specific type, will be made internal with 1.0.0")
data class BasicAssertionGroup
@Deprecated(
    "Use `AssertImpl.builder.customType` instead, will be made `internal` with 1.0.0",
    ReplaceWith(
        "AssertImpl.builder.customType(type).withAssertions(description, representation, assertions).build()",
        "ch.tutteli.atrium.domain.builders.AssertImpl"
    )
)
constructor(
    override val type: AssertionGroupType,
    override val description: Translatable,
    override val representation: Any,
    override val assertions: List<Assertion>
) : AssertionGroup
