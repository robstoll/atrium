@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.LocalDateTimeAssertions
import ch.tutteli.atrium.domain.creating.localDateTimeAssertions
import java.time.LocalDateTime

/**
 * Delegates inter alia to the implementation of [LocalDateTimeAssertions].
 * In detail, it implements [LocalDateTimeAssertions] by delegating to [localDateTimeAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object LocalDateTimeAssertionsBuilder : LocalDateTimeAssertions {
    override inline fun <T: LocalDateTime> year(assertionContainer: Expect<T>) = localDateTimeAssertions.year(assertionContainer)

    override inline fun <T: LocalDateTime> month(assertionContainer: Expect<T>) = localDateTimeAssertions.month(assertionContainer)

    override inline fun <T: LocalDateTime> dayOfWeek(assertionContainer: Expect<T>) =
        localDateTimeAssertions.dayOfWeek(assertionContainer)
}
