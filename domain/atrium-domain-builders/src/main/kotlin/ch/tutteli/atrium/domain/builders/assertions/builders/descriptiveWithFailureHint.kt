package ch.tutteli.atrium.domain.builders.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionBuilder
import ch.tutteli.atrium.assertions.builders.DescriptiveLikeAssertionBuilder
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.DescriptiveAssertionWithFailureHintShowOptionImpl

/**
 * Builder to create a descriptive [Assertion] with an additional hint which might be shown if the
 * [DescriptiveAssertionBuilder.test] fails.
 */
fun DescriptiveAssertionBuilder.withFailureHint(
    failureHintFactory: () -> Assertion
): DescriptiveAssertionWithFailureHintShowOption
    = DescriptiveAssertionWithFailureHintShowOptionImpl(test, failureHintFactory)

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
}

/**
 * Builder to create kind of a [DescriptiveAssertion] if a test holds or an [AssertionGroup] which includes additionally
 * a failure hint created by the given [failureHintFactory] in case [showHint] evaluates to `true`.
 */
interface DescriptiveAssertionWithFailureHintBuilder : DescriptiveLikeAssertionBuilder {
    val showHint: () -> Boolean
    val failureHintFactory: () -> Assertion
}
