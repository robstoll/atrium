@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.Translatable

@Deprecated("Will be removed with 1.0.0")
class ExplanatoryFailureHandlerWithHint<in S : Any, out T : Any>(
    private val showHint: () -> Boolean,
    private val failureHintFactory: () -> Assertion
) : ExplanatoryFailureHandlerBase<S, T>() {

    override fun createFailingAssertion(description: Translatable, representation: Any): Assertion {
        val failingAssertion = AssertImpl.builder.descriptive
            .failing
            .withDescriptionAndRepresentation(description, representation)
            .build()
        return if (showHint()) {
            AssertImpl.builder.invisibleGroup
                .withAssertions(failingAssertion, failureHintFactory())
                .build()
        } else {
            failingAssertion
        }
    }
}
