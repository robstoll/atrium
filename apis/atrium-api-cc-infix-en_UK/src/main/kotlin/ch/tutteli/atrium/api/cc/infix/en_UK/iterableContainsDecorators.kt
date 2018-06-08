package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
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
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith(" ch.tutteli.atrium.api.cc.infix.en_GB.inAny(order)"))
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, NoOpSearchBehaviour>.inAny(@Suppress("UNUSED_PARAMETER") order: order)
    = AssertImpl.iterable.contains.searchBehaviours.inAnyOrder(this)

@Deprecated("Use the extension fun `inAny` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder inAny order"))
fun <E, T : Iterable<E>> inAny(builder: IterableContainsBuilder<E, T, NoOpSearchBehaviour>, @Suppress("UNUSED_PARAMETER") order: order): IterableContainsBuilder<E, T, InAnyOrderSearchBehaviour>
    = IterableContainsBuilder(builder.plant, (builder inAny order).searchBehaviour)


/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith(" ch.tutteli.atrium.api.cc.infix.en_GB.but(only)"))
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>.but(@Suppress("UNUSED_PARAMETER") only: only)
    = AssertImpl.iterable.contains.searchBehaviours.inAnyOrderOnly(this)

@Deprecated("Use the extension fun `but` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder but only"))
fun <E, T : Iterable<E>> inAnyOrderOnly(builder: IterableContainsBuilder<E, T, InAnyOrderSearchBehaviour>, @Suppress("UNUSED_PARAMETER") only: only): IterableContainsBuilder<E, T, InAnyOrderOnlySearchBehaviour>
    = IterableContainsBuilder(builder.plant, (builder but only).searchBehaviour)


/**
 * Defines that the search behaviour "find entries `in order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @param order Has to be `order`.
 *
 * @return The newly created builder.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith(" ch.tutteli.atrium.api.cc.infix.en_GB.inGiven(order)"))
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, NoOpSearchBehaviour>.inGiven(@Suppress("UNUSED_PARAMETER") order: order)
    = AssertImpl.iterable.contains.searchBehaviours.inOrder(this)

@Deprecated("Use the extension fun `inGiven` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder inGiven order"))
fun <E, T : Iterable<E>> inGiven(builder: IterableContainsBuilder<E, T, NoOpSearchBehaviour>, @Suppress("UNUSED_PARAMETER") order: order): IterableContainsBuilder<E, T, InOrderSearchBehaviour>
    = IterableContainsBuilder(builder.plant, (builder inGiven order).searchBehaviour)


/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains in order` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith(" ch.tutteli.atrium.api.cc.infix.en_GB.and(only)"))
@JvmName("yet")
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderSearchBehaviour>.but(@Suppress("UNUSED_PARAMETER") only: only)
    = AssertImpl.iterable.contains.searchBehaviours.inOrderOnly(this)

@Deprecated("Use the extension fun `but` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder but only"))
fun <E, T : Iterable<E>> inOrderOnly(builder: IterableContainsBuilder<E, T, InOrderSearchBehaviour>, @Suppress("UNUSED_PARAMETER") only: only): IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>
    = IterableContainsBuilder(builder.plant, (builder but only).searchBehaviour)
