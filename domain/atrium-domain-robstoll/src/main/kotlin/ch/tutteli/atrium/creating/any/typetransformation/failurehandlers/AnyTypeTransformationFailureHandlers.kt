package ch.tutteli.atrium.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.creating.any.typetransformation.AnyTypeTransformation

/**
 * Robstoll's implementation of [IAnyTypeTransformationFailureHandlers].
 */
object AnyTypeTransformationFailureHandlers : IAnyTypeTransformationFailureHandlers {
    override fun <S : Any, T : Any> newExplanatory(): AnyTypeTransformation.FailureHandler<S, T>
        = ExplanatoryTypeTransformationFailureHandler()
}
