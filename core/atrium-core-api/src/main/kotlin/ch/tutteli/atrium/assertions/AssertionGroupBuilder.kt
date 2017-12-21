package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a builder for [IAssertionGroup].
 */
class AssertionGroupBuilder {
    companion object {
        val root = BasicAssertionGroupBuilder(RootAssertionGroupType)
        val list = BasicAssertionGroupBuilder(ListAssertionGroupType)
        val feature = BasicAssertionGroupBuilder(FeatureAssertionGroupType)
        val summary = BasicAssertionGroupBuilder(SummaryAssertionGroupType)
        val explanatory = ExplanatoryAssertionGroupOption()
        val invisible = InvisibleAssertionGroupBuilder()

        fun withType(groupType: IAssertionGroupType) = BasicAssertionGroupBuilder(groupType)

        class BasicAssertionGroupBuilder(private val groupType: IAssertionGroupType) {
            fun create(name: Translatable, subject: Any, assertion: IAssertion)
                = create(name, subject, listOf(assertion))

            fun create(name: Translatable, subject: Any, assertions: List<IAssertion>): IAssertionGroup
                = BasicAssertionGroup(groupType, name, subject, assertions)
        }

        class ExplanatoryAssertionGroupOption {
            val withDefault = ExplanatoryAssertionGroupBuilder(DefaultExplanatoryAssertionGroupType)
            val withWarning = ExplanatoryAssertionGroupBuilder(WarningAssertionGroupType)
            fun withType(groupType: IExplanatoryAssertionGroupType) = ExplanatoryAssertionGroupBuilder(groupType)
        }

        class ExplanatoryAssertionGroupBuilder(private val groupType: IExplanatoryAssertionGroupType) {
            fun create(assertion: IAssertion)
                = create(listOf(assertion))

            fun create(assertions: List<IAssertion>): ExplanatoryAssertionGroup
                = ExplanatoryAssertionGroup(groupType, assertions)
        }

        class InvisibleAssertionGroupBuilder {
            fun create(assertion: IAssertion)
                = create(listOf(assertion))

            fun create(assertions: List<IAssertion>): InvisibleAssertionGroup
                = InvisibleAssertionGroup(assertions)
        }
    }
}
