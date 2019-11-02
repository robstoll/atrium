package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ChronoLocalDateAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isBefore
import java.time.chrono.ChronoLocalDate

class ChronoLocalDateAssertionsImpl : ChronoLocalDateAssertions {
    override fun <T : ChronoLocalDate> isBefore(assertionContainer: Expect<T>, expected: T): Assertion =
        _isBefore(assertionContainer, expected)
}
