package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.last

/**
 * Expects that the subject of `this` expectation (a [List]) is not empty and that the last element
 * in the list holds all assertions the given [IndexWithCreator.assertionCreator] creates for it.
 *
 *  Use the function `last() { ... }` to create an [IndexWithCreator].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.ListFeatureExtractorSamples.last
 *
 * @since 1.2.0
 */
fun <E, T : List<E>> Expect<T>.last(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.last().collectAndAppend(assertionCreator)
