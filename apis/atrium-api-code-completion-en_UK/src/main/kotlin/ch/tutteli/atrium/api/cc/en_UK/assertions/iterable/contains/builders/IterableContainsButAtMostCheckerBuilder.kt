package ch.tutteli.atrium.api.cc.en_UK.assertions.iterable.contains.builders

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsButAtMostCheckerBuilderBase
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour

/**
 * Represents the builder of the second step of a `contains at least but at most` check within the
 * fluent API of a sophisticated `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 *
 * @constructor Represents the builder of the second step of a `contains at least but at most` check within the
 *              fluent API of a sophisticated `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *              found in the [Iterable].
 * @param containsBuilder The previously used [IterableContainsBuilder].
 */
open class IterableContainsButAtMostCheckerBuilder<E, T : Iterable<E>>(
    times: Int,
    atLeastBuilder: IterableContainsAtLeastCheckerBuilder<E, T>,
    containsBuilder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>
) : IterableContainsButAtMostCheckerBuilderBase<E, T, IterableContainsInAnyOrderSearchBehaviour>(
    times,
    atLeastBuilder,
    containsBuilder,
    containsBuilder.plant::containsNot.name,
    { l, u -> "${containsBuilder::atLeast.name}($l).${atLeastBuilder::butAtMost.name}($u)" },
    { "${containsBuilder::atMost.name}($it)" },
    { "${containsBuilder::atLeast.name}($it)" },
    { "${atLeastBuilder::butAtMost.name}($it)" },
    { "${containsBuilder::exactly.name}($it)" }
)
