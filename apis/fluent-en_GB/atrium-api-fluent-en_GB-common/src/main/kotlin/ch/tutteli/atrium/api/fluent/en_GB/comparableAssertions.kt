package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating._domain

/**
 * Expects that the subject of the assertion is less than [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Comparable<T>> Expect<T>.isLessThan(expected: T) =
    addAssertion(_domain.isLessThan(expected))

/**
 * Expects that the subject of the assertion is less than or equals [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Comparable<T>> Expect<T>.isLessOrEquals(expected: T) =
    addAssertion(_domain.isLessOrEquals(expected))

/**
 * Expects that the subject of the assertion is greater than [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Comparable<T>> Expect<T>.isGreaterThan(expected: T) =
    addAssertion(_domain.isGreaterThan(expected))

/**
 * Expects that the subject of the assertion is greater than or equals [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Comparable<T>> Expect<T>.isGreaterOrEquals(expected: T) =
    addAssertion(_domain.isGreaterOrEquals(expected))

