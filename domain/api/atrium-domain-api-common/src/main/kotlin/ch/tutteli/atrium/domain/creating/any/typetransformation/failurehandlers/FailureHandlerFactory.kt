@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.domain.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation

/**
 * The access point to an implementation of [FailureHandlerFactory].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
@Suppress("DEPRECATION")
@Deprecated("use subjectChanger instead; will be removed with 1.0.0")
val failureHandlerFactory by lazy { loadSingleService(FailureHandlerFactory::class) }

/**
 * Defines the minimum set of [AnyTypeTransformation.FailureHandler]s an implementation of the domain of Atrium
 * has to provide.
 */
@Deprecated("use subjectChanger instead; will be removed with 1.0.0")
interface FailureHandlerFactory {

    /**
     * Creates a [AnyTypeTransformation.FailureHandler] which wraps subsequent assertions into an
     * [AssertionGroup] with an [ExplanatoryAssertionGroupType] so that the user of Atrium can see in reporting
     * what one wanted to assert additionally.
     */
    fun <S : Any, T : Any> newExplanatory(): AnyTypeTransformation.FailureHandler<S, T>

    /**
     * Creates a [AnyTypeTransformation.FailureHandler] which wraps subsequent assertions into an
     * [AssertionGroup] with an [ExplanatoryAssertionGroupType] so that the user of Atrium can see in reporting
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
        failureHintFactory: () -> Assertion
    ): AnyTypeTransformation.FailureHandler<S, T>
}
