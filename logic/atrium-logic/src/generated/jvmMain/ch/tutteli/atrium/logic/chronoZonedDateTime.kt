// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoZonedDateTime
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultChronoZonedDateTimeAssertions

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isBefore(expected: ChronoZonedDateTime<*>): Assertion = impl.isBefore(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isBeforeOrEqual(expected: ChronoZonedDateTime<*>): Assertion = impl.isBeforeOrEqual(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isAfter(expected: ChronoZonedDateTime<*>): Assertion = impl.isAfter(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isAfterOrEqual(expected: ChronoZonedDateTime<*>): Assertion = impl.isAfterOrEqual(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isEqual(expected: ChronoZonedDateTime<*>): Assertion = impl.isEqual(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isBefore(expected: String): Assertion = impl.isBefore(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isBeforeOrEqual(expected: String): Assertion = impl.isBeforeOrEqual(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isAfter(expected: String): Assertion = impl.isAfter(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isAfterOrEqual(expected: String): Assertion = impl.isAfterOrEqual(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isEqual(expected: String): Assertion = impl.isEqual(this, expected)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ChronoZonedDateTimeAssertions
    get() = getImpl(ChronoZonedDateTimeAssertions::class) { DefaultChronoZonedDateTimeAssertions() }
