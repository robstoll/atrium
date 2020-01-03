package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoZonedDateTime

/**
 * The access point to an implementation of [ChronoZonedDateTimeAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val chronoZonedDateTimeAssertions by lazy { loadSingleService(ChronoZonedDateTimeAssertions::class) }

/**
 * Defines the minimum set of assertion functions and builders applicable to [ChronoZonedDateTime],
 * which an implementation of the domain of Atrium has to provide.
 */
interface ChronoZonedDateTimeAssertions {
    fun <D : ChronoLocalDate, T : ChronoZonedDateTime<D>> isAfter(assertionContainer: Expect<T>, expected: T): Assertion
    fun <D : ChronoLocalDate, T : ChronoZonedDateTime<D>> isBeforeOrEquals(assertionContainer: Expect<T>, expected: T): Assertion
    fun <D : ChronoLocalDate, T : ChronoZonedDateTime<D>> isBefore(assertionContainer: Expect<T>, expected: T): Assertion
}
