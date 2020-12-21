package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.MapLikeAssertions
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.impl.NoOpSearchBehaviourImpl
import ch.tutteli.atrium.logic.creating.maplike.contains.steps.impl.EntryPointStepImpl
import ch.tutteli.atrium.logic.creating.typeutils.MapLike

class DefaultMapLikeAssertions : MapLikeAssertions {
    override fun <T : MapLike, K, V> builderContainsInMapLike(
        container: AssertionContainer<T>,
        converter: (T) -> Map<out K, V>
    ): MapLikeContains.EntryPointStep<K, V, T, NoOpSearchBehaviour> =
        EntryPointStepImpl(container, converter, NoOpSearchBehaviourImpl())
}
