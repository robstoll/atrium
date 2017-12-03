package ch.tutteli.atrium.assertions

/**
 * Represents the [IAssertionGroupType] for [IAssertionGroup]s whose [assertions][IAssertionGroup.assertions] should
 * be displayed in a manner that the user does not even notice that the [IAssertion]s have been grouped.
 */
interface IInvisibleAssertionGroupType : IAssertionGroupType

/**
 * The [IAssertionGroupType] for [IAssertionGroup]s whose grouping behaviour should be kind of invisible to the user.
 */
object InvisibleAssertionGroupType : IInvisibleAssertionGroupType
