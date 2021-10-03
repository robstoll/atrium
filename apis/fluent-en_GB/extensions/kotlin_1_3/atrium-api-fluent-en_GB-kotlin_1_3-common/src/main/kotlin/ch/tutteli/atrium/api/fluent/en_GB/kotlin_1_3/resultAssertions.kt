package ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.kotlin_1_3.isFailureOfType
import ch.tutteli.atrium.logic.kotlin_1_3.isSuccess

/**
 * Expects that the subject of `this` expectation (a [Result]) is a Success
 * and returns an [Expect] for the inner type [E].
 *
 * @return The newly created [Expect] if the given assertion is success
 *
 * @since 0.9.0
 */
@Deprecated("Use toBeASuccess; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBeASuccess<E, T>()"))
fun <E, T : Result<E>> Expect<T>.isSuccess(): Expect<E> =
    _logic.isSuccess().transform()

/**
 * Expects that the subject of `this` expectation (a [Result]) is a Success and
 * that it holds all assertions the given [assertionCreator] creates.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use toBeASuccess; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeASuccess<E, T>(assertionCreator)")
)
fun <E, T : Result<E>> Expect<T>.isSuccess(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.isSuccess().collectAndAppend(assertionCreator)

/**
 * Expects that the subject of `this` expectation (a [Result]) is a Failure and
 * that it encapsulates an exception of type [TExpected].
 *
 * @return An [Expect] with the new type [TExpected]
 *
 * @since 0.9.0
 */
@Deprecated("Use toBeAFailure; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBeAFailure<TExpected>()"))
inline fun <reified TExpected : Throwable> Expect<out Result<*>>.isFailure(): Expect<TExpected> =
    _logic.isFailureOfType(TExpected::class).transform()

/**
 * Expects that the subject of `this` expectation (a [Result]) is a Failure,
 * it encapsulates an exception of type [TExpected] and that the exception
 * holds all assertions the given [assertionCreator] creates.
 *
 * @return An [Expect] with the new type [TExpected]
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use toBeAFailure; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeAFailure<TExpected>(assertionCreator)")
)
inline fun <reified TExpected : Throwable> Expect<out Result<*>>.isFailure(
    noinline assertionCreator: Expect<TExpected>.() -> Unit
): Expect<TExpected> = _logic.isFailureOfType(TExpected::class).transformAndAppend(assertionCreator)
