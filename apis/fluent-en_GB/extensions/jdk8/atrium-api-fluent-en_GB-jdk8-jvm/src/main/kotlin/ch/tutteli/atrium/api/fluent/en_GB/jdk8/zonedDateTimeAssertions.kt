@file:Suppress("FINAL_UPPER_BOUND" /* remove once https://youtrack.jetbrains.com/issue/KT-34257 is fixed */)

package ch.tutteli.atrium.api.fluent.en_GB.jdk8

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
 * @since 0.9.0
 */
val <T : ZonedDateTime> Expect<T>.year get(): Expect<Int> = ExpectImpl.zonedDateTime.year(this).getExpectOfFeature()

/**
 * Expects that the property [ZonedDateTime.year][ZonedDateTime.getYear] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : ZonedDateTime> Expect<T>.year(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    ExpectImpl.zonedDateTime.year(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [ZonedDateTime.month][ZonedDateTime.getMonthValue] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 *
 * @since 0.9.0
 */
val <T : ZonedDateTime> Expect<T>.month get(): Expect<Int> = ExpectImpl.zonedDateTime.month(this).getExpectOfFeature()

/**
 * Expects that the property [ZonedDateTime.month][ZonedDateTime.getMonthValue] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : ZonedDateTime> Expect<T>.month(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    ExpectImpl.zonedDateTime.month(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [ZonedDateTime.getDayOfWeek] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 *
 * @since 0.9.0
 */
val <T : ZonedDateTime> Expect<T>.dayOfWeek get(): Expect<DayOfWeek> =
    ExpectImpl.zonedDateTime.dayOfWeek(this).getExpectOfFeature()

/**
 * Expects that the property [ZonedDateTime.getDayOfWeek] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : ZonedDateTime> Expect<T>.dayOfWeek(assertionCreator: Expect<DayOfWeek>.() -> Unit): Expect<T> =
    ExpectImpl.zonedDateTime.dayOfWeek(this).addToInitial(assertionCreator)
/**
 * Creates an [Expect] for the property [ZonedDateTime.getDayOfMonth] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 *
 * @since 0.9.0
 */
val <T : ZonedDateTime> Expect<T>.day get(): Expect<Int> =
    ExpectImpl.zonedDateTime.day(this).getExpectOfFeature()

/**
 * Expects that the property [ZonedDateTime.getDayOfMonth] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : ZonedDateTime> Expect<T>.day(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    ExpectImpl.zonedDateTime.day(this).addToInitial(assertionCreator)
