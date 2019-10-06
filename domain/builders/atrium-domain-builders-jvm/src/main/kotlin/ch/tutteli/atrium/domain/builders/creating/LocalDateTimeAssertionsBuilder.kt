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
    override fun year(assertionContainer: Expect<LocalDateTime>) = localDateTimeAssertions.year(assertionContainer)

}
