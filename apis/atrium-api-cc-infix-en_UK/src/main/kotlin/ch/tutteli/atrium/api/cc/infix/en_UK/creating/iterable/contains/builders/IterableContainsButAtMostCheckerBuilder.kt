package ch.tutteli.atrium.api.cc.infix.en_UK.creating.iterable.contains.builders

import ch.tutteli.atrium.api.cc.infix.en_UK.atLeast
import ch.tutteli.atrium.api.cc.infix.en_UK.atMost
import ch.tutteli.atrium.api.cc.infix.en_UK.butAtMost
import ch.tutteli.atrium.api.cc.infix.en_UK.exactly
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsButAtMostCheckerBuilderBase
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsWithTimesCheckerBuilder
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour

/**
 * Represents the extension point for another option after a `contains at least but at most`-check within a
 * sophisticated `contains` assertion building process for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
interface ButAtMostCheckerBuilder<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>
    : IterableContainsWithTimesCheckerBuilder<E, T, S>

/**
 * Represents the builder of the second step of a `contains at least but at most` check within the
 * fluent API of a sophisticated `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 *
 * @constructor Represents the builder of the second step of a `contains at least but at most` check within the
 *   fluent API of a sophisticated `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 * @param containsBuilder The previously used [IterableContains.Builder].
 */
@Deprecated("Do not rely on this type, will be made internal with 1.0.0", ReplaceWith("ButAtMostCheckerBuilder"))
open class IterableContainsButAtMostCheckerBuilder<out E, out T : Iterable<E>, out S : IterableContainsInAnyOrderSearchBehaviour>(
    times: Int,
    atLeastBuilder: AtLeastCheckerBuilder<E, T, S>,
    containsBuilder: IterableContains.Builder<E, T, S>
) : IterableContainsButAtMostCheckerBuilderBase<E, T, S>(
    times,
    atLeastBuilder,
    containsBuilder,
    nameContainsNotValuesFun(),
    { l, u -> "`${containsBuilder::atLeast.name} $l ${atLeastBuilder::butAtMost.name} $u`" },
    { "`${containsBuilder::atMost.name} $it`" },
    { "`${containsBuilder::atLeast.name} $it`" },
    { "`${atLeastBuilder::butAtMost.name} $it`" },
    { "`${containsBuilder::exactly.name} $it`" }
), ButAtMostCheckerBuilder<E, T, S>
