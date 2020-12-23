package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.PairExpectations
import ch.tutteli.atrium.logic.property

class DefaultPairExpectations : PairExpectations {
    override fun <K, T : Pair<K, *>> first(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, K> =
        container.property(Pair<K, *>::first)

    override fun <V, T : Pair<*, V>> second(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, V> =
        container.property(Pair<*, V>::second)
}
