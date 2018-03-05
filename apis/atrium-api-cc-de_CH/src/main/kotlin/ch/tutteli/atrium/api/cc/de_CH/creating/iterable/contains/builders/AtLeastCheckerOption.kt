package ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders

import ch.tutteli.atrium.api.cc.de_CH.zumindest
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.builders.AtLeastCheckerOptionBase
import ch.tutteli.atrium.creating.iterable.contains.builders.WithTimesCheckerOption
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

/**
 * Represents the extension point for another option after a `contains at least`-check within a sophisticated
 * `contains` assertion building process for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
interface AtLeastCheckerOption<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>
    : WithTimesCheckerOption<E, T, S>

/**
 * Represents the builder of a `contains at least` check within the fluent API of a sophisticated
 * `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 *
 * @constructor Represents the builder of a `contains at least` check within the fluent API of a sophisticated
 *   `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 * @param containsBuilder The previously used [IterableContains.Builder].
 */
@Deprecated("Do not rely on this type, will be made internal with 1.0.0", ReplaceWith("AtLeastCheckerBuilder"))
open class AtLeastCheckerOptionImpl<out E, out T : Iterable<E>, out S : InAnyOrderSearchBehaviour>(
    times: Int,
    containsBuilder: IterableContains.Builder<E, T, S>
) : AtLeastCheckerOptionBase<E, T, S>(
    times,
    containsBuilder,
    nameContainsNotValuesFun(),
    { "${containsBuilder::zumindest.name}($it)" }
), AtLeastCheckerOption<E, T, S>
