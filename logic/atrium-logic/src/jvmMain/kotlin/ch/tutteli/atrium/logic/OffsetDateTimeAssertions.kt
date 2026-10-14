package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.time.OffsetDateTime

/**
 * Collection of expectation functions and builders which are applicable to subjects with a [OffsetDateTime] type.
 */
interface OffsetDateTimeAssertions {
    fun toBeBefore(container: AssertionContainer<OffsetDateTime>, expected: OffsetDateTime): Assertion
    fun toBeBeforeOrTheSamePointInTimeAs(container: AssertionContainer<OffsetDateTime>, expected: OffsetDateTime): Assertion
    fun toBeAfter(container: AssertionContainer<OffsetDateTime>, expected: OffsetDateTime): Assertion
    fun toBeAfterOrTheSamePointInTimeAs(container: AssertionContainer<OffsetDateTime>, expected: OffsetDateTime): Assertion
    fun toBeEqual(container: AssertionContainer<OffsetDateTime>, expected: OffsetDateTime): Assertion

    fun toBeBefore(container: AssertionContainer<OffsetDateTime>, expected: String): Assertion
    fun toBeBeforeOrTheSamePointInTimeAs(container: AssertionContainer<OffsetDateTime>, expected: String): Assertion
    fun toBeAfter(container: AssertionContainer<OffsetDateTime>, expected: String): Assertion
    fun toBeAfterOrTheSamePointInTimeAs(container: AssertionContainer<OffsetDateTime>, expected: String): Assertion
    fun toBeEqual(container: AssertionContainer<OffsetDateTime>, expected: String): Assertion
}
