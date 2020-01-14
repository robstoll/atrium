package ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.kotlin_1_3.result

/**
 * Expects that the subject of the assertion (a [Result]) is a Success
 * and returns an [Expect] for the inner type [E].
 *
 * @return The newly created [Expect] if the given assertion is success
 * @throws AssertionError Might throw an [AssertionError] if the given assertion is not a success.
 *
 * @since 0.9.0
 */
fun <E, T : Result<E>> Expect<T>.isSuccess(): Expect<E> = ExpectImpl.result.isSuccess(this).getExpectOfFeature()

/**
 * Expects that the subject of the assertion (a [Result]) is a Success and
 * that it holds all assertions the given [assertionCreator] creates.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the given assertions are not success.
 *
 * @since 0.9.0
 */
fun <E, T : Result<E>> Expect<T>.isSuccess(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    ExpectImpl.result.isSuccess(this).addToInitial(assertionCreator)

/**
 * Expects that the subject of the assertion (a [Result]) is a Failure and
 * that it encapsulates an exception of type [TExpected].
 *
 * @return An assertion container with the new type [TExpected]
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
inline fun <reified TExpected : Throwable> Expect<out Result<*>>.isFailure(): Expect<TExpected> =
    ExpectImpl.result.isFailure(this, TExpected::class).getExpectOfFeature()

/**
 * Expects that the subject of the assertion (a [Result]) is a Failure,
 * it encapsulates an exception of type [TExpected] and that the exception
 * holds all assertions the given [assertionCreator] creates.
 *
 * @return An assertion container with the new type [TExpected]
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
inline fun <reified TExpected : Throwable> Expect<out Result<*>>.isFailure(
    noinline assertionCreator: Expect<TExpected>.() -> Unit
): Expect<TExpected> = ExpectImpl.result.isFailure(this, TExpected::class).addToFeature(assertionCreator)
