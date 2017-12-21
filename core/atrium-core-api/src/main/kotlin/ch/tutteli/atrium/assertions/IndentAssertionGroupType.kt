package ch.tutteli.atrium.assertions

/**
 * Represents the [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions]
 * should be displayed with an extra indent. Such a group might have a [AssertionGroup.name] and
 * [AssertionGroup.subject] (by accident) but should not be mentioned in reporting.
 */
interface IndentAssertionGroupType : AssertionGroupType

/**
 * The [AssertionGroupType] for [AssertionGroup]s whose assertions should be displayed with an extra indent.
 */
object DefaultIndentAssertionGroupType : IndentAssertionGroupType
