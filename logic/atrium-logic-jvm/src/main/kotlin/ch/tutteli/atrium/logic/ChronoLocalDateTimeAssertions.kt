package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

interface ChronoLocalDateTimeAssertions {
    fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBefore(
        container: AssertionContainer<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion

    fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBeforeOrEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion

    fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfter(
        container: AssertionContainer<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion

    fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfterOrEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion

    fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion

    fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBefore(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion

    fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBeforeOrEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion

    fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfter(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion

    fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfterOrEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion

    fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion

}
