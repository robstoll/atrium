@file:Suppress(
    "OVERRIDE_BY_INLINE",
    "NOTHING_TO_INLINE",
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

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
object ChronoLocalDateTimeAssertionsBuilder : ChronoLocalDateTimeAssertions {

    override inline fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBefore(
        assertionContainer: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ) = chronoLocalDateTimeAssertions.isBefore(assertionContainer, expected)

    override inline fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBeforeOrEquals(
        assertionContainer: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ) = chronoLocalDateTimeAssertions.isBeforeOrEquals(assertionContainer, expected)

    override inline fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfter(
        assertionContainer: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ) = chronoLocalDateTimeAssertions.isAfter(assertionContainer, expected)
}
