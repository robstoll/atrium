//TODO rename file to mapLikeToContain... in 0.18.0
package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.*
import ch.tutteli.atrium.logic.creating.maplike.contains.steps.*
import kotlin.jvm.JvmName

/**
 * Defines that the search behaviour "find entries `in any order` in the [MapLike]" shall be applied to this
 * sophisticated `contains` in [MapLike] assertion.
 *
 * @return The newly created builder.
 *
* @since 0.15.0
 */
infix fun <K, V, T: MapLike> EntryPointStep<K, V, T, NoOpSearchBehaviour>.inAny(
    @Suppress("UNUSED_PARAMETER") order: order
): EntryPointStep<K, V, T, InAnyOrderSearchBehaviour> = _logic.inAnyOrder

/**
 * Defines that the constraint "`only` the specified entries exist in the [MapLike]" shall be applied to this
 * sophisticated `contains` [MapLike] assertion.
 *
 * @return The newly created builder.
 *
* @since 0.15.0
 */
infix fun <K, V, T: MapLike> EntryPointStep<K, V, T, InAnyOrderSearchBehaviour>.but(
@Suppress("UNUSED_PARAMETER") only: only
): EntryPointStep<K, V, T, InAnyOrderOnlySearchBehaviour> = _logic.butOnly

/**
 * Defines that the search behaviour "find entries `in order` in the [MapLike]" shall be applied to this
 * sophisticated `contains` in [MapLike] assertion.
 *
 * @return The newly created builder.
 *
* @since 0.15.0
 */
infix fun <K, V, T: MapLike> EntryPointStep<K, V, T, NoOpSearchBehaviour>.inGiven(
@Suppress("UNUSED_PARAMETER") order: order
): EntryPointStep<K, V, T, InOrderSearchBehaviour> = _logic.inOrder

/**
 * Defines that the constraint "`only` the specified entries exist in the [MapLike]" shall be applied to this
 * sophisticated `contains in order` [MapLike] assertion.
 *
 * @return The newly created builder.
 *
* @since 0.15.0
 */
infix fun <K, V, T: MapLike> EntryPointStep<K, V, T, InOrderSearchBehaviour>.and(
@Suppress("UNUSED_PARAMETER") only: only
): EntryPointStep<K, V, T, InOrderOnlySearchBehaviour> = _logic.andOnly
