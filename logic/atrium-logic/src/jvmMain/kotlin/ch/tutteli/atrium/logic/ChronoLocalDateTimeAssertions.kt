//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [ChronoLocalDateTime] type.
 */
//TODO 1.3.0 deprecate
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
