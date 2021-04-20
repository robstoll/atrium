package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.kbox.identity

//TODO move to collectionExpectations.kt with 0.18.0
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

//TODO move to collectionExpectations.kt with 0.18.0
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
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CollectionAssertionSamples.hasSize
 */
infix fun <T : Collection<*>> Expect<T>.hasSize(expected: Int): Expect<T> =
    size { toEqual(expected) }


//TODO move to collectionExpectations.kt with 0.18.0
/**
 * Creates an [Expect] for the property [Collection.size] of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CollectionExpectationSamples.sizeFeature
 */
val <T : Collection<*>> Expect<T>.size: Expect<Int>
    get() = _logic.size(::identity).transform()

//TODO move to collectionExpectations.kt with 0.18.0
/**
 * Expects that the property [Collection.size] of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CollectionExpectationSamples.size
 */
infix fun <E, T : Collection<E>> Expect<T>.size(assertionCreator: Expect<Int>.() -> Unit): Expect<T> =
    _logic.size(::identity).collectAndAppend(assertionCreator)
