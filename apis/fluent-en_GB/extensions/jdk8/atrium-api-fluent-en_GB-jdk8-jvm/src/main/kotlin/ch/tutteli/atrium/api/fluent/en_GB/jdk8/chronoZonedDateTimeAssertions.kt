package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.chronoZonedDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime
import java.time.chrono.ChronoZonedDateTime

/**
 * Expects that the subject of the assertion is after [expected] [ChronoZonedDateTime].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <D : ChronoLocalDate, T : ChronoZonedDateTime<D>> Expect<T>.isAfter(expected: T): Expect<T> =
    addAssertion(ExpectImpl.chronoZonedDateTime.isAfter(this, expected))

/**
 * Expects that the subject of the assertion is before or equals [expected] [ChronoZonedDateTime].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <D: ChronoLocalDate, T : ChronoZonedDateTime<D>> Expect<T>.isBeforeOrEquals(expected: T): Expect<T> =
    addAssertion(ExpectImpl.chronoZonedDateTime.isBeforeOrEquals(this, expected))
