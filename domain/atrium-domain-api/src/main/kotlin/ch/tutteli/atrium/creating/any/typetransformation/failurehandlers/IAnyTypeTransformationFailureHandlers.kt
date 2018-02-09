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

    /**
     * Creates a [AnyTypeTransformation.FailureHandler] which wraps subsequent assertions into an
     * [AssertionGroup] with an [ExplanatoryAssertionGroupType] so that the user of Atrium can see in error reporting
     * what one wanted to assert additionally -- moreover it includes a hint about the subject which shall only be shown
     * if [showHint] evaluates to `true` in which case the given [failureHintFactory] should be used to create the hint.
     *
     * As an example, Atrium uses this failure handler internally to show the message of a thrown [Throwable] in case
     * it is of a different type than the expected one.
     *
     * @param showHint Indicates whether the failure hint shall be included or not
     * @param failureHintFactory Creates the failure hint.
     */
    fun <S : Any, T : Any> newExplanatoryWithHint(
        showHint: () -> Boolean,
        failureHintFactory: () -> AssertionGroup
    ): AnyTypeTransformation.FailureHandler<S, T>
}
