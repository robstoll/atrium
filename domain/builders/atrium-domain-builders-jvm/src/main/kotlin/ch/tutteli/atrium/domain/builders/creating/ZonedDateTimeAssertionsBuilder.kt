@file:Suppress(
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
    override inline fun <T : ZonedDateTime> year(assertionContainer: Expect<T>) =
        zonedDateTimeAssertions.year(assertionContainer)

    override inline fun <T : ZonedDateTime> month(assertionContainer: Expect<T>) =
        zonedDateTimeAssertions.month(assertionContainer)

    override inline fun <T : ZonedDateTime> day(assertionContainer: Expect<T>) =
        zonedDateTimeAssertions.day(assertionContainer)

    override inline fun <T : ZonedDateTime> dayOfWeek(assertionContainer: Expect<T>) =
        zonedDateTimeAssertions.dayOfWeek(assertionContainer)
}
