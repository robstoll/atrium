package ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.kotlin_1_3.result

/**
 * Expects that the given assertion is a success
 * returns an [Expect]
 *
 * @return The newly created [Expect] if the given assertion is success
 * @throws AssertionError Might throw an [AssertionError] if the given assertion is not a success.
 */
fun <E, T : Result<E>> Expect<T>.isSuccess(): Expect<E> = ExpectImpl.result.isSuccess(this).getExpectOfFeature()

/**
 * Expects that the given assertions are success
 * returns [Expect]
 *
 * @return The newly created [Expect] if the given assertions are success
 * @throws AssertionError Might throw an [AssertionError]  if the given assertions are not success.
 */
fun <E, T : Result<E>> Expect<T>.isSuccess(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    ExpectImpl.result.isSuccess(this).addToInitial(assertionCreator)
