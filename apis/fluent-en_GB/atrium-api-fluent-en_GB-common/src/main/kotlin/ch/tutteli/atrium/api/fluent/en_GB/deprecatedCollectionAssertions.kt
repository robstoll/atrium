package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.isEmpty
import ch.tutteli.atrium.logic.isNotEmpty
import ch.tutteli.atrium.logic.size
import ch.tutteli.kbox.identity

/**
 * Expects that the subject of `this` expectation (a [Collection]) is an empty [Collection].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CollectionAssertionSamples.isEmpty
 */
@Deprecated("Use toBeEmpty; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBeEmpty<T>()"))
fun <T : Collection<*>> Expect<T>.isEmpty(): Expect<T> =
    _logicAppend { isEmpty(::identity) }

/**
 * Expects that the subject of `this` expectation (a [Collection]) is not an empty [Collection].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CollectionAssertionSamples.isNotEmpty
 */
@Deprecated("Use notToBeEmpty; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToBeEmpty<T>()"))
fun <T : Collection<*>> Expect<T>.isNotEmpty(): Expect<T> =
    _logicAppend { isNotEmpty(::identity) }

/**
 * Expects that the subject of `this` expectation (a [Collection]) has the given [expected] size.
 *
 * Shortcut for `size.toEqual(expected)`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CollectionAssertionSamples.hasSize
 */
@Deprecated("Use toHaveSize; will be removed with 1.0.0 at the latest", ReplaceWith("this.toHaveSize<T>(expected)"))
fun <T : Collection<*>> Expect<T>.hasSize(expected: Int): Expect<T> =
    size { toEqual(expected) }
