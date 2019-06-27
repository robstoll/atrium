package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * DSL Marker for [Expect].
 */
@DslMarker
annotation class ExpectMarker


/**
 * Represents the extension point of [Assertion] functions and sophisticated builders for subjects of type [T].
 *
 * It is also the base type of all assertion container types (like [ReportingAssertionContainer],
 * [CollectingAssertionContainer] etc.)
 *
 * @param T The type of the [subject] of the assertion.
 */
@ExpectMarker
interface Expect<T> : SubjectProvider<T>, AssertionHolder {

    /**
     * The provider which provides [subject].
     */
    val subjectProvider: () -> T

    /**
     * Adds the assertions created by the [assertionCreator] lambda to this container.
     *
     * @param assertionCreator The receiver function which might create and add assertions to this container.
     *
     * @return This container to support a fluent API.
     *
     * @throws AssertionError Might throw an [AssertionError] depending on the concrete implementation.
     */
    fun addAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): Expect<T>

    /**
     * Adds the given [assertion] to this container.
     *
     * @param assertion The assertion which will be added to this container.
     *
     * @return This container to support a fluent API.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [Assertion]s are immediately
     *   evaluated (see [ReportingAssertionContainer]).
     */
    override fun addAssertion(assertion: Assertion): Expect<T>

    /**
     * Creates a [DescriptiveAssertion] based on the given [description], [expected] and [test]
     * and [adds][addAssertion] it to the container.
     *
     * @param description The description of the assertion, e.g., `is less than`.
     * @param expected The expected value, e.g., `5`
     * @param test Indicates whether the assertion holds or fails.
     *
     * @return This container to support a fluent API.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [Assertion]s are immediately
     *   evaluated (see [ReportingAssertionContainer]).
     */
    fun createAndAddAssertion(description: Translatable, expected: Any, test: () -> Boolean): Expect<T> =
        addAssertion(assertionBuilder.createDescriptive(description, expected, test))
}
