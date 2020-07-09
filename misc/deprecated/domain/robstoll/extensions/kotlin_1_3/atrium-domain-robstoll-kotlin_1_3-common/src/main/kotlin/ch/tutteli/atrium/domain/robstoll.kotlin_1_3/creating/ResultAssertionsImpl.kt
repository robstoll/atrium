//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.robstoll.kotlin_1_3.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.kotlin_1_3.creating.ResultAssertions
import ch.tutteli.atrium.domain.robstoll.lib.kotlin_1_3.creating._isFailure
import ch.tutteli.atrium.domain.robstoll.lib.kotlin_1_3.creating._isSuccess
import kotlin.reflect.KClass

@Deprecated("Will be removed with 1.0.0")
class ResultAssertionsImpl : ResultAssertions {
    override fun <E, T : Result<E>> isSuccess(expect: Expect<T>) =
        _isSuccess(expect)

    override fun <TExpected : Throwable> isFailure(expect: Expect<out Result<*>>, expectedType: KClass<TExpected>) =
        _isFailure(expect, expectedType)
}
