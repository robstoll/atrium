package ch.tutteli.atrium.domain.builders.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionBuilder
import ch.tutteli.atrium.assertions.builders.DescriptiveLikeAssertionBuilder
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.DescriptiveAssertionWithFailureHintBuilderImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.DescriptiveAssertionWithFailureHintShowOptionImpl

/**
 * Option to create a descriptive [Assertion] with an additional hint which might be shown if the
 * [DescriptiveAssertionBuilder.test] fails.
 */
fun DescriptiveAssertionBuilder.withFailureHint(
    failureHintFactory: () -> Assertion
): DescriptiveAssertionWithFailureHintShowOption
    = DescriptiveAssertionWithFailureHintShowOption.create(test, failureHintFactory)

/**
 * Provides options to specify in which situations the failure hint should be shown.
 */
interface DescriptiveAssertionWithFailureHintShowOption {
    /**
     * Specifies that the failure hint shall be shown in any case.
     */
    val showForAnyFailure: DescriptiveAssertionWithFailureHintBuilder

    /**
     * Specifies that the failure hint shall only be shown if the given [predicate] holds
     */
    fun showOnlyIf(predicate: () -> Boolean): DescriptiveAssertionWithFailureHintBuilder

    companion object {
        fun create(
            test: () -> Boolean,
            failureHintFactory: () -> Assertion
        ): DescriptiveAssertionWithFailureHintShowOption
            = DescriptiveAssertionWithFailureHintShowOptionImpl(test, failureHintFactory)
    }
}

/**
 * Builder to create kind of a [DescriptiveAssertion] if a test holds or an [AssertionGroup] which includes additionally
 * a failure hint created by the given [failureHintFactory] in case [showHint] evaluates to `true`.
 */
interface DescriptiveAssertionWithFailureHintBuilder : DescriptiveLikeAssertionBuilder {
    /**
     * Defines if the failure hint shall be shown in case the assertion fails.
     */
    val showHint: () -> Boolean
    /**
     * The factory method which creates the failure hint.
     */
    val failureHintFactory: () -> Assertion

    companion object {
        fun create(
            test: () -> Boolean,
            showHint: () -> Boolean,
            failureHintFactory: () -> Assertion
        ): DescriptiveAssertionWithFailureHintBuilder
            = DescriptiveAssertionWithFailureHintBuilderImpl(test, showHint, failureHintFactory)
    }
}
