package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.KeyWithCreator
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.because

/**
 * Allows to state a reason for one or multiple assertions for the current subject.
 *
 * @param keyWithCreator Combines the reason with the assertionCreator-lambda. Use the function
 *   `of(reason) { ... }` to create a [KeyWithCreator].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.AnyExpectationSamples.becauseOf
 *
 * @since 0.15.0
 */
infix fun <T> Expect<T>.because(keyWithCreator: KeyWithCreator<String, T>): Expect<T> =
    _logicAppend { because(keyWithCreator.key, keyWithCreator.assertionCreator) }

/**
 * Helper function to create a [KeyWithCreator] based on the given [reason] and [assertionCreator].
 */
fun <T> of(reason: String, assertionCreator: Expect<T>.() -> Unit): KeyWithCreator<String, T> =
    KeyWithCreator(reason, assertionCreator)
