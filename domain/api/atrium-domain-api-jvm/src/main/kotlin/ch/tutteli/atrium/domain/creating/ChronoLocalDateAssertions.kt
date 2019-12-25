package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import java.time.chrono.ChronoLocalDate

val chronoLocalDateAssertions by lazy { loadSingleService(ChronoLocalDateAssertions::class) }

interface ChronoLocalDateAssertions {
    fun <T : ChronoLocalDate> isAfter(assertionContainer: Expect<T>, expected: T): Assertion
}
