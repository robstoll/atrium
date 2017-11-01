package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.IAtriumFactory
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IReporter

/**
 * Represents the [IAssertionGroupType] for [IAssertionGroup]s whose [assertions][IAssertionGroup.assertions] should
 * all be reported in reporting (no filtering by an [IReporter]) since it represents a summary which itself
 * will point out which assertions hold and which do not.
 *
 * This [IAssertionGroupType] should only be used for cases where we need to present a result as a whole and where
 * filtering out successful assertions would either be misleading or not complete.
 *
 * An [IAssertionFormatter] will typically use [PrefixSuccessfulSummaryAssertion] and [PrefixFailingSummaryAssertion]
 * to prefix the [IAssertionGroup.assertions].
 */
interface ISummaryAssertionGroupType : IDoNotFilterAssertionGroupType

/**
 * Represents the identifier for bullet points used to prefix [IAssertion]s which hold, in context of an
 * [IAssertionGroup] with type [ISummaryAssertionGroupType].
 *
 * See also [IAtriumFactory.registerSameLineTextAssertionFormatterCapabilities].
 */
class PrefixSuccessfulSummaryAssertion private constructor() : IBulletPointIdentifier

/**
 * Represents the identifier for bullet points used to prefix [IAssertion]s which do not hold, in context of an
 * [IAssertionGroup] with type [ISummaryAssertionGroupType].
 *
 * See also [IAtriumFactory.registerSameLineTextAssertionFormatterCapabilities].
 */
class PrefixFailingSummaryAssertion private constructor() : IBulletPointIdentifier

/**
 * The [IAssertionGroupType] for [IAssertionGroup]s whose [assertions][IAssertionGroup.assertions] should not be
 * filtered (by an [IReporter]) and for which an [IAssertionFormatter] should state whether they hold or not.
 */
object SummaryAssertionGroupType : ISummaryAssertionGroupType
