//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoZonedDateTime

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isBefore(expected: ChronoZonedDateTime<*>): Assertion = _chronoZonedDateTimeImpl.isBefore(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isBeforeOrEqual(expected: ChronoZonedDateTime<*>): Assertion = _chronoZonedDateTimeImpl.isBeforeOrEqual(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isAfter(expected: ChronoZonedDateTime<*>): Assertion = _chronoZonedDateTimeImpl.isAfter(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isAfterOrEqual(expected: ChronoZonedDateTime<*>): Assertion = _chronoZonedDateTimeImpl.isAfterOrEqual(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isEqual(expected: ChronoZonedDateTime<*>): Assertion = _chronoZonedDateTimeImpl.isEqual(this, expected)
