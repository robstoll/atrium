package ch.tutteli.atrium.assertions

/**
 * Represents the [IAssertionGroupType] for [IAssertionGroup]s whose [assertions][IAssertionGroup.assertions] are mainly
 * used to explain something (it is not of importance whether they hold or not).
 */
interface IExplanatoryAssertionGroupType : IAssertionGroupType

/**
 * The [IAssertionGroupType] for [IAssertionGroup]s whose [assertions][IAssertionGroup.assertions] are used to explain
 * something rather than to make out something.
 */
object ExplanatoryAssertionGroupType : IListAssertionGroupType
