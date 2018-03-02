package ch.tutteli.atrium.api.cc.infix.en_UK.creating.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.infix.en_UK.atLeast
import ch.tutteli.atrium.api.cc.infix.en_UK.atMost
import ch.tutteli.atrium.api.cc.infix.en_UK.butAtMost
import ch.tutteli.atrium.api.cc.infix.en_UK.exactly
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains.SearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.builders.ButAtMostCheckerBuilderBase

/**
 * Represents the extension point for another option after a `contains at least but at most`-check within
 * a sophisticated `contains` assertion building process for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
interface ButAtMostCheckerBuilder<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>
    : CharSequenceContains.CheckerBuilder<T, S>

/**
 * Represents the builder of the second step of a `contains at least but at most` check within the
 * fluent API of a sophisticated `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied to the input of the search.
 *
 * @constructor Represents the builder of the second step of a `contains at least but at most` check within the
 *   fluent API of a sophisticated `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContains.Builder].
 */
@Deprecated("Do not rely on this type, will be made internal with 1.0.0", ReplaceWith("ButAtMostCheckerBuilder"))
open class ButAtMostCheckerBuilderImpl<out T : CharSequence, out S : SearchBehaviour>(
    times: Int,
    atLeastBuilder: AtLeastCheckerBuilder<T, S>,
    containsBuilder: CharSequenceContains.Builder<T, S>
) : ButAtMostCheckerBuilderBase<T, S>(
    times,
    atLeastBuilder,
    containsBuilder,
    nameContainsNotValuesFun(),
    { l, u -> "`${containsBuilder::atLeast.name} $l ${atLeastBuilder::butAtMost.name} $u`" },
    { "`${containsBuilder::atMost.name} $it`" },
    { "`${containsBuilder::atLeast.name} $it`" },
    { "`${atLeastBuilder::butAtMost.name} $it`" },
    { "`${containsBuilder::exactly.name} $it`" }
), ButAtMostCheckerBuilder<T, S>
