package ch.tutteli.atrium.robstoll.assertions.composers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.composers.AssertionComposer
import ch.tutteli.atrium.assertions.composers._createDescriptiveWithFailureHint
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Robstoll's implementation of [AssertionComposer].
 */
class AssertionComposerImpl : AssertionComposer {

    override fun createDescriptiveWithFailureHint(
        description: Translatable,
        representation: Any,
        test: () -> Boolean,
        showHint: () -> Boolean,
        failureHintFactory: () -> Assertion
    ): Assertion
        = _createDescriptiveWithFailureHint(description, representation, test, showHint, failureHintFactory)
}
