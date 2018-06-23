package ch.tutteli.atrium.assertions

/**
 * Represents the [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions]
 * should be displayed with an extra indent. Such a group might have a [AssertionGroup.description] and
 * [AssertionGroup.representation] (by accident) but should not be mentioned in reporting.
 */
@Deprecated("So far indentation was achieved by grouping (which is the solution to go). See other `AssertionGroupType`s. Will be removed with 1.0.0")
interface IndentAssertionGroupType : AssertionGroupType

/**
 * The [AssertionGroupType] for [AssertionGroup]s whose assertions should be displayed with an extra indent.
 */
@Deprecated("So far indentation was achieved by grouping (which is the solution to go). See other `AssertionGroupType`s. Will be removed with 1.0.0")
object DefaultIndentAssertionGroupType : IndentAssertionGroupType
