package ch.tutteli.atrium.assertions

/**
 * Represents the [IAssertionGroupType] for [IAssertionGroup]s whose [assertions][IAssertionGroup.assertions] are mainly
 * used to explain something -- it is not of importance whether they hold or not and thus such [IAssertionGroup]s should
 * always return `true` for [holds][IAssertionGroup.holds].
 */
interface IExplanatoryAssertionGroupType : IAssertionGroupType

/**
 * The [IAssertionGroupType] for [IAssertionGroup]s whose [assertions][IAssertionGroup.assertions] are used to explain
 * something rather than pointing something out -- accordingly the [IAssertionGroup.holds] should always return `true`.
 */
object ExplanatoryAssertionGroupType : IExplanatoryAssertionGroupType
