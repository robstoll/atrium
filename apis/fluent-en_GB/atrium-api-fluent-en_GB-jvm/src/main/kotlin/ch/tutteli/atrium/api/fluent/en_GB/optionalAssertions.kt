@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.isEmpty
import ch.tutteli.atrium.logic.isPresent
import java.util.*

/**
 * Expects that the subject of `this` expectation (an [Optional]) is empty (not present).
 *
 * Shortcut for more or less something like `feature(Optional<T>::isEmpty) { toEqual(true) }`
 * depends on the underlying implementation though.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.9.0
 */
@Deprecated("Use toBeEmpty; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBeEmpty<T>()"))
fun <T : Optional<*>> Expect<T>.isEmpty(): Expect<T> =
    _logicAppend { isEmpty() }

/**
 * Expects that the subject of `this` expectation (an [Optional]) is present
 * and returns an [Expect] for the inner type [E].
 *
 * Shortcut for more or less something like `feature(Optional<T>::get)` but with error handling; yet it
 * depends on the underlying implementation though.
 *
 * @return The newly created [Expect] for the inner type [E].
 *
 * @since 0.9.0
 */
@Deprecated("Use toBeEmpty; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBePresent<T>()"))
fun <E, T : Optional<E>> Expect<T>.isPresent(): Expect<E> =
    _logic.isPresent().transform()

/**
 * Expects that the subject of `this` expectation (an [Optional]) is present and
 * that it holds all assertions the given [assertionCreator] creates.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use toBeEmpty; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBePresent<T>(assertionCreator)")
)
fun <E, T : Optional<E>> Expect<T>.isPresent(assertionCreator: Expect<E>.() -> Unit): Expect<T> =
    _logic.isPresent().collectAndAppend(assertionCreator)
