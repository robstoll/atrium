//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium._coreAppend
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.kbox.identity

/**
 * Expects that the subject of `this` expectation (a [Collection]) is an empty [Collection].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CollectionExpectationSamples.toBeEmpty
 *
 * @since 0.17.0
 */
fun <T : Collection<*>> Expect<T>.toBeEmpty(): Expect<T> =
    _coreAppend { isEmpty(::identity) }

/**
 * Expects that the subject of `this` expectation (a [Collection]) is not an empty [Collection].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CollectionExpectationSamples.notToBeEmpty
 */
fun <T : Collection<*>> Expect<T>.notToBeEmpty(): Expect<T> =
    _coreAppend { isNotEmpty(::identity) }

/**
 * Expects that the subject of `this` expectation (a [Collection]) has the given [expected] size.

 * Shortcut for `size.toEqual(expected)`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CollectionExpectationSamples.toHaveSize
 *
 * @since 0.17.0
 */
fun <T : Collection<*>> Expect<T>.toHaveSize(expected: Int): Expect<T> =
    size { toEqual(expected) }
