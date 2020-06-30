package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.IndexWithCreator
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl

/**
 * Expects that the given [index] is within the bounds of the subject of the assertion (a [List]) and
 * returns an [Expect] for the element at that position.
 *
 * @return The newly created [Expect] for the element at position [index].
 * @throws AssertionError Might throw an [AssertionError] if the given [index] is out of bound.
 */
infix fun <E, T : List<E>> Expect<T>.get(index: Int): Expect<E> =
    ExpectImpl.list.get(this, index).getExpectOfFeature()

/**
 * Expects that the given [index][IndexWithCreator.index] is within the bounds of the subject of the assertion
 * (a [List]) and that the element at that position holds all assertions the given
 * [IndexWithCreator.assertionCreator] creates for it.
 *
 *  Use the function `index(Int) { ... }` to create an [IndexWithCreator].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the given [index] is out of bound or
 *   if the assertion made is not correct.
 */
infix fun <E, T : List<E>> Expect<T>.get(index: IndexWithCreator<E>): Expect<T> =
    ExpectImpl.list.get(this, index.index).addToInitial(index.assertionCreator)

/**
 * Helper function to create an [IndexWithCreator] based on the given [index] and [assertionCreator].
 */
fun <E> index(index: Int, assertionCreator: Expect<E>.() -> Unit): IndexWithCreator<E> =
    IndexWithCreator(index, assertionCreator)
