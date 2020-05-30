@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll.assertions.composers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.assertions.composers.AssertionComposer
import ch.tutteli.atrium.domain.robstoll.lib.assertions.composers._createDescriptiveWithFailureHint
import ch.tutteli.atrium.reporting.translating.Translatable

@Deprecated("Will be removed with 1.0.0")
class AssertionComposerImpl : AssertionComposer {

    override fun createDescriptiveWithFailureHint(
        description: Translatable,
        representation: Any,
        test: () -> Boolean,
        showHint: () -> Boolean,
        failureHintFactory: () -> Assertion
    ): Assertion = _createDescriptiveWithFailureHint(description, representation, test, showHint, failureHintFactory)
}
