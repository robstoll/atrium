package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import java.time.chrono.ChronoZonedDateTime

val chronoZonedDateTimeAssertions by lazy { loadSingleService(ChronoZonedDateTimeAssertions::class) }

interface ChronoZonedDateTimeAssertions {
    fun <T : ChronoZonedDateTime> isAfter(assertionContainer: Expect<T>, expected: T): Assertion
}
