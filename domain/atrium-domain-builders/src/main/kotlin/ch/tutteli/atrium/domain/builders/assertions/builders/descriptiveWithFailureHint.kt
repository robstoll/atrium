package ch.tutteli.atrium.domain.builders.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.AssertionBuilderFinalStep
import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionFinalStep
import ch.tutteli.atrium.assertions.builders.DescriptiveLikeAssertionDescriptionOption
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.DescriptiveAssertionWithFailureHintFinalStepImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.DescriptiveAssertionWithFailureHintShowOptionImpl
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Option to create a descriptive [Assertion] with an additional hint which might be shown if the
 * [DescriptiveLikeAssertionDescriptionOption.test] fails.
 */
fun DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>.withFailureHint(
    failureHintFactory: () -> Assertion
): DescriptiveAssertionWithFailureHintShowOption =
    DescriptiveAssertionWithFailureHintShowOption.create(test, failureHintFactory)

/**
 * Provides options to specify in which situations the failure hint should be shown.
 */
interface DescriptiveAssertionWithFailureHintShowOption {
    /**
     * Specifies that the failure hint shall be shown in any case.
     */
    val showForAnyFailure: DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionWithFailureHintFinalStep>

    /**
     * Specifies that the failure hint shall only be shown if the given [predicate] holds
     */
    fun showOnlyIf(predicate: () -> Boolean): DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionWithFailureHintFinalStep>

    companion object {
        fun create(
            test: () -> Boolean,
            failureHintFactory: () -> Assertion
        ): DescriptiveAssertionWithFailureHintShowOption
            = DescriptiveAssertionWithFailureHintShowOptionImpl(test, failureHintFactory)
    }
}

/**
 * Builder to create a [DescriptiveAssertion] if the [test] holds or an [AssertionGroup] which includes additionally
 * a failure hint created by the given [failureHintFactory] in case [showHint] evaluates to `true`.
 */
interface DescriptiveAssertionWithFailureHintFinalStep : AssertionBuilderFinalStep<Assertion> {
    /**
     * The previously defined test which is used to determine [DescriptiveAssertion.holds].
     */
    val test: () -> Boolean

    /**
     * Defines if the failure hint shall be shown in case the assertion fails.
     */
    val showHint: () -> Boolean

    /**
     * The factory method which creates the failure hint.
     */
    val failureHintFactory: () -> Assertion

    /**
     * The [DescriptiveAssertion.description].
     */
    val description: Translatable

    /**
     * The [DescriptiveAssertion.representation].
     */
    val representation: Any

    companion object {
        fun create(
            test: () -> Boolean,
            showHint: () -> Boolean,
            failureHintFactory: () -> Assertion,
            description: Translatable,
            representation: Any
        ): DescriptiveAssertionWithFailureHintFinalStep = DescriptiveAssertionWithFailureHintFinalStepImpl(
            test,
            showHint,
            failureHintFactory,
            description,
            representation
        )
    }
}
