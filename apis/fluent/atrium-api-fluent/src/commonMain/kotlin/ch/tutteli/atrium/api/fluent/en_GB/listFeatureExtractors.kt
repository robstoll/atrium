package ch.tutteli.atrium.api.fluent.en_GB

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
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ListFeatureExtractorSamples.getFeature
 */
fun <E, T : List<E>> Expect<T>.get(index: Int): Expect<E> =
    _logic.get(index).transform()

/**
 * Expects that the given [index] is within the bounds of the subject of `this` expectation (a [List]) and that
 * the element at that position holds all assertions the given [assertionCreator] creates for it.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ListFeatureExtractorSamples.get
 */
fun <E, T : List<E>> Expect<T>.get(index: Int, assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.get(index).collectAndAppend(assertionCreator)

/**
 * Expects that list is not empty and returns an [Expect] for the last element in list.
 *
 * @return The newly created [Expect] for last element.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ListFeatureExtractorSamples.last
 *
 * @since 1.2.0
 */
val <E, T : List<E>> Expect<T>.last: Expect<E>
    get() = _logic.last().transform()

