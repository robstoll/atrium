package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ChronoZonedDateTimeAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isAfter
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoZonedDateTime

class ChronoZonedDateTimeAssertionsImpl : ChronoZonedDateTimeAssertions {
    override fun <D: ChronoLocalDate, T : ChronoZonedDateTime<D>> isAfter(assertionContainer: Expect<T>, expected: T): Assertion =
        _isAfter(assertionContainer, expected)
}
