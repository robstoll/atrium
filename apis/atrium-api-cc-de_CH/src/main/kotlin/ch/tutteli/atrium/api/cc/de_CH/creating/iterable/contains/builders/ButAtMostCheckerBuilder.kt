package ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders

import ch.tutteli.atrium.api.cc.de_CH.aberHoechstens
import ch.tutteli.atrium.api.cc.de_CH.genau
import ch.tutteli.atrium.api.cc.de_CH.hoechstens
import ch.tutteli.atrium.api.cc.de_CH.zumindest
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.builders.ButAtMostCheckerBuilderBase
import ch.tutteli.atrium.creating.iterable.contains.builders.WithTimesCheckerBuilder
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

/**
 * Represents the extension point for another option after a `contains at least but at most`-check within a
 * sophisticated `contains` assertion building process for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
interface ButAtMostCheckerBuilder<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>
    : WithTimesCheckerBuilder<E, T, S>

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
open class ButAtMostCheckerBuilderImpl<out E, out T : Iterable<E>, out S : InAnyOrderSearchBehaviour>(
    times: Int,
    atLeastBuilder: AtLeastCheckerBuilder<E, T, S>,
    containsBuilder: IterableContains.Builder<E, T, S>
) : ButAtMostCheckerBuilderBase<E, T, S>(
    times,
    atLeastBuilder,
    containsBuilder,
    nameContainsNotValuesFun(),
    { l, u -> "${containsBuilder::zumindest.name}($l).${atLeastBuilder::aberHoechstens.name}($u)" },
    { "${containsBuilder::hoechstens.name}($it)" },
    { "${containsBuilder::zumindest.name}($it)" },
    { "${atLeastBuilder::aberHoechstens.name}($it)" },
    { "${containsBuilder::genau.name}($it)" }
), ButAtMostCheckerBuilder<E, T, S>
