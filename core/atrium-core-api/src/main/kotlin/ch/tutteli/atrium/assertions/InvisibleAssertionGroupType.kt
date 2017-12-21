package ch.tutteli.atrium.assertions

/**
 * Represents the [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] should
 * be displayed in a manner that the user does not even notice that the [Assertion]s have been grouped.
 */
interface InvisibleAssertionGroupType : AssertionGroupType

/**
 * The [AssertionGroupType] for [AssertionGroup]s whose grouping behaviour should be kind of invisible to the user.
 */
object DefaultInvisibleAssertionGroupType : InvisibleAssertionGroupType
