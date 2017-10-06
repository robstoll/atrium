package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.IReporter

/**
 * Represents the [IAssertionGroupType] for [IAssertionGroup]s whose [assertions][IAssertionGroup.assertions] should
 * be displayed in an list (up to the [IReporter] if it is a list with bullets, squares etc.).
 */
interface IInvisibleAssertionGroupType : IAssertionGroupType

/**
 * The [IAssertionGroupType] for [IAssertionGroup]s whose [assertions][IAssertionGroup.assertions] should be displayed
 * in an list.
 */
object InvisibleAssertionGroupType : IInvisibleAssertionGroupType
