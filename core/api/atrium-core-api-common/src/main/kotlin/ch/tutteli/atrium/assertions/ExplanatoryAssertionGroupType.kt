package ch.tutteli.atrium.assertions

/**
 * Represents the [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] are mainly
 * used to explain something -- it is not of importance whether they hold or not and thus such [AssertionGroup]s should
 * always return `true` for [holds][AssertionGroup.holds].
 */
interface ExplanatoryAssertionGroupType : DoNotFilterAssertionGroupType

/**
 * The [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] are used to explain
 * something rather than pointing something out -- accordingly the [AssertionGroup.holds] should always return `true`.
 */
object DefaultExplanatoryAssertionGroupType : ExplanatoryAssertionGroupType

/**
 * The [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] are used to state
 * a warning rather than making an assumption.
 *
 * For instance, to state that an implicit assumption is not met.
 */
object WarningAssertionGroupType : ExplanatoryAssertionGroupType

/**
 * The [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] are used to document
 * the reason for one or multiple assertions.
 */
object InformationAssertionGroupType : ExplanatoryAssertionGroupType
