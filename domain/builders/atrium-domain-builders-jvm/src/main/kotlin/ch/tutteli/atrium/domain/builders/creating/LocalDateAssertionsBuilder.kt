@file:Suppress(
    "OVERRIDE_BY_INLINE",
    "NOTHING_TO_INLINE",
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
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
object LocalDateAssertionsBuilder : LocalDateAssertions {
    override inline fun <T : LocalDate> year(assertionContainer: Expect<T>) =
        localDateAssertions.year(assertionContainer)

    override inline fun <T : LocalDate> month(assertionContainer: Expect<T>) =
        localDateAssertions.month(assertionContainer)

    override inline fun <T : LocalDate> day(assertionContainer: Expect<T>) =
        localDateAssertions.day(assertionContainer)

    override inline fun <T : LocalDate> dayOfWeek(assertionContainer: Expect<T>) =
        localDateAssertions.dayOfWeek(assertionContainer)
}
