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

    override inline fun <T : ChronoLocalDate> isBefore(expect: Expect<T>, expected: ChronoLocalDate) =
        chronoLocalDateAssertions.isBefore(expect, expected)

    override inline fun <T : ChronoLocalDate> isBeforeOrEquals(expect: Expect<T>, expected: ChronoLocalDate) =
        chronoLocalDateAssertions.isBeforeOrEquals(expect, expected)

    override inline fun <T : ChronoLocalDate> isAfter(expect: Expect<T>, expected: ChronoLocalDate) =
        chronoLocalDateAssertions.isAfter(expect, expected)

    override inline fun <T : ChronoLocalDate> isAfterOrEquals(expect: Expect<T>, expected: ChronoLocalDate) =
        chronoLocalDateAssertions.isAfterOrEquals(expect, expected)

    override inline fun <T : ChronoLocalDate> isEqual(expect: Expect<T>, expected: ChronoLocalDate) =
        chronoLocalDateAssertions.isEqual(expect, expected)
}
