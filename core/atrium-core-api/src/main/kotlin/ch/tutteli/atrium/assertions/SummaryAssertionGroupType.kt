package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.IAtriumFactory
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.Reporter

/**
 * Represents the [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] should
 * all be reported in reporting (no filtering by an [Reporter]) since it represents a summary which itself
 * will point out which assertions hold and which do not.
 *
 * This [AssertionGroupType] should only be used for cases where we need to present a result as a whole and where
 * filtering out successful assertions would either be misleading or not complete.
 *
 * An [AssertionFormatter] will typically use [PrefixSuccessfulSummaryAssertion] and [PrefixFailingSummaryAssertion]
 * to prefix the [AssertionGroup.assertions].
 */
interface SummaryAssertionGroupType : DoNotFilterAssertionGroupType

/**
 * Represents the identifier for bullet points used to prefix [Assertion]s which hold, in context of an
 * [AssertionGroup] with type [SummaryAssertionGroupType].
 *
 * See also [IAtriumFactory.registerSameLineTextAssertionFormatterCapabilities].
 */
class PrefixSuccessfulSummaryAssertion private constructor() : BulletPointIdentifier

/**
 * Represents the identifier for bullet points used to prefix [Assertion]s which do not hold, in context of an
 * [AssertionGroup] with type [SummaryAssertionGroupType].
 *
 * See also [IAtriumFactory.registerSameLineTextAssertionFormatterCapabilities].
 */
class PrefixFailingSummaryAssertion private constructor() : BulletPointIdentifier

/**
 * The [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] should not be
 * filtered (by an [Reporter]) and for which an [AssertionFormatter] should state whether they hold or not.
 */
object DefaultSummaryAssertionGroupType : SummaryAssertionGroupType
