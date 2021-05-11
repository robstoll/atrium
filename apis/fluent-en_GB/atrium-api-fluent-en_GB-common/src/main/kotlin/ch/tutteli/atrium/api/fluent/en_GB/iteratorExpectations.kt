package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.hasNext
import ch.tutteli.atrium.logic.hasNotNext

/**
 * Expects that the subject of `this` expectation (an [Iterator]) has a next element.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.IteratorExpectationSamples.toHaveNext
 *
 * @since 0.17.0
 */
fun <E, T : Iterator<E>> Expect<T>.toHaveNext(): Expect<T> =
    _logicAppend { hasNext() }

/**
 * Expects that the subject of `this` expectation (an [Iterator]) does not have a next element.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.IteratorExpectationSamples.notToHaveNext
 *
 * @since 0.17.0
 */
fun <E, T : Iterator<E>> Expect<T>.notToHaveNext(): Expect<T> =
    _logicAppend { hasNotNext() }
