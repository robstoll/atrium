package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.DefaultFeatureAssertionGroupType
import ch.tutteli.atrium.assertions.DefaultListAssertionGroupType
import ch.tutteli.atrium.assertions.DefaultSummaryAssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.BasicAssertionGroupBuilder
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

internal object AssertionBuilderImpl : AssertionBuilder {

    override fun list(name: Translatable, representation: Any)
        = BasicAssertionGroupBuilderImpl(DefaultListAssertionGroupType, name, representation)

    override fun feature(featureName: Translatable, featureRepresentation: Any)
        = BasicAssertionGroupBuilderImpl(DefaultFeatureAssertionGroupType, featureName, featureRepresentation)

    override fun summary(name: Translatable)
        = BasicAssertionGroupBuilderImpl(DefaultSummaryAssertionGroupType, name, RawString.EMPTY)

    override val explanatoryGroup get()
        = ExplanatoryAssertionGroupOptionImpl

    override val descriptive get()
        = DescriptiveAssertionHoldsOptionImpl

    override val explanatory get()
        = ExplanatoryAssertionBuilderImpl

    override fun <T: AssertionGroupType> customType(groupType: T, name: Translatable, representation: Any) : BasicAssertionGroupBuilder<T>
        = BasicAssertionGroupBuilderImpl(groupType, name, representation)
}
