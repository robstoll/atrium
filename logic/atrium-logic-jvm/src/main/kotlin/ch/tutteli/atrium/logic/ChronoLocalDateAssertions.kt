package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.time.chrono.ChronoLocalDate

interface ChronoLocalDateAssertions {
    fun <T : ChronoLocalDate> isBefore(container: AssertionContainer<T>, expected: ChronoLocalDate): Assertion
    fun <T : ChronoLocalDate> isBeforeOrEqual(container: AssertionContainer<T>, expected: ChronoLocalDate): Assertion
    fun <T : ChronoLocalDate> isAfter(container: AssertionContainer<T>, expected: ChronoLocalDate): Assertion
    fun <T : ChronoLocalDate> isAfterOrEqual(container: AssertionContainer<T>, expected: ChronoLocalDate): Assertion
    fun <T : ChronoLocalDate> isEqual(container: AssertionContainer<T>, expected: ChronoLocalDate): Assertion
}
