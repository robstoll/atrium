package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains.Builder
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*

/**
 * Defines that the search behaviour "find entries `in any order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> Builder<E, T, NoOpSearchBehaviour>.inAny(@Suppress("UNUSED_PARAMETER") order: order) =
    ExpectImpl.iterable.contains.searchBehaviours.inAnyOrder(this)

/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> Builder<E, T, InAnyOrderSearchBehaviour>.but(@Suppress("UNUSED_PARAMETER") only: only) =
    ExpectImpl.iterable.contains.searchBehaviours.inAnyOrderOnly(this)


/**
 * Defines that the search behaviour "find entries `in order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> Builder<E, T, NoOpSearchBehaviour>.inGiven(@Suppress("UNUSED_PARAMETER") order: order) =
    ExpectImpl.iterable.contains.searchBehaviours.inOrder(this)

/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains in order` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> Builder<E, T, InOrderSearchBehaviour>.and(@Suppress("UNUSED_PARAMETER") only: only) =
    ExpectImpl.iterable.contains.searchBehaviours.inOrderOnly(this)

/**
 * Defines that the [Iterable] contains `in order only` groups of entries
 * whereas the order within the group is specified as next step.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> Builder<E, T, InOrderOnlySearchBehaviour>.grouped(@Suppress("UNUSED_PARAMETER") entries: entries) =
    ExpectImpl.iterable.contains.searchBehaviours.inOrderOnlyGrouped(this)

/**
 * A filler word to emphasis that the next step defines the order within expected groups of values.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> Builder<E, T, InOrderOnlyGroupedSearchBehaviour>.within(@Suppress("UNUSED_PARAMETER") group: group) =
    ExpectImpl.iterable.contains.searchBehaviours.inOrderOnlyGroupedWithin(this)

