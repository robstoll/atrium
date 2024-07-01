//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.assertions

/**
 * Represents the [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] are mainly
 * used to explain something -- hence in the normal case it should not be of importance whether they hold or not
 * and thus such [AssertionGroup]s should usually return `true` for [holds][AssertionGroup.holds].
 *
 * But they can return `false` in which case the corresponding group holding it should no longer hold either.
 */
@Deprecated("Switch from AssertionGroup to ProofGroup which does not require AssertionGroupType, will be removed with 2.0.0 at the latest")
interface ExplanatoryAssertionGroupType : DoNotFilterAssertionGroupType

/**
 * The [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] are used to explain
 * something rather than pointing something out -- accordingly the [AssertionGroup.holds] should always return `true`.
 */
@Deprecated("Switch from AssertionGroup to ProofGroup which does not require AssertionGroupType, will be removed with 2.0.0 at the latest")
object DefaultExplanatoryAssertionGroupType : ExplanatoryAssertionGroupType

/**
 * The [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] are used to state
 * a warning rather than making an assumption.
 *
 * For instance, to state that an implicit assumption is not met.
 */
@Deprecated("Switch from AssertionGroup to ProofGroup which does not require AssertionGroupType, will be removed with 2.0.0 at the latest")
object WarningAssertionGroupType : ExplanatoryAssertionGroupType

/**
 * The [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] are used to document
 * the reason for one or multiple assertions.
 *
 * @param withIndent defines whether the group header should be indented (hence also all children will be indented
 *   one level more) or not.
 */
@Deprecated("Switch from AssertionGroup to ProofGroup which does not require AssertionGroupType, will be removed with 2.0.0 at the latest")
data class InformationAssertionGroupType(val withIndent: Boolean) : ExplanatoryAssertionGroupType

/**
 * The [AssertionGroupType] for [AssertionGroup]s whose [assertions][AssertionGroup.assertions] are used to document
 * a hint about the usage of the chosen function or rules in Atrium
 */
@Deprecated("Switch from AssertionGroup to ProofGroup which does not require AssertionGroupType, will be removed with 2.0.0 at the latest")
object HintAssertionGroupType : ExplanatoryAssertionGroupType
