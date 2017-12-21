package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.Reporter

/**
 * An [AssertionGroupType] which indicates that a [Reporter] should not filter its [AssertionGroup.assertions].
 *
 * In contrast to [ExplanatoryAssertionGroupType] (which is a subtype), a [Reporter] is allowed to filter out
 * the whole [AssertionGroup].
 */
interface DoNotFilterAssertionGroupType : AssertionGroupType
