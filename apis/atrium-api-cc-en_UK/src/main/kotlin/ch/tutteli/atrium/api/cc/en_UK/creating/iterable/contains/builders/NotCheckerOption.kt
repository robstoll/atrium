package ch.tutteli.atrium.api.cc.en_UK.creating.iterable.contains.builders

import ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders.NotCheckerOptionBase
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

/**
 * Represents the extension point for another option after a `contains not at all`-check within
 * a sophisticated `contains` assertion building process for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders.NotCheckerOption"))
interface NotCheckerOption<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>
    : IterableContains.CheckerOption<E, T, S>

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
@Deprecated("Do not rely on this type, will be made internal with 1.0.0", ReplaceWith("NotCheckerOption"))
open class NotCheckerOptionImpl<out E, out T : Iterable<E>, out S : InAnyOrderSearchBehaviour>(
    containsBuilder: IterableContains.Builder<E, T, S>
) : NotCheckerOptionBase<E, T, S>(containsBuilder), NotCheckerOption<E, T, S>

