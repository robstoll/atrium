package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ChronoZonedDateTimeAssertions
import ch.tutteli.atrium.domain.creating.chronoZonedDateTimeAssertions
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoZonedDateTime

/**
 * Delegates inter alia to the implementation of [ChronoZonedDateTimeAssertionsBuilder].
 * In detail, it implements [ChronoZonedDateTimeAssertions] by delegating to [chronoZonedDateTimeAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object ChronoZonedDateTimeAssertionsBuilder : ChronoZonedDateTimeAssertions {
    override inline fun <D : ChronoLocalDate, T : ChronoZonedDateTime<D>> isAfter(assertionContainer: Expect<T>, expected: T) =
        chronoZonedDateTimeAssertions.isAfter(assertionContainer, expected)
}
