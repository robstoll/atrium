@file:Suppress("DEPRECATION" /* will be removed with 0.10.0 */)
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.entries
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.group
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.only
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*

/**
 * Defines that the search behaviour "find entries `in any order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @param order Has to be `order`.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, NoOpSearchBehaviour>.inAny(@Suppress("UNUSED_PARAMETER") order: order)
    = AssertImpl.iterable.contains.searchBehaviours.inAnyOrder(this)

/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>.but(@Suppress("UNUSED_PARAMETER") only: only)
    = AssertImpl.iterable.contains.searchBehaviours.inAnyOrderOnly(this)


/**
 * Defines that the search behaviour "find entries `in order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @param order Has to be `order`.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, NoOpSearchBehaviour>.inGiven(@Suppress("UNUSED_PARAMETER") order: order)
    = AssertImpl.iterable.contains.searchBehaviours.inOrder(this)

/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains in order` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderSearchBehaviour>.and(@Suppress("UNUSED_PARAMETER") only: only)
    = AssertImpl.iterable.contains.searchBehaviours.inOrderOnly(this)

/**
 * Defines that the [Iterable] contains groups of entries `in order only` where the entries within the group can be in
 * any order.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.grouped(@Suppress("UNUSED_PARAMETER") entries: entries)
    = AssertImpl.iterable.contains.searchBehaviours.inOrderOnlyGrouped(this)

/**
 * Defines that the [Iterable] contains groups of entries `in order only` where the entries within the group can be in
 * any order.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour>.within(@Suppress("UNUSED_PARAMETER") group: group)
    = AssertImpl.iterable.contains.searchBehaviours.inOrderOnlyGroupedWithin(this)
