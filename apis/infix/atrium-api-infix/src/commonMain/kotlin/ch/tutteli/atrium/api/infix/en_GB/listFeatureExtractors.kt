package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.IndexWithCreator
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.get
import ch.tutteli.atrium.logic.last

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

//TODO 1.2.0 move to iterableExtractors
/**
 * Expects that list is not empty and returns an [Expect] for the last element in list.
 *
 * @param o The filler object [o].
 *
 * @return The newly created [Expect] for last element.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ListFeatureExtractorSamples.lastFeature
 *
 * @since 1.2.0
 */
infix fun <E, T : List<E>> Expect<T>.last(@Suppress("UNUSED_PARAMETER") o: o): Expect<E> =
    _logic.last().transform()

/**
 * Expects that the subject of `this` expectation (a [List]) is not empty and that the last element
 * in the list holds all assertions the given [assertionCreator] creates for it.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.ListFeatureExtractorSamples.last
 *
 * @since 1.2.0
 */
infix fun <E, T : List<E>> Expect<T>.last(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.last().collectAndAppend(assertionCreator)
