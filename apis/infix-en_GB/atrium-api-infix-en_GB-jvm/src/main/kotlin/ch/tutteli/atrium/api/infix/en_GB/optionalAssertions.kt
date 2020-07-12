@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.PresentWithCreator
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.isEmpty
import ch.tutteli.atrium.logic.isPresent
import java.util.*

/**
 * Expects that the subject of the assertion (an [Optional]) is empty (not present).
 *
 * Shortcut for more or less something like `feature(Optional<T>::isEmpty) { toBe(true) }`
 * depends on the underlying implementation though.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : Optional<*>> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") empty: empty): Expect<T> =
    _logicAppend { isEmpty() }

/**
 * Expects that the subject of the assertion (an [Optional]) is present
 * and returns an [Expect] for the inner type [E].
 *
 * Shortcut for more or less something like `feature(Optional<T>::get)` but with error handling; yet it
 * depends on the underlying implementation though.
 *
 * @return The newly created [Expect] for the inner type [E].
 * @throws AssertionError Might throw an [AssertionError] if the given assertion is not a success.
 *
 * @since 0.12.0
 */
infix fun <E, T : Optional<E>> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") present: present): FeatureExpect<T, E> =
    _logic.isPresent().getExpectOfFeature()

/**
 * Expects that the subject of the assertion (an [Optional]) is present and
 * that it holds all assertions the given [PresentWithCreator.assertionCreator] creates.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the given assertions are not success.
 *
 * @since 0.12.0
 */
infix fun <E, T : Optional<E>> Expect<T>.toBe(present: PresentWithCreator<E>): Expect<T> =
    _logic.isPresent().addToInitial(present.assertionCreator)
