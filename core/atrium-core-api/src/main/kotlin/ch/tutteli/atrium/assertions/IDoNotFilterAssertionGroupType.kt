package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.IReporter

/**
 * An [IAssertionGroupType] which indicates that a [IReporter] should not filter its [IAssertionGroup.assertions].
 *
 * In contrast to [IExplanatoryAssertionGroupType] (which is a subtype), a [IReporter] is allowed to filter out
 * the whole [IAssertionGroup].
 */
interface IDoNotFilterAssertionGroupType : IAssertionGroupType
