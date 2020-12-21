package ch.tutteli.atrium.logic.creating.maplike.contains.steps

import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.*
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.impl.*
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.typeutils.MapLike

val <K, V, T : MapLike> MapLikeContains.EntryPointStepLogic<K, V, T, NoOpSearchBehaviour>.inAnyOrder: MapLikeContains.EntryPointStep<K, V, T, InAnyOrderSearchBehaviour>
    get() = withSearchBehaviour(InAnyOrderSearchBehaviourImpl())

val <K, V, T : MapLike> MapLikeContains.EntryPointStepLogic<K, V, T, InAnyOrderSearchBehaviour>.butOnly: MapLikeContains.EntryPointStep<K, V, T, InAnyOrderOnlySearchBehaviour>
    get() = withSearchBehaviour(InAnyOrderOnlySearchBehaviourImpl())

val <K, V, T : MapLike> MapLikeContains.EntryPointStepLogic<K, V, T, NoOpSearchBehaviour>.inOrder: MapLikeContains.EntryPointStep<K, V, T, InOrderSearchBehaviour>
    get() = withSearchBehaviour(InOrderSearchBehaviourImpl())

val <K, V, T : MapLike> MapLikeContains.EntryPointStepLogic<K, V, T, InOrderSearchBehaviour>.andOnly: MapLikeContains.EntryPointStep<K, V, T, InOrderOnlySearchBehaviour>
    get() = withSearchBehaviour(InOrderOnlySearchBehaviourImpl())
