package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.Reporter

/**
 * An [IAssertionGroupType] which indicates that a [Reporter] should not filter its [IAssertionGroup.assertions].
 *
 * In contrast to [IExplanatoryAssertionGroupType] (which is a subtype), a [Reporter] is allowed to filter out
 * the whole [IAssertionGroup].
 */
interface IDoNotFilterAssertionGroupType : IAssertionGroupType
