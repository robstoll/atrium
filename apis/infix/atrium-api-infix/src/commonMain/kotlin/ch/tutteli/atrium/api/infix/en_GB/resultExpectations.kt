package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.SuccessWithCreator
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.isFailureOfType
import ch.tutteli.atrium.logic.isSuccess

/**
 * Expects that the subject of `this` expectation (a [Result]) is a success ([Result.isSuccess])
 * and returns an [Expect] for the inner type [E].
 *
 * @return The newly created [Expect] if the given assertion is success.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ResultExpectationSamples.toBeASuccessFeature
 *
 * @since 1.1.0 (was in kotlin_1_3 extension since 0.17.0)
 */
infix fun <E, T : Result<E>> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") aSuccess: aSuccess): Expect<E> =
    _logic.isSuccess().transform()

/**
 * Expects that the subject of `this` expectation (a [Result]) is a success ([Result.isSuccess]) and
 * that it holds all assertions the given [SuccessWithCreator.assertionCreator] creates.
 *
 * Use the function `aSuccess { ... }` to create a [SuccessWithCreator].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ResultExpectationSamples.toBeASuccess
 *
 * @since 1.1.0 (was in kotlin_1_3 extension since 0.12.0)
 */
infix fun <E, T : Result<E>> Expect<T>.toBe(success: SuccessWithCreator<E>): Expect<T> =
    _logic.isSuccess().collectAndAppend(success.assertionCreator)

/**
 * Expects that the subject of `this` expectation (a [Result]) is a failure ([Result.isFailure]) and
 * that it encapsulates an exception of type [TExpected].
 *
 * @return An [Expect] with the new type [TExpected]
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ResultExpectationSamples.toBeAFailureFeature
 *
 * @since 1.1.0 (was in kotlin_1_3 extension since 0.17.0)
 */
inline fun <reified TExpected : Throwable> Expect<Result<*>>.toBeAFailure(): Expect<TExpected> =
    _logic.isFailureOfType(TExpected::class).transform()

/**
 * Expects that the subject of `this` expectation (a [Result]) is a failure ([Result.isFailure]) ,
 * that it encapsulates an exception of type [TExpected] and
 * that the exception holds all assertions the given [assertionCreator] creates.
 *
 * @return An [Expect] with the new type [TExpected]
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ResultExpectationSamples.toBeAFailure
 *
 * @since 1.1.0 (was in kotlin_1_3 extension since 0.17.0)
 */
inline infix fun <reified TExpected : Throwable> Expect<Result<*>>.toBeAFailure(
    noinline assertionCreator: Expect<TExpected>.() -> Unit
): Expect<TExpected> = _logic.isFailureOfType(TExpected::class).transformAndAppend(assertionCreator)
