package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [Result] type.
 */
interface ResultAssertions {
    fun <E, T : Result<E>> isSuccess(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, E>

    fun <TExpected : Throwable> isFailureOfType(
        container: AssertionContainer<Result<*>>,
        expectedType: KClass<TExpected>
    ): SubjectChangerBuilder.ExecutionStep<Throwable?, TExpected>
}
