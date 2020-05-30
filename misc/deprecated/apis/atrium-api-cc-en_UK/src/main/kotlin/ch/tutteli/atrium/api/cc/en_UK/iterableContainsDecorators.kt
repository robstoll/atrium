// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*

/**
 * Defines that the search behaviour "find entries `in any order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith(" ch.tutteli.atrium.api.cc.en_GB.inAnyOrder"))
val <E, T : Iterable<E>> IterableContains.Builder<E, T, NoOpSearchBehaviour>.inAnyOrder
    get() = AssertImpl.iterable.contains.searchBehaviours.inAnyOrder(this)

@Deprecated("Use the extension fun `inAnyOrder` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.inAnyOrder"))
fun <E, T : Iterable<E>> getInAnyOrder(builder: IterableContainsBuilder<E, T, NoOpSearchBehaviour>): IterableContainsBuilder<E, T, InAnyOrderSearchBehaviour>
    = IterableContainsBuilder(builder.subjectProvider, builder.inAnyOrder.searchBehaviour)


/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>.only
    @Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith(" ch.tutteli.atrium.api.cc.en_GB.only"))
    @JvmName("butOnly")
    get() = AssertImpl.iterable.contains.searchBehaviours.inAnyOrderOnly(this)

@Deprecated("Use the extension fun `only` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.only"))
fun <E, T : Iterable<E>> inAnyOrderOnly(builder: IterableContainsBuilder<E, T, InAnyOrderSearchBehaviour>): IterableContainsBuilder<E, T, InAnyOrderOnlySearchBehaviour>
    = IterableContainsBuilder(builder.subjectProvider, builder.only.searchBehaviour)


/**
 * Defines that the search behaviour "find entries `in order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith(" ch.tutteli.atrium.api.cc.en_GB.inOrder"))
val <E, T : Iterable<E>> IterableContains.Builder<E, T, NoOpSearchBehaviour>.inOrder
    get() = AssertImpl.iterable.contains.searchBehaviours.inOrder(this)

@Deprecated("Use the extension fun `inOrder` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.inOrder"))
fun <E, T : Iterable<E>> getInOrder(builder: IterableContainsBuilder<E, T, NoOpSearchBehaviour>): IterableContainsBuilder<E, T, InOrderSearchBehaviour>
    = IterableContainsBuilder(builder.subjectProvider, builder.inOrder.searchBehaviour)


/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains in order` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderSearchBehaviour>.only
    @Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith(" ch.tutteli.atrium.api.cc.en_GB.only"))
    @JvmName("andOnly")
    get() = AssertImpl.iterable.contains.searchBehaviours.inOrderOnly(this)

@Deprecated("Use the extension fun `only` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("builder.only"))
fun <E, T : Iterable<E>> inOrderOnly(builder: IterableContainsBuilder<E, T, InOrderSearchBehaviour>): IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>
    = IterableContainsBuilder(builder.subjectProvider, builder.only.searchBehaviour)
