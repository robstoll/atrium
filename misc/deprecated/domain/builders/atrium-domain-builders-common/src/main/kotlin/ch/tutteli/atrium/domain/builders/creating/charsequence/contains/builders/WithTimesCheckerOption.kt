//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains

/**
 * A [CharSequenceContains.CheckerOption] which is used to choose
 * a [CharSequenceContains.Checker] which is based on a number of [times].
 */
interface WithTimesCheckerOption<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>
    : CharSequenceContains.CheckerOption<T, S> {
    val times: Int
}
