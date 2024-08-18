package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*

/**
 * Expects that the subject of `this` expectation (an [Iterator]) has a next element.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IteratorExpectationSamples.toHave_next
 *
 * @since 0.17.0
 */
infix fun <T : Iterator<*>> Expect<T>.toHave(@Suppress("UNUSED_PARAMETER") next: next): Expect<T> =
    _logicAppend { hasNext() }

/**
 * Expects that the subject of `this` expectation (an [Iterator]) does not have next element.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IteratorExpectationSamples.notToHave_next
 *
 * @since 0.17.0
 */
infix fun <T : Iterator<*>> Expect<T>.notToHave(@Suppress("UNUSED_PARAMETER") next: next): Expect<T> =
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
infix fun <E, T : Iterator<E>> Expect<T>.next(@Suppress("UNUSED_PARAMETER") next: next): Expect<E> =
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
infix fun <E, T : Iterator<E>> Expect<T>.next(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.next().collectAndAppend(assertionCreator)
