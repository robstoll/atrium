package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import java.util.*

/**
 * Collection of assertion functions and builders which are applicable to subjects with an [Optional] type.
 */
interface OptionalAssertions {
    fun <T : Optional<*>> isEmpty(container: AssertionContainer<T>): Assertion
    fun <E: Any, T : Optional<E>> isPresent(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, E>
}

