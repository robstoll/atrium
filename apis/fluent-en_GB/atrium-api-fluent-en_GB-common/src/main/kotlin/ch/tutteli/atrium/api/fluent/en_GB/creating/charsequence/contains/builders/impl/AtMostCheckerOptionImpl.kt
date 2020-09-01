//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB.creating.charsequence.contains.builders.impl

import ch.tutteli.atrium.api.fluent.en_GB.creating.charsequence.contains.builders.AtMostCheckerOption
import ch.tutteli.atrium.api.fluent.en_GB.creating.charsequence.contains.builders.impl.StaticName.nameContainsNotValuesFun
import ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.AtMostCheckerOptionBase
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains

/**
 * Represents the builder of a `contains at least once but at most` check within the fluent API of a
 * sophisticated `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the builder of a `contains at least once but at most` check within the fluent API of a
 *   sophisticated `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContains.Builder].
 */
internal class AtMostCheckerOptionImpl<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    times: Int,
    containsBuilder: CharSequenceContains.Builder<T, S>
) : AtMostCheckerOptionBase<T, S>(
    times,
    containsBuilder,
    nameContainsNotValuesFun,
    { "atMost($it)" },
    { "atLeast($it)" },
    { "exactly($it)" }
), AtMostCheckerOption<T, S>
