package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

/**
 * The access point to an implementation of [ChronoLocalDateAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val chronoLocalDateAssertions by lazy { loadSingleService(ChronoLocalDateAssertions::class) }

/**
 * Defines the minimum set of assertion functions and builders applicable to [ChronoLocalDateTime<ChronoLocalDate>>],
 * which an implementation of the domain of Atrium has to provide.
 */
interface ChronoLocalDateAssertions {
    fun <T : ChronoLocalDateTime<ChronoLocalDate>> isBefore(
        assertionContainer: Expect<T>,
        expected: T
    ): Assertion
}
