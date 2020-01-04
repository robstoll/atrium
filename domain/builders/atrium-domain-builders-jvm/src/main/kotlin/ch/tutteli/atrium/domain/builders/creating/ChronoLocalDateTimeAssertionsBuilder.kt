@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ChronoLocalDateTimeAssertions
import ch.tutteli.atrium.domain.creating.chronoLocalDateTimeAssertions
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

/**
 * Delegates inter alia to the implementation of [ChronoLocalDateTimeAssertionsBuilder].
 * In detail, it implements [ChronoLocalDateTimeAssertions] by delegating to [chronoLocalDateTimeAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object ChronoLocalDateTimeAssertionsBuilder  : ChronoLocalDateTimeAssertions {

    override inline fun <D : ChronoLocalDate, T : ChronoLocalDateTime<D>> isAfter(assertionContainer : Expect<T>, expected: T) =
        chronoLocalDateTimeAssertions.isAfter(assertionContainer, expected)

    override inline fun <D : ChronoLocalDate, T : ChronoLocalDateTime<D>> isBeforeOrEquals(assertionContainer : Expect<T>, expected: T) =
        chronoLocalDateTimeAssertions.isBeforeOrEquals(assertionContainer, expected)

    override inline fun <D : ChronoLocalDate, T : ChronoLocalDateTime<D>> isBefore(assertionContainer : Expect<T>, expected: T) =
        chronoLocalDateTimeAssertions.isBefore(assertionContainer, expected)
}
