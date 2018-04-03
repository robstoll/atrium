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

    override fun list(name: Translatable, subject: Any)
        = BasicAssertionGroupBuilderImpl(DefaultListAssertionGroupType, name, subject)

    override fun feature(featureName: Translatable, feature: Any)
        = BasicAssertionGroupBuilderImpl(DefaultFeatureAssertionGroupType, featureName, feature)

    override fun summary(name: Translatable)
        = BasicAssertionGroupBuilderImpl(DefaultSummaryAssertionGroupType, name, RawString.EMPTY)

    override val explanatoryGroup get()
        = ExplanatoryAssertionGroupOptionImpl

    override val descriptive get()
        = DescriptiveAssertionBuilderImpl

    override val explanatory get()
        = ExplanatoryAssertionBuilderImpl

    override fun <T: AssertionGroupType> withType(groupType: T, name: Translatable, subject: Any) : BasicAssertionGroupBuilder<T>
        = BasicAssertionGroupBuilderImpl(groupType, name, subject)
}
