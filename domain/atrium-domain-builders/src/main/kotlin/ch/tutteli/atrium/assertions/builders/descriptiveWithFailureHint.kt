package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.composers.AssertionComposer
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Builder to create a descriptive [Assertion] with an additional hint which is only shown if the `test()` fails.
 */
@Suppress("unused")
fun DescriptiveAssertionBuilder.withFailureHint(failureHintFactory: () -> AssertionGroup) =
    DescriptiveAssertionWithFailureHintOption(failureHintFactory)

/**
 * Provides options to create a descriptive [Assertion] with an additional failure hint.
 */
class DescriptiveAssertionWithFailureHintOption(private val failureHintFactory: () -> AssertionGroup) {
    /**
     * Specifies that the failure hint shall be shown in any case.
     */
    val showForAnyFailure get() = DescriptiveAssertionWithFailureHintBuilder({ true }, failureHintFactory)

    /**
     * Specifies that the failure hint shall only be shown if the given [predicate] holds
     */
    fun showOnlyIf(predicate: () -> Boolean) = DescriptiveAssertionWithFailureHintBuilder(predicate, failureHintFactory)
}

/**
 * Builder to create kind of a [DescriptiveAssertion] if a test holds or an [AssertionGroup] which includes additionally
 * a failure hint created by the given [failureHintFactory] in case [showHint] evaluates to `true`.
 */
class DescriptiveAssertionWithFailureHintBuilder internal constructor(
    private val showHint: () -> Boolean,
    private val failureHintFactory: () -> AssertionGroup
) {
    /**
     * Creates kind of a [DescriptiveAssertion] if [test] holds or an [AssertionGroup] which includes additionally
     * a failure hint created by the previously specified [failureHintFactory] in case the previously specified
     * [showHint]-lambda evaluates to `true`.
     */
    fun create(
        description: Translatable,
        representation: Any,
        test: () -> Boolean
    ): Assertion = AssertionComposer.createDescriptiveWithFailureHint(
        description,
        representation,
        test,
        showHint,
        failureHintFactory
    )
}
