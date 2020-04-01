package ch.tutteli.atrium.api.infix.en_GB.creating.iterable.contains.builders.impl

import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders.NotCheckerOptionBase
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

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
internal class NotCheckerOptionImpl<out E, out T : Iterable<E>, out S : InAnyOrderSearchBehaviour>(
    containsBuilder: IterableContains.Builder<E, T, S>
) : NotCheckerOptionBase<E, T, S>(containsBuilder),
    NotCheckerOption<E, T, S>
