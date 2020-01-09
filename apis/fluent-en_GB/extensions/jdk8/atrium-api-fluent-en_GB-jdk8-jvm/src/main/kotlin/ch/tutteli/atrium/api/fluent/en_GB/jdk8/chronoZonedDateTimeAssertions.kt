@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.chronoZonedDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoZonedDateTime

/**
 * Expects that the subject of the assertion (a [ChronoZonedDateTime])
 * is before the [expected] [ChronoZonedDateTime].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.isBefore(expected: ChronoZonedDateTime<*>): Expect<T> =
    addAssertion(ExpectImpl.chronoZonedDateTime.isBefore(this, expected))

/**
 * Expects that the subject of the assertion (a [ChronoZonedDateTime])
 * is before or equals the [expected] [ChronoZonedDateTime].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.isBeforeOrEquals(expected: ChronoZonedDateTime<*>): Expect<T> =
    addAssertion(ExpectImpl.chronoZonedDateTime.isBeforeOrEquals(this, expected))

/**
 * Expects that the subject of the assertion (a [ChronoZonedDateTime])
 * is after the [expected] [ChronoZonedDateTime].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.isAfter(expected: ChronoZonedDateTime<*>): Expect<T> =
    addAssertion(ExpectImpl.chronoZonedDateTime.isAfter(this, expected))

/**
 * Expects that the subject of the assertion (a [ChronoZonedDateTime])
 * is after or equals the [expected] [ChronoZonedDateTime].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.isAfterOrEquals(expected: ChronoZonedDateTime<*>): Expect<T> =
    addAssertion(ExpectImpl.chronoZonedDateTime.isAfterOrEquals(this, expected))
