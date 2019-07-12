package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Represents a container for [Assertion]s and offers the possibility to check whether [allAssertionsHold]
 * which have been [added][addAssertion] to this container (since the last check).
 *
 * In contrast to [ReportingAssertionContainer], this container does not offer reporting capabilities
 * but merely indicates whether the added [Assertion]s hold.
 *
 * @param T The type of the [subject] of this [Assert].
 */
interface CheckingAssertionContainer<T> : Expect<T> {

    /**
     * Checks whether the newly [added][addAssertion] [Assertion]s hold.
     *
     * Calling this method more than once should not re-check previously [added][addAssertion] [Assertion]s.
     * In contrast to [ReportingAssertionContainer], this method should not report or throw an exception
     * if an assertion does not hold. It merely states whether the newly [added][addAssertion] [Assertion]s hold.
     *
     * However, it should throw an [IllegalStateException] in case no new assertions have been added to this container.
     * Particularly after one called [allAssertionsHold] and a second call happens without adding an additional
     * assertion in between.
     *
     * @return `true` if the [added][addAssertion] [Assertion]s hold; `false` otherwise.
     *
     * @throws IllegalStateException in case no new assertions have been added to this container.
     */
    fun allAssertionsHold(): Boolean
}
