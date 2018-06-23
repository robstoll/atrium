package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.Reporter

/**
 * Represents the [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] should
 * be displayed in a list (up to the [Reporter] if it is a list with bullets, squares etc.).
 */
interface ListAssertionGroupType : AssertionGroupType

/**
 * The [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] should be displayed
 * in a list.
 */
object DefaultListAssertionGroupType : ListAssertionGroupType
