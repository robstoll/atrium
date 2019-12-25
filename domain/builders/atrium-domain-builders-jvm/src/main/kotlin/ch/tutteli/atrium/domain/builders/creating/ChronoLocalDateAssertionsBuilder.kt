package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ChronoLocalDateAssertions
import ch.tutteli.atrium.domain.creating.chronoLocalDateAssertions
import java.time.chrono.ChronoLocalDate

/**
 * Delegates inter alia to the implementation of [ChronoLocalDateAssertionsBuilder].
 * In detail, it implements [ChronoLocalDateAssertions] by delegating to [chronoLocalDateAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object ChronoLocalDateAssertionsBuilder : ChronoLocalDateAssertions {
    override inline fun <T : ChronoLocalDate> isAfter(assertionContainer: Expect<T>, expected: T) =
        chronoLocalDateAssertions.isAfter(assertionContainer, expected)
}
