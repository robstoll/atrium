package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DefaultExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.WarningAssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionsOption
import ch.tutteli.atrium.assertions.builders.ExplanatoryAssertionGroupFinalStep
import ch.tutteli.atrium.assertions.builders.ExplanatoryAssertionGroupOption
import ch.tutteli.atrium.reporting.translating.Translatable

internal object ExplanatoryAssertionGroupOptionImpl : ExplanatoryAssertionGroupOption {

    override val withDefault: AssertionsOption<DefaultExplanatoryAssertionGroupType, ExplanatoryAssertionGroupFinalStep>
        get() = assertionsOption(DefaultExplanatoryAssertionGroupType)

    override val withWarning: AssertionsOption<WarningAssertionGroupType, ExplanatoryAssertionGroupFinalStep>
        get() = assertionsOption(WarningAssertionGroupType)

    override fun <T : ExplanatoryAssertionGroupType> withType(
        groupType: T
    ): AssertionsOption<T, ExplanatoryAssertionGroupFinalStep> = assertionsOption(groupType)

    private fun <T : ExplanatoryAssertionGroupType> assertionsOption(groupType: T)
        = AssertionsOption.withEmptyDescriptionAndRepresentation(
            groupType, ::createExplanatoryAssertionGroupFinalStepImpl
        )

    @Suppress("UNUSED_PARAMETER")
    private fun <T : ExplanatoryAssertionGroupType> createExplanatoryAssertionGroupFinalStepImpl(
        groupType: T,
        ignoredDescription: Translatable,
        ignoredRepresentation: Any,
        explanatoryAssertions: List<Assertion>
    ): ExplanatoryAssertionGroupFinalStep = ExplanatoryAssertionGroupFinalStep.create(groupType, explanatoryAssertions)
}
