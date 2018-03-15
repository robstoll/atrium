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

    @Deprecated("use AssertImpl.builder instead, will be removed with 1.0.0")
    object Builder {
        @Deprecated(
            "Use AssertImpl.builder.root instead, will be removed with 1.0.0",
            ReplaceWith(
                "AssertImpl.builder.root",
                "ch.tutteli.atrium.domain.builders.creating.AssertImpl",
                "ch.tutteli.atrium.assertions.builders.root"
            )
        )
        val root = BasicAssertionGroupBuilder(RootAssertionGroupType)

        @Deprecated(
            "use AssertImpl.builder.list instead, will be removed with 1.0.0",
            ReplaceWith("AssertImpl.builder.list", "ch.tutteli.atrium.domain.builders.creating.AssertImpl")
        )
        val list = BasicAssertionGroupBuilder(DefaultListAssertionGroupType)

        @Deprecated(
            "use AssertImpl.builder.feature instead, will be removed with 1.0.0",
            ReplaceWith("AssertImpl.builder.feature", "ch.tutteli.atrium.domain.builders.creating.AssertImpl")
        )
        val feature = BasicAssertionGroupBuilder(DefaultFeatureAssertionGroupType)

        @Deprecated(
            "use AssertImpl.builder.summary instead, will be removed with 1.0.0",
            ReplaceWith("AssertImpl.builder.summary", "ch.tutteli.atrium.domain.builders.creating.AssertImpl")
        )
        val summary = BasicAssertionGroupBuilder(DefaultSummaryAssertionGroupType)

        @Deprecated(
            "use AssertImpl.builder.explanatoryGroup instead, will be removed with 1.0.0",
            ReplaceWith("AssertImpl.builder.explanatoryGroup", "ch.tutteli.atrium.domain.builders.creating.AssertImpl")
        )
        val explanatory = ExplanatoryAssertionGroupOption()

        @Deprecated(
            "use AssertImpl.builder.invisible instead, will be removed with 1.0.0",
            ReplaceWith(
                "AssertImpl.builder.invisibleGroup",
                "ch.tutteli.atrium.domain.builders.creating.AssertImpl",
                "ch.tutteli.atrium.assertions.builders.invisibleGroup"
            )
        )
        val invisible = EmptyNameAndSubjectAssertionGroupBuilder(DefaultInvisibleAssertionGroupType)

        @Deprecated(
            "use AssertImpl.builder.withType instead",
            ReplaceWith(
                "AssertImpl.builder.withType(groupType)",
                "ch.tutteli.atrium.domain.builders.creating.AssertImpl"
            )
        )
        fun withType(groupType: AssertionGroupType) = BasicAssertionGroupBuilder(groupType)

        @Deprecated("use AssertImpl.builder instead, will be removed with 1.0.0")
        class BasicAssertionGroupBuilder(private val groupType: AssertionGroupType) {
            fun create(name: Translatable, subject: Any, assertion: Assertion)
                = ch.tutteli.atrium.assertions.builders.BasicAssertionGroupBuilder(groupType).create(name, subject, assertion)

            fun create(name: Translatable, subject: Any, assertions: List<Assertion>): AssertionGroup
                = ch.tutteli.atrium.assertions.builders.BasicAssertionGroupBuilder(groupType).create(name, subject, assertions)
        }

        @Deprecated("use AssertImpl.builder instead, will be removed with 1.0.0")
        class ExplanatoryAssertionGroupOption {
            val withDefault = ExplanatoryAssertionGroupBuilder(DefaultExplanatoryAssertionGroupType)
            val withWarning = ExplanatoryAssertionGroupBuilder(WarningAssertionGroupType)
            fun withType(groupType: ExplanatoryAssertionGroupType) = ExplanatoryAssertionGroupBuilder(groupType)
        }

        @Deprecated("use AssertImpl.builder instead, will be removed with 1.0.0")
        class ExplanatoryAssertionGroupBuilder(private val groupType: ExplanatoryAssertionGroupType) {
            fun create(assertion: Assertion): ExplanatoryAssertionGroup
                = create(assertion)

            fun create(assertions: List<Assertion>): ExplanatoryAssertionGroup
                = ExplanatoryAssertionGroup(groupType, assertions)
        }

        @Deprecated("use AssertImpl.builder instead, will be removed with 1.0.0")
        class EmptyNameAndSubjectAssertionGroupBuilder(private val groupType: AssertionGroupType) {
            fun create(assertion: Assertion): AssertionGroup
                = ch.tutteli.atrium.assertions.builders.EmptyNameAndSubjectAssertionGroupBuilder(groupType).create(assertion)

            fun create(assertions: List<Assertion>): AssertionGroup
                = ch.tutteli.atrium.assertions.builders.EmptyNameAndSubjectAssertionGroupBuilder(groupType).create(assertions)
        }
    }
}
