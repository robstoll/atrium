package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * A default implementation for [AssertionGroup] -- it has a certain [type], a [name], a [subject]
 * and of course [assertions].
 *
 * @constructor A default implementation for [AssertionGroup].
 * @param type The type of the group, e.g. [FeatureAssertionGroupType].
 * @param name The name of the group.
 * @param subject The subject for which the [assertions] are defined.
 * @param assertions The assertions of this group, which are defined for [subject].
 */
@Deprecated("Use AssertionGroup, do not rely on this specific type, will be made internal with 1.0.0")
data class BasicAssertionGroup
@Deprecated(
    "use `AssertImpl.builder.explanatoryGroup` instead, will be made `internal` with 1.0.0",
    ReplaceWith(
        "AssertImpl.builder.withType(type).create(name, subject, assertions)",
        "ch.tutteli.atrium.domain.builders.creating.AssertImpl"
    )
)
constructor(
    override val type: AssertionGroupType,
    override val name: Translatable,
    override val subject: Any,
    override val assertions: List<Assertion>
) : AssertionGroup
