package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.*

internal object AssertionBuilderImpl : AssertionBuilder {

    override val list: DefaultAssertionBuilderOptions<ListAssertionGroupType>
        get() = descriptionAndRepresentationOption(DefaultListAssertionGroupType)

    override val feature: DefaultAssertionBuilderOptions<FeatureAssertionGroupType>
        get() = descriptionAndRepresentationOption(DefaultFeatureAssertionGroupType)

    override val summary: DescriptionAndEmptyRepresentationOption<SummaryAssertionGroupType, AssertionsOption<SummaryAssertionGroupType, BasicAssertionGroupFinalStep>>
        get() = DescriptionAndEmptyRepresentationOption.create(DefaultSummaryAssertionGroupType, AssertionsOption.asFactoryWithDefaultFinalStep())

    override val explanatoryGroup get()
        = ExplanatoryAssertionGroupOptionImpl

    override val descriptive get()
        = DescriptiveAssertionHoldsOptionImpl

    override val explanatory get()
        = ExplanatoryAssertionBuilderImpl

    override fun <T : AssertionGroupType> customType(groupType: T): DefaultAssertionBuilderOptions<T>
        = descriptionAndRepresentationOption(groupType)


    private fun <T: AssertionGroupType> descriptionAndRepresentationOption(type: T): DefaultAssertionBuilderOptions<T>
        = DescriptionAndRepresentationOption.create(type, AssertionsOption.asFactoryWithDefaultFinalStep())
}
