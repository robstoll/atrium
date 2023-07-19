package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.impl.descriptiveWithFailureHint.*
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Option to create a [DescriptiveAssertion] like assertion with an additional hint which might be shown if the
 * [Descriptive.DescriptionOption.test] fails.
 *
 * You can use [withHelpOnFailureBasedOnSubject] or [withHelpOnFailureBasedOnDefinedSubject]
 * in case your [DescriptiveAssertion] is based on the subject.
 */
fun Descriptive.DescriptionOption<Descriptive.FinalStep>.withHelpOnFailure(
    failureHintFactory: () -> Assertion
): DescriptiveAssertionWithFailureHint.ShowOption =
    DescriptiveAssertionWithFailureHint.ShowOption.create(test, failureHintFactory)

/**
 * Option to create a [DescriptiveAssertion] like assertion with an additional hint
 * which is based on the subject of the expectation and
 * which is only shown the subject is defined.
 *
 * You can use [withHelpOnFailureBasedOnSubject] in case you want to:
 * - provide a hint also if the subject is absent.
 * - do not show the hint in certain cases even if the subject is defined
 *
 * Or use [withHelpOnFailure] which does not expect an [Expect] in case your [DescriptiveAssertion] is not based
 * on the subject of the expectation.
 */
//TODO if we introduce Proof or something else as replacement for Assertion then not but if we keep Assertion
// then move to logic and expect ProofContainer with 1.2.0
fun <T> Descriptive.DescriptionOption<Descriptive.FinalStep>.withHelpOnFailureBasedOnDefinedSubject(
    expect: Expect<T>,
    failureHintFactory: (T) -> Assertion
): Descriptive.DescriptionOption<DescriptiveAssertionWithFailureHint.FinalStep> {
    return withHelpOnFailureBasedOnSubject(expect) {
        ifDefined(failureHintFactory)
            .ifAbsent(::createShouldNotBeShownToUserWarning)
    }.showOnlyIfSubjectDefined(expect)
}

fun createShouldNotBeShownToUserWarning(): Assertion =
    assertionBuilder.explanatoryGroup
        .withWarningType
        .withExplanatoryAssertion(Text(SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG))
        .build()

/**
 * Option to create a [DescriptiveAssertion] like assertion with an additional hint
 * (which is based on the subject of the expectation)
 * which might be shown if the [Descriptive.DescriptionOption.test] fails.
 *
 * You can use [withHelpOnFailure] which does not expect an [Expect] in case your [DescriptiveAssertion] is not based
 * on the subject of the expectation.
 */
fun <T> Descriptive.DescriptionOption<Descriptive.FinalStep>.withHelpOnFailureBasedOnSubject(
    expect: Expect<T>,
    failureHintSubStep: DescriptiveAssertionWithFailureHint.FailureHintSubjectDefinedOption<T>.() -> Pair<() -> Assertion, (T) -> Assertion>
): DescriptiveAssertionWithFailureHint.ShowOption = withHelpOnFailure {
    SubjectBasedOption(
        expect,
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
     * Sub Option step to define a failure hint based on a defined subject of the expectation.
     */
    interface FailureHintSubjectDefinedOption<T> :
        SubjectBasedOption.DefinedOption<T, Assertion, FailureHintSubjectAbsentOption<T>> {

        companion object {
            fun <T> create(): FailureHintSubjectDefinedOption<T> = FailureHintSubjectDefinedOptionImpl()
        }
    }

    /**
     * Sub Option step to define a failure hint in case the subject of the expectation is not defined.
     */
    interface FailureHintSubjectAbsentOption<T> : SubjectBasedOption.AbsentOption<T, Assertion> {

        companion object {
            fun <T> create(
                ifDefined: (T) -> Assertion
            ): FailureHintSubjectAbsentOption<T> = FailureHintSubjectAbsentOptionImpl(ifDefined)
        }
    }

    /**
     * Option which allows specifying in which situations the failure hint should be shown.
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
        fun <T> showOnlyIfSubjectDefined(
            expect: Expect<T>
        ): Descriptive.DescriptionOption<FinalStep> =
            showOnlyIf { (expect as AssertionContainer<*>).maybeSubject.isDefined() }

        /**
         * Defines that the failure hint shall be shown if the subject is defined and the given [predicate] holds for it
         */
        fun <T> showBasedOnDefinedSubjectOnlyIf(
            expect: Expect<T>,
            predicate: (T) -> Boolean
        ): Descriptive.DescriptionOption<FinalStep> =
            showBasedOnSubjectOnlyIf(expect) { ifDefined { predicate(it) } ifAbsent { false } }

        /**
         * Defines that the failure hint shall only be shown based on a predicate influenced by the
         * subject of the expectation.
         *
         * You can use the other overload without [expect] in case the predicate is not based on the subject
         * of the assertion.
         */
        fun <T> showBasedOnSubjectOnlyIf(
            expect: Expect<T>,
            showSubStep: ShowSubjectDefinedOption<T>.() -> Pair<() -> Boolean, (T) -> Boolean>
        ): Descriptive.DescriptionOption<FinalStep> = showOnlyIf {
            SubjectBasedOption(expect, showSubStep, ShowSubjectDefinedOption.Companion::create)
        }

        companion object {
            fun create(
                test: () -> Boolean,
                failureHintFactory: () -> Assertion
            ): ShowOption = ShowOptionImpl(test, failureHintFactory)
        }
    }

    /**
     * Sub Option step to define a failure hint based on a defined subject of the expectation.
     */
    interface ShowSubjectDefinedOption<T> : SubjectBasedOption.DefinedOption<T, Boolean, ShowSubjectAbsentOption<T>> {

        companion object {
            fun <T> create(): ShowSubjectDefinedOption<T> = ShowSubjectDefinedOptionImpl()
        }
    }

    /**
     * Sub Option step to define a failure hint in case the subject of the expectation is not defined.
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
