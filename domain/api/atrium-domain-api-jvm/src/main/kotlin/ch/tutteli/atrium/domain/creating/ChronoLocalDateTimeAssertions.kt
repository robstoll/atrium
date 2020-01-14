@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

/**
 * The access point to an implementation of [ChronoLocalDateTimeAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val chronoLocalDateTimeAssertions by lazy { loadSingleService(ChronoLocalDateTimeAssertions::class) }

/**
 * Defines the minimum set of assertion functions and builders applicable to [ChronoLocalDateTime],
 * which an implementation of the domain of Atrium has to provide.
 */
interface ChronoLocalDateTimeAssertions {
    fun <D : ChronoLocalDate, T : ChronoLocalDateTime<D>> isBefore(
        assertionContainer: Expect<T>,
        expected: T
    ): Assertion

    fun <D : ChronoLocalDate, T : ChronoLocalDateTime<D>> isBeforeOrEquals(
        assertionContainer: Expect<T>,
        expected: T
    ): Assertion

    fun <D : ChronoLocalDate, T : ChronoLocalDateTime<D>> isAfter(
        assertionContainer: Expect<T>,
        expected: T
    ): Assertion

    fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isEqual(
        expect: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion
}
