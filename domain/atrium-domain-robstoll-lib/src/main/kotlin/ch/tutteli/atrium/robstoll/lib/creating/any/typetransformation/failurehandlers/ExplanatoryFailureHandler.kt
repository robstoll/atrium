package ch.tutteli.atrium.robstoll.lib.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.reporting.translating.Translatable

class ExplanatoryFailureHandler<in S : Any, out T : Any> : ExplanatoryFailureHandlerBase<S, T>() {

    override fun createFailingAssertion(description: Translatable, representation: Any)
        = AssertionBuilder.descriptive.create(description, representation, false)
}
