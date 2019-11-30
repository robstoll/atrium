package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.Fun0Assertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isThrowing
import ch.tutteli.atrium.domain.robstoll.lib.creating._isNotThrowing
import kotlin.reflect.KClass


class Fun0AssertionsImpl : Fun0Assertions {

    override fun <TExpected : Throwable> isThrowing(
        assertionContainer: Expect<out () -> Any?>,
        expectedType: KClass<TExpected>
    ) = _isThrowing(assertionContainer, expectedType)

    override fun <R, T : () -> R> isNotThrowing(assertionContainer: Expect<T>) = _isNotThrowing(assertionContainer)
}
