package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.*

internal object AssertionBuilderImpl : AssertionBuilder {

    override val list: DefaultAssertionGroupBuilderOptions<ListAssertionGroupType>
        = createDescriptionAndRepresentationOption(DefaultListAssertionGroupType)

    override val feature: DefaultAssertionGroupBuilderOptions<FeatureAssertionGroupType>
        = createDescriptionAndRepresentationOption(DefaultFeatureAssertionGroupType)

    override val summary: AssertionGroupDescriptionAndEmptyRepresentationOption<SummaryAssertionGroupType, AssertionsOption<SummaryAssertionGroupType, BasicAssertionGroupFinalStep>>
        = AssertionGroupDescriptionAndEmptyRepresentationOption.create(DefaultSummaryAssertionGroupType, AssertionsOption.factoryWithDefaultFinalStep())

    override val explanatoryGroup: ExplanatoryGroup.GroupTypeOption
        = ExplanatoryGroup.GroupTypeOption.create()

    override val descriptive: Descriptive.HoldsOption
        = Descriptive.HoldsOption.create()

    override val explanatory: Explanatory.ExplanationOption
        = Explanatory.ExplanationOption.create()

    override fun <T : AssertionGroupType> customType(groupType: T): DefaultAssertionGroupBuilderOptions<T>
        = createDescriptionAndRepresentationOption(groupType)


    private fun <T: AssertionGroupType> createDescriptionAndRepresentationOption(type: T): DefaultAssertionGroupBuilderOptions<T>
        = AssertionGroupDescriptionAndRepresentationOption.create(type, AssertionsOption.factoryWithDefaultFinalStep())
}
