// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
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
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultChronoLocalDateAssertions

fun <T : ChronoLocalDate> AssertionContainer<T>.isBefore(expected: ChronoLocalDate): Assertion = impl.isBefore(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isBeforeOrEqual(expected: ChronoLocalDate): Assertion = impl.isBeforeOrEqual(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isAfter(expected: ChronoLocalDate): Assertion = impl.isAfter(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isAfterOrEqual(expected: ChronoLocalDate): Assertion = impl.isAfterOrEqual(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isEqual(expected: ChronoLocalDate): Assertion = impl.isEqual(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isBefore(expected: String): Assertion = impl.isBefore(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isBeforeOrEqual(expected: String): Assertion = impl.isBeforeOrEqual(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isAfter(expected: String): Assertion = impl.isAfter(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isAfterOrEqual(expected: String): Assertion = impl.isAfterOrEqual(this, expected)
fun <T : ChronoLocalDate> AssertionContainer<T>.isEqual(expected: String): Assertion = impl.isEqual(this, expected)

                    @OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ChronoLocalDateAssertions
    get() = getImpl(ChronoLocalDateAssertions::class) { DefaultChronoLocalDateAssertions() }
