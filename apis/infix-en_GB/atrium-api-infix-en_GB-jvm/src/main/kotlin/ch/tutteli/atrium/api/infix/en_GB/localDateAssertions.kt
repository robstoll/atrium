@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import java.time.DayOfWeek
import java.time.LocalDate

/**
 * Creates an [Expect] for the property [LocalDate.year][LocalDate.getYear] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.12.0
 */
val Expect<LocalDate>.year: Expect<Int>
    get() = _logic.year().getExpectOfFeature()

/**
 * Expects that the property [LocalDate.year][LocalDate.getYear]of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun Expect<LocalDate>.year(assertionCreator: Expect<Int>.() -> Unit): Expect<LocalDate> =
    _logic.year().addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [LocalDate.monthValue][LocalDate.getMonthValue] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.12.0
 */
val Expect<LocalDate>.month: Expect<Int>
    get() = _logic.month().getExpectOfFeature()

/**
 * Expects that the property [LocalDate.monthValue][LocalDate.getMonthValue] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun Expect<LocalDate>.month(assertionCreator: Expect<Int>.() -> Unit): Expect<LocalDate> =
    _logic.month().addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [LocalDate.getDayOfWeek] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.12.0
 */
val Expect<LocalDate>.dayOfWeek: Expect<DayOfWeek>
    get() = _logic.dayOfWeek().getExpectOfFeature()

/**
 * Expects that the property [LocalDate.getDayOfWeek] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun Expect<LocalDate>.dayOfWeek(assertionCreator: Expect<DayOfWeek>.() -> Unit): Expect<LocalDate> =
    _logic.dayOfWeek().addToInitial(assertionCreator)


/**
 * Creates an [Expect] for the property [LocalDate.dayOfMonth][LocalDate.getDayOfMonth] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.12.0
 */
val Expect<LocalDate>.day: Expect<Int>
    get() = _logic.day().getExpectOfFeature()

/**
 * Expects that the property [LocalDate.dayOfMonth][LocalDate.getDayOfMonth] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun Expect<LocalDate>.day(assertionCreator: Expect<Int>.() -> Unit): Expect<LocalDate> =
    _logic.day().addToInitial(assertionCreator)
