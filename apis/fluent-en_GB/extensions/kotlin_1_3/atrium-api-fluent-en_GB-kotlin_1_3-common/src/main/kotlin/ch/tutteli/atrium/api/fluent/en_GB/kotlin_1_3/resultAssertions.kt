package ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.kotlin_1_3.isFailure
import ch.tutteli.atrium.logic.kotlin_1_3.isSuccess

/**
 * Expects that the subject of the assertion (a [Result]) is a Success
 * and returns an [Expect] for the inner type [E].
 *
 * @return The newly created [Expect] if the given assertion is success
 * @throws AssertionError Might throw an [AssertionError] if the given assertion is not a success.
 *
 * @since 0.9.0
 */
fun <E, T : Result<E>> Expect<T>.isSuccess(): Expect<E> =
    _logic.isSuccess().getExpectOfFeature()

/**
 * Expects that the subject of the assertion (a [Result]) is a Success and
 * that it holds all assertions the given [assertionCreator] creates.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the given assertions are not success.
 *
 * @since 0.9.0
 */
fun <E, T : Result<E>> Expect<T>.isSuccess(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.isSuccess().addToInitial(assertionCreator)

/**
 * Expects that the subject of the assertion (a [Result]) is a Failure and
 * that it encapsulates an exception of type [TExpected].
 *
 * @return An [Expect] with the new type [TExpected]
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
inline fun <reified TExpected : Throwable> Expect<out Result<*>>.isFailure(): Expect<TExpected> =
    _logic.isFailure(TExpected::class).getExpectOfFeature()

/**
 * Expects that the subject of the assertion (a [Result]) is a Failure,
 * it encapsulates an exception of type [TExpected] and that the exception
 * holds all assertions the given [assertionCreator] creates.
 *
 * @return An [Expect] with the new type [TExpected]
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
inline fun <reified TExpected : Throwable> Expect<out Result<*>>.isFailure(
    noinline assertionCreator: Expect<TExpected>.() -> Unit
): Expect<TExpected> = _logic.isFailure(TExpected::class).addToFeature(assertionCreator)
