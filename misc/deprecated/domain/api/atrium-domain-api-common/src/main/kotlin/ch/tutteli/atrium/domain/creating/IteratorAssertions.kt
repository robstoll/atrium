//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect

/**
 * The access point to an implementation of [IteratorAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
@Deprecated("Use _logic from ch.tutteli.atrium.logic instead; will be removed with 1.0.0")
val iteratorAssertions by lazy { loadSingleService(IteratorAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [Iterator],
 * which an implementation of the domain of Atrium has to provide.
 */
@Deprecated(
    "Use IteratorAssertions from atrium-logic; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.logic.IteratorAssertions")
)
interface IteratorAssertions {
    fun <E, T : Iterator<E>> hasNext(expect: Expect<T>): Assertion
    fun <E, T : Iterator<E>> hasNotNext(expect: Expect<T>): Assertion
}
