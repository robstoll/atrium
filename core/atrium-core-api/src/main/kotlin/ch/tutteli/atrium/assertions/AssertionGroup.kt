package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The base interface for [Assertion] groups, providing a default implementation for [Assertion.holds]
 * which returns `true` if all its [assertions] hold.
 */
interface AssertionGroup : Assertion {
    /**
     * The name of the group.
     */
    val name: Translatable
    /**
     * The type of the group, e.g. [RootAssertionGroupType].
     */
    val type: AssertionGroupType

    /**
     * The subject for which the [assertions] are defined.
     */
    val subject: Any
    /**
     * The assertions of this group, which are defined for [subject].
     */
    val assertions: List<Assertion>

    /**
     * Holds if all its [assertions] hold.
     *
     * @return `true` if all [assertions] hold; `false` otherwise.
     */
    override fun holds() = assertions.all(Assertion::holds)

    @Deprecated("use AssertionBuilder instead, will be removed with 1.0.0")
    object Builder {
        @Deprecated("Use AssertionBuilder.root instead, will be removed with 1.0.0", ReplaceWith("AssertionBuilder.root", "ch.tutteli.atrium.assertions.builders.AssertionBuilder"))
        val root = BasicAssertionGroupBuilder(RootAssertionGroupType)
        @Deprecated("use AssertionBuilder.list instead, will be removed with 1.0.0", ReplaceWith("AssertionBuilder.list", "ch.tutteli.atrium.assertions.builders.AssertionBuilder"))
        val list = BasicAssertionGroupBuilder(DefaultListAssertionGroupType)
        @Deprecated("use AssertionBuilder.feature instead, will be removed with 1.0.0", ReplaceWith("AssertionBuilder.feature", "ch.tutteli.atrium.assertions.builders.AssertionBuilder"))
        val feature = BasicAssertionGroupBuilder(DefaultFeatureAssertionGroupType)
        @Deprecated("use AssertionBuilder.summary instead, will be removed with 1.0.0", ReplaceWith("AssertionBuilder.summary", "ch.tutteli.atrium.assertions.builders.AssertionBuilder"))
        val summary = BasicAssertionGroupBuilder(DefaultSummaryAssertionGroupType)
        @Deprecated("use AssertionBuilder.explanatoryGroup instead, will be removed with 1.0.0", ReplaceWith("AssertionBuilder.explanatoryGroup", "ch.tutteli.atrium.assertions.builders.AssertionBuilder"))
        val explanatory = ExplanatoryAssertionGroupOption()
        @Deprecated("use AssertionBuilder.invisible instead, will be removed with 1.0.0", ReplaceWith("AssertionBuilder.invisible", "ch.tutteli.atrium.assertions.builders.AssertionBuilder"))
        val invisible = EmptyNameAndSubjectAssertionGroupBuilder(DefaultInvisibleAssertionGroupType)

        @Deprecated("use AssertionBuilder.withType instead", ReplaceWith("AssertionBuilder.withType(groupType)", "ch.tutteli.atrium.assertions.builders.AssertionBuilder"))
        fun withType(groupType: AssertionGroupType) = BasicAssertionGroupBuilder(groupType)

        @Deprecated("use AssertionBuilder instead, will be removed with 1.0.0")
        class BasicAssertionGroupBuilder(private val groupType: AssertionGroupType) {
            fun create(name: Translatable, subject: Any, assertion: Assertion)
                = ch.tutteli.atrium.assertions.builders.BasicAssertionGroupBuilder(groupType).create(name, subject, assertion)

            fun create(name: Translatable, subject: Any, assertions: List<Assertion>): AssertionGroup
                = ch.tutteli.atrium.assertions.builders.BasicAssertionGroupBuilder(groupType).create(name, subject, assertions)
        }

        @Deprecated("use AssertionBuilder instead, will be removed with 1.0.0")
        class ExplanatoryAssertionGroupOption {
            val withDefault = ExplanatoryAssertionGroupBuilder(DefaultExplanatoryAssertionGroupType)
            val withWarning = ExplanatoryAssertionGroupBuilder(WarningAssertionGroupType)
            fun withType(groupType: ExplanatoryAssertionGroupType) = ExplanatoryAssertionGroupBuilder(groupType)
        }

        @Deprecated("use AssertionBuilder instead, will be removed with 1.0.0")
        class ExplanatoryAssertionGroupBuilder(private val groupType: ExplanatoryAssertionGroupType) {
            fun create(assertion: Assertion): AssertionGroup
                = ch.tutteli.atrium.assertions.builders.ExplanatoryAssertionGroupBuilder(groupType).create(assertion)

            fun create(assertions: List<Assertion>): AssertionGroup
                = ch.tutteli.atrium.assertions.builders.ExplanatoryAssertionGroupBuilder(groupType).create(assertions)
        }

        @Deprecated("use AssertionBuilder instead, will be removed with 1.0.0")
        class EmptyNameAndSubjectAssertionGroupBuilder(private val groupType: AssertionGroupType) {
            fun create(assertion: Assertion): AssertionGroup
                = ch.tutteli.atrium.assertions.builders.EmptyNameAndSubjectAssertionGroupBuilder(groupType).create(assertion)

            fun create(assertions: List<Assertion>): AssertionGroup
                = ch.tutteli.atrium.assertions.builders.EmptyNameAndSubjectAssertionGroupBuilder(groupType).create(assertions)
        }
    }
}
