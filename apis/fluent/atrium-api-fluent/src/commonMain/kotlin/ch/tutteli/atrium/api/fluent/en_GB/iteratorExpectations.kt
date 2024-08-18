package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*

/**
 * Expects that the subject of `this` expectation (an [Iterator]) has a next element.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.IteratorExpectationSamples.toHaveNext
 *
 * @since 0.17.0
 */
fun <T : Iterator<*>> Expect<T>.toHaveNext(): Expect<T> =
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
fun <T : Iterator<*>> Expect<T>.notToHaveNext(): Expect<T> =
    _logicAppend { hasNotNext() }

/**
 * Expects that `this` expectation (an [Iterator]) has a next element and
 * returns an [Expect] for the next element.
 *
 * @return The newly created [Expect] for the next element.
 *
 * @sample TODO
 *
 * @since 1.3.0
 */
fun <E, T : Iterator<E>> Expect<T>.next(): Expect<E> =
    _logic.next().transform()

/**
 * Expects that `this` expectation (an [Iterator]) has a next element and that
 * the next element holds all assertions the given [assertionCreator] creates for it.
 *
 * @return an [Expect] for the subject of the next expectation.
 *
 * @sample TODO
 *
 * @since 1.3.0
 */
fun <E, T : Iterator<E>> Expect<T>.next(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.next().collectAndAppend(assertionCreator)
