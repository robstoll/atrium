package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep

interface PairAssertions {
    fun <K, T : Pair<K, *>> first(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, K>
    fun <V, T : Pair<*, V>> second(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, V>
}
