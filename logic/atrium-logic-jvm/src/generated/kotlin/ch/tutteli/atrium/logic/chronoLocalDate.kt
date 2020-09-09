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

fun <T : ChronoLocalDate> AssertionContainer<T>.isBefore(expected: ChronoLocalDate): Assertion = _chronoLocalDateImpl.isBefore(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isBeforeOrEqual(expected: ChronoLocalDate): Assertion = _chronoLocalDateImpl.isBeforeOrEqual(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isAfter(expected: ChronoLocalDate): Assertion = _chronoLocalDateImpl.isAfter(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isAfterOrEqual(expected: ChronoLocalDate): Assertion = _chronoLocalDateImpl.isAfterOrEqual(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isEqual(expected: ChronoLocalDate): Assertion = _chronoLocalDateImpl.isEqual(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isBefore(expected: String): Assertion = _chronoLocalDateImpl.isBefore(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isBeforeOrEqual(expected: String): Assertion = _chronoLocalDateImpl.isBeforeOrEqual(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isAfter(expected: String): Assertion = _chronoLocalDateImpl.isAfter(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isAfterOrEqual(expected: String): Assertion = _chronoLocalDateImpl.isAfterOrEqual(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isEqual(expected: String): Assertion = _chronoLocalDateImpl.isEqual(this, expected)
