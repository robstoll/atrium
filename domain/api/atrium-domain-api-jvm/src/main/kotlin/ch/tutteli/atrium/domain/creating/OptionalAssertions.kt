@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

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
    fun <T : Optional<*>> isEmpty(expect: Expect<T>): Assertion
}
