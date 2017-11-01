package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.IReporter

/**
 * An [IAssertionGroupType] which indicates that a [IReporter] should not filter its [IAssertionGroup.assertions].
 */
interface IDoNotFilterAssertionGroupType : IAssertionGroupType
