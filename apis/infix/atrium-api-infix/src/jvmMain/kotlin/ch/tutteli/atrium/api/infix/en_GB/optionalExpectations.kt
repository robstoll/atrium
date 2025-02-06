//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium._coreAppend
import ch.tutteli.atrium.api.infix.en_GB.creating.PresentWithCreator
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.isEmpty
import ch.tutteli.atrium.logic.isPresent
import java.util.*

/**
 * Expects that the subject of `this` expectation (an [Optional]) is empty (not present).
 *
 * Shortcut for more or less something like `feature(Optional<T>::isEmpty) { it toEqual true }`
 * depends on the underlying implementation though.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.12.0
 */
infix fun <T : Optional<*>> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") empty: empty): Expect<T> =
    _coreAppend { isEmpty() }

/**
 * Expects that the subject of `this` expectation (an [Optional]) is present
 * and returns an [Expect] for the inner type [E].
 *
 * Shortcut for more or less something like `feature(Optional<T>::get)` but with error handling; yet it
 * depends on the underlying implementation though.
 *
 * @return the newly created [Expect] for the inner type [E].
 *
 * @since 0.12.0
 */
infix fun <E: Any, T : Optional<E>> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") present: present): FeatureExpect<T, E> =
    _logic.isPresent().transform()

/**
 * Expects that the subject of `this` expectation (an [Optional]) is present and
 * that the wrapped value of type [E] holds all assertions the given [PresentWithCreator.assertionCreator] creates.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.12.0
 */
infix fun <E: Any, T : Optional<E>> Expect<T>.toBe(present: PresentWithCreator<E>): Expect<T> =
    _logic.isPresent().collectAndAppend(present.assertionCreator)
