package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.localDate
import ch.tutteli.atrium.domain.builders.localDateTime
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Creates an [Expect] for the property [LocalDateTime.year][[LocalDateTime.getYear] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 *
 * @since 0.9.0
 */
val Expect<LocalDateTime>.year get(): Expect<Int> = ExpectImpl.localDateTime.year(this).getExpectOfFeature()

/**
 * Expects that the property [LocalDateTime.year][LocalDateTime.getYear] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun Expect<LocalDateTime>.year(assertionCreator: Expect<Int>.() -> Unit): Expect<LocalDateTime> =
    ExpectImpl.localDateTime.year(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [LocalDateTime.month][LocalDateTime.getMonthValue] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 *
 * @since 0.9.0
 */
val Expect<LocalDateTime>.month get(): Expect<Int> = ExpectImpl.localDateTime.month(this).getExpectOfFeature()

/**
 * Expects that the property [LocalDateTime.month][LocalDateTime.getMonthValue]of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun Expect<LocalDateTime>.month(assertionCreator: Expect<Int>.() -> Unit): Expect<LocalDateTime> =
    ExpectImpl.localDateTime.month(this).addToInitial(assertionCreator)

