package ch.tutteli.atrium.api.fluent.en_GB

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
val <K, V, T: MapLike> EntryPointStep<K, V, T, NoOpSearchBehaviour>.inAnyOrder: EntryPointStep<K, V, T, InAnyOrderSearchBehaviour>
    get() = _logic.inAnyOrder

/**
 * Defines that the constraint "`only` the specified entries exist in the [MapLike]" shall be applied to this
 * sophisticated `contains` [MapLike] assertion.
 *
 * @return The newly created builder.
 *
* @since 0.15.0
 */
val <K, V, T: MapLike> EntryPointStep<K, V, T, InAnyOrderSearchBehaviour>.only: EntryPointStep<K, V, T, InAnyOrderOnlySearchBehaviour>
    @JvmName("butOnly")
    get() = _logic.butOnly

/**
 * Defines that the search behaviour "find entries `in order` in the [MapLike]" shall be applied to this
 * sophisticated `contains` in [MapLike] assertion.
 *
 * @return The newly created builder.
 *
* @since 0.15.0
 */
val <K, V, T: MapLike> EntryPointStep<K, V, T, NoOpSearchBehaviour>.inOrder: EntryPointStep<K, V, T, InOrderSearchBehaviour>
    get() = _logic.inOrder

/**
 * Defines that the constraint "`only` the specified entries exist in the [MapLike]" shall be applied to this
 * sophisticated `contains in order` [MapLike] assertion.
 *
 * @return The newly created builder.
 *
* @since 0.15.0
 */
val <K, V, T: MapLike> EntryPointStep<K, V, T, InOrderSearchBehaviour>.only: EntryPointStep<K, V, T, InOrderOnlySearchBehaviour>
    @JvmName("andOnly")
    get() = _logic.andOnly
