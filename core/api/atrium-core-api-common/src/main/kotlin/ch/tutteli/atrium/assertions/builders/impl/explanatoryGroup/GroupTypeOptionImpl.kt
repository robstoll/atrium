package ch.tutteli.atrium.assertions.builders.impl.explanatoryGroup

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DefaultExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.WarningAssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionsOption
import ch.tutteli.atrium.assertions.builders.ExplanatoryGroup
import ch.tutteli.atrium.reporting.translating.Translatable

internal object GroupTypeOptionImpl : ExplanatoryGroup.GroupTypeOption {

    override val withDefaultType: AssertionsOption<DefaultExplanatoryAssertionGroupType, ExplanatoryGroup.FinalStep>
        = createAssertionsOption(DefaultExplanatoryAssertionGroupType)

    override val withWarningType: AssertionsOption<WarningAssertionGroupType, ExplanatoryGroup.FinalStep>
        = createAssertionsOption(WarningAssertionGroupType)

    override fun <T : ExplanatoryAssertionGroupType> withType(
        groupType: T
    ): AssertionsOption<T, ExplanatoryGroup.FinalStep> = createAssertionsOption(groupType)

    private fun <T : ExplanatoryAssertionGroupType> createAssertionsOption(groupType: T)
        = AssertionsOption.withEmptyDescriptionAndRepresentation(
            groupType, GroupTypeOptionImpl::createExplanatoryAssertionGroupFinalStep
        )

    @Suppress("UNUSED_PARAMETER")
    private fun <T : ExplanatoryAssertionGroupType> createExplanatoryAssertionGroupFinalStep(
        groupType: T,
        ignoredDescription: Translatable,
        ignoredRepresentation: Any,
        explanatoryAssertions: List<Assertion>
    ): ExplanatoryGroup.FinalStep = ExplanatoryGroup.FinalStep.create(groupType, explanatoryAssertions)
}
