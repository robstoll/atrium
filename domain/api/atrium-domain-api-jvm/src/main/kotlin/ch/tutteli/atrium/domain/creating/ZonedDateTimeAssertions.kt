package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import java.time.ZonedDateTime

/**
 * The access point to an implementation of [ZonedDateTimeAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val zonedDateTimeAssertions by lazy { loadSingleService(ZonedDateTimeAssertions::class) }

/**
 * Defines the minimum set of assertion functions and builders applicable to [ZonedDateTime],
 * which an implementation of the domain of Atrium has to provide.
 */
interface ZonedDateTimeAssertions {
}
