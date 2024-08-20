package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.next

/**
 * Expects that `this` expectation (an [Iterator]) has a next element and
 * returns an [Expect] for the next element.
 *
 * @return The newly created [Expect] for the next element.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IteratorFeatureExtractorSamples.nextFeature
 *
 * @since 1.3.0
 */
infix fun <E, T : Iterator<E>> Expect<T>.next(@Suppress("UNUSED_PARAMETER") o: o): Expect<E> =
    _logic.next().transform()

/**
 * Expects that `this` expectation (an [Iterator]) has a next element and that
 * the next element holds all assertions the given [assertionCreator] creates for it.
 *
 * @return an [Expect] for the subject of the next expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IteratorFeatureExtractorSamples.next
 *
 * @since 1.3.0
 */
infix fun <E, T : Iterator<E>> Expect<T>.next(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.next().collectAndAppend(assertionCreator)
