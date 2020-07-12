package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect

/**
 * Appends the [Assertion] the given [factory] creates based on this [Expect].
 *
 * Use [_logic] for more sophisticated scenarios, like feature extraction.
 */
inline fun <T> Expect<T>._logicAppend(factory: AssertionContainer<T>.() -> Assertion): Expect<T> =
    addAssertion(_logic.factory())

/**
 * Entry point to the logic of Atrium which is a level deeper than the API and does not return new [Expect] but
 * [Assertion]s and the like.
 *
 * Use [_logicAppend] in case you want to create and append an [Assertion] to this [Expect].
 */
inline val <T> Expect<T>._logic get() = this.toAssertionContainer()

