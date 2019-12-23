package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating._domain

/**
 * Expects that the subject of the assertion (a [Collection]) is an empty [Collection].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Collection<*>> Expect<T>.isEmpty(): Expect<T> =
    addAssertion(_domain.isEmpty())

/**
 * Expects that the subject of the assertion (a [Collection]) is not an empty [Collection].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Collection<*>> Expect<T>.isNotEmpty(): Expect<T> =
    addAssertion(_domain.isNotEmpty())

/**
 * Expects that the subject of the assertion (a [Collection]) has the given [expected] size.
 *
 * Shortcut for `expected.toBe(expectedSize)`.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Collection<*>> Expect<T>.hasSize(expected: Int): Expect<T> =
    size { toBe(expected) }

/**
 * Creates an [Expect] for the property [Collection.size] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 */
val <T : Collection<*>> Expect<T>.size: Expect<Int>
    get() = _domain.size.getExpectOfFeature()

/**
 * Expects that the property [Collection.size] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Collection<E>> Expect<T>.size(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    _domain.size.addToInitial(assertionCreator)
