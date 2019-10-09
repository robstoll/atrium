package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import java.util.*

/**
 * The access point to an implementation of [OptionalAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val optionalAssertions by lazy { loadSingleService(OptionalAssertions::class) }

/**
 * Defines the minimum set of assertion functions and builders applicable to [Optional],
 * which an implementation of the domain of Atrium has to provide.
 */
interface OptionalAssertions {
    fun <E, T: Optional<E>> isEmpty(assertionContainer: Expect<T>): Assertion
}
