package ch.tutteli.atrium.domain.robstoll.kotlin_1_3.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.kotlin_1_3.creating.ResultAssertions
import ch.tutteli.atrium.domain.robstoll.lib.kotlin_1_3.creating._isSuccess

class ResultAssertionsImpl : ResultAssertions {
    override fun <E, T : Result<E>> isSuccess(assertionContainer: Expect<T>) = _isSuccess(assertionContainer)
}
