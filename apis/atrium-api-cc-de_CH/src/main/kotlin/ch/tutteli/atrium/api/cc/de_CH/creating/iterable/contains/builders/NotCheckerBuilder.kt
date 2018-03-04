package ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders

import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.builders.NotCheckerBuilderBase
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

/**
 * Represents the extension point for another option after a `contains not at all`-check within
 * a sophisticated `contains` assertion building process for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
interface NotCheckerBuilder<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>
    : IterableContains.CheckerBuilder<E, T, S>

/**
 * Represents the builder of a `contains not at all` check within the fluent API of a sophisticated
 * `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the builder of a `contains not at all` check within the fluent API of a sophisticated
 *   `contains not` assertion for [Iterable].
 * @param containsBuilder The previously used [IterableContains.Builder].
 */
@Deprecated("Do not rely on this type, will be made internal with 1.0.0", ReplaceWith("NotCheckerBuilder"))
open class NotCheckerBuilderImpl<out E, out T : Iterable<E>, out S : InAnyOrderSearchBehaviour>(
    containsBuilder: IterableContains.Builder<E, T, S>
) : NotCheckerBuilderBase<E, T, S>(containsBuilder), NotCheckerBuilder<E, T, S>

