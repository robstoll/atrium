@file:Suppress(
    "FINAL_UPPER_BOUND" /* remove once https://youtrack.jetbrains.com/issue/KT-34257 is fixed */,
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.api.infix.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.localDateTime
import java.time.DayOfWeek
import java.time.LocalDateTime

/**
 * Creates an [Expect] for the property [LocalDateTime.year][[LocalDateTime.getYear] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.10.0
 */
val <T : LocalDateTime> Expect<T>.year: Expect<Int>
    get() = ExpectImpl.localDateTime.year(this).getExpectOfFeature()

/**
 * Expects that the property [LocalDateTime.year][LocalDateTime.getYear] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : LocalDateTime> Expect<T>.year(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    ExpectImpl.localDateTime.year(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [LocalDateTime.monthValue][LocalDateTime.getMonthValue]
 * of the subject of the assertion, so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.10.0
 */
val <T : LocalDateTime> Expect<T>.month: Expect<Int>
    get() = ExpectImpl.localDateTime.month(this).getExpectOfFeature()

/**
 * Expects that the property [LocalDateTime.monthValue][LocalDateTime.getMonthValue]of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : LocalDateTime> Expect<T>.month(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    ExpectImpl.localDateTime.month(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [LocalDateTime.dayOfWeek][LocalDateTime.getDayOfWeek]
 * of the subject of the assertion, so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.10.0
 */
val <T : LocalDateTime> Expect<T>.dayOfWeek: Expect<DayOfWeek>
    get() = ExpectImpl.localDateTime.dayOfWeek(this).getExpectOfFeature()

/**
 * Expects that the property [LocalDateTime.dayOfWeek][LocalDateTime.getDayOfWeek]of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : LocalDateTime> Expect<T>.dayOfWeek(assertionCreator: Expect<DayOfWeek>.() -> Unit): Expect<T> =
    ExpectImpl.localDateTime.dayOfWeek(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [LocalDateTime.dayOfMonth][LocalDateTime.getDayOfMonth]
 * of the subject of the assertion, so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.10.0
 */
val <T : LocalDateTime> Expect<T>.day: Expect<Int>
    get() = ExpectImpl.localDateTime.day(this).getExpectOfFeature()

/**
 * Expects that the property [LocalDateTime.dayOfMonth][LocalDateTime.getDayOfMonth] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : LocalDateTime> Expect<T>.day(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    ExpectImpl.localDateTime.day(this).addToInitial(assertionCreator)

