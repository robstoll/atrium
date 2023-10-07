package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.Reporter

/**
 * Represents the [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions]
 * all be reported in reporting (no filtering by a [Reporter]) since it represents a group of assertions made
 * for (most likely) unrelated subjects.
 *
 * @since 1.1.0
 */
interface GroupingAssertionGroupType : AssertionGroupType

/**
 * The [AssertionGroupType] for [AssertionGroup]s which contain assertions which shall be grouped.
 *
 * @since 1.1.0
 */
object DefaultGroupingAssertionGroupType : GroupingAssertionGroupType
