package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.PairAssertions
import ch.tutteli.atrium.logic.property

class DefaultPairAssertions : PairAssertions {
    override fun <K, T : Pair<K, *>> first(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, K> =
        container.property(Pair<K, *>::first)

    override fun <V, T : Pair<*, V>> second(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, V> =
        container.property(Pair<*, V>::second)
}
