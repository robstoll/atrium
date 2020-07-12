@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.chronoLocalDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

/**
 * Expects that the subject of the assertion (a [ChronoLocalDateTime])
 * is before the [expected] [ChronoLocalDateTime].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.isBefore(expected: ChronoLocalDateTime<*>): Expect<T> =
    addAssertion(ExpectImpl.chronoLocalDateTime.isBefore(this, expected))

/**
 * Expects that the subject of the assertion (a [ChronoLocalDateTime])
 * is before or equal the [expected] [ChronoLocalDateTime].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.isBeforeOrEqual(
    expected: ChronoLocalDateTime<*>
): Expect<T> = addAssertion(ExpectImpl.chronoLocalDateTime.isBeforeOrEquals(this, expected))

/**
 * Expects that the subject of the assertion (a [ChronoLocalDateTime])
 * is after the [expected] [ChronoLocalDateTime].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.isAfter(
    expected: ChronoLocalDateTime<*>
): Expect<T> = addAssertion(ExpectImpl.chronoLocalDateTime.isAfter(this, expected))

/**
 * Expects that the subject of the assertion (a [ChronoLocalDateTime])
 * is after or equal the [expected] [ChronoLocalDateTime].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.isAfterOrEqual(
    expected: ChronoLocalDateTime<*>
): Expect<T> = addAssertion(ExpectImpl.chronoLocalDateTime.isAfterOrEquals(this, expected))

/**
 * Expects that the subject of the assertion (a [ChronoLocalDateTime])
 * is equal to the [expected] [ChronoLocalDateTime].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : ChronoLocalDateTime<out ChronoLocalDate>> Expect<T>.isEqual(
    expected: ChronoLocalDateTime<*>
): Expect<T> = addAssertion(ExpectImpl.chronoLocalDateTime.isEqual(this, expected))
