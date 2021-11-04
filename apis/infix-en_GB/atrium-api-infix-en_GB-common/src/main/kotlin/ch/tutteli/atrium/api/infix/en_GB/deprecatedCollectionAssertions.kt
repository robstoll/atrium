package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.kbox.identity

/**
 * Expects that the subject of `this` expectation (a [Collection]) has the given [expected] size.
 *
 * Shortcut for `size.toBe(expected)`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CollectionAssertionSamples.hasSize
 */
@Deprecated("Use toHaveSize; will be removed with 1.0.0 at the latest", ReplaceWith("this.toHaveSize<T>(expected)"))
infix fun <T : Collection<*>> Expect<T>.hasSize(expected: Int): Expect<T> =
    size { toEqual(expected) }

