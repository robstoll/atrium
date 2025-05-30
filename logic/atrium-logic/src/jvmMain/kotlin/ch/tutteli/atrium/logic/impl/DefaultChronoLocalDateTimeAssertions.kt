//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

class DefaultChronoLocalDateTimeAssertions : ChronoLocalDateTimeAssertions {
    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBefore(
        container: AssertionContainer<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion = container.createDescriptiveAssertion(TO_BE_BEFORE, expected) { it.isBefore(expected) }

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBefore(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isBefore(stringToLocalDateTime(expected))

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBeforeOrEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion = container.createDescriptiveAssertion(TO_BE_BEFORE_OR_THE_SAME_POINT_IN_TIME_AS, expected) {
        it.isBefore(expected) || it.isEqual(expected)
    }

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBeforeOrEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isBeforeOrEqual(stringToLocalDateTime(expected))

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfter(
        container: AssertionContainer<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion = container.createDescriptiveAssertion(TO_BE_AFTER, expected) { it.isAfter(expected) }

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfter(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isAfter(stringToLocalDateTime(expected))

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfterOrEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion = container.createDescriptiveAssertion(TO_BE_AFTER_OR_THE_SAME_POINT_IN_TIME_AS, expected) {
        it.isAfter(expected) || it.isEqual(expected)
    }

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfterOrEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isAfterOrEqual(stringToLocalDateTime(expected))

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion = container.createDescriptiveAssertion(TO_BE_THE_SAME_POINT_IN_TIME_AS, expected) {
        it.isEqual(expected)
    }

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isEqual(stringToLocalDateTime(expected))

    private fun stringToLocalDateTime(data: String): LocalDateTime {
        return if (data.contains("T")) {
            LocalDateTime.parse(data)
        } else {
            LocalDate.parse(data).atStartOfDay()
        }
    }
}
