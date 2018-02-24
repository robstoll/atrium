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

@Deprecated("use `inAnyOrder` instead, it is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("inAnyOrder"))
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>.getInAnyOrder(): IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>
    = IterableContainsBuilder(this.plant, inAnyOrder.searchBehaviour)


/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>.only
    @JvmName("inAnyOrderOnly")
    get() = AssertImpl.iterable.contains.searchBehaviours.inAnyOrderOnly(this)

@Deprecated("use `only` instead, it is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("only"))
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>.inAnyOrderOnly(): IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>
    = IterableContainsBuilder(this.plant, only.searchBehaviour)


/**
 * Defines that the search behaviour "find entries `in order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>.inOrder
    get() = AssertImpl.iterable.contains.searchBehaviours.inOrder(this)

@Deprecated("use `inOrder` instead, it is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("inOrder"))
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>.getInOrder(): IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>
    = IterableContainsBuilder(this.plant, inOrder.searchBehaviour)


/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsInOrderSearchBehaviour>.only
    @JvmName("inOrderOnly")
    get() = AssertImpl.iterable.contains.searchBehaviours.inOrderOnly(this)

@Deprecated("use `only` instead, it is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("only"))
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>.inOrderOnly(): IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>
    = IterableContainsBuilder(this.plant, only.searchBehaviour)
