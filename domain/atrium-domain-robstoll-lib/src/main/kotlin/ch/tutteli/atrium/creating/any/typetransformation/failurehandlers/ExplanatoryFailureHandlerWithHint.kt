package ch.tutteli.atrium.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.reporting.translating.Translatable

class ExplanatoryFailureHandlerWithHint<in S : Any, out T : Any>(
    private val showHint: () -> Boolean,
    private val failureHintFactory: () -> AssertionGroup
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
