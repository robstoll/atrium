package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.AssertImpl
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.*

/**
 * Defines that the search behaviour "find entries `in any order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>.inAnyOrder
    get() = AssertImpl.iterable.contains.searchBehaviours.inAnyOrder(this)

@Deprecated("use the extension fun `inAnyOrder` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder.inAnyOrder"))
fun <E, T : Iterable<E>> getInAnyOrder(builder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>): IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>
    = IterableContainsBuilder(builder.plant, builder.inAnyOrder.searchBehaviour)


/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>.only
    @JvmName("inAnyOrderOnly")
    get() = AssertImpl.iterable.contains.searchBehaviours.inAnyOrderOnly(this)

@Deprecated("use the extension fun `only` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder.only"))
fun <E, T : Iterable<E>> inAnyOrderOnly(builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>): IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>
    = IterableContainsBuilder(builder.plant, builder.only.searchBehaviour)


/**
 * Defines that the search behaviour "find entries `in order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>.inOrder
    get() = AssertImpl.iterable.contains.searchBehaviours.inOrder(this)

@Deprecated("use the extension fun `inOrder` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder.inOrder"))
fun <E, T : Iterable<E>> getInOrder(builder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>): IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>
    = IterableContainsBuilder(builder.plant, builder.inOrder.searchBehaviour)


/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsInOrderSearchBehaviour>.only
    @JvmName("inOrderOnly")
    get() = AssertImpl.iterable.contains.searchBehaviours.inOrderOnly(this)

@Deprecated("use the extension fun `only` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder.only"))
fun <E, T : Iterable<E>> inOrderOnly(builder: IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>): IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>
    = IterableContainsBuilder(builder.plant, builder.only.searchBehaviour)
