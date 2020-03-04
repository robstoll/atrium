package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Represents a container for [Assertion] which is intended to serve as receiver object for lambdas which create
 * [Assertion]s, in which this [Expect] collects the assertions created this ways.
 *
 * This container does not offer reporting capabilities in contrast to [ReportingAssertionContainer].
 * It merely offers a method to [getAssertions] (the collected ones).
 *
 * @param T The type of the [subject] of this [Expect].
 */
interface CollectingAssertionContainer<T> : Expect<T> {

    override fun addAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): CollectingAssertionContainer<T>

    override fun addAssertion(assertion: Assertion): CollectingAssertionContainer<T>

    /**
     * Returns the [Assertion]s which have been [added][addAssertion] to this container.
     *
     * @return The [Assertion]s which have been [added][addAssertion] to this container.
     */
    fun getAssertions(): List<Assertion>
}
