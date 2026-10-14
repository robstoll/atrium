// @formatter:off
//---------------------------------------------------
//  Generated content, don't change here but in:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  Enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.time.OffsetDateTime
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultOffsetDateTimeAssertions

fun AssertionContainer<OffsetDateTime>.toBeBefore(expected: OffsetDateTime): Assertion = impl.toBeBefore(this, expected)
fun AssertionContainer<OffsetDateTime>.toBeBeforeOrTheSamePointInTimeAs(expected: OffsetDateTime): Assertion = impl.toBeBeforeOrTheSamePointInTimeAs(this, expected)
fun AssertionContainer<OffsetDateTime>.toBeAfter(expected: OffsetDateTime): Assertion = impl.toBeAfter(this, expected)
fun AssertionContainer<OffsetDateTime>.toBeAfterOrTheSamePointInTimeAs(expected: OffsetDateTime): Assertion = impl.toBeAfterOrTheSamePointInTimeAs(this, expected)
fun AssertionContainer<OffsetDateTime>.toBeEqual(expected: OffsetDateTime): Assertion = impl.toBeEqual(this, expected)

fun AssertionContainer<OffsetDateTime>.toBeBefore(expected: String): Assertion = impl.toBeBefore(this, expected)
fun AssertionContainer<OffsetDateTime>.toBeBeforeOrTheSamePointInTimeAs(expected: String): Assertion = impl.toBeBeforeOrTheSamePointInTimeAs(this, expected)
fun AssertionContainer<OffsetDateTime>.toBeAfter(expected: String): Assertion = impl.toBeAfter(this, expected)
fun AssertionContainer<OffsetDateTime>.toBeAfterOrTheSamePointInTimeAs(expected: String): Assertion = impl.toBeAfterOrTheSamePointInTimeAs(this, expected)
fun AssertionContainer<OffsetDateTime>.toBeEqual(expected: String): Assertion = impl.toBeEqual(this, expected)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: OffsetDateTimeAssertions
    get() = getImpl(OffsetDateTimeAssertions::class) { DefaultOffsetDateTimeAssertions() }
