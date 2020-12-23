package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

/**
 * Collection of assertion functions and builders which are applicable to subjects which can be transformed to a
 * [Map] - intended for types which are Map like such as [IterableLike] with an element type [Pair].
 */
interface MapLikeExpectations {

    fun <T : MapLike, K, V> builderContainsInMapLike(
        container: AssertionContainer<T>,
        converter: (T) -> Map<out K, V>
    ): MapLikeContains.EntryPointStep<K, V, T, NoOpSearchBehaviour>

    fun <K, T: MapLike> containsKey(
        container: AssertionContainer<T>,
        converter: (T) -> Map<out K, *>,
        key: K
    ): Assertion

    fun <K, T: MapLike> containsNotKey(
        container: AssertionContainer<T>,
        converter: (T) -> Map<out K, *>,
        key: K
    ): Assertion

    fun <K, V, T: MapLike> getExisting(
        container: AssertionContainer<T>,
        converter: (T) -> Map<out K, V>,
        key: K
    ): FeatureExtractorBuilder.ExecutionStep<T, V>
}
