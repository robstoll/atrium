//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.Fun0Assertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isThrowing
import ch.tutteli.atrium.domain.robstoll.lib.creating._isNotThrowing
import kotlin.reflect.KClass


class Fun0AssertionsImpl : Fun0Assertions {

    override fun <TExpected : Throwable> isThrowing(
        expect: Expect<out () -> Any?>,
        expectedType: KClass<TExpected>
    ) = _isThrowing(expect, expectedType)

    override fun <R, T : () -> R> isNotThrowing(expect: Expect<T>) = _isNotThrowing(expect)
}
