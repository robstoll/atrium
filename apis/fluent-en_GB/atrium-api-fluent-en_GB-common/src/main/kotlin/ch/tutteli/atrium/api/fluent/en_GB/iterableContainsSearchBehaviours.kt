package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains.Builder
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*
import kotlin.jvm.JvmName

/**
 * Defines that the search behaviour "find entries `in any order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> Builder<E, T, NoOpSearchBehaviour>.inAnyOrder: Builder<E, T, InAnyOrderSearchBehaviour>
    get() = ExpectImpl.iterable.contains.searchBehaviours.inAnyOrder(this)

/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> Builder<E, T, InAnyOrderSearchBehaviour>.only: Builder<E, T, InAnyOrderOnlySearchBehaviour>
    @JvmName("butOnly")
    get() = ExpectImpl.iterable.contains.searchBehaviours.inAnyOrderOnly(this)


/**
 * Defines that the search behaviour "find entries `in order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> Builder<E, T, NoOpSearchBehaviour>.inOrder: Builder<E, T, InOrderSearchBehaviour>
    get() = ExpectImpl.iterable.contains.searchBehaviours.inOrder(this)

/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains in order` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> Builder<E, T, InOrderSearchBehaviour>.only: Builder<E, T, InOrderOnlySearchBehaviour>
    @JvmName("andOnly")
    get() = ExpectImpl.iterable.contains.searchBehaviours.inOrderOnly(this)

/**
 * Defines that the [Iterable] contains `in order only` groups of entries
 * whereas the order within the group is specified as next step.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> Builder<E, T, InOrderOnlySearchBehaviour>.grouped: Builder<E, T, InOrderOnlyGroupedSearchBehaviour>
    get() = ExpectImpl.iterable.contains.searchBehaviours.inOrderOnlyGrouped(this)

/**
 * A filler word to emphasis that the next step defines the order within expected groups of values.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> Builder<E, T, InOrderOnlyGroupedSearchBehaviour>.within: Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>
    get() = ExpectImpl.iterable.contains.searchBehaviours.inOrderOnlyGroupedWithin(this)
