package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.chronoLocalDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

/**
 * Expects that the subject of the assertion is after [expected] [ChronoLocalDateTime].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <D: ChronoLocalDate, T : ChronoLocalDateTime<D>> Expect<T>.isAfter(expected: T): Expect<T> =
    addAssertion(ExpectImpl.chronoLocalDateTime.isAfter(this, expected))

/**
 * Expects that the subject of the assertion is before or equals [expected] [ChronoLocalDateTime].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <D: ChronoLocalDate,  T: ChronoLocalDateTime<D>> Expect<T>.isBeforeOrEquals(expected: T): Expect<T> =
    addAssertion(ExpectImpl.chronoLocalDateTime.isBeforeOrEquals(this, expected))
