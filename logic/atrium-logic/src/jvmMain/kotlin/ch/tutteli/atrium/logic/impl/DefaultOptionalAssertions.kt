package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.OptionalAssertions
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.extractFeature
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import ch.tutteli.atrium.translations.DescriptionOptionalAssertion.*
import java.util.*

class DefaultOptionalAssertions : OptionalAssertions {

    override fun <T : Optional<*>> isEmpty(container: AssertionContainer<T>): Assertion =
        container.createDescriptiveAssertion(TO_BE, EMPTY) { !it.isPresent }

    override fun <E, T : Optional<E>> isPresent(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, E> =
        container.extractFeature
            .withDescription(GET)
            .withRepresentationForFailure(IS_NOT_PRESENT)
            .withFeatureExtraction {
                Option.someIf(it.isPresent) { it.get() }
            }
            .withoutOptions()
            .build()
}

