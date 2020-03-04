@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.api.infix.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.chronoLocalDate
import java.time.chrono.ChronoLocalDate

/**
 * Expects that the subject of the assertion (a [ChronoLocalDate])
 * is before the [expected] [ChronoLocalDate].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : ChronoLocalDate> Expect<T>.isBefore(expected: ChronoLocalDate): Expect<T> =
    addAssertion(ExpectImpl.chronoLocalDate.isBefore(this, expected))

/**
 * Expects that the subject of the assertion (a [ChronoLocalDate])
 * is before or equal the [expected] [ChronoLocalDate].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : ChronoLocalDate> Expect<T>.isBeforeOrEqual(expected: ChronoLocalDate): Expect<T> =
    addAssertion(ExpectImpl.chronoLocalDate.isBeforeOrEquals(this, expected))


/**
 * Expects that the subject of the assertion (a [ChronoLocalDate])
 * is after the [expected] [ChronoLocalDate].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : ChronoLocalDate> Expect<T>.isAfter(expected: ChronoLocalDate): Expect<T> =
    addAssertion(ExpectImpl.chronoLocalDate.isAfter(this, expected))

/**
 * Expects that the subject of the assertion (a [ChronoLocalDate])
 * is after or equal the [expected] [ChronoLocalDate].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : ChronoLocalDate> Expect<T>.isAfterOrEqual(expected: ChronoLocalDate): Expect<T> =
    addAssertion(ExpectImpl.chronoLocalDate.isAfterOrEquals(this, expected))

/**
 * Expects that the subject of the assertion (a [ChronoLocalDate])
 * is equal to the [expected] [ChronoLocalDate].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : ChronoLocalDate> Expect<T>.isEqual(expected: ChronoLocalDate): Expect<T> =
    addAssertion(ExpectImpl.chronoLocalDate.isEqual(this, expected))
