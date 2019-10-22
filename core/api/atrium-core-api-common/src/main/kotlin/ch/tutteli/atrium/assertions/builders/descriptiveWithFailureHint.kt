package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.impl.descriptiveWithFailureHint.*
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Option to create a [DescriptiveAssertion] like assertion with an additional hint which might be shown if the
 * [Descriptive.DescriptionOption.test] fails.
 *
 * You can use [withFailureHintBasedOnSubject] or [withFailureHintBasedOnDefinedSubject]
 * in case your [DescriptiveAssertion] is based on the subject.
 */
fun Descriptive.DescriptionOption<Descriptive.FinalStep>.withFailureHint(
    failureHintFactory: () -> Assertion
): DescriptiveAssertionWithFailureHint.ShowOption =
    DescriptiveAssertionWithFailureHint.ShowOption.create(test, failureHintFactory)

/**
 * Option to create a [DescriptiveAssertion] like assertion with an additional hint
 * which is based on the subject of the assertion and
 * which is only shown the subject is defined.
 *
 * You can use [withFailureHintBasedOnSubject] in case you want to:
 * - provide a hint also if the subject is absent.
 * - do not show the hint in certain cases even if the subject is defined
 *
 * Or use [withFailureHint] which does not expect a [subjectProvider] in case your [DescriptiveAssertion] is not based
 * on the subject of the assertion.
 */
fun <T> Descriptive.DescriptionOption<Descriptive.FinalStep>.withFailureHintBasedOnDefinedSubject(
    subjectProvider: SubjectProvider<T>,
    failureHintFactory: (T) -> Assertion
): Descriptive.DescriptionOption<DescriptiveAssertionWithFailureHint.FinalStep> {
    return withFailureHintBasedOnSubject(subjectProvider) {
        ifDefined(failureHintFactory)
            .ifAbsent {
                assertionBuilder.explanatoryGroup
                    .withWarningType
                    .withExplanatoryAssertion(RawString.create(SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG))
                    .build()
            }
    }.showOnlyIfSubjectDefined(subjectProvider)
}

/**
 * Option to create a [DescriptiveAssertion] like assertion with an additional hint
 * (which is based on the subject of the assertion)
 * which might be shown if the [Descriptive.DescriptionOption.test] fails.
 *
 * You can use [withFailureHint] which does not expect a [subjectProvider] in case your [DescriptiveAssertion] is not based
 * on the subject of the assertion.
 */
fun <T> Descriptive.DescriptionOption<Descriptive.FinalStep>.withFailureHintBasedOnSubject(
    subjectProvider: SubjectProvider<T>,
    failureHintSubStep: DescriptiveAssertionWithFailureHint.FailureHintSubjectDefinedOption<T>.() -> Pair<() -> Assertion, (T) -> Assertion>
): DescriptiveAssertionWithFailureHint.ShowOption = withFailureHint {
    SubjectBasedOption(
        subjectProvider,
        failureHintSubStep,
        DescriptiveAssertionWithFailureHint.FailureHintSubjectDefinedOption.Companion::create
    )
}


/**
 * Defines the contract to build a [DescriptiveAssertion] like assertion with an additional hint
 * which might be shown if the [Descriptive.DescriptionOption.test] fails.
 */
interface DescriptiveAssertionWithFailureHint {

    /**
     * Sub Option step to define a failure hint based on a defined subject of the assertion.
     */
    interface FailureHintSubjectDefinedOption<T> :
        SubjectBasedOption.DefinedOption<T, Assertion, FailureHintSubjectAbsentOption<T>> {

        companion object {
            fun <T> create(): FailureHintSubjectDefinedOption<T> = FailureHintSubjectDefinedOptionImpl()
        }
    }

    /**
     * Sub Option step to define a failure hint in case the subject of the assertion is not defined.
     */
    interface FailureHintSubjectAbsentOption<T> : SubjectBasedOption.AbsentOption<T, Assertion> {

        companion object {
            fun <T> create(
                ifDefined: (T) -> Assertion
            ): FailureHintSubjectAbsentOption<T> = FailureHintSubjectAbsentOptionImpl(ifDefined)
        }
    }

