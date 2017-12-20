package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.Reporter

/**
 * Represents the [IAssertionGroupType] for [IAssertionGroup]s whose [assertions][IAssertionGroup.assertions] should
 * be displayed in a list (up to the [Reporter] if it is a list with bullets, squares etc.).
 */
interface IListAssertionGroupType : IAssertionGroupType

/**
 * The [IAssertionGroupType] for [IAssertionGroup]s whose [assertions][IAssertionGroup.assertions] should be displayed
 * in a list.
 */
object ListAssertionGroupType : IListAssertionGroupType
