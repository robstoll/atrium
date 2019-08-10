package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.impl.AssertionBuilderImpl
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.Reporter
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
     *
     * Notice, return type will change to [ExplanatoryGroup.GroupTypeOption] with 1.0.0.
     */
    @Suppress(
        "DEPRECATION"
        /** TODO change to ExplanatoryGroup.GroupTypeOption with 1.0.0 */
    )
    val explanatoryGroup: ExplanatoryAssertionGroupTypeOption

    /**
     * Builder to create a [DescriptiveAssertion] -- use it to create a simple assertion consisting of a
     * [DescriptiveAssertion.description] (such as `is less than`) and a [DescriptiveAssertion.representation]
     * (which most of time corresponds to the expected value).
     */
    val descriptive: Descriptive.HoldsOption

    /**
     * Builder to create an [ExplanatoryAssertion] -- use it to explain something which is typically formatted by an
     * [ObjectFormatter] -- has to be a child of an [ExplanatoryGroup] (see [explanatoryGroup]).
     *
     * For instance, it is used to explain additional entries in an [Iterable] `contains entries` assertion.
     * It is typically used in an [explanatoryGroup].
     */
    val explanatory: Explanatory.ExplanationOption

    /**
     * Builder to create a basic [AssertionGroup] with a custom [AssertionGroupType].
     *
     * @param groupType the [AssertionGroup.type].
     */
    fun <T : AssertionGroupType> customType(groupType: T): DefaultAssertionGroupBuilderOptions<T>

    /**
     * Creates a [DescriptiveAssertion] based on the given [description], [representation] and [test]
     *
     * Notice, if you want to use text (e.g. a [String]) as representation,
     * then wrap it into a [RawString] via [RawString.create] and pass the [RawString] instead.
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
     * Creates a [DescriptiveAssertion] based on the given [description], [representation] and [test]
     *
     * Notice, if you want to use text (e.g. a [String]) as representation,
     * then wrap it into a [RawString] via [RawString.create] and pass the [RawString] instead.
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

    /**
     * Creates a [DescriptiveAssertion] based on the [description], [representation] and [test] as well as the
     * [SubjectProvider.maybeSubject] of the given [subjectProvider].
     *
     * Notice, if you want to use text (e.g. a [String]) as representation,
     * then wrap it into a [RawString] via [RawString.create] and pass the [RawString] instead.
     *
     * Shortcut for:
     * ```
     * descriptive
     *   .withTest(subjectProvider, test)
     *   .withDescriptionAndRepresentation(description, representation)
     *   .build()
     * ```
     *
     * @param subjectProvider The [Expect] from which we take the [Expect.maybeSubject] and pass it on to the given [test].
     * @param description The description of the assertion, e.g. `to Be`.
     * @param representation The representation of the expected outcome.
     * @param test The test which checks whether the assertion holds.
     *
     * @throws PlantHasNoSubjectException in case [test] is called in a context where it is not safe to call it.
     *   For instance, if [test] is called within an explanatory assertion where it is possible that
     *   [Expect.maybeSubject] is [None].
     */
    fun <T> createDescriptive(
        subjectProvider: SubjectProvider<T>,
        description: String,
        representation: Any?,
        test: (T) -> Boolean
    ): DescriptiveAssertion = createDescriptive(subjectProvider, Untranslatable(description), representation, test)

    /**
     * Creates a [DescriptiveAssertion] based on the [description], [representation] and [test] as well as the
     * [SubjectProvider.maybeSubject] of the given [subjectProvider].
     *
     * Notice, if you want to use text (e.g. a [String]) as representation,
     * then wrap it into a [RawString] via [RawString.create] and pass the [RawString] instead.
     *
     * Shortcut for:
     * ```
     * descriptive
     *   .withTest(subjectProvider, test)
     *   .withDescriptionAndRepresentation(description, representation)
     *   .build()
     * ```
     *
     * @param subjectProvider The [Expect] from which we take the [Expect.maybeSubject] and pass it on to the given [test].
     * @param description The description of the assertion, e.g. `to Be`.
     * @param representation The representation of the expected outcome.
     * @param test The test which checks whether the assertion holds.
     *
     * @throws PlantHasNoSubjectException in case [test] is called in a context where it is not safe to call it.
     *   For instance, if [test] is called within an explanatory assertion where it is possible that
     *   [Expect.maybeSubject] is [None].
     */
    fun <T> createDescriptive(
        subjectProvider: SubjectProvider<T>,
        description: Translatable,
        representation: Any?,
        test: (T) -> Boolean
    ): DescriptiveAssertion =
        descriptive
            .withTest(subjectProvider, test)
            .withDescriptionAndRepresentation(description, representation)
            .build()
}
