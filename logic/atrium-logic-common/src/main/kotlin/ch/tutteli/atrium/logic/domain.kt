package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect

@Suppress("DEPRECATION" /* RequiresOptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@Experimental
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
annotation class ExperimentalLogic

@ExperimentalLogic
inline fun <T, R> Expect<T>._domain(provider: AssertionContainer<T>.() -> R): R  =
    this.toAssertionContainer().provider()