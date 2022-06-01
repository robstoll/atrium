package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Sole purpose of this interface is to hide [AssertionContainer] from newcomers which usually don't have to deal with
 * this.
 *
 * Moreover, we separate Expect from AssertionContainer so that we can provide extension functions for
 * AssertionContainer which are more or less identical to the ones defined for api-fluent but don't return Expect but
 * [Assertion] etc.
 *
 * See https://github.com/robstoll/atrium-roadmap/wiki/Requirements#personas for more information about the personas.
 */
interface ExpectInternal<T> : Expect<T>, AssertionContainer<T>

/**
 * Represents the extension point for [Assertion] functions and sophisticated builders for subjects of type [T].
 *
 * @param T The type of the subject of `this` expectation.
 */
interface Expect<T>
