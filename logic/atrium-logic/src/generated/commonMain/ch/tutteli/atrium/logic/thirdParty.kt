// @formatter:off
//---------------------------------------------------
//  Generated content, don't change here but in:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  Enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultThirdPartyAssertions


fun <T> AssertionContainer<T>.toHoldThirdPartyExpectation(description: String, representation: Any?, expectationExecutor: (T) -> Unit): Assertion =
    impl.toHoldThirdPartyExpectation(this, description, representation, expectationExecutor)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ThirdPartyAssertions
    get() = getImpl(ThirdPartyAssertions::class) { DefaultThirdPartyAssertions() }
