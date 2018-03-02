package ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders

import ch.tutteli.atrium.api.cc.de_CH.genau
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.builders.ExactlyCheckerBuilderBase
import ch.tutteli.atrium.creating.iterable.contains.builders.WithTimesCheckerBuilder
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour

/**
 * Represents the extension point for another option after a `contains exactly`-check within
 * a sophisticated `contains` assertion building process for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
interface ExactlyCheckerBuilder<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>
    : WithTimesCheckerBuilder<E, T, S>

/**
 * Represents the builder of a `contains exactly` check within the fluent API of a sophisticated
 * `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 *
 * @constructor Represents the builder of a `contains exactly` check within the fluent API of a sophisticated
 *   `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 * @param containsBuilder The previously used [IterableContains.Builder].
 */
@Deprecated("Do not rely on this type, will be made internal with 1.0.0", ReplaceWith("ExactlyCheckerBuilder"))
open class ExactlyCheckerBuilderImpl<out E, out T : Iterable<E>, out S : IterableContainsInAnyOrderSearchBehaviour>(
    times: Int,
    containsBuilder: IterableContains.Builder<E, T, S>
) : ExactlyCheckerBuilderBase<E, T, S>(
    times,
    containsBuilder,
    nameContainsNotValuesFun(),
    { "${containsBuilder::genau.name}($it)" }
), ExactlyCheckerBuilder<E, T, S>
