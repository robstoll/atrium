//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.*
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.*

/**
 * Defines that the search behaviour "find entries `in any order` in the [IterableLike]" shall be applied to this
 * sophisticated `to contain` in [IterableLike] expectation.
 *
 * @return The newly created builder.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
infix fun <E, T : IterableLike> EntryPointStep<E, T, NoOpSearchBehaviour>.inAny(
    @Suppress("UNUSED_PARAMETER") order: order
): EntryPointStep<E, T, InAnyOrderSearchBehaviour> = _logic.inAnyOrder

/**
 * Defines that the constraint "`only` the specified entries exist in the [IterableLike]" shall be applied to this
 * sophisticated `to contain` in [IterableLike] expectation.
 *
 * @return The newly created builder.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
infix fun <E, T : IterableLike> EntryPointStep<E, T, InAnyOrderSearchBehaviour>.but(
    @Suppress("UNUSED_PARAMETER") only: only
): EntryPointStep<E, T, InAnyOrderOnlySearchBehaviour> = _logic.butOnly


/**
 * Defines that the search behaviour "find entries `in order` in the [IterableLike]" shall be applied to this
 * sophisticated `to contain` in [IterableLike] expectation.
 *
 * @return The newly created builder.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
infix fun <E, T : IterableLike> EntryPointStep<E, T, NoOpSearchBehaviour>.inGiven(
    @Suppress("UNUSED_PARAMETER") order: order
): EntryPointStep<E, T, InOrderSearchBehaviour> = _logic.inOrder

/**
 * Defines that the constraint "`only` the specified entries exist in the [IterableLike]" shall be applied to this
 * sophisticated `to contain in order` [IterableLike] expectation.
 *
 * @return The newly created builder.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
infix fun <E, T : IterableLike> EntryPointStep<E, T, InOrderSearchBehaviour>.and(
    @Suppress("UNUSED_PARAMETER") only: only
): EntryPointStep<E, T, InOrderOnlySearchBehaviour> = _logic.andOnly

/**
 * Defines that the [IterableLike] contains `in order only` groups of entries
 * whereas the order within the group is specified as next step.
 *
 * @return The newly created builder.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
infix fun <E, T : IterableLike> EntryPointStep<E, T, InOrderOnlySearchBehaviour>.grouped(
    @Suppress("UNUSED_PARAMETER") entries: entries
): EntryPointStep<E, T, InOrderOnlyGroupedSearchBehaviour> = _logic.grouped

/**
 * A filler word to emphasise that the next step defines the order within expected groups of values.
 *
 * @return The newly created builder.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
infix fun <E, T : IterableLike> EntryPointStep<E, T, InOrderOnlyGroupedSearchBehaviour>.within(
    @Suppress("UNUSED_PARAMETER") group: group
): EntryPointStep<E, T, InOrderOnlyGroupedWithinSearchBehaviour> = _logic.within

