package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl

/**
 * Expects that the subject of the assertion (a [Collection]) is an empty [Collection].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Collection<*>> Expect<T>.isEmpty() = addAssertion(ExpectImpl.collection.isEmpty(this))

/**
 * Expects that the subject of the assertion (a [Collection]) is not an empty [Collection].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Collection<*>> Expect<T>.isNotEmpty() = addAssertion(ExpectImpl.collection.isNotEmpty(this))

/**
 * Expects that the subject of the assertion (a [Collection]) has the given [expected] size.
 *
 * Shortcut for `size.toBe(expected)`.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Collection<*>> Expect<T>.hasSize(expected: Int) = size { toBe(expected) }

/**
 * Creates an [Expect] for the property [Collection.size] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 */
val <T : Collection<*>> Expect<T>.size: Expect<Int>
    get() = ExpectImpl.collection.size(this).getExpectOfFeature()

/**
 * Expects that the property [Collection.size] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Collection<E>> Expect<T>.size(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    ExpectImpl.collection.size(this).addToInitial(assertionCreator)
