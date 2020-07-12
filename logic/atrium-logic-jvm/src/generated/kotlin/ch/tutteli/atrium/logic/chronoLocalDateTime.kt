//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isBefore(expected: ChronoLocalDateTime<*>): Assertion = _chronoLocalDateTimeImpl.isBefore(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isBeforeOrEqual(expected: ChronoLocalDateTime<*>): Assertion = _chronoLocalDateTimeImpl.isBeforeOrEqual(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isAfter(expected: ChronoLocalDateTime<*>): Assertion = _chronoLocalDateTimeImpl.isAfter(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isAfterOrEqual(expected: ChronoLocalDateTime<*>): Assertion = _chronoLocalDateTimeImpl.isAfterOrEqual(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isEqual(expected: ChronoLocalDateTime<*>): Assertion = _chronoLocalDateTimeImpl.isEqual(this, expected)
