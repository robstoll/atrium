package ch.tutteli.atrium.assertions

/**
 * Represents the [IAssertionGroupType] for [IAssertionGroup]s whose [assertions][IAssertionGroup.assertions]
 * should be displayed with an extra indent. Such a group might have a [IAssertionGroup.name] and
 * [IAssertionGroup.subject] (by accident) but should not be mentioned in reporting.
 */
interface IIndentAssertionGroupType : IAssertionGroupType

/**
 * The [IAssertionGroupType] for [IAssertionGroup]s whose assertions should be displayed with an extra indent.
 */
object IndentAssertionGroupType : IIndentAssertionGroupType
