//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer

//TODO 1.3.0 deprecate
interface ThirdPartyAssertions {

    fun <T> toHoldThirdPartyExpectation(
        container: AssertionContainer<T>,
        description: String,
        representation: Any?,
        expectationExecutor: (T) -> Unit
    ): Assertion
}
