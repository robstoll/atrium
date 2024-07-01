//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [kotlin.Function0] type.
 */
//TODO 1.3.0 deprecate
interface Fun0Assertions {

    fun <ExpectedThrowableT : Throwable> toThrow(
        container: AssertionContainer<out () -> Any?>,
        expectedType: KClass<ExpectedThrowableT>
    ): SubjectChangerBuilder.ExecutionStep<*, ExpectedThrowableT>

    fun <R, T : () -> R> notToThrow(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<*, R>
}
