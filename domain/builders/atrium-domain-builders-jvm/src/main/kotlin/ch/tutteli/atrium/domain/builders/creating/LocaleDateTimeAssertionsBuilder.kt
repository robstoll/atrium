@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.domain.creating.LocalDateTimeAssertions
import ch.tutteli.atrium.domain.creating.localDateTimeAssertions

/**
 * Delegates inter alia to the implementation of [LocalDateTimeAssertions].
 * In detail, it implements [LocalDateTimeAssertions] by delegating to [localDateTimeAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object LocaleDateTimeAssertionsBuilder : LocalDateTimeAssertions {

}
