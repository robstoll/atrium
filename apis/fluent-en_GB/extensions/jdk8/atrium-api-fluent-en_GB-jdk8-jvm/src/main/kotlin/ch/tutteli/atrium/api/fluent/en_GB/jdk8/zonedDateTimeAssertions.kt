package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.localDateTime
import ch.tutteli.atrium.domain.builders.zonedDateTime
import java.time.LocalDateTime
import java.time.ZonedDateTime

/**
 * Creates an [Expect] for the property [ZonedDateTime.year][ZonedDateTime.getYear] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 *
 * @since 0.9.0
 */
val Expect<ZonedDateTime>.year get(): Expect<Int> = ExpectImpl.zonedDateTime.year(this).getExpectOfFeature()

/**
 * Expects that the property [ZonedDateTime.year][ZonedDateTime.getYear] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun Expect<ZonedDateTime>.year(assertionCreator: Expect<Int>.() -> Unit): Expect<ZonedDateTime> =
    ExpectImpl.zonedDateTime.year(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [ZonedDateTime.month][ZonedDateTime.getMonthValue] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 *
 * @since 0.9.0
 */
val Expect<ZonedDateTime>.month get(): Expect<Int> = ExpectImpl.zonedDateTime.month(this).getExpectOfFeature()

/**
 * Expects that the property [ZonedDateTime.month][ZonedDateTime.getMonthValue] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun Expect<ZonedDateTime>.month(assertionCreator: Expect<Int>.() -> Unit): Expect<ZonedDateTime> =
    ExpectImpl.zonedDateTime.month(this).addToInitial(assertionCreator)
