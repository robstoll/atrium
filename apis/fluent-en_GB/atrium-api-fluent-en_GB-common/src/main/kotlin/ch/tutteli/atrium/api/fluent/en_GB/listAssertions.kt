package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Expects that the given [index] is within the bounds of the subject of the assertion (a [List]) and
 * returns an [Expect] for the element at that position.
 *
 * @return The newly created [Expect] for the element at position [index].
 * @throws AssertionError Might throw an [AssertionError] if the given [index] is out of bound.
 */
fun <E, T : List<E>> Expect<T>.get(index: Int): Expect<E> = AssertImpl.list.get(this, index)

/**
 * Expects that the given [index] is within the bounds of the subject of the assertion (a [List]) and that
 * the element at that position holds all assertions the given [assertionCreator] might create for it.
 * if so, returns an [Expect] for the element at that position.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the given [index] is out of bound.
 */
fun <E : Any, T : List<E>> Expect<T>.get(index: Int, assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    addAssertion(AssertImpl.list.get(this, index, assertionCreator))
