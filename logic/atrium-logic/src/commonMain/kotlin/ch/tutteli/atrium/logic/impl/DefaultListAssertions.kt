package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.ListAssertions
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.extractFeature
import ch.tutteli.atrium.translations.DescriptionListLikeExpectation.EMPTY
import ch.tutteli.atrium.translations.DescriptionListLikeExpectation.INDEX_OUT_OF_BOUNDS

class DefaultListAssertions : ListAssertions {

    override fun <E, T : List<E>> get(container: AssertionContainer<T>, index: Int): FeatureExtractorBuilder.ExecutionStep<T, E> =
        container.extractFeature
            .methodCall("get", index)
            .withRepresentationForFailure(INDEX_OUT_OF_BOUNDS)
            .withFeatureExtraction {
                Option.someIf(index < it.size) { it[index] }
            }
            .withoutOptions()
            .build()

    override fun <E, T : List<E>> last(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, E> =
        container.extractFeature
            .methodCall("last")
            .withRepresentationForFailure(EMPTY)
            .withFeatureExtraction {
                Option.someIf(it.size > 0) { it.last() }
            }
            .withoutOptions()
            .build()
}
