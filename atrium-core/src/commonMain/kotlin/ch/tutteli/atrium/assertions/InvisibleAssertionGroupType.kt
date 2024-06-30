//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.AssertionFormatterController

/**
 * Represents the [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] should
 * be displayed in a manner that the user does not even notice that the [Assertion]s have been grouped.
 *
 * An [AssertionFormatterController] has to take special care about such groups.
 */
@Deprecated("Switch from AssertionGroup to ProofGroup which does not require AssertionGroupType, will be removed with 2.0.0 at the latest")
object InvisibleAssertionGroupType : AssertionGroupType
