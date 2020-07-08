//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)
package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
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
@Deprecated(
    "Use OptionalAssertions from atrium-logic; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.logic.OptionalAssertions")
)
interface OptionalAssertions {
    fun <T : Optional<*>> isEmpty(expect: Expect<T>): Assertion
    fun <E, T : Optional<E>> isPresent(expect: Expect<T>): ExtractedFeaturePostStep<T, E>
}
