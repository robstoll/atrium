package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

interface ThirdPartyAssertions {

    fun <T> toHoldThirdPartyExpectation(
        container: AssertionContainer<T>,
        description: String,
        representation: Any?,
        expectationExecutor: (T) -> Unit
    ): Assertion
}
