package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ChronoZonedDateTimeAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isAfter
import ch.tutteli.atrium.domain.robstoll.lib.creating._isBefore
import ch.tutteli.atrium.domain.robstoll.lib.creating._isBeforeOrEquals
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoZonedDateTime

class ChronoZonedDateTimeAssertionsImpl : ChronoZonedDateTimeAssertions {

    override fun <D: ChronoLocalDate, T : ChronoZonedDateTime<D>> isAfter(assertionContainer: Expect<T>, expected: T): Assertion =
        _isAfter(assertionContainer, expected)

    override fun <D: ChronoLocalDate, T : ChronoZonedDateTime<D>> isBeforeOrEquals(assertionContainer: Expect<T>, expected: T): Assertion =
        _isBeforeOrEquals(assertionContainer, expected)

    override fun <D: ChronoLocalDate, T : ChronoZonedDateTime<D>> isBefore(assertionContainer: Expect<T>, expected: T): Assertion =
        _isBefore(assertionContainer, expected)
}
