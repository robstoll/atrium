package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ChronoZonedDateTimeAssertions
import ch.tutteli.atrium.domain.creating.chronoZonedDateTimeAssertions
import java.time.chrono.ChronoZonedDateTime

object ChronoZonedDateTimeAssertionsBuilder : ChronoZonedDateTimeAssertions {
    override inline fun <T : ChronoZonedDateTime> isAfter(assertionContainer: Expect<T>, expected: T) =
        chronoZonedDateTimeAssertions.isAfter(assertionContainer, expected)
}
