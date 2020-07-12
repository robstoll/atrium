package ch.tutteli.atrium.logic.kotlin_1_3

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import kotlin.reflect.KClass

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [Result] type.
 */
interface ResultAssertions {
    fun <E, T : Result<E>> isSuccess(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, E>

    fun <TExpected : Throwable> isFailure(
        container: AssertionContainer<out Result<*>>,
        expectedType: KClass<TExpected>
    ): ChangedSubjectPostStep<Throwable?, TExpected>
}
