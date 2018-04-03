package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.*


/**
 * Provides options to create an [AssertionGroup] with a certain [ExplanatoryAssertionGroupType].
 */
interface ExplanatoryAssertionGroupOption {

    /**
     * Builder to create an [AssertionGroup] with a [DefaultExplanatoryAssertionGroupType].
     */
    val withDefault: ExplanatoryAssertionGroupBuilder<DefaultExplanatoryAssertionGroupType>

    /**
     * Builder to create an [AssertionGroup] with a [WarningAssertionGroupType].
     */
    val withWarning: ExplanatoryAssertionGroupBuilder<WarningAssertionGroupType>

    /**
     * Builder to create an [AssertionGroup] with a custom [ExplanatoryAssertionGroupType].
     */
    fun <T: ExplanatoryAssertionGroupType> withType(groupType: T): ExplanatoryAssertionGroupBuilder<T>
}
