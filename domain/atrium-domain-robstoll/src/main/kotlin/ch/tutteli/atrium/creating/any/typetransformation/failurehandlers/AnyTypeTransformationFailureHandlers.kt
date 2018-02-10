package ch.tutteli.atrium.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.any.typetransformation.AnyTypeTransformation

/**
 * Robstoll's implementation of [IAnyTypeTransformationFailureHandlers].
 */
object AnyTypeTransformationFailureHandlers : IAnyTypeTransformationFailureHandlers {
    override fun <S : Any, T : Any> newExplanatory(): AnyTypeTransformation.FailureHandler<S, T>
        = ExplanatoryFailureHandler()

    override fun <S : Any, T : Any> newExplanatoryWithHint(
        showHint: () -> Boolean,
        failureHintFactory: () -> Assertion
    ): AnyTypeTransformation.FailureHandler<S, T>
        = ExplanatoryFailureHandlerWithHint(showHint, failureHintFactory)

}
