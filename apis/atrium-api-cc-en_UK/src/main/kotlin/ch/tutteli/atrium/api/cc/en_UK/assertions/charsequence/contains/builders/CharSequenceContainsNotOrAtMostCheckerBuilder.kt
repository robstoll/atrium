package ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.en_UK.notOrAtMost
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains.SearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsNotOrAtMostCheckerBuilderBase

/**
 * Represents the builder of a `contains not or at most` check within the fluent API of a
 * sophisticated `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the builder of a `contains not or at most` check within the fluent API of a
 *   sophisticated `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContainsBuilder].
 */
open class CharSequenceContainsNotOrAtMostCheckerBuilder<out T : CharSequence, out S : SearchBehaviour>(
    times: Int,
    containsBuilder: CharSequenceContainsBuilder<T, S>
) : CharSequenceContainsNotOrAtMostCheckerBuilderBase<T, S>(
    times,
    containsBuilder,
    nameContainsNotValuesFun(),
    { "${containsBuilder::notOrAtMost.name}($it)" }
)
