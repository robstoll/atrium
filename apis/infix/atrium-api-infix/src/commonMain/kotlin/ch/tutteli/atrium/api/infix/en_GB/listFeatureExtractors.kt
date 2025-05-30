//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.IndexWithCreator
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.get

/**
 * Expects that the given [index] is within the bounds of the subject of `this` expectation (a [List]) and
 * returns an [Expect] for the element at that position.
 *
 * @return The newly created [Expect] for the element at position [index].
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ListFeatureExtractorSamples.getFeature
 */
infix fun <E, T : List<E>> Expect<T>.get(index: Int): Expect<E> =
    _logic.get(index).transform()

/**
 * Expects that the given [index][IndexWithCreator.index] is within the bounds of the subject of `this` expectation
 * (a [List]) and that the element at that position holds all assertions the given
 * [IndexWithCreator.assertionCreator] creates for it.
 *
 *  Use the function `index(Int) { ... }` to create an [IndexWithCreator].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ListFeatureExtractorSamples.get
 */
infix fun <E, T : List<E>> Expect<T>.get(index: IndexWithCreator<E>): Expect<T> =
    _logic.get(index.index).collectAndAppend(index.assertionCreator)

/**
 * Helper function to create an [IndexWithCreator] based on the given [index] and [assertionCreator].
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ListFeatureExtractorSamples.get
 */
fun <E> index(index: Int, assertionCreator: Expect<E>.() -> Unit): IndexWithCreator<E> =
    IndexWithCreator(index, assertionCreator)
