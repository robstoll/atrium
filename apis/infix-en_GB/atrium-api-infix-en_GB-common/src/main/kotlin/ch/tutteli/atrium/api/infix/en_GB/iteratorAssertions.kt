package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.hasNext
import ch.tutteli.atrium.logic.hasNotNext

/**
 * Expects that the subject of `this` expectation (an [Iterator]) has at least one element.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.IteratorAssertionSamples.has
 *
 * @since 0.13.0
 */
infix fun <E, T : Iterator<E>> Expect<T>.has(@Suppress("UNUSED_PARAMETER") next: next): Expect<T> =
    _logicAppend { hasNext() }

/**
 * Expects that the subject of `this` expectation (an [Iterator]) does not have next element.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.IteratorAssertionSamples.hasNot
 *
 * @since 0.13.0
 */
infix fun <E, T : Iterator<E>> Expect<T>.hasNot(@Suppress("UNUSED_PARAMETER") next: next): Expect<T> =
    _logicAppend { hasNotNext() }
