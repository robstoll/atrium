@file:Suppress(
    "OVERRIDE_BY_INLINE",
    "NOTHING_TO_INLINE",
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
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

    override inline fun <T : ChronoLocalDate> isBefore(assertionContainer: Expect<T>, expected: ChronoLocalDate) =
        chronoLocalDateAssertions.isBefore(assertionContainer, expected)

    override inline fun <T : ChronoLocalDate> isBeforeOrEquals(assertionContainer: Expect<T>, expected: ChronoLocalDate) =
        chronoLocalDateAssertions.isBeforeOrEquals(assertionContainer, expected)

    override inline fun <T : ChronoLocalDate> isAfter(assertionContainer: Expect<T>, expected: ChronoLocalDate) =
        chronoLocalDateAssertions.isAfter(assertionContainer, expected)
}
