@file:Suppress(
    "OVERRIDE_BY_INLINE",
    "NOTHING_TO_INLINE",
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
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

    override inline fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isBefore(
        assertionContainer: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ) = chronoZonedDateTimeAssertions.isBefore(assertionContainer, expected)

    override inline fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isBeforeOrEquals(
        assertionContainer: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ) = chronoZonedDateTimeAssertions.isBeforeOrEquals(assertionContainer, expected)

    override inline fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isAfter(
        assertionContainer: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ) = chronoZonedDateTimeAssertions.isAfter(assertionContainer, expected)
}
