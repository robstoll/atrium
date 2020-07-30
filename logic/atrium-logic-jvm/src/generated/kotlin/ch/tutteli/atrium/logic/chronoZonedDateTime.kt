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
import java.time.chrono.ChronoZonedDateTime

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultChronoZonedDateTimeAssertions

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ChronoZonedDateTimeAssertions
    get() = getImpl(ChronoZonedDateTimeAssertions::class) { DefaultChronoZonedDateTimeAssertions() }

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isBefore(expected: ChronoZonedDateTime<*>): Assertion = impl.isBefore(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isBeforeOrEqual(expected: ChronoZonedDateTime<*>): Assertion = impl.isBeforeOrEqual(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isAfter(expected: ChronoZonedDateTime<*>): Assertion = impl.isAfter(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isAfterOrEqual(expected: ChronoZonedDateTime<*>): Assertion = impl.isAfterOrEqual(this, expected)

fun <T : ChronoZonedDateTime<out ChronoLocalDate>> AssertionContainer<T>.isEqual(expected: ChronoZonedDateTime<*>): Assertion = impl.isEqual(this, expected)
