package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ChronoLocalDateTimeAssertions
import ch.tutteli.atrium.domain.creating.chronoLocalDateTimeAssertions
import java.time.chrono.ChronoLocalDateTime

/**
 * Delegates inter alia to the implementation of [ChronoLocalDateTimeAssertionsBuilder].
 * In detail, it implements [ChronoLocalDateTimeAssertions] by delegating to [chronoLocalDateTimeAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object ChronoLocalDateTimeAssertionsBuilder  : ChronoLocalDateTimeAssertions {
    override inline fun <T : ChronoLocalDateTime> isAfter(assertionContainer : Expect<T>, expected: T) =
        chronoLocalDateTimeAssertions.isAfter(assertionContainer, expected)
}
