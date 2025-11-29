package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.length


/**
 * Creates an [Expect] for the property [CharSequence.length] of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.lengthFeature
 *
 * @since 1.3.0
 */
val <T : CharSequence> Expect<T>.length: Expect<Int>
    get() = _logic.length().transform()

/**
 * Expects that the property [CharSequence.length] of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.length
 *
 * @since 1.3.0
 */
fun <T : CharSequence> Expect<T>.length(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    _logic.length().collectAndAppend(assertionCreator)

