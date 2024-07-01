//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.Reporter

/**
 * An [AssertionGroupType] which indicates that a [Reporter] should not filter its [AssertionGroup.assertions].
 *
 * In contrast to [ExplanatoryAssertionGroupType] (which is a subtype), a [Reporter] is allowed to filter out
 * the whole [AssertionGroup].
 */
@Deprecated("Switch from AssertionGroup to ProofGroup which does not require AssertionGroupType, will be removed with 2.0.0 at the latest")
interface DoNotFilterAssertionGroupType : AssertionGroupType
