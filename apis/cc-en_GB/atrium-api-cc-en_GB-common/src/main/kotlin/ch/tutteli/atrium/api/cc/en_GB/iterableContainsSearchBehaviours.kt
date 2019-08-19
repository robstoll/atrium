@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*
import kotlin.jvm.JvmName

/**
 * Defines that the search behaviour "find entries `in any order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, NoOpSearchBehaviour>.inAnyOrder
    get() = AssertImpl.iterable.contains.searchBehaviours.inAnyOrder(this)

/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>.only
    @JvmName("butOnly")
    get() = AssertImpl.iterable.contains.searchBehaviours.inAnyOrderOnly(this)


/**
 * Defines that the search behaviour "find entries `in order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, NoOpSearchBehaviour>.inOrder
    get() = AssertImpl.iterable.contains.searchBehaviours.inOrder(this)

/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains in order` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderSearchBehaviour>.only
    @JvmName("andOnly")
    get() = AssertImpl.iterable.contains.searchBehaviours.inOrderOnly(this)

/**
 * Defines that the [Iterable] contains `in order only` groups of entries
 * whereas the order within the group is specified as next step.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.grouped
    get() = AssertImpl.iterable.contains.searchBehaviours.inOrderOnlyGrouped(this)

/**
 * A filler word to emphasis that the next step defines the order within expected groups of values.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour>.within
    get() = AssertImpl.iterable.contains.searchBehaviours.inOrderOnlyGroupedWithin(this)
