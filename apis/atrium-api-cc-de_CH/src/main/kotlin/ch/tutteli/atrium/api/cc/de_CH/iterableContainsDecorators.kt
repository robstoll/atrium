package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInOrderSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsSearchBehaviours

/**
 * Defines that the search behaviour "find entries `in any order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>.inBeliebigerReihenfolge
    get() = IterableContainsSearchBehaviours.containsInAnyOrder(this)

/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>.nur
    @JvmName("inAnyOrderOnly")
    get() = IterableContainsSearchBehaviours.containsInAnyOrderOnly(this)

/**
 * Defines that the search behaviour "find entries `in order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>.inGegebenerReihenfolge
    get() = IterableContainsSearchBehaviours.containsInOrder(this)

/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>.nur
    @JvmName("inOrderOnly")
    get() = IterableContainsSearchBehaviours.containsInOrderOnly(this)
