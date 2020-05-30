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
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.isBefore(expected)","ch.tutteli.atrium.api.fluent.en_GB.isBefore")
)
fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.isBefore(
    expected: ChronoZonedDateTime<*>
): Expect<T> = addAssertion(ExpectImpl.chronoZonedDateTime.isBefore(this, expected))

/**
 * Expects that the subject of the assertion (a [ChronoZonedDateTime])
 * is before or equals the [expected] [ChronoZonedDateTime].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.isBeforeOrEqual(expected)","ch.tutteli.atrium.api.fluent.en_GB.isBeforeOrEqual")
)
fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.isBeforeOrEqual(
    expected: ChronoZonedDateTime<*>
): Expect<T> = addAssertion(ExpectImpl.chronoZonedDateTime.isBeforeOrEqual(this, expected))

/**
 * Expects that the subject of the assertion (a [ChronoZonedDateTime])
 * is after the [expected] [ChronoZonedDateTime].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.isAfter(expected)","ch.tutteli.atrium.api.fluent.en_GB.isAfter")
)
fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.isAfter(
    expected: ChronoZonedDateTime<*>
): Expect<T> = addAssertion(ExpectImpl.chronoZonedDateTime.isAfter(this, expected))

/**
 * Expects that the subject of the assertion (a [ChronoZonedDateTime])
 * is after or equal the [expected] [ChronoZonedDateTime].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.isAfterOrEqual(expected)","ch.tutteli.atrium.api.fluent.en_GB.isAfterOrEqual")
)
fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.isAfterOrEqual(
    expected: ChronoZonedDateTime<*>
): Expect<T> = addAssertion(ExpectImpl.chronoZonedDateTime.isAfterOrEqual(this, expected))

/**
 * Expects that the subject of the assertion (a [ChronoZonedDateTime])
 * is equal to the [expected] [ChronoZonedDateTime].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use the function from the normal jvm module; the jdk8 extension will be removed with 1.0.0",
    ReplaceWith("this.isEqual(expected)","ch.tutteli.atrium.api.fluent.en_GB.isEqual")
)
fun <T : ChronoZonedDateTime<out ChronoLocalDate>> Expect<T>.isEqual(
    expected: ChronoZonedDateTime<*>
): Expect<T> = addAssertion(ExpectImpl.chronoZonedDateTime.isEqual(this, expected))
