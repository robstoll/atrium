package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import kotlin.reflect.KClass

/**
 * Represents the extension point of the logic level for subjects of type [T].
 *
 * In contrast to assertion function defined for [Expect] which usually return [Expect], functions defined for
 * [AssertionContainer] return [Assertion] so that they can be appended to whatever we want.
 *
 * Note, do not use [SubjectProvider] as this interface will be removed with 0.17.0.
 *
 * @param T The type of the subject of `this` expectation.
 */
interface AssertionContainer<T> : @kotlin.Suppress("DEPRECATION") SubjectProvider<T> {
    /**
     * Either [Some] wrapping the subject of an [Assertion] or [None] in case a previous subject transformation
     * could not be carried out.
     */
    override val maybeSubject: Option<T>

    /**
     * Do not use yet, this is experimental
     */
    @ExperimentalNewExpectTypes
    fun <I : Any> getImpl(kClass: KClass<I>, defaultFactory: () -> I): I

//    /**
//     * Appends the given [assertion] to this container and returns an [Expect] which includes it.
//     *
//     * Whether the returned [Expect] is the same as the initial one is up to the implementation (i.e. if a mutable
//     * structure is used or an immutable). Atrium strives for an immutable data structure in the long run and will
//     * little by little refactor the code accordingly.
//     *
//     * @param assertion The assertion which will be appended to this container.
//     *
//     * @return an [Expect] for the subject of `this` expectation.
//     *
//     * @throws AssertionError Might throw an [AssertionError] in case [Assertion]s are immediately evaluated.
//     */
//    fun append(assertion: Assertion): Expect<T>
}
