package ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.kotlin_1_3.isFailureOfType
import ch.tutteli.atrium.logic.kotlin_1_3.isSuccess

/**
 * Expects that the subject of `this` expectation (a [Result]) is a success ([Result.isSuccess])
 * and returns an [Expect] for the inner type [E].
 *
 * @return The newly created [Expect] if the given assertion is a success.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3.samples.ResultExpectationSamples.toBeASuccessFeature
 *
 * @since 0.17.0
 */
@Deprecated(
    "The whole kotlin_1_3 extension will be removed with 1.2.0, use the function from the ch.tutteli.atrium.api.fluent.en_GB package",
    ReplaceWith("this.toBeASuccess()", "ch.tutteli.atrium.api.fluent.en_GB.toBeASuccess")
)
fun <E, T : Result<E>> Expect<T>.toBeASuccess(): Expect<E> =
    _logic.isSuccess().transform()

/**
 * Expects that the subject of `this` expectation (a [Result]) is a success ([Result.isSuccess]) and
 * that it holds all assertions the given [assertionCreator] creates.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3.samples.ResultExpectationSamples.toBeASuccess
 *
 * @since 0.17.0
 */
@Deprecated(
    "The whole kotlin_1_3 extension will be removed with 1.2.0, use the function from the ch.tutteli.atrium.api.fluent.en_GB package",
    ReplaceWith("this.toBeASuccess(assertionCreator)", "ch.tutteli.atrium.api.fluent.en_GB.toBeASuccess")
)
fun <E, T : Result<E>> Expect<T>.toBeASuccess(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.isSuccess().collectAndAppend(assertionCreator)

/**
 * Expects that the subject of `this` expectation (a [Result]) is a failure ([Result.isFailure]) and
 * that it encapsulates an exception of type [TExpected].
 *
 * @return An [Expect] with the new type [TExpected]
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3.samples.ResultExpectationSamples.toBeAFailureFeature
 *
 * @since 0.17.0
 */
@Deprecated(
    "The whole kotlin_1_3 extension will be removed with 1.2.0, use the function from the ch.tutteli.atrium.api.fluent.en_GB package",
    ReplaceWith("this.toBeAFailure<TExpected>()", "ch.tutteli.atrium.api.fluent.en_GB.toBeAFailure")
)
inline fun <reified TExpected : Throwable> Expect<out Result<*>>.toBeAFailure(): Expect<TExpected> =
    _logic.isFailureOfType(TExpected::class).transform()

/**
 * Expects that the subject of `this` expectation (a [Result]) is a failure ([Result.isFailure]) ,
 * that it encapsulates an exception of type [TExpected] and
 * that the exception holds all assertions the given [assertionCreator] creates.
 *
 * @return An [Expect] with the new type [TExpected]
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3.samples.ResultExpectationSamples.toBeAFailure
 *
 * @since 0.17.0
 */
@Deprecated(
    "The whole kotlin_1_3 extension will be removed with 1.2.0, use the function from the ch.tutteli.atrium.api.fluent.en_GB package",
    ReplaceWith("this.toBeAFailure<TExpected>(assertionCreator)", "ch.tutteli.atrium.api.fluent.en_GB.toBeAFailure")
)
inline fun <reified TExpected : Throwable> Expect<out Result<*>>.toBeAFailure(
    noinline assertionCreator: Expect<TExpected>.() -> Unit
): Expect<TExpected> = _logic.isFailureOfType(TExpected::class).transformAndAppend(assertionCreator)
