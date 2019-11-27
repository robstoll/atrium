@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.impl

import ch.tutteli.atrium.api.cc.de_CH.aberHoechstens
import ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.ButAtMostCheckerOption
import ch.tutteli.atrium.api.cc.de_CH.genau
import ch.tutteli.atrium.api.cc.de_CH.hoechstens
import ch.tutteli.atrium.api.cc.de_CH.zumindest
import ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.ButAtMostCheckerOptionBase
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains

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
@Deprecated("Do not rely on this type; will be made internal with 1.0.0", ReplaceWith("ButAtMostCheckerBuilder"))
open class ButAtMostCheckerOptionImpl<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    times: Int,
    atLeastBuilder: AtLeastCheckerOption<T, S>,
    containsBuilder: CharSequenceContains.Builder<T, S>
) : ButAtMostCheckerOptionBase<T, S>(
    times,
    atLeastBuilder,
    containsBuilder,
    nameContainsNotValuesFun(),
    { l, u -> "${containsBuilder::zumindest.name}($l).${atLeastBuilder::aberHoechstens.name}($u)" },
    { "${containsBuilder::hoechstens.name}($it)" },
    { "${containsBuilder::zumindest.name}($it)" },
    { "${atLeastBuilder::aberHoechstens.name}($it)" },
    { "${containsBuilder::genau.name}($it)" }
), ButAtMostCheckerOption<T, S>
