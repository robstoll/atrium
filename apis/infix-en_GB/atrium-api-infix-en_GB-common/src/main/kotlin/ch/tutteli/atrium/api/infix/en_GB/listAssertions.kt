package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.list.get.builders.ListGetStep
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating._domain

/**
 * Expects that the given [index] is within the bounds of the subject of the assertion (a [List]) and
 * returns an [Expect] for the element at that position.
 *
 * @return The newly created [Expect] for the element at position [index].
 * @throws AssertionError if the given [index] is out of bound.
 */
infix fun <E, T : List<E>> Expect<T>.get(index: Int): Expect<E> =
    _domain.get(index).getExpectOfFeature()

/**
 * Prepares the assertion about the return value of calling [get][List.get] with the given [index].
 *
 * @return A fluent builder to finish the assertion.
 */
infix fun <E, T : List<E>> Expect<T>.get(index: Index): ListGetStep<E, T> =
    ListGetStep.create(this, index.index)
