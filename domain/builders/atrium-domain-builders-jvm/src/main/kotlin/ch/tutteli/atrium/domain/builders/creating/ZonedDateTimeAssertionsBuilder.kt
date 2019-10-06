@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

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
    override fun year(assertionContainer: Expect<ZonedDateTime>) = zonedDateTimeAssertions.year(assertionContainer)

}
