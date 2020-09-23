package ch.tutteli.atrium.logic.kotlin_1_3

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [Result] type.
 */
interface ResultAssertions {
    fun <E, T : Result<E>> isSuccess(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, E>

    fun <TExpected : Throwable> isFailureOfType(
        container: AssertionContainer<out Result<*>>,
        expectedType: KClass<TExpected>
    ): SubjectChangerBuilder.ExecutionStep<Throwable?, TExpected>
}
