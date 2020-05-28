package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl

/**
 * Expects that the subject of the assertion (an [Iterator]) has at least one element.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.13.0
 */
infix fun <E, T : Iterator<E>> Expect<T>.has(@Suppress("UNUSED_PARAMETER") next: next) =
    addAssertion(ExpectImpl.iterator.hasNext(this))

/**
 * Expects that the subject of the assertion (an [Iterator]) does not have next element.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <E, T : Iterator<E>> Expect<T>.hasNot(@Suppress("UNUSED_PARAMETER") next: next) =
    addAssertion(ExpectImpl.iterator.hasNotNext(this))
