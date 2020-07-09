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
import ch.tutteli.atrium.domain.creating.LocalDateAssertions
import ch.tutteli.atrium.domain.creating.localDateAssertions
import java.time.LocalDate

/**
 * Delegates inter alia to the implementation of [LocalDateAssertions].
 * In detail, it implements [LocalDateAssertions] by delegating to [localDateAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
@Deprecated("Use _logic from ch.tutteli.atrium.logic instead; will be removed with 1.0.0")
object LocalDateAssertionsBuilder : LocalDateAssertions {
    override inline fun <T : LocalDate> year(expect: Expect<T>) =
        localDateAssertions.year(expect)

    override inline fun <T : LocalDate> month(expect: Expect<T>) =
        localDateAssertions.month(expect)

    override inline fun <T : LocalDate> day(expect: Expect<T>) =
        localDateAssertions.day(expect)

    override inline fun <T : LocalDate> dayOfWeek(expect: Expect<T>) =
        localDateAssertions.dayOfWeek(expect)
}
