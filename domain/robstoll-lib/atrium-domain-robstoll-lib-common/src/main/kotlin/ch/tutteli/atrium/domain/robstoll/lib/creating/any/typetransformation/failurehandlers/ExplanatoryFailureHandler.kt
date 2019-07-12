package ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.Translatable

@Deprecated("will be removed with 1.0.0")
class ExplanatoryFailureHandler<in S : Any, out T : Any> : ExplanatoryFailureHandlerBase<S, T>() {

    override fun createFailingAssertion(description: Translatable, representation: Any)
        = AssertImpl.builder.descriptive
            .failing
            .withDescriptionAndRepresentation(description, representation)
            .build()
}
