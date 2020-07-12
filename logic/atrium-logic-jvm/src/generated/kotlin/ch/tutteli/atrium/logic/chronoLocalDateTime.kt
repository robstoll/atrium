//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

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

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isBefore(expected: String): Assertion = _chronoLocalDateTimeImpl.isBefore(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isBeforeOrEqual(expected: String): Assertion = _chronoLocalDateTimeImpl.isBeforeOrEqual(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isAfter(expected: String): Assertion = _chronoLocalDateTimeImpl.isAfter(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isAfterOrEqual(expected: String): Assertion = _chronoLocalDateTimeImpl.isAfterOrEqual(this, expected)

fun <T : ChronoLocalDateTime<out ChronoLocalDate>> AssertionContainer<T>.isEqual(expected: String): Assertion = _chronoLocalDateTimeImpl.isEqual(this, expected)

