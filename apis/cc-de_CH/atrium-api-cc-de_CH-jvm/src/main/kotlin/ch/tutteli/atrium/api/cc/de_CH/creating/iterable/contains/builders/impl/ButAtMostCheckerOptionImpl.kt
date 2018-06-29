package ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.impl

import ch.tutteli.atrium.api.cc.de_CH.aberHoechstens
import ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.ButAtMostCheckerOption
import ch.tutteli.atrium.api.cc.de_CH.genau
import ch.tutteli.atrium.api.cc.de_CH.hoechstens
import ch.tutteli.atrium.api.cc.de_CH.zumindest
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
@Deprecated("Do not rely on this type; will be made internal with 1.0.0", ReplaceWith("ButAtMostCheckerBuilder"))
open class ButAtMostCheckerOptionImpl<out E, out T : Iterable<E>, out S : InAnyOrderSearchBehaviour>(
    times: Int,
    atLeastBuilder: AtLeastCheckerOption<E, T, S>,
    containsBuilder: IterableContains.Builder<E, T, S>
) : ButAtMostCheckerOptionBase<E, T, S>(
    times,
    atLeastBuilder,
    containsBuilder,
    nameContainsNotValuesFun(),
    { l, u -> "${containsBuilder::zumindest.name}($l).${atLeastBuilder::aberHoechstens.name}($u)" },
    { "${containsBuilder::hoechstens.name}($it)" },
    { "${containsBuilder::zumindest.name}($it)" },
    { "${atLeastBuilder::aberHoechstens.name}($it)" },
    { "${containsBuilder::genau.name}($it)" }
), ButAtMostCheckerOption<E, T, S>