    /**
     * Option which allows to specify in which situations the failure hint should be shown.
     */
    interface ShowOption {
        /**
         * Defines that the failure hint shall be shown in any case.
         */
        val showForAnyFailure: Descriptive.DescriptionOption<FinalStep>

        /**
         * Defines that the failure hint shall only be shown if the given [predicate] holds.
         */
        fun showOnlyIf(predicate: () -> Boolean): Descriptive.DescriptionOption<FinalStep>


        /**
         * Defines that the failure hint shall be shown in any case as long as the subject is defined
         */
        fun <T> showOnlyIfSubjectDefined(subjectProvider: SubjectProvider<T>): Descriptive.DescriptionOption<FinalStep> =
            showOnlyIf { subjectProvider.maybeSubject.isDefined() }

        /**
         * Defines that the failure hint shall be shown if the subject is defined and the given [predicate] holds for it
         */
        fun <T> showBasedOnDefinedSubjectOnlyIf(
            subjectProvider: SubjectProvider<T>,
            predicate: (T) -> Boolean
        ): Descriptive.DescriptionOption<FinalStep> =
            showBasedOnSubjectOnlyIf(subjectProvider) { ifDefined { predicate(it) } ifAbsent { false } }

        /**
         * Defines that the failure hint shall only be shown based on a predicate influenced by the
         * subject of the assertion.
         *
         * You can use the other overload without [subjectProvider] in case the predicate is not based on the subject
         * of the assertion.
         */
        fun <T> showBasedOnSubjectOnlyIf(
            subjectProvider: SubjectProvider<T>,
            showSubStep: ShowSubjectDefinedOption<T>.() -> Pair<() -> Boolean, (T) -> Boolean>
        ): Descriptive.DescriptionOption<FinalStep> = showOnlyIf {
            SubjectBasedOption(subjectProvider, showSubStep, ShowSubjectDefinedOption.Companion::create)
        }

        companion object {
            fun create(
                test: () -> Boolean,
                failureHintFactory: () -> Assertion
            ): ShowOption = ShowOptionImpl(test, failureHintFactory)
        }
    }

    /**
     * Sub Option step to define a failure hint based on a defined subject of the assertion.
     */
    interface ShowSubjectDefinedOption<T> : SubjectBasedOption.DefinedOption<T, Boolean, ShowSubjectAbsentOption<T>> {

        companion object {
            fun <T> create(): ShowSubjectDefinedOption<T> = ShowSubjectDefinedOptionImpl()
        }
    }

    /**
     * Sub Option step to define a failure hint in case the subject of the assertion is not defined.
     */
    interface ShowSubjectAbsentOption<T> : SubjectBasedOption.AbsentOption<T, Boolean> {

        companion object {
            fun <T> create(
                ifDefined: (T) -> Boolean
            ): ShowSubjectAbsentOption<T> = ShowSubjectAbsentOptionImpl(ifDefined)
        }
    }

    /**
     * Final step which creates a [DescriptiveAssertion] if the [test] holds or an [AssertionGroup] which includes
     * additionally a failure hint created by the given [failureHintFactory] in case [showHint] evaluates to `true`.
     */
    interface FinalStep : AssertionBuilderFinalStep<Assertion> {
        /**
         * The previously defined test which is used to determine [DescriptiveAssertion.holds].
         */
        val test: () -> Boolean

        /**
         *  The previously defined [showHint] predicate which defines whether the failure hint shall be shown
         *  in case the assertion fails or not.
         */
        val showHint: () -> Boolean

        /**
         * The previously defined factory method which creates the failure hint.
         */
        val failureHintFactory: () -> Assertion

        /**
         * The previously defined [DescriptiveAssertion.description].
         */
        val description: Translatable

        /**
         * The previously defined [DescriptiveAssertion.representation].
         */
        val representation: Any

        companion object {
            fun create(
                test: () -> Boolean,
                showHint: () -> Boolean,
                failureHintFactory: () -> Assertion,
                description: Translatable,
                representation: Any
            ): FinalStep = FinalStepImpl(test, showHint, failureHintFactory, description, representation)
        }
    }
}
