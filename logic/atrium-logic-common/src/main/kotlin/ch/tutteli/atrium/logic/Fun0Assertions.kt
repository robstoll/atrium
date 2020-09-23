package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [kotlin.Function0] type.
 */
interface Fun0Assertions {

    fun <TExpected : Throwable> toThrow(
        container: AssertionContainer<out () -> Any?>,
        expectedType: KClass<TExpected>
    ): SubjectChangerBuilder.ExecutionStep<*, TExpected>

    fun <R, T : () -> R> notToThrow(container: AssertionContainer<T>): SubjectChangerBuilder.ExecutionStep<*, R>
}
