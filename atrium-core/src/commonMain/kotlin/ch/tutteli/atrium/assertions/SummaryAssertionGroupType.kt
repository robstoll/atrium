//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.Reporter

/**
 * Represents the [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] should
 * all be reported in reporting (no filtering by a [Reporter]) since it represents a summary which itself
 * will point out which assertions hold and which do not.
 *
 * This [AssertionGroupType] should only be used for cases where we need to present a result as a whole and where
 * filtering out successful assertions would either be misleading or not complete.
 *
 * An [AssertionFormatter] will typically use [PrefixSuccessfulSummaryAssertion] and [PrefixFailingSummaryAssertion]
 * to prefix the [AssertionGroup.assertions].
 */
@Deprecated("Switch from AssertionGroup to ProofGroup which does not require AssertionGroupType, will be removed with 2.0.0 at the latest")
interface SummaryAssertionGroupType : DoNotFilterAssertionGroupType

/**
 * Represents the identifier for bullet points used to prefix [Assertion]s which hold, in context of an
 * [AssertionGroup] with type [SummaryAssertionGroupType].
 */
@Deprecated("Switch from AssertionGroup to ProofGroup which does not require AssertionGroupType, will be removed with 2.0.0 at the latest")
class PrefixSuccessfulSummaryAssertion private constructor() : BulletPointIdentifier

/**
 * Represents the identifier for bullet points used to prefix [Assertion]s which do not hold, in context of an
 * [AssertionGroup] with type [SummaryAssertionGroupType].
 */
@Deprecated("Switch from AssertionGroup to ProofGroup which does not require AssertionGroupType, will be removed with 2.0.0 at the latest")
class PrefixFailingSummaryAssertion private constructor() : BulletPointIdentifier

/**
 * The [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] should not be
 * filtered (by a [Reporter]) and for which an [AssertionFormatter] should state whether they hold or not.
 */
@Deprecated("Switch from AssertionGroup to ProofGroup which does not require AssertionGroupType, will be removed with 2.0.0 at the latest")
object DefaultSummaryAssertionGroupType : SummaryAssertionGroupType
