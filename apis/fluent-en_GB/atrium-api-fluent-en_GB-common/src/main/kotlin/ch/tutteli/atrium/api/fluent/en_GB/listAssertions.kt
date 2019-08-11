package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl

/**
 * Expects that the given [index] is within the bounds of the subject of the assertion (a [List]) and
 * returns an [Expect] for the element at that position.
 *
 * @return The newly created [Expect] for the element at position [index].
 * @throws AssertionError Might throw an [AssertionError] if the given [index] is out of bound.
 */
fun <E, T : List<E>> Expect<T>.get(index: Int): Expect<E> = ExpectImpl.list.get(this, index).getExpectOfFeature()

/**
 * Expects that the given [index] is within the bounds of the subject of the assertion (a [List]) and that
 * the element at that position holds all assertions the given [assertionCreator] creates for it.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the given [index] is out of bound.
 */
fun <E, T : List<E>> Expect<T>.get(index: Int, assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    ExpectImpl.list.get(this, index).addToInitial(assertionCreator)
