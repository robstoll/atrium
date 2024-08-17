package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.next

/**
 * Expects that `this` expectation (a [Iterator]) has more elements and
 * returns an [Expect] for the next element.
 *
 * @return The newly created [Expect] for the next element.
 *
 * @sample TODO
 */
fun <E, T : Iterator<E>> Expect<T>.next(): Expect<E> =
    _logic.next().transform()

/**
 * Expects that `this` expectation (a [Iterator]) has more elements and that
 * the next element holds all assertions the given [assertionCreator] creates for it.
 *
 * @return an [Expect] for the subject of the next expectation. // TODO Confirm: is this true?
 *
 * @sample TODO
 */
fun <E, T : Iterator<E>> Expect<T>.next(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.next().collectAndAppend(assertionCreator)

