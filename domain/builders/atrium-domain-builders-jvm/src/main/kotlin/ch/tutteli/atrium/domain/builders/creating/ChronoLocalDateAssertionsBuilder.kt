package ch.tutteli.atrium.domain.builders.creating

@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.creating.ChronoLocalDateAssertions
import ch.tutteli.atrium.domain.creating.chronoLocalDateAssertions
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect

/**
 * Delegates inter alia to the implementation of [ChronoLocalDateAssertions].
 * In detail, it implements [ChronoLocalDateAssertions] by delegating to [ChronoLocalDateAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object ChronoLocalDateAssertionsBuilder : ChronoLocalDateAssertions {
    override inline fun <T: ChronoLocalDate> isBeforeOrEquals(assertionContainer: Expect<T>, expected: T): Assertion =
        chronoLocalDateAssertions.isBeforeOrEquals(assertionContainer, expected)
}
