package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import kotlin.reflect.KClass

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [kotlin.Function0] type.
 */
interface Fun0Assertions {

    fun <TExpected : Throwable> toThrow(
        container: AssertionContainer<out () -> Any?>,
        expectedType: KClass<TExpected>
    ): ChangedSubjectPostStep<*, TExpected>

    fun <R, T : () -> R> notToThrow(container: AssertionContainer<T>): ChangedSubjectPostStep<*, R>
}
