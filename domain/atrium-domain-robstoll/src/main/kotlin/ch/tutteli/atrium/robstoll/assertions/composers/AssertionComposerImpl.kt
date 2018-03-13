package ch.tutteli.atrium.robstoll.assertions.composers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.composers.AssertionComposer
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.robstoll.lib.assertions.composers._createDescriptiveWithFailureHint

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
