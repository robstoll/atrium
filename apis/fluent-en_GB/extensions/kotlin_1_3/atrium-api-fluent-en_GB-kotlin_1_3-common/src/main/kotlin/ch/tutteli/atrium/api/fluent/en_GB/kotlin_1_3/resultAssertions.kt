package ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.kotlin_1_3.result


/**
 * Expects that given result is successful
 * returns an [Expect] for the element at that position.
 *
 * @return The newly created [Expect].
 */
fun <E, T : Result<E>> Expect<T>.isSuccess(): Expect<E> = ExpectImpl.result.isSuccess(this).getExpectOfFeature()
