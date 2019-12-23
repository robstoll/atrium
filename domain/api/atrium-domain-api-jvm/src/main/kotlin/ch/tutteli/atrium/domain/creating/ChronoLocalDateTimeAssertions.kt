package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

val chronoLocalDateTimeAssertions by lazy { loadSingleService(ChronoLocalDateTimeAssertions::class) }

interface ChronoLocalDateTimeAssertions {
    fun <D : ChronoLocalDate, T : ChronoLocalDateTime<D>> isAfter(assertionContainer: Expect<T>, expected: T): Assertion
}
