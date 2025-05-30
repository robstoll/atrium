//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [Result] type.
 */
//TODO 1.3.0 deprecate
interface ResultAssertions {
    fun <E, T : Result<E>> isSuccess(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, E>

    fun <ExpectedThrowableT : Throwable> isFailureOfType(
        container: AssertionContainer<out Result<*>>,
        expectedType: KClass<ExpectedThrowableT>
    ): SubjectChangerBuilder.ExecutionStep<Throwable?, ExpectedThrowableT>
}
