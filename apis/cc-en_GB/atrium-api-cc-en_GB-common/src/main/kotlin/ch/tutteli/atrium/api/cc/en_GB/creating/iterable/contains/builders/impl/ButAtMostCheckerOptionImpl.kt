@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders.impl

import ch.tutteli.atrium.api.cc.en_GB.atLeast
import ch.tutteli.atrium.api.cc.en_GB.atMost
import ch.tutteli.atrium.api.cc.en_GB.butAtMost
import ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders.ButAtMostCheckerOption
import ch.tutteli.atrium.api.cc.en_GB.exactly
import ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders.ButAtMostCheckerOptionBase
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

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
internal class ButAtMostCheckerOptionImpl<out E, out T : Iterable<E>, out S : InAnyOrderSearchBehaviour>(
    times: Int,
    atLeastBuilder: AtLeastCheckerOption<E, T, S>,
    containsBuilder: IterableContains.Builder<E, T, S>
) : ButAtMostCheckerOptionBase<E, T, S>(
    times,
    atLeastBuilder,
    containsBuilder,
    nameContainsNotValuesFun(),
    { l, u -> "${containsBuilder::atLeast.name}($l).${atLeastBuilder::butAtMost.name}($u)" },
    { "${containsBuilder::atMost.name}($it)" },
    { "${containsBuilder::atLeast.name}($it)" },
    { "${atLeastBuilder::butAtMost.name}($it)" },
    { "${containsBuilder::exactly.name}($it)" }
), ButAtMostCheckerOption<E, T, S>
