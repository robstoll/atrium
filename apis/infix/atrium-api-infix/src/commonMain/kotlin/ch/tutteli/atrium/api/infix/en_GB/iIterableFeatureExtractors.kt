//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.last
import ch.tutteli.atrium.logic.max
import ch.tutteli.atrium.logic.min
import ch.tutteli.kbox.identity

/**
 * Creates an [Expect] for the result of calling `min()` on the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @param o The filler object [o].
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableFeatureExtractorSamples.minFeature
 *
 * @since 0.12.0
 */
infix fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.min(@Suppress("UNUSED_PARAMETER") o: o): Expect<E> =
    _logic.min(::identity).transform()

/**
 * Expects that the result of calling `min()` on the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableFeatureExtractorSamples.min
 *
 * @since 0.12.0
 */
infix fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.min(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.min(::identity).collectAndAppend(assertionCreator)


/**
 * Creates an [Expect] for the result of calling `max()` on the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @param o The filler object [o].
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableFeatureExtractorSamples.maxFeature
 *
 * @since 0.12.0
 */
infix fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.max(@Suppress("UNUSED_PARAMETER") o: o): Expect<E> =
    _logic.max(::identity).transform()

/**
 * Expects that the result of calling `max()` on  the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableFeatureExtractorSamples.max
 *
 * @since 0.12.0
 */
infix fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.max(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.max(::identity).collectAndAppend(assertionCreator)

/**
 * Expects that Iterable is not empty and returns an [Expect] for the last element in it.
 *
 * @param o The filler object [o].
 *
 * @return The newly created [Expect] for last element.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableFeatureExtractorSamples.lastFeature
 *
 * @since 1.2.0
 */
infix fun <E : Comparable<E>, T: Iterable<E>> Expect<T>.last(@Suppress("UNUSED_PARAMETER") o: o): Expect<E> =
    _logic.last(::identity).transform()

/**
 * Expects that the subject of `this` expectation (an [Iterable]) is not empty and that the last
 * element in it holds all assertions the given [assertionCreator] creates for it.
 *
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableFeatureExtractorSamples.last
 *
 * @since 1.2.0
 */
infix fun <E : Comparable<E>, T : Iterable<E>> Expect<T>.last(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.last(::identity).collectAndAppend(assertionCreator)
