package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.localDate
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.MonthDay


/**
 * Creates an [Expect] for the property [LocalDate.year][LocalDate.getYear] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 *
 * @since 0.9.0
 */
val <T : LocalDate> Expect<T>.year get(): Expect<Int> = ExpectImpl.localDate.year(this).getExpectOfFeature()

/**
 * Expects that the property [LocalDate.year][LocalDate.getYear]of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : LocalDate> Expect<T>.year(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    ExpectImpl.localDate.year(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [LocalDate.month][LocalDate.getMonthValue] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 *
 * @since 0.9.0
 */
val <T : LocalDate> Expect<T>.month get(): Expect<Int> = ExpectImpl.localDate.month(this).getExpectOfFeature()

/**
 * Expects that the property [LocalDate.month][LocalDate.getMonthValue]of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : LocalDate> Expect<T>.month(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    ExpectImpl.localDate.month(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [LocalDate.getDayOfWeek] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 *
 * @since 0.9.0
 */
val <T : LocalDate> Expect<T>.dayOfWeek get(): Expect<DayOfWeek> =
    ExpectImpl.localDate.dayOfWeek(this).getExpectOfFeature()

/**
 * Expects that the property [LocalDate.getDayOfWeek] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : LocalDate> Expect<T>.dayOfWeek(assertionCreator: Expect<DayOfWeek>.() -> Unit): Expect<T> =
    ExpectImpl.localDate.dayOfWeek(this).addToInitial(assertionCreator)


/**
 * Creates an [Expect] for the property [LocalDate.day][LocalDate.getDayOfMonth] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 *
 * @since 0.9.0
 */
val <T : LocalDate> Expect<T>.day get(): Expect<Int> =
    ExpectImpl.localDate.day(this).getExpectOfFeature()

/**
 * Expects that the property [LocalDate.day][LocalDate.getDayOfMonth]of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : LocalDate> Expect<T>.day(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    ExpectImpl.localDate.day(this).addToInitial(assertionCreator)
