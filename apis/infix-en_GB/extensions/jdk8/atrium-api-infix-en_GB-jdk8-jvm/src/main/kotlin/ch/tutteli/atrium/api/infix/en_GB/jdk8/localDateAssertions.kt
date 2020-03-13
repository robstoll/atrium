@file:Suppress(
    "FINAL_UPPER_BOUND" /* remove once https://youtrack.jetbrains.com/issue/KT-34257 is fixed */,
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.api.infix.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.localDate
import java.time.DayOfWeek
import java.time.LocalDate

/**
 * Creates an [Expect] for the property [LocalDate.year][LocalDate.getYear] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.11.0
 */
val <T : LocalDate> Expect<T>.year: Expect<Int>
    get() = ExpectImpl.localDate.year(this).getExpectOfFeature()

/**
 * Expects that the property [LocalDate.year][LocalDate.getYear]of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.11.0
 */
infix fun <T : LocalDate> Expect<T>.year(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    ExpectImpl.localDate.year(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [LocalDate.monthValue][LocalDate.getMonthValue] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.11.0
 */
val <T : LocalDate> Expect<T>.month: Expect<Int>
    get() = ExpectImpl.localDate.month(this).getExpectOfFeature()

/**
 * Expects that the property [LocalDate.monthValue][LocalDate.getMonthValue] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.11.0
 */
infix fun <T : LocalDate> Expect<T>.month(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    ExpectImpl.localDate.month(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [LocalDate.getDayOfWeek] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.11.0
 */
val <T : LocalDate> Expect<T>.dayOfWeek: Expect<DayOfWeek>
    get() = ExpectImpl.localDate.dayOfWeek(this).getExpectOfFeature()

/**
 * Expects that the property [LocalDate.getDayOfWeek] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.11.0
 */
infix fun <T : LocalDate> Expect<T>.dayOfWeek(assertionCreator: Expect<DayOfWeek>.() -> Unit): Expect<T> =
    ExpectImpl.localDate.dayOfWeek(this).addToInitial(assertionCreator)


/**
 * Creates an [Expect] for the property [LocalDate.dayOfMonth][LocalDate.getDayOfMonth] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.11.0
 */
val <T : LocalDate> Expect<T>.day: Expect<Int>
    get() = ExpectImpl.localDate.day(this).getExpectOfFeature()

/**
 * Expects that the property [LocalDate.dayOfMonth][LocalDate.getDayOfMonth] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.11.0
 */
infix fun <T : LocalDate> Expect<T>.day(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    ExpectImpl.localDate.day(this).addToInitial(assertionCreator)
