package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation.*
import java.time.OffsetDateTime

class DefaultOffsetDateTimeAssertions : OffsetDateTimeAssertions {
    override fun toBeBefore(
        container: AssertionContainer<OffsetDateTime>,
        expected: OffsetDateTime
    ): Assertion = container.createDescriptiveAssertion(TO_BE_BEFORE, expected) { it.isBefore(expected) }

    override fun toBeBeforeOrTheSamePointInTimeAs(
        container: AssertionContainer<OffsetDateTime>,
        expected: OffsetDateTime
    ): Assertion = container.createDescriptiveAssertion(TO_BE_BEFORE_OR_THE_SAME_POINT_IN_TIME_AS, expected) {
        it.isBefore(expected) || it.isEqual(expected)
    }

    override fun toBeAfter(
        container: AssertionContainer<OffsetDateTime>,
        expected: OffsetDateTime
    ): Assertion = container.createDescriptiveAssertion(TO_BE_AFTER, expected) { it.isAfter(expected) }

    override fun toBeAfterOrTheSamePointInTimeAs(
        container: AssertionContainer<OffsetDateTime>,
        expected: OffsetDateTime
    ): Assertion = container.createDescriptiveAssertion(TO_BE_AFTER_OR_THE_SAME_POINT_IN_TIME_AS, expected) {
        it.isAfter(expected) || it.isEqual(expected)
    }

    override fun toBeEqual(
        container: AssertionContainer<OffsetDateTime>,
        expected: OffsetDateTime
    ): Assertion = container.createDescriptiveAssertion(TO_BE_THE_SAME_POINT_IN_TIME_AS, expected) {
        it.isEqual(expected)
    }

    override fun toBeBefore(
        container: AssertionContainer<OffsetDateTime>,
        expected: String
    ): Assertion = container.toBeBefore(parseOffsetDateTime(expected))

    override fun toBeBeforeOrTheSamePointInTimeAs(
        container: AssertionContainer<OffsetDateTime>,
        expected: String
    ): Assertion = container.toBeBeforeOrTheSamePointInTimeAs(parseOffsetDateTime(expected))

    override fun toBeAfter(
        container: AssertionContainer<OffsetDateTime>,
        expected: String
    ): Assertion = container.toBeAfter(parseOffsetDateTime(expected))

    override fun toBeAfterOrTheSamePointInTimeAs(
        container: AssertionContainer<OffsetDateTime>,
        expected: String
    ): Assertion = container.toBeAfterOrTheSamePointInTimeAs(parseOffsetDateTime(expected))

    override fun toBeEqual(
        container: AssertionContainer<OffsetDateTime>,
        expected: String
    ): Assertion = container.toBeEqual(parseOffsetDateTime(expected))

    private fun parseOffsetDateTime(data: String): OffsetDateTime =
        DefaultChronoZonedDateTimeAssertions.parseZonedDateTime(data).toOffsetDateTime()
}
