package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ChronoLocalDateTimeAssertions
import ch.tutteli.atrium.domain.creating.chronoLocalDateTimeAssertions
import java.time.chrono.ChronoLocalDateTime

object ChronoLocalDateTimeAssertionsBuilder  : ChronoLocalDateTimeAssertions {
    override inline fun <T : ChronoLocalDateTime> isAfter(assertionContainer : Expect<T>, expected: T) =
        chronoLocalDateTimeAssertions.isAfter(assertionContainer, expected)
}
