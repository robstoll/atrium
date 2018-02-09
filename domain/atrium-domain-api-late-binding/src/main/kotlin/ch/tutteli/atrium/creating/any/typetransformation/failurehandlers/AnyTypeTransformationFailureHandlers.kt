package ch.tutteli.atrium.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.creating.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.creating.any.typetransformation.creators.IAnyTypeTransformationAssertions
import ch.tutteli.atrium.creating.throwUnsupportedOperationException

/**
 * A dummy implementation of [IAnyTypeTransformationAssertions] which should be replaced by an actual implementation.
 */
object AnyTypeTransformationFailureHandlers : IAnyTypeTransformationFailureHandlers {
    override fun <S : Any, T : Any> newExplanatory(): AnyTypeTransformation.FailureHandler<S, T> =
        throwUnsupportedOperationException()
}
