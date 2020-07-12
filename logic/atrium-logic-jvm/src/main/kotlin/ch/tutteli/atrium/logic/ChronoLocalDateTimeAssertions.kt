@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [ChronoLocalDateTime] type.
 */
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
}
