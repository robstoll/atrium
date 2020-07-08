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
import ch.tutteli.atrium.domain.creating.ZonedDateTimeAssertions
import ch.tutteli.atrium.domain.creating.zonedDateTimeAssertions
import java.time.ZonedDateTime

/**
 * Delegates inter alia to the implementation of [ZonedDateTimeAssertions].
 * In detail, it implements [ZonedDateTimeAssertions] by delegating to [zonedDateTimeAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object ZonedDateTimeAssertionsBuilder : ZonedDateTimeAssertions {
    override inline fun <T : ZonedDateTime> year(expect: Expect<T>) =
        zonedDateTimeAssertions.year(expect)

    override inline fun <T : ZonedDateTime> month(expect: Expect<T>) =
        zonedDateTimeAssertions.month(expect)

    override inline fun <T : ZonedDateTime> day(expect: Expect<T>) =
        zonedDateTimeAssertions.day(expect)

    override inline fun <T : ZonedDateTime> dayOfWeek(expect: Expect<T>) =
        zonedDateTimeAssertions.dayOfWeek(expect)
}
