package ch.tutteli.atrium.robstoll.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.domain.creating.any.typetransformation.failrehandlers.FailureHandlerFactory
import ch.tutteli.atrium.robstoll.lib.creating.any.typetransformation.failurehandlers.ExplanatoryFailureHandler
import ch.tutteli.atrium.robstoll.lib.creating.any.typetransformation.failurehandlers.ExplanatoryFailureHandlerWithHint

/**
 * Robstoll's implementation of [FailureHandlerFactory].
 */
class FailureHandlerFactoryImpl : FailureHandlerFactory {

    override fun <S : Any, T : Any> newExplanatory(): AnyTypeTransformation.FailureHandler<S, T>
        = ExplanatoryFailureHandler()

    override fun <S : Any, T : Any> newExplanatoryWithHint(
        showHint: () -> Boolean,
        failureHintFactory: () -> Assertion
    ): AnyTypeTransformation.FailureHandler<S, T>
        = ExplanatoryFailureHandlerWithHint(showHint, failureHintFactory)
}
