package ch.tutteli.atrium.assertions.composers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.throwUnsupportedOperationException
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * A dummy implementation of [IAssertionComposer] which should be replaced by an actual implementation.
 */
object AssertionComposer : IAssertionComposer {

    override fun createDescriptiveWithFailureHint(
        description: Translatable,
        representation: Any,
        test: () -> Boolean,
        showHint: () -> Boolean,
        failureHintFactory: () -> Assertion
    ): Assertion = throwUnsupportedOperationException()
}
