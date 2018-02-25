package ch.tutteli.atrium.api.cc.de_CH

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
val <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>.inBeliebigerReihenfolge
    get() = AssertImpl.iterable.contains.searchBehaviours.inAnyOrder(this)

@Deprecated("use the extension fun `inBeliebigerReihenfolge` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder.inBeliebigerReihenfolge"))
fun <E, T : Iterable<E>> getInBeliebigerReihenfolge(builder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>): IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>
    = IterableContainsBuilder(builder.plant, builder.inBeliebigerReihenfolge.searchBehaviour)


/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>.nur
    @JvmName("inAnyOrderOnly")
    get() = AssertImpl.iterable.contains.searchBehaviours.inAnyOrderOnly(this)

@Deprecated("use the extension fun `nur` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder.nur"))
fun <E, T : Iterable<E>> inAnyOrderOnly(builder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>): IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>
    = IterableContainsBuilder(builder.plant, builder.nur.searchBehaviour)


/**
 * Defines that the search behaviour "find entries `in order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>.inGegebenerReihenfolge
    get() = AssertImpl.iterable.contains.searchBehaviours.inOrder(this)

@Deprecated("use the extension fun `inGegebenerReihenfolge` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder.inGegebenerReihenfolge"))
fun <E, T : Iterable<E>> getInGegebenerReihenfolge(builder: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>): IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>
    = IterableContainsBuilder(builder.plant, builder.inGegebenerReihenfolge.searchBehaviour)


/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, IterableContainsInOrderSearchBehaviour>.nur
    @JvmName("inOrderOnly")
    get() = AssertImpl.iterable.contains.searchBehaviours.inOrderOnly(this)

@Deprecated("use the extension fun `only` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder.nur"))
fun <E, T : Iterable<E>> inOrderOnly(builder: IterableContainsBuilder<E, T, IterableContainsInOrderSearchBehaviour>): IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>
    = IterableContainsBuilder(builder.plant, builder.nur.searchBehaviour)
