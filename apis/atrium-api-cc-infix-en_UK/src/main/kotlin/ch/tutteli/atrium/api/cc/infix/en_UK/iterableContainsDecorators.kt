package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.AssertImpl
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.*

/**
 * Defines that the search behaviour "find entries `in any order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @param order Has to be `order`.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>.inAny(@Suppress("UNUSED_PARAMETER") order: order)
    = AssertImpl.iterable.contains.searchBehaviours.inAnyOrder(this)

@Deprecated("use the extension fun `inAny` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder inAny order"))
fun <E, T : Iterable<E>> inAny(builder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>, @Suppress("UNUSED_PARAMETER") order: order): IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>
    = IterableContainsBuilder(builder.plant, (builder inAny order).searchBehaviour)


/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>.but(@Suppress("UNUSED_PARAMETER") only: only)
    = AssertImpl.iterable.contains.searchBehaviours.inAnyOrderOnly(this)

@Deprecated("use the extension fun `but` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder but only"))
fun <E, T : Iterable<E>> inAnyOrderOnly(builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>, @Suppress("UNUSED_PARAMETER") only: only): IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>
    = IterableContainsBuilder(builder.plant, (builder but only).searchBehaviour)


/**
 * Defines that the search behaviour "find entries `in order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @param order Has to be `order`.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>.inGiven(@Suppress("UNUSED_PARAMETER") order: order)
    = AssertImpl.iterable.contains.searchBehaviours.inOrder(this)

@Deprecated("use the extension fun `inGiven` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder inGiven order"))
fun <E, T : Iterable<E>> inGiven(builder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>, @Suppress("UNUSED_PARAMETER") order: order): IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>
    = IterableContainsBuilder(builder.plant, (builder inGiven order).searchBehaviour)


/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
@JvmName("yet")
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsInOrderSearchBehaviour>.but(@Suppress("UNUSED_PARAMETER") only: only)
    = AssertImpl.iterable.contains.searchBehaviours.inOrderOnly(this)

@Deprecated("use the extension fun `but` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder but only"))
fun <E, T : Iterable<E>> inOrderOnly(builder: IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>, @Suppress("UNUSED_PARAMETER") only: only): IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>
    = IterableContainsBuilder(builder.plant, (builder but only).searchBehaviour)
