package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * The internal type which we work with which combines [Expect], [AssertionContainer] and [ExpectGrouping].
 *
 * In theory, [Expect] should extend [ExpectGrouping] but due to Kotlin type inference/overload resolution bugs, we have
 * to split it. One benefit of it, we can define extensions for [ExpectGrouping] which are not visible for [Expect].
 *
 * Similarly, we separate [Expect] from [AssertionContainer] so that we can provide extension functions for
 * [AssertionContainer] which are more or less identical to the ones defined for api-fluent but don't return an [Expect]
 * but [Assertion] etc.
 *
 * Also, we separate [Expect] form [AssertionContainer] since a lot of functionality defined for AssertionContainer is
 * not relevant for newcomers to Atrium (see [https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas](https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas)
 * for more information about the personas).
 */
interface ExpectInternal<T> : Expect<T>, AssertionContainer<T>

/**
 * Represents the extension point for expectation functions and sophisticated builders for subjects of type [T].
 *
 * @param T The type of the subject of `this` expectation.
 */
interface Expect<T>
/**
 * Type alias which shall help to reduce boilerplate when creating expectationCreator-lambdas.
 *
 * @since 1.1.0
 */
typealias ExpectationCreator<T> = Expect<T>.() -> Unit
