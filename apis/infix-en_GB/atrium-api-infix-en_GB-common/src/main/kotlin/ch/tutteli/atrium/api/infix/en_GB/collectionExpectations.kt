package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

/**
 * Expects that the subject of `this` expectation (a [Collection]) has the given [expected] size.
 *
 * Shortcut for `size.toBe(expected)`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CollectionExpectationSamples.toHaveSize
 *
 * @since 0.17.0
 */
infix fun <T : Collection<*>> Expect<T>.toHaveSize(expected: Int): Expect<T> =
    size { toEqual(expected) }

