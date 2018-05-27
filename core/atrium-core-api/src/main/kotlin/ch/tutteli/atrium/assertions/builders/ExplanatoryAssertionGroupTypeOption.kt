package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DefaultExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.WarningAssertionGroupType

/**
 * Provides options to create an [AssertionGroup] with a certain [ExplanatoryAssertionGroupType].
 */
interface ExplanatoryAssertionGroupTypeOption {

    /**
     * Builder to create an [AssertionGroup] with a [DefaultExplanatoryAssertionGroupType].
     */
    val withDefaultType: AssertionsOption<DefaultExplanatoryAssertionGroupType, ExplanatoryAssertionGroupFinalStep>

    /**
     * Builder to create an [AssertionGroup] with a [WarningAssertionGroupType].
     */
    val withWarningType: AssertionsOption<WarningAssertionGroupType, ExplanatoryAssertionGroupFinalStep>

    /**
     * Builder to create an [AssertionGroup] with a custom [ExplanatoryAssertionGroupType].
     */
    fun <T : ExplanatoryAssertionGroupType> withType(groupType: T): AssertionsOption<T, ExplanatoryAssertionGroupFinalStep>
}
