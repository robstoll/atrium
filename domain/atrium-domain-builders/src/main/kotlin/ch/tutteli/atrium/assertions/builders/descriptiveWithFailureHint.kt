package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.composers.AssertionComposer
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Builder to create a descriptive [Assertion] with an additional hint which is only shown if the `test()` fails.
 */
@Suppress("unused")
fun AssertionBuilder.DescriptiveAssertionBuilder.withFailureHint(failureHintFactory: () -> AssertionGroup) =
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

class DescriptiveAssertionWithFailureHintBuilder internal constructor(
    private val showHint: () -> Boolean,
    private val failureHintFactory: () -> AssertionGroup
) {
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
