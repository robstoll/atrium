//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.impl.AssertionBuilderImpl
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Returns the [AssertionBuilder].
 */
val assertionBuilder: AssertionBuilder = AssertionBuilderImpl

/**
 * Type alias which can be used if only the [AssertionGroupType] is parameterised and the next step is
 * an [AssertionsOption] which in turn has the [BasicAssertionGroupFinalStep] as final step.
 */
typealias DefaultAssertionGroupBuilderOptions<T> = AssertionGroupDescriptionAndRepresentationOption<T, AssertionsOption<T, BasicAssertionGroupFinalStep>>

/**
 * Represents a builder which creates [Assertion]s and [AssertionGroup]s.
 */
//TODO 1.3.0 deprecate and probably all other symbols in this package and sub-packages
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
     * [Assertion]s which hold are shown to a user in reporting. This kind is inter alia used for [Iterable]
     * `contains.inAnyOrder.only` assertions where it quickly gets confusing if you do not see the assertions which
     * hold.
     *
     * For example, hamcrest has this problem; if you write the following using hamcrest:
     *
     * `expect(listOf(1,1), IsIterableContainingInOrder.contains(1))`
     *
     * it will complain that you expected `1` but it did not match `1`. If you could see that it actually matched the
     * first `1` and only did not match the second `1`, then it would be clear from the beginning.
     */
    val summary: AssertionGroupDescriptionAndEmptyRepresentationOption<SummaryAssertionGroupType, AssertionsOption<SummaryAssertionGroupType, BasicAssertionGroupFinalStep>>

    /**
     * Builder to create an [AssertionGroup] with a [ExplanatoryAssertionGroupType] -- such a group is always shown in
     * reporting (a [Reporter] has to neglect whether the [Assertion.holds] or not). Use it to provide explanations.
     * It is inter alia used in [Iterable] `contains entries` assertions to describe the identification lambda you used.
     *
     * Notice, return type will change to [ExplanatoryGroup.GroupTypeOption] with 1.0.0.
     */
    val explanatoryGroup: ExplanatoryGroup.GroupTypeOption

    /**
     * Builder to create a [DescriptiveAssertion] -- use it to create a simple assertion consisting of a
     * [DescriptiveAssertion.description] (such as `is less than`) and a [DescriptiveAssertion.representation]
     * (which most of the time corresponds to the expected value).
     *
     * Use [representationOnly] in case your assertion does not require a description.
     */
    val descriptive: Descriptive.HoldsOption

    /**
     * Builder to create an [ExplanatoryAssertion] -- use it to explain something which is typically formatted by an
     * [ObjectFormatter] -- has to be a child of an [ExplanatoryGroup] (see [explanatoryGroup]).
     *
     * For instance, it is used to explain additional entries in an [Iterable] `contains entries` assertion.
     *
     * Use [representationOnly] instead if the assertion can also fail.
     */
    val explanatory: Explanatory.ExplanationOption

    /**
     * Builder to create an [RepresentationOnlyAssertion] -- typically used within an [AssertionGroup] where the
     * description of the group gives enough context, so that this assertion does not require an additional description.
     *
     * Use [descriptive] in case the assertion requires an additional description.
     */
    val representationOnly: RepresentationOnly.HoldsStep

    /**
     * Builder to create a basic [AssertionGroup] with a custom [AssertionGroupType].
     *
     * @param groupType the [AssertionGroup.type].
     */
    fun <T : AssertionGroupType> customType(groupType: T): DefaultAssertionGroupBuilderOptions<T>

    /**
     * Creates a [DescriptiveAssertion] based on the given [description], [representation] and [test].
     *
     * Notice, if you want to use text (a [String] which is treated as raw string in reporting) as representation,
     * then wrap it into a [Text] and pass it instead.
     *
     * Shortcut for:
     * ```
     * descriptive
     *   .withTest(test)
     *   .withDescriptionAndRepresentation(description, representation)
     *   .build()
     * ```
     * @param description The description of the assertion, e.g. `to Be`
     * @param representation The representation of the expected outcome
     * @param test The test which checks whether the assertion holds
     */
    fun createDescriptive(description: String, representation: Any?, test: () -> Boolean): DescriptiveAssertion =
        createDescriptive(Untranslatable(description), representation, test)

    /**
     * Creates a [DescriptiveAssertion] based on the given [description], [representation] and [test].
     *
     * Notice, if you want to use text (a [String] which is treated as raw string in reporting) as representation,
     * then wrap it into a [Text] and pass it instead.
     *
     * Shortcut for:
     * ```
     * descriptive
     *   .withTest(test)
     *   .withDescriptionAndRepresentation(description, representation)
     *   .build()
     * ```
     * @param description The description of the assertion, e.g. `to Be`
     * @param representation The representation of the expected outcome
     * @param test The test which checks whether the assertion holds
     */
    fun createDescriptive(description: Translatable, representation: Any?, test: () -> Boolean): DescriptiveAssertion =
        descriptive
            .withTest(test)
            .withDescriptionAndRepresentation(description, representation)
            .build()
}
