@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

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
    override inline fun year(assertionContainer: Expect<LocalDate>) = localDateAssertions.year(assertionContainer)

    override inline fun month(assertionContainer: Expect<LocalDate>) = localDateAssertions.month(assertionContainer)
}
