package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.MapLike

/**
 * Collection of assertion functions and builders which are applicable to subjects which can be transformed to an
 * [Iterable] - intended for types which are Iterable like such as [Array] or [Sequence].
 */
interface MapLikeAssertions {

    fun <T : MapLike, K, V> builderContainsInMapLike(
        container: AssertionContainer<T>,
        converter: (T) -> Map<out K, V>
    ): MapLikeContains.EntryPointStep<K, V, T, NoOpSearchBehaviour>
}
