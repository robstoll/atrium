@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.domain.robstoll.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.domain.creating.any.typetransformation.failurehandlers.FailureHandlerFactory
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers.ExplanatoryFailureHandler
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers.ExplanatoryFailureHandlerWithHint

/**
 * Robstoll's implementation of [FailureHandlerFactory].
 */
@Deprecated("use _changeSubject or _extractFeature instead; will be removed with 1.0.0")
class FailureHandlerFactoryImpl : FailureHandlerFactory {

    override fun <S : Any, T : Any> newExplanatory(): AnyTypeTransformation.FailureHandler<S, T>
        = ExplanatoryFailureHandler()

    override fun <S : Any, T : Any> newExplanatoryWithHint(
        showHint: () -> Boolean,
        failureHintFactory: () -> Assertion
    ): AnyTypeTransformation.FailureHandler<S, T>
        = ExplanatoryFailureHandlerWithHint(showHint, failureHintFactory)
}
