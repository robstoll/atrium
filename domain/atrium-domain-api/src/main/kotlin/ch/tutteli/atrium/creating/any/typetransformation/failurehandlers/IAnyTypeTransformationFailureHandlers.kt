package ch.tutteli.atrium.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.creating.any.typetransformation.AnyTypeTransformation

interface IAnyTypeTransformationFailureHandlers {
    /**
     * Creates a [AnyTypeTransformation.FailureHandler] which wraps subsequent assertions into an
     * [AssertionGroup] with an [ExplanatoryAssertionGroupType] so that the user of Atrium can see in error reporting
     * what one wanted to assert additionally.
     */
    fun <S : Any, T : Any> newExplanatory(): AnyTypeTransformation.FailureHandler<S, T>
}
