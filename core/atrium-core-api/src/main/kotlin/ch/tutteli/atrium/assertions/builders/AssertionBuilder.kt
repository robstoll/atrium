@file:JvmName("AssertionBuilderKt")

package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.impl.AssertionBuilderImpl
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Returns the [AssertionBuilder].
 */
val assertionBuilder: AssertionBuilder = AssertionBuilderImpl

typealias DefaultAssertionGroupBuilderOptions<T> = AssertionGroupDescriptionAndRepresentationOption<T, AssertionsOption<T, BasicAssertionGroupFinalStep>>

/**
 * Represents a builder which creates [Assertion]s and [AssertionGroup]s.
 */
interface AssertionBuilder {

    /**
     * Builder to create an [AssertionGroup] with a [ListAssertionGroupType] -- kind of the default type
     * for [AssertionGroup]s, if you do not know what to choose, this is probably the best fit for you.
     */
    val list: DefaultAssertionGroupBuilderOptions<ListAssertionGroupType>

    /**
     * Builder to create an [AssertionGroup] with a [FeatureAssertionGroupType] -- use it if you want to make an
     * [Assertion] about a feature of the subject.
     */
    val feature: DefaultAssertionGroupBuilderOptions<FeatureAssertionGroupType>

    /**
     * Builder to create an [AssertionGroup] with a [SummaryAssertionGroupType] -- use it if it is essential that also
     * [Assertion]s which hold are shown to a user in error reporting. This kind is inter alia used for [Iterable]
     * `contains` assertions where it quickly gets confusing if you do not see the assertions which hold.
     *
     * For example, hamcrest has this problem; if you write the following using hamcrest:
     *
     * `assertThat(listOf(1,1), IsIterableContainingInOrder.contains(1))`
     *
     * it will complain that you expected `1` but it did not match `1`. If you could see that it actually matched the
     * first `1` and only did not match the second `1`, then it would be clear from the beginning.
     */
    val summary: AssertionGroupDescriptionAndEmptyRepresentationOption<SummaryAssertionGroupType, AssertionsOption<SummaryAssertionGroupType, BasicAssertionGroupFinalStep>>

    /**
     * Builder to create an [AssertionGroup] with a [ExplanatoryAssertionGroupType] -- such a group is always shown in
     * reporting (a [Reporter] has to neglect whether the [Assertion.holds] or not). Use it to provide explanations.
     * It is inter alia used in [Iterable] `contains entries` assertions to describe the identification lambda you used.
     */
    val explanatoryGroup: ExplanatoryAssertionGroupTypeOption

    /**
     * Builder to create a [DescriptiveAssertion] -- use it to create a simple assertion consisting of a
     * [DescriptiveAssertion.description] (such as `is less than`) and a [DescriptiveAssertion.representation]
     * (which most of time corresponds to the expected value).
     */
    val descriptive: DescriptiveAssertionHoldsOption

    /**
     * Builder to create an [ExplanatoryAssertion] -- use it to explain something which is typically formatted by an
     * [ObjectFormatter].
     *
     * For instance, it is used to explain additional entries in an [Iterable] `contains entries` assertion.
     * It is typically used in an [explanatoryGroup].
     */
    val explanatory: ExplanatoryAssertionDescriptionOption

    /**
     * Builder to create a basic [AssertionGroup] with a custom [AssertionGroupType].
     *
     * @param groupType the [AssertionGroup.type].
     */
    fun <T : AssertionGroupType> customType(groupType: T): DefaultAssertionGroupBuilderOptions<T>

    /**
     * Creates a [DescriptiveAssertion] based on the given [description], [representation] and [test]
     *
     * Shortcut for:
     * ```
     * descriptive
     *   .withTest(test)
     *   .withDescriptionAndRepresentation(description, representation)
     *   .build()
     * ```
     */
    fun createDescriptive(description: Translatable, representation: Any, test: () -> Boolean)
        = descriptive.withTest(test).withDescriptionAndRepresentation(description, representation).build()
}
