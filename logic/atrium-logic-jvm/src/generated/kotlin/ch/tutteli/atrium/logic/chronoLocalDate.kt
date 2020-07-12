//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.time.chrono.ChronoLocalDate

fun <T : ChronoLocalDate> AssertionContainer<T>.isBefore(expected: ChronoLocalDate): Assertion = _chronoLocalDateImpl.isBefore(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isBeforeOrEqual(expected: ChronoLocalDate): Assertion = _chronoLocalDateImpl.isBeforeOrEqual(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isAfter(expected: ChronoLocalDate): Assertion = _chronoLocalDateImpl.isAfter(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isAfterOrEqual(expected: ChronoLocalDate): Assertion = _chronoLocalDateImpl.isAfterOrEqual(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isEqual(expected: ChronoLocalDate): Assertion = _chronoLocalDateImpl.isEqual(this, expected)
