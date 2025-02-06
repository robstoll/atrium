//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium._coreAppend
import ch.tutteli.atrium.api.infix.en_GB.creating.KeyWithCreator
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.because

/**
 * Allows to state a reason for one or multiple assertions for the current subject.
 *
 * Use `because of("reason") { ... }` if you want to use it in an infix style.
 *
 * @param reason The explanation for the assertion(s) created by [assertionCreator].
 * @param assertionCreator The group of assertions to make.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.DocumentationUtilSamples.because
 *
 * @since 1.2.0
 */
fun <T> Expect<T>.because(reason: String, assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    _coreAppend { because(reason, assertionCreator) }

/**
 * Allows to state a reason for one or multiple assertions for the current subject.
 *
 * @param keyWithCreator Combines the reason with the assertionCreator-lambda. Use the function
 *   `of(reason) { ... }` to create a [KeyWithCreator].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.DocumentationUtilSamples.becauseOf
 *
 * @since 0.15.0
 */
infix fun <T> Expect<T>.because(keyWithCreator: KeyWithCreator<String, T>): Expect<T> =
    _coreAppend { because(keyWithCreator.key, keyWithCreator.assertionCreator) }

/**
 * Helper function to create a [KeyWithCreator] based on the given [reason] and [assertionCreator].
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.DocumentationUtilSamples.becauseOf
 */
fun <T> of(reason: String, assertionCreator: Expect<T>.() -> Unit): KeyWithCreator<String, T> =
    KeyWithCreator(reason, assertionCreator)
