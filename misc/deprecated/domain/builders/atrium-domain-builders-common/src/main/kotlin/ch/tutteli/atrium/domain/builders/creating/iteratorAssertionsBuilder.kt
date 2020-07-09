//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.IteratorAssertions
import ch.tutteli.atrium.domain.creating.iteratorAssertions


/**
 * Delegates inter alia to the implementation of [IteratorAssertions].
 * In detail, it implements [IteratorAssertions] by delegating to [iteratorAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
@Deprecated("Use _logic from ch.tutteli.atrium.logic instead; will be removed with 1.0.0")
object IteratorAssertionsBuilder : IteratorAssertions {
    override inline fun <E, T : Iterator<E>> hasNext(expect: Expect<T>): Assertion =
        iteratorAssertions.hasNext(expect)

    override inline fun <E, T : Iterator<E>> hasNotNext(expect: Expect<T>): Assertion =
        iteratorAssertions.hasNotNext(expect)
}
