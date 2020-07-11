@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import java.time.chrono.ChronoLocalDate

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [ChronoLocalDate] type.
 */
interface ChronoLocalDateAssertions {
    fun <T : ChronoLocalDate> isBefore(container: AssertionContainer<T>, expected: ChronoLocalDate): Assertion
    fun <T : ChronoLocalDate> isBeforeOrEqual(container: AssertionContainer<T>, expected: ChronoLocalDate): Assertion
    fun <T : ChronoLocalDate> isAfter(container: AssertionContainer<T>, expected: ChronoLocalDate): Assertion
    fun <T : ChronoLocalDate> isAfterOrEqual(container: AssertionContainer<T>, expected: ChronoLocalDate): Assertion
    fun <T : ChronoLocalDate> isEqual(container: AssertionContainer<T>, expected: ChronoLocalDate): Assertion
}
