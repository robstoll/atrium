package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Represents a builder for [Assertion].
 */
object AssertionBuilder {
    val root = BasicAssertionGroupBuilder(RootAssertionGroupType)
    val list = BasicAssertionGroupBuilder(DefaultListAssertionGroupType)
    val feature = BasicAssertionGroupBuilder(DefaultFeatureAssertionGroupType)
    val summary = BasicAssertionGroupBuilder(DefaultSummaryAssertionGroupType)
    val explanatory = ExplanatoryAssertionGroupOption()
    val invisibleGroup = EmptyNameAndSubjectAssertionGroupBuilder(DefaultInvisibleAssertionGroupType)
    val descriptive = DescriptiveAssertionBuilder()

    fun withType(groupType: AssertionGroupType) = BasicAssertionGroupBuilder(groupType)

    class BasicAssertionGroupBuilder(private val groupType: AssertionGroupType) {
        fun create(name: Translatable, subject: Any, assertion: Assertion)
            = create(name, subject, listOf(assertion))

        fun create(name: Translatable, subject: Any, assertions: List<Assertion>): AssertionGroup
            = BasicAssertionGroup(groupType, name, subject, assertions)
    }

    class ExplanatoryAssertionGroupOption {
        val withDefault = ExplanatoryAssertionGroupBuilder(DefaultExplanatoryAssertionGroupType)
        val withWarning = ExplanatoryAssertionGroupBuilder(WarningAssertionGroupType)
        fun withType(groupType: ExplanatoryAssertionGroupType) = ExplanatoryAssertionGroupBuilder(groupType)
    }

    class ExplanatoryAssertionGroupBuilder(private val groupType: ExplanatoryAssertionGroupType) {
        fun create(assertion: Assertion) = create(listOf(assertion))

        fun create(assertions: List<Assertion>): ExplanatoryAssertionGroup
            = ExplanatoryAssertionGroup(groupType, assertions)
    }

    class EmptyNameAndSubjectAssertionGroupBuilder(private val groupType: AssertionGroupType) {
        fun create(assertion: Assertion) = create(listOf(assertion))

        fun create(assertions: List<Assertion>): EmptyNameAndSubjectAssertionGroup
            = EmptyNameAndSubjectAssertionGroup(groupType, assertions)
    }

    class DescriptiveAssertionBuilder {
        /**
         * Wraps the [test] into a lambda and delegates to the other `create` overload.
         */
        fun create(description: String, representation: Any, test: Boolean): DescriptiveAssertion
            = create(description, representation, { test })

        /**
         * Wraps the given [description] into an [Untranslatable] and delegates to the other `create` overload.
         */
        fun create(description: String, representation: Any, test: () -> Boolean): DescriptiveAssertion
            = create(Untranslatable(description), representation, test)

        /**
         * Creates an [DescriptiveAssertion] based on the given [description], [representation] and [test].
         */
        fun create(description: Translatable, representation: Any, test: Boolean): DescriptiveAssertion
            = create(description, representation, { test })

        /**
         * Creates an [DescriptiveAssertion] based on the given [description], [representation] and [test].
         */
        fun create(description: Translatable, representation: Any, test: () -> Boolean): DescriptiveAssertion
            = BasicDescriptiveAssertion(description, representation, test)
    }
}
