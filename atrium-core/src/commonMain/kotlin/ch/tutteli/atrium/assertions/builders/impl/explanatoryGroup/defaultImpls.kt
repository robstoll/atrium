//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions.builders.impl.explanatoryGroup

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.AssertionsOption
import ch.tutteli.atrium.assertions.builders.ExplanatoryGroup
import ch.tutteli.atrium.reporting.translating.Translatable

internal object GroupTypeOptionImpl : ExplanatoryGroup.GroupTypeOption {

    override val withDefaultType: AssertionsOption<DefaultExplanatoryAssertionGroupType, ExplanatoryGroup.FinalStep> =
        createAssertionsOption(DefaultExplanatoryAssertionGroupType)

    override val withWarningType: AssertionsOption<WarningAssertionGroupType, ExplanatoryGroup.FinalStep> =
        createAssertionsOption(WarningAssertionGroupType)

    override fun withInformationType(withIndent: Boolean): AssertionsOption<InformationAssertionGroupType, ExplanatoryGroup.FinalStep> =
        createAssertionsOption(InformationAssertionGroupType(withIndent))

    override fun <T : ExplanatoryAssertionGroupType> withType(
        groupType: T
    ): AssertionsOption<T, ExplanatoryGroup.FinalStep> = createAssertionsOption(groupType)

    override val withHintType: AssertionsOption<HintAssertionGroupType, ExplanatoryGroup.FinalStep>
        get() = createAssertionsOption(HintAssertionGroupType)

    private fun <T : ExplanatoryAssertionGroupType> createAssertionsOption(groupType: T) =
        AssertionsOption.withEmptyDescriptionAndRepresentation(
            groupType, GroupTypeOptionImpl::createExplanatoryAssertionGroupFinalStep
        )

    @Suppress("UNUSED_PARAMETER" /* params are here so that we can use a method reference */)
    private fun <T : ExplanatoryAssertionGroupType> createExplanatoryAssertionGroupFinalStep(
        groupType: T,
        ignoredDescription: Translatable,
        ignoredRepresentation: Any,
        explanatoryAssertions: List<Assertion>
    ): ExplanatoryGroup.FinalStep = ExplanatoryGroup.FinalStep.create(groupType, explanatoryAssertions)
}

internal class FinalStepImpl(
    override val groupType: ExplanatoryAssertionGroupType,
    override val explanatoryAssertions: List<Assertion>,
    private val holds: Boolean
) : ExplanatoryGroup.FinalStep {

    @Suppress("DEPRECATION")
    override fun build(): AssertionGroup = ExplanatoryAssertionGroup(groupType, explanatoryAssertions, holds)
}
