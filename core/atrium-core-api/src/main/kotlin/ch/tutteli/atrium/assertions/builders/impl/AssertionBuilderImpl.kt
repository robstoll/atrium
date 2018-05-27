package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.*

internal object AssertionBuilderImpl : AssertionBuilder {

    override val list: DefaultAssertionBuilderOptions<ListAssertionGroupType>
        = createDescriptionAndRepresentationOption(DefaultListAssertionGroupType)

    override val feature: DefaultAssertionBuilderOptions<FeatureAssertionGroupType>
        = createDescriptionAndRepresentationOption(DefaultFeatureAssertionGroupType)

    override val summary: DescriptionAndEmptyRepresentationOption<SummaryAssertionGroupType, AssertionsOption<SummaryAssertionGroupType, BasicAssertionGroupFinalStep>>
        = DescriptionAndEmptyRepresentationOption.create(DefaultSummaryAssertionGroupType, AssertionsOption.asFactoryWithDefaultFinalStep())

    override val explanatoryGroup: ExplanatoryAssertionGroupTypeOption
        = ExplanatoryAssertionGroupTypeOptionImpl

    override val descriptive: DescriptiveAssertionHoldsOption
        = DescriptiveAssertionHoldsOptionImpl

    override val explanatory: ExplanatoryAssertionDescriptionOption
        = ExplanatoryAssertionDescriptionOptionImpl

    override fun <T : AssertionGroupType> customType(groupType: T): DefaultAssertionBuilderOptions<T>
        = createDescriptionAndRepresentationOption(groupType)


    private fun <T: AssertionGroupType> createDescriptionAndRepresentationOption(type: T): DefaultAssertionBuilderOptions<T>
        = DescriptionAndRepresentationOption.create(type, AssertionsOption.asFactoryWithDefaultFinalStep())
}
