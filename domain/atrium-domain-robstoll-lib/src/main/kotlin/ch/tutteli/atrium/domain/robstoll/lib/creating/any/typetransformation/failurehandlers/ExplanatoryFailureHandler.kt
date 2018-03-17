package ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.domain.builders.creating.AssertImpl
import ch.tutteli.atrium.reporting.translating.Translatable

class ExplanatoryFailureHandler<in S : Any, out T : Any> : ExplanatoryFailureHandlerBase<S, T>() {

    override fun createFailingAssertion(description: Translatable, representation: Any)
        = AssertImpl.builder.descriptive.create(description, representation, false)
}
