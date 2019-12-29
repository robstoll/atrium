package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.chronoLocalDate
import java.time.chrono.ChronoLocalDate

/**
 * Expects that the subject of the assertion is after [expected] [ChronoLocalDate].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : ChronoLocalDate> Expect<T>.isAfter(expected: T): Expect<T> =
    addAssertion(ExpectImpl.chronoLocalDate.isAfter(this, expected))

/**
 * Expects that the subject of the assertion is before or equals [expected] [ChronoLocalDate].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : ChronoLocalDate> Expect<T>.isBeforeOrEquals(expected: T): Expect<T> =
    addAssertion(ExpectImpl.chronoLocalDate.isBeforeOrEquals(this, expected))
