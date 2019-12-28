package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ChronoLocalDateTimeAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isAfter
import ch.tutteli.atrium.domain.robstoll.lib.creating._isBeforeOrEquals
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

class ChronoLocalDateTimeAssertionsImpl : ChronoLocalDateTimeAssertions {

    override fun <D: ChronoLocalDate, T : ChronoLocalDateTime<D>> isAfter(assertionContainer: Expect<T>, expected: T): Assertion =
        _isAfter(assertionContainer, expected)

    override fun <D: ChronoLocalDate, T : ChronoLocalDateTime<D>> isBeforeOrEquals(assertionContainer: Expect<T>, expected: T): Assertion =
        _isBeforeOrEquals(assertionContainer, expected)
}
