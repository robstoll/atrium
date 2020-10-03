package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.ListAssertions
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.extractFeature
import ch.tutteli.atrium.translations.DescriptionListAssertion.INDEX_OUT_OF_BOUNDS

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
}
