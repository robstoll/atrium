package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a builder for [AssertionGroup].
 */
object AssertionGroupBuilder {
    val root = BasicAssertionGroupBuilder(RootAssertionGroupType)
    val list = BasicAssertionGroupBuilder(DefaultListAssertionGroupType)
    val feature = BasicAssertionGroupBuilder(DefaultFeatureAssertionGroupType)
    val summary = BasicAssertionGroupBuilder(DefaultSummaryAssertionGroupType)
    val explanatory = ExplanatoryAssertionGroupOption()
    val invisible = EmptyNameAndSubjectAssertionGroupBuilder(DefaultInvisibleAssertionGroupType)

    fun withType(groupType: AssertionGroupType) = BasicAssertionGroupBuilder(groupType)

    class BasicAssertionGroupBuilder(private val groupType: AssertionGroupType) {
        fun create(name: Translatable, subject: Any, assertion: Assertion) = create(name, subject, listOf(assertion))

        fun create(name: Translatable, subject: Any, assertions: List<Assertion>): AssertionGroup = BasicAssertionGroup(groupType, name, subject, assertions)
    }

    class ExplanatoryAssertionGroupOption {
        val withDefault = ExplanatoryAssertionGroupBuilder(DefaultExplanatoryAssertionGroupType)
        val withWarning = ExplanatoryAssertionGroupBuilder(WarningAssertionGroupType)
        fun withType(groupType: ExplanatoryAssertionGroupType) = ExplanatoryAssertionGroupBuilder(groupType)
    }

    class ExplanatoryAssertionGroupBuilder(private val groupType: ExplanatoryAssertionGroupType) {
        fun create(assertion: Assertion) = create(listOf(assertion))

        fun create(assertions: List<Assertion>): ExplanatoryAssertionGroup = ExplanatoryAssertionGroup(groupType, assertions)
    }

    class EmptyNameAndSubjectAssertionGroupBuilder(private val groupType: AssertionGroupType) {
        fun create(assertion: Assertion) = create(listOf(assertion))

        fun create(assertions: List<Assertion>): EmptyNameAndSubjectAssertionGroup = EmptyNameAndSubjectAssertionGroup(groupType, assertions)
    }
}
