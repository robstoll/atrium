package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.builders.AssertionsOption
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.reporting.LazyRepresentation
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The base interface for [Assertion] groups, providing a default implementation for [Assertion.holds]
 * which returns `true` if all its [assertions] hold.
 */
interface AssertionGroup : Assertion {

    /**
     * The description of the group.
     */
    val description: Translatable

    @Deprecated("Use description, will be removed with 1.0.0", ReplaceWith("description"))
    val name get() = description


    /**
     * The type of the group, e.g. [RootAssertionGroupType].
     */
    val type: AssertionGroupType


    /**
     * A complementing representation to the description -- typically the subject for which the [assertions]
     * are defined.
     *
     * For instance, if the description is `index 0` then the representation shows what is at index 0.
     */
    val representation: Any

    @Deprecated("Use representation, will be removed with 1.0.0", ReplaceWith("representation"))
    val subject get() = representation


    /**
     * The assertions of this group, which are defined for the subject represented by [representation].
     */
    val assertions: List<Assertion>

    /**
     * Holds if all its [assertions] hold.
     *
     * @return `true` if all [assertions] hold; `false` otherwise.
     */
    override fun holds() = assertions.all(Assertion::holds)

    @Deprecated("Use AssertImpl.builder instead, will be removed with 1.0.0")
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
        val invisible = EmptyNameAndSubjectAssertionGroupBuilder(InvisibleAssertionGroupType)

        @Deprecated(
            "use AssertImpl.builder.customType instead",
            ReplaceWith(
                "AssertImpl.builder.customType(groupType)",
                "ch.tutteli.atrium.domain.builders.creating.AssertImpl"
            )
        )
        fun withType(groupType: AssertionGroupType) = BasicAssertionGroupBuilder(groupType)

        @Deprecated("Use AssertImpl.builder instead, will be removed with 1.0.0")
        class BasicAssertionGroupBuilder(private val groupType: AssertionGroupType) {
            fun create(name: Translatable, subject: Any, assertion: Assertion): AssertionGroup
                = assertionBuilder.customType(groupType)
                .withDescriptionAndRepresentation(name, subject)
                .withAssertion(assertion)
                .build()

            fun create(name: Translatable, subject: Any, assertions: List<Assertion>): AssertionGroup
                = assertionBuilder.customType(groupType)
                .withDescriptionAndRepresentation(name, subject)
                .withAssertions(assertions)
                .build()
        }

        @Deprecated("Use AssertImpl.builder instead, will be removed with 1.0.0")
        class ExplanatoryAssertionGroupOption {
            val withDefault = ExplanatoryAssertionGroupBuilder(DefaultExplanatoryAssertionGroupType)
            val withWarning = ExplanatoryAssertionGroupBuilder(WarningAssertionGroupType)
            fun withType(groupType: ExplanatoryAssertionGroupType) = ExplanatoryAssertionGroupBuilder(groupType)
        }

        @Deprecated("Use AssertImpl.builder instead, will be removed with 1.0.0")
        class ExplanatoryAssertionGroupBuilder(private val groupType: ExplanatoryAssertionGroupType) {
            fun create(assertion: Assertion): ExplanatoryAssertionGroup
                = create(assertion)

            fun create(assertions: List<Assertion>): ExplanatoryAssertionGroup
                = ExplanatoryAssertionGroup(groupType, assertions)
        }

        @Deprecated("Use AssertImpl.builder instead, will be removed with 1.0.0")
        class EmptyNameAndSubjectAssertionGroupBuilder(private val groupType: AssertionGroupType) {
            fun create(assertion: Assertion): AssertionGroup
                = AssertionsOption.withDefaultFinalStepAndEmptyDescriptionAndRepresentation(groupType)
                    .withAssertion(assertion)
                    .build()

            fun create(assertions: List<Assertion>): AssertionGroup
                = AssertionsOption.withDefaultFinalStepAndEmptyDescriptionAndRepresentation(groupType)
                    .withAssertions(assertions)
                    .build()
        }
    }
}
