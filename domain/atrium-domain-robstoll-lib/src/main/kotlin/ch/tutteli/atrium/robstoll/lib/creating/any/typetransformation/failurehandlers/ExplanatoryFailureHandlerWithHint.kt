package ch.tutteli.atrium.robstoll.lib.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.reporting.translating.Translatable

class ExplanatoryFailureHandlerWithHint<in S : Any, out T : Any>(
    private val showHint: () -> Boolean,
    private val failureHintFactory: () -> Assertion
) : ExplanatoryFailureHandlerBase<S, T>() {

    override fun createFailingAssertion(description: Translatable, representation: Any): Assertion {
        val failingAssertion = AssertionBuilder.descriptive.create(description, representation, false)
        return if (showHint()) {
            AssertionBuilder.invisibleGroup.create(listOf(failureHintFactory(), failingAssertion))
        } else {
            failingAssertion
        }
    }
}
