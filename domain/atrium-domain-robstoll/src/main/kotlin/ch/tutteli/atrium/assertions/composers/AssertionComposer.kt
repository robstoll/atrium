package ch.tutteli.atrium.assertions.composers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Robstoll's implementation of [IAssertionComposer].
 */
object AssertionComposer : IAssertionComposer {

    override fun createDescriptiveWithFailureHint(
        description: Translatable,
        representation: Any,
        test: () -> Boolean,
        showHint: () -> Boolean,
        failureHintFactory: () -> Assertion
    ): Assertion
        = _createDescriptiveWithFailureHint(description, representation, test, showHint, failureHintFactory)
}
