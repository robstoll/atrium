//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
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
        expect: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ) = chronoLocalDateTimeAssertions.isBefore(expect, expected)

    override inline fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBeforeOrEquals(
        expect: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ) = chronoLocalDateTimeAssertions.isBeforeOrEquals(expect, expected)

    override inline fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfter(
        expect: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ) = chronoLocalDateTimeAssertions.isAfter(expect, expected)

    override inline fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfterOrEquals(
        expect: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ) = chronoLocalDateTimeAssertions.isAfterOrEquals(expect, expected)

    override inline fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isEqual(
        expect: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ) = chronoLocalDateTimeAssertions.isEqual(expect, expected)
}
