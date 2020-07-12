package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import kotlin.reflect.KClass

interface Fun0Assertions {

    fun <TExpected : Throwable> toThrow(
        container: AssertionContainer<out () -> Any?>,
        expectedType: KClass<TExpected>
    ): ChangedSubjectPostStep<*, TExpected>

    fun <R, T : () -> R> notToThrow(container: AssertionContainer<T>): ChangedSubjectPostStep<*, R>
}
