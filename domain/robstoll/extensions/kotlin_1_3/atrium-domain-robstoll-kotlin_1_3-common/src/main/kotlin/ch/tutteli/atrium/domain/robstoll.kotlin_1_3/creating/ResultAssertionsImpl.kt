package ch.tutteli.atrium.domain.robstoll.kotlin_1_3.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.kotlin_1_3.creating.ResultAssertions
import ch.tutteli.atrium.domain.robstoll.lib.kotlin_1_3.creating._isFailure
import ch.tutteli.atrium.domain.robstoll.lib.kotlin_1_3.creating._isSuccess
import kotlin.reflect.KClass

class ResultAssertionsImpl : ResultAssertions {
    override fun <E, T : Result<E>> isSuccess(assertionContainer: Expect<T>) = _isSuccess(assertionContainer)

    override fun <TExpected : Throwable> isFailure(
        assertionContainer: Expect<out Result<Any?>>,
        expectedType: KClass<TExpected>
    ) = _isFailure(assertionContainer, expectedType)
}
