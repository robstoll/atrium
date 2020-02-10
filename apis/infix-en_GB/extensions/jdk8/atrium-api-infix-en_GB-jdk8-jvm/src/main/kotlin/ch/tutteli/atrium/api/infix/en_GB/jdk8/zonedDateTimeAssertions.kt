@file:Suppress(
    "FINAL_UPPER_BOUND" /* remove once https://youtrack.jetbrains.com/issue/KT-34257 is fixed */,
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.api.infix.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.zonedDateTime
import java.time.DayOfWeek
import java.time.ZonedDateTime

/**
 * Creates an [Expect] for the property [ZonedDateTime.year][ZonedDateTime.getYear] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 *
 * @since 0.10.0
 */
val <T : ZonedDateTime> Expect<T>.year: Expect<Int>
    get() = ExpectImpl.zonedDateTime.year(this).getExpectOfFeature()

/**
 * Expects that the property [ZonedDateTime.year][ZonedDateTime.getYear] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : ZonedDateTime> Expect<T>.year(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    ExpectImpl.zonedDateTime.year(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [ZonedDateTime.monthValue][ZonedDateTime.getMonthValue]
 * of the subject of the assertion, so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 *
 * @since 0.10.0
 */
val <T : ZonedDateTime> Expect<T>.month: Expect<Int>
    get() = ExpectImpl.zonedDateTime.month(this).getExpectOfFeature()

/**
 * Expects that the property [ZonedDateTime.monthValue][ZonedDateTime.getMonthValue] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : ZonedDateTime> Expect<T>.month(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    ExpectImpl.zonedDateTime.month(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [ZonedDatetime.dayOfWeek][ZonedDateTime.getDayOfWeek]
 * of the subject of the assertion, so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 *
 * @since 0.10.0
 */
val <T : ZonedDateTime> Expect<T>.dayOfWeek: Expect<DayOfWeek>
    get() = ExpectImpl.zonedDateTime.dayOfWeek(this).getExpectOfFeature()

/**
 * Expects that the property [ZonedDatetime.dayOfWeek][ZonedDateTime.getDayOfWeek] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : ZonedDateTime> Expect<T>.dayOfWeek(assertionCreator: Expect<DayOfWeek>.() -> Unit): Expect<T> =
    ExpectImpl.zonedDateTime.dayOfWeek(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [ZonedDateTime.dayOfMonth][ZonedDateTime.getDayOfMonth]
 * of the subject of the assertion, so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 *
 * @since 0.10.0
 */
val <T : ZonedDateTime> Expect<T>.day: Expect<Int>
    get() = ExpectImpl.zonedDateTime.day(this).getExpectOfFeature()

/**
 * Expects that the property [ZonedDateTime.dayOfMonth][ZonedDateTime.getDayOfMonth] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : ZonedDateTime> Expect<T>.day(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    ExpectImpl.zonedDateTime.day(this).addToInitial(assertionCreator)
