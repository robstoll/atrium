package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import kotlin.reflect.KClass

//TODO 0.14.0 remove SubjectProvider
interface AssertionContainer<T> : SubjectProvider<T> {
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
//     * @return An [Expect] for the current subject of the assertion.
//     *
//     * @throws AssertionError Might throw an [AssertionError] in case [Assertion]s are immediately evaluated.
//     */
//    fun append(assertion: Assertion): Expect<T>
}
