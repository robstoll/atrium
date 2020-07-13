@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.ChronoLocalDateTimeAssertions
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

class DefaultChronoLocalDateTimeAssertions : ChronoLocalDateTimeAssertions {
    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBefore(
        container: AssertionContainer<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion = container.createDescriptiveAssertion(IS_BEFORE, expected) { it.isBefore(expected) }

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBefore(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isBefore(stringToLocalDateTime(expected))

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBeforeOrEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion = container.createDescriptiveAssertion(IS_BEFORE_OR_EQUAL, expected) {
        it.isBefore(expected) || it.isEqual(expected)
    }

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBeforeOrEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.createDescriptiveAssertion(IS_BEFORE_OR_EQUAL, expected) {
        it.isBefore(stringToLocalDateTime(expected)) || it.isEqual(stringToLocalDateTime(expected))
    }

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfter(
        container: AssertionContainer<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion = container.createDescriptiveAssertion(IS_AFTER, expected) { it.isAfter(expected) }

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfter(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.createDescriptiveAssertion(IS_AFTER, expected) {
        it.isAfter(stringToLocalDateTime(expected))
    }

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfterOrEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion = container.createDescriptiveAssertion(IS_AFTER_OR_EQUAL, expected) {
        it.isAfter(expected) || it.isEqual(expected)
    }

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfterOrEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.createDescriptiveAssertion(IS_AFTER_OR_EQUAL, expected) {
        it.isAfter(stringToLocalDateTime(expected)) || it.isEqual(stringToLocalDateTime(expected))
    }

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion = container.createDescriptiveAssertion(IS_EQUAL_TO, expected) {
        it.isEqual(expected)
    }

    override fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.createDescriptiveAssertion(IS_EQUAL_TO, expected) {
        it.isEqual(stringToLocalDateTime(expected))
    }

    private fun stringToLocalDateTime(data: String): LocalDateTime {
        return if (data.contains("T")) {
            LocalDateTime.parse(data)
        } else {
            LocalDate.parse(data).atStartOfDay()
        }
    }
}
