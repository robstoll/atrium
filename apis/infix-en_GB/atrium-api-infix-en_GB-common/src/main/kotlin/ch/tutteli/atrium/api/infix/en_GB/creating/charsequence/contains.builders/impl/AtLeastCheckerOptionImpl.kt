//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.builders.impl

import ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.builders.impl.StaticName.nameContainsNotValuesFun
import ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.AtLeastCheckerOptionBase
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains

/**
 * Represents the builder of a `contains at least`-check within the fluent API of a sophisticated
 * `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the builder of a `contains at least` check within the fluent API of a sophisticated
 *   `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContains.Builder].
 */
internal class AtLeastCheckerOptionImpl<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    times: Int,
    containsBuilder: CharSequenceContains.Builder<T, S>
) : AtLeastCheckerOptionBase<T, S>(
    times,
    containsBuilder,
    nameContainsNotValuesFun,
    { "`atLeast $it`" }
), AtLeastCheckerOption<T, S>
