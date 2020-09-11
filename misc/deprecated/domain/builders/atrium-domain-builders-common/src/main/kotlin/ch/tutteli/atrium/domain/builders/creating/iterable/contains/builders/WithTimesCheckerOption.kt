//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders

import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains

/**
 * A [IterableContains.CheckerOption] which is used to choose
 * a [IterableContains.Checker] which is based on a number of [times].
 */
@Deprecated("Use WithTimesCheckerStep from atrium-logic; will be removed with 1.0.0")
interface WithTimesCheckerOption<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>
    : IterableContains.CheckerOption<E, T, S> {
    val times: Int
}
