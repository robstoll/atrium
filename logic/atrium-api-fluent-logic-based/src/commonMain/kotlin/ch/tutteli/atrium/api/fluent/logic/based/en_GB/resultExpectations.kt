//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.isFailureOfType
import ch.tutteli.atrium.logic.isSuccess

/**
 * Expects that the subject of `this` expectation (a [Result]) is a success ([Result.isSuccess])
 * and returns an [Expect] for the inner type [E].
 *
 * @return The newly created [Expect] if the given assertion is a success.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ResultExpectationSamples.toBeASuccessFeature
 *
 * @since 1.1.0 (was in kotlin_1_3 extension since 0.17.0)
 */
fun <E, T : Result<E>> Expect<T>.toBeASuccess(): Expect<E> = _logic.isSuccess().transform()

/**
 * Expects that the subject of `this` expectation (a [Result]) is a success ([Result.isSuccess]) and
 * that it holds all assertions the given [assertionCreator] creates.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ResultExpectationSamples.toBeASuccess
 *
 * @since 1.1.0 (was in kotlin_1_3 extension since 0.17.0)
 */
fun <E, T : Result<E>> Expect<T>.toBeASuccess(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.isSuccess().collectAndAppend(assertionCreator)

/**
 * Expects that the subject of `this` expectation (a [Result]) is a failure ([Result.isFailure]) and
 * that it encapsulates a [Throwable] of type [ExpectedThrowableT] or a subtype thereof.
 *
 * @return An [Expect] with the new type [ExpectedThrowableT]
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ResultExpectationSamples.toBeAFailureFeature
 *
 * @since 1.1.0 (was in kotlin_1_3 extension since 0.17.0)
 */
inline fun <reified ExpectedThrowableT : Throwable> Expect<out Result<*>>.toBeAFailure(): Expect<ExpectedThrowableT> =
    _logic.isFailureOfType(ExpectedThrowableT::class).transform()

/**
 * Expects that the subject of `this` expectation (a [Result]) is a failure ([Result.isFailure]) ,
 * that it encapsulates a [Throwable] of type [ExpectedThrowableT] or a subtype thereof and
 * that the [ExpectedThrowableT] holds all assertions the given [assertionCreator] creates.
 *
 * @return An [Expect] with the new type [ExpectedThrowableT]
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ResultExpectationSamples.toBeAFailure
 *
 * @since 1.1.0 (was in kotlin_1_3 extension since 0.17.0)
 */
inline fun <reified ExpectedThrowableT : Throwable> Expect<out Result<*>>.toBeAFailure(
    noinline assertionCreator: Expect<ExpectedThrowableT>.() -> Unit
): Expect<ExpectedThrowableT> = _logic.isFailureOfType(ExpectedThrowableT::class).transformAndAppend(assertionCreator)
