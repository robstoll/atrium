package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect

@Suppress("DEPRECATION" /* RequiresOptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@Experimental
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class ExperimentalLogic

@ExperimentalLogic
/**
 * Appends the [Assertion] the given [factory] creates based on this [Expect].
 *
 * Use [_logic] for more sophisticated scenarios, like feature extraction.
 */
inline fun <T> Expect<T>._logicAppend(factory: AssertionContainer<T>.() -> Assertion): Expect<T> =
    _logic { addAssertion(factory()) }

@ExperimentalLogic
/**
 * Entry point to the logic of Atrium which is a level deeper than the API and does not return new [Expect] but
 * [Assertion]s and the like.
 *
 * Use [_logicAppend] in case you want to create and append an [Assertion] to this [Expect].
 */
inline fun <T, R> Expect<T>._logic(factory: AssertionContainer<T>.() -> R): R =
    this.toAssertionContainer().factory()

