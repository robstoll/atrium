package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.isEmpty
import ch.tutteli.atrium.logic.isNotEmpty
import ch.tutteli.kbox.identity

/**
 * Expects that the subject of `this` expectation (a [Collection]) is an empty [Collection].
 *
 * @param empty Use the pseudo-keyword `empty`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CollectionExpectationSamples.toBeEmpty
 */
infix fun <T : Collection<*>> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") empty: empty): Expect<T> =
    _logicAppend { isEmpty(::identity) }

/**
 * Expects that the subject of `this` expectation (a [Collection]) is not an empty [Collection].
 *
 * @param empty Use the pseudo-keyword `empty`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CollectionExpectationSamples.notToBeEmpty
 */
infix fun <T : Collection<*>> Expect<T>.notToBe(@Suppress("UNUSED_PARAMETER") empty: empty): Expect<T> =
    _logicAppend { isNotEmpty(::identity) }

/**
 * Expects that the subject of `this` expectation (a [Collection]) has the given [expected] size.
 *
 * Shortcut for `size.toBe(expected)`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CollectionExpectationSamples.toHaveSize
 *
 * @since 0.17.0
 */
infix fun <T : Collection<*>> Expect<T>.toHaveSize(expected: Int): Expect<T> =
    size { toEqual(expected) }

