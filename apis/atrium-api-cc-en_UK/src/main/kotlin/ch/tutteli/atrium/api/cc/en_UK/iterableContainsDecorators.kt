package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.creating.AssertImpl
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInOrderSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviour

/**
 * Defines that the search behaviour "find entries `in any order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>.inAnyOrder
    get() = AssertImpl.iterable.contains.searchBehaviours.inAnyOrder(this)

/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>.only
    @JvmName("inAnyOrderOnly")
    get() = AssertImpl.iterable.contains.searchBehaviours.inAnyOrderOnly(this)

/**
 * Defines that the search behaviour "find entries `in order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>.inOrder
    get() = AssertImpl.iterable.contains.searchBehaviours.inOrder(this)

/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>.only
    @JvmName("inOrderOnly")
    get() = AssertImpl.iterable.contains.searchBehaviours.inOrderOnly(this)
