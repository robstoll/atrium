//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import java.time.DayOfWeek
import java.time.LocalDateTime

/**
 * Creates an [Expect] for the property [LocalDateTime.year][[LocalDateTime.getYear] of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.LocalDateTimeFeatureExtractorSamples.yearFeature
 *
 * @since 0.9.0
 */
val Expect<LocalDateTime>.year: Expect<Int>
    get() = _logic.year().transform()

/**
 * Expects that the property [LocalDateTime.year][LocalDateTime.getYear] of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.LocalDateTimeFeatureExtractorSamples.year
 *
 * @since 0.9.0
 */
fun Expect<LocalDateTime>.year(assertionCreator: Expect<Int>.() -> Unit): Expect<LocalDateTime> =
    _logic.year().collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [LocalDateTime.monthValue][LocalDateTime.getMonthValue]
 * of the subject of `this` expectation, so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.LocalDateTimeFeatureExtractorSamples.monthFeature
 *
 * @since 0.9.0
 */
val Expect<LocalDateTime>.month: Expect<Int>
    get() = _logic.month().transform()

/**
 * Expects that the property [LocalDateTime.monthValue][LocalDateTime.getMonthValue]of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.LocalDateTimeFeatureExtractorSamples.month
 *
 * @since 0.9.0
 */
fun Expect<LocalDateTime>.month(assertionCreator: Expect<Int>.() -> Unit): Expect<LocalDateTime> =
    _logic.month().collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [LocalDateTime.dayOfWeek][LocalDateTime.getDayOfWeek]
 * of the subject of `this` expectation, so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.LocalDateTimeFeatureExtractorSamples.dayOfWeekFeature
 *
 * @since 0.9.0
 */
val Expect<LocalDateTime>.dayOfWeek: Expect<DayOfWeek>
    get() = _logic.dayOfWeek().transform()

/**
 * Expects that the property [LocalDateTime.dayOfWeek][LocalDateTime.getDayOfWeek]of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.LocalDateTimeFeatureExtractorSamples.dayOfWeek
 *
 * @since 0.9.0
 */
fun Expect<LocalDateTime>.dayOfWeek(assertionCreator: Expect<DayOfWeek>.() -> Unit): Expect<LocalDateTime> =
    _logic.dayOfWeek().collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [LocalDateTime.dayOfMonth][LocalDateTime.getDayOfMonth]
 * of the subject of `this` expectation, so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.LocalDateTimeFeatureExtractorSamples.dayFeature
 *
 * @since 0.9.0
 */
val Expect<LocalDateTime>.day: Expect<Int>
    get() = _logic.day().transform()

/**
 * Expects that the property [LocalDateTime.dayOfMonth][LocalDateTime.getDayOfMonth] of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.LocalDateTimeFeatureExtractorSamples.day
 *
 * @since 0.9.0
 */
fun Expect<LocalDateTime>.day(assertionCreator: Expect<Int>.() -> Unit): Expect<LocalDateTime> =
    _logic.day().collectAndAppend(assertionCreator)

