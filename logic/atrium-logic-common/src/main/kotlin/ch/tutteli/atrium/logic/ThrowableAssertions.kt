package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [Throwable] type.
 */
interface ThrowableAssertions {

    fun <TExpected : Throwable> causeIsA(
        container: AssertionContainer<out Throwable>,
        expectedType: KClass<TExpected>
    ): SubjectChangerBuilder.ExecutionStep<Throwable?, TExpected>
}
